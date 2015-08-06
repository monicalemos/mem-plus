var game = null;
var imageID = "img1";

function InitGame() {
	LoadNewImage(imageID);
}

function LoadNewImage(imgID) {
	imageID = imgID;

	SetRowsColumn();

	LoadGame();
}

function LoadGame() {
	var canvasID = "canJigsaw";

	game = new jigsaw(canvasID, imageID, totalRows, totalColumns);

	game.initDrawing();

}

function ShowPreview() {
	if (game) {
		game.showPreview();
	}
}

function SetRowsColumn() {
	totalRows = 3;
	totalColumns = 3;
}

function imageBlock(no, x, y) {

	this.no = no;
	this.x = x;
	this.y = y;
	this.isSelected = false;
}

function jigsaw(canvasID, imageID, rows, columns) {
	// Variáveis
	var canvas = document.getElementById(canvasID);
	var image = document.getElementById(imageID);
	var context = canvas.getContext('2d');

	// Numero de colunas, Linhas
	var TOTAL_ROWS = rows;// 4;
	var TOTAL_COLUMNS = columns; // 4;
	var TOTAL_PIECES = TOTAL_ROWS * TOTAL_COLUMNS;
	var movimentos = 0;

	// Tamanho da Imagem
	var MAIN_IMG_WIDTH = image.naturalWidth;
	var MAIN_IMG_HEIGHT = image.naturalHeight;
	var IMG_WIDTH = Math.round(MAIN_IMG_WIDTH / TOTAL_COLUMNS);
	var IMG_HEIGHT = Math.round(MAIN_IMG_HEIGHT / TOTAL_ROWS);

	// Tamanho do tabuleiro
	var BLOCK_IMG_WIDTH = 600;
	var BLOCK_IMG_HEIGHT = 450;
	var BLOCK_WIDTH = Math.round(BLOCK_IMG_WIDTH / TOTAL_COLUMNS);
	var BLOCK_HEIGHT = Math.round(BLOCK_IMG_HEIGHT / TOTAL_ROWS);

	imageBlockList = new Array(); // Collection of Image pieces
	blockList = new Array(); // Collection of Board pieces
	selectedBlock = null;
	mySelf = this;

	this.initDrawing = function() {
		// Adicionar eventos
		canvas.onmousedown = handleOnMouseDown;
		canvas.onmouseup = handleOnMouseUp;
		canvas.onmouseout = handleOnMouseOut;
		canvas.onmousemove = handleOnMouseMove;
		// Criar novo jogo
		initializeNewGame();
	};

	function initializeNewGame() {
		SetImageBlock();
		DrawGame();
	}

	function SetImageBlock() {
		// Limites onde poderão ser colocadas as peças
		var x1 = BLOCK_IMG_WIDTH + 50;
		var x2 = BLOCK_IMG_WIDTH + 50;
		var y2 = BLOCK_IMG_HEIGHT - 50;

		for (var i = 0; i < TOTAL_PIECES; i++) {

			// Posição aleatória onde serão colocada as peças
			var randomX = randomXtoY(x1, x2);
			var randomY = randomXtoY(0, y2);
			var imgBlock = new imageBlock(i, randomX, randomY);
			imageBlockList.push(imgBlock);

			// Posição relativa do tabuleiro
			var x = (i % TOTAL_COLUMNS) * BLOCK_WIDTH;
			var y = Math.floor(i / TOTAL_COLUMNS) * BLOCK_HEIGHT;
			var block = new imageBlock(i, x, y);
			blockList.push(block);
		}

	}
	function DrawGame() {
		clear(context);
		drawLines();
		drawAllImages();
		if (selectedBlock) {
			drawImageBlock(selectedBlock);
		}
	}

	function drawLines() {

		context.strokeStyle = "#e9e9e9";

		context.lineWidth = 1;
		context.beginPath();

		// draw verticle lines
		for (var i = 0; i <= TOTAL_COLUMNS; i++) {
			var x = BLOCK_WIDTH * i;
			context.moveTo(x, 0);
			context.lineTo(x, BLOCK_IMG_HEIGHT);
		}

		// draw horizontal lines
		for (var i = 0; i <= TOTAL_ROWS; i++) {
			var y = BLOCK_HEIGHT * i;
			context.moveTo(0, y);
			context.lineTo(BLOCK_IMG_WIDTH, y);
		}
		context.closePath();
		context.stroke();
	}

	function drawAllImages() {
		for (var i = 0; i < imageBlockList.length; i++) {
			if (imageBlockList[i].isSelected == false) {
				drawImageBlock(imageBlockList[i]);
			}
		}
	}

	function drawImageBlock(imgBlock) {
		context.save();
		var srcX = (imgBlock.no % TOTAL_COLUMNS) * IMG_WIDTH;
		var srcY = Math.floor(imgBlock.no / TOTAL_COLUMNS) * IMG_HEIGHT;
		context.drawImage(image, srcX, srcY, IMG_WIDTH, IMG_HEIGHT, imgBlock.x,
				imgBlock.y, BLOCK_WIDTH, BLOCK_HEIGHT);

		context.restore();
	}

	function drawImage(image) {
		context.save();
		context.drawImage(image, 0, 0, BLOCK_WIDTH, BLOCK_WIDTH, 10, 10,
				BLOCK_WIDTH, BLOCK_WIDTH);
		context.restore();
	}

	// FIM DO JOGO
	var interval = 500;
	var remove_width;
	var remove_height;

	function OnFinished() {

		var audioElement = document.createElement('audio');
		audioElement.setAttribute('src', 'resources/static/music/games/jigsaw/finish.mp3');
		audioElement.play();

		remove_width = BLOCK_WIDTH;
		remove_height = BLOCK_HEIGHT;
		// Clear Board
		interval = setInterval(function() {
			mySelf.ClearGame();
		}, 1000);
	}

	this.ClearGame = function() {

		remove_width -= 30;
		remove_height -= 20;

		if (remove_width > 0 && remove_height > 0) {

			clear(context);

			for (var i = 0; i < imageBlockList.length; i++) {
				var imgBlock = imageBlockList[i];

				imgBlock.x += 10;
				imgBlock.y += 10;

				drawFinalImage(imgBlock.no, imgBlock.x, imgBlock.y,
						remove_width, remove_height);
			}

			//DrawGame();
		} else {

			clearInterval(interval);

			// Restart game
			initializeNewGame();

		}
	};

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////// EVENTS
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////

	function handleOnMouseDbClick(e) {
	}

	function handleOnMouseOut(e) {
		if (selectedBlock != null) {
			imageBlockList[selectedBlock.no].isSelected = false;
			selectedBlock = null;
			DrawGame();

		}
	}

	function handleOnMouseDown(e) {
		if (selectedBlock != null) {
			imageBlockList[selectedBlock.no].isSelected = false;
		}
		var rect = canvas.getBoundingClientRect();
		
		selectedBlock = GetImageBlock(imageBlockList, e.clientX - rect.left, e.clientY - rect.top);
		if (selectedBlock) {
			imageBlockList[selectedBlock.no].isSelected = true;
		}

	}

	function handleOnMouseUp(e) {

		if (selectedBlock) {
			movimentos++;
			var index = selectedBlock.no;
			var rect = canvas.getBoundingClientRect();
			var block = GetImageBlock(blockList, e.clientX - rect.left, e.clientY - rect.top);
			if (block) {
				var blockOldImage = GetImageBlockOnEqual(imageBlockList,
						block.x, block.y);
				if (blockOldImage == null) {
					imageBlockList[index].x = block.x;
					imageBlockList[index].y = block.y;

				}
			} else {
				imageBlockList[index].x = selectedBlock.x;
				imageBlockList[index].y = selectedBlock.y;
			}

			imageBlockList[index].isSelected = false;
			selectedBlock = null;
			DrawGame();

			if (isFinished()) {
				OnFinished();
			}

		}
	}

	function handleOnMouseMove(e) {
		if (selectedBlock) {
			var rect = canvas.getBoundingClientRect();
			selectedBlock.x = e.clientX - rect.left  - 25;
			selectedBlock.y = e.clientY - rect.top - 25;
			DrawGame();
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////// HELPER METHODS
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////

	function clear(c) {
		c.clearRect(0, 0, canvas.width, canvas.height);
	}

	function randomXtoY(minVal, maxVal, floatVal) {
		var randVal = minVal + (Math.random() * (maxVal - minVal));
		var val = typeof floatVal == 'undefined' ? Math.round(randVal)
				: randVal.toFixed(floatVal);

		return Math.round(val);
	}

	function GetImageBlock(list, x, y) {
		
		for (var i = list.length - 1; i >= 0; i--) {
			var imgBlock = list[i];

			var x1 = imgBlock.x;
			var x2 = x1 + BLOCK_WIDTH;

			var y1 = imgBlock.y;
			var y2 = y1 + BLOCK_HEIGHT;

			if ((x >= x1 && x <= x2) && (y >= y1 && y <= y2)) {
				var img = new imageBlock(imgBlock.no, imgBlock.x, imgBlock.y);
				return img;
			}
		}

		return null;
	}

	function GetImageBlockOnEqual(list, x, y) {

		for (var i = 0; i < list.length; i++) {
			var imgBlock = list[i];

			var x1 = imgBlock.x;
			var y1 = imgBlock.y;

			if ((x == x1) && (y == y1)) {

				return new imageBlock(imgBlock.no, imgBlock.x, imgBlock.y);
			}
		}

		return null;
	}

	function isFinished() {

		var total = TOTAL_PIECES;

		for (var i = 0; i < total; i++) {

			var img = imageBlockList[i];
			var block = blockList[i];

			if ((img.x != block.x) || (img.y != block.y)) {
				return false;
			}

		}
		alert(movimentos);
		return true;
	}

}
