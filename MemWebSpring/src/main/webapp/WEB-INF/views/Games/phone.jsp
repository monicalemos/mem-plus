	<script type="text/javascript"> 
	function	NumberPressed(number) {
	var display = document.getElementById("display"); 
	var text = display.value;
	if(display.value.length>8) return;
	display.value+=number; } 
	function ClearDisplay()
	{
		var display = document.getElementById("display");
		display.value="";
	}
	function Dial()
	{
		alert("number dialed: "+document.getElementById("display").value);
	}
	</script>
<div>
	<input id="display" type="tel" disabled="disabled" maxlength="9" style="text-align:right;" />

	<div>
		<button id="1" value="1" onclick="NumberPressed(1);" class="btn btn-default btn-lg">1</button>
		<button id="2" value="2" onclick="NumberPressed(2);" class="btn btn-default btn-lg">2</button>
		<button id="3" value="3" onclick="NumberPressed(3);" class="btn btn-default btn-lg">3</button>
	</div>
	<div>
		<button id="4" value="4" onclick="NumberPressed(4);" class="btn btn-default btn-lg">4</button>
		<button id="5" value="5" onclick="NumberPressed(5);" class="btn btn-default btn-lg">5</button>
		<button id="6" value="6" onclick="NumberPressed(6);" class="btn btn-default btn-lg">6</button>
	</div>
	<div>
		<button id="7" value="7" onclick="NumberPressed(7);"  class="btn btn-default btn-lg">7</button>
		<button id="8" value="8" onclick="NumberPressed(8);"  class="btn btn-default btn-lg">8</button>
		<button id="9" value="9" onclick="NumberPressed(9);"  class="btn btn-default btn-lg">9</button>
	</div>
	<div>
		<button id="0" value="0" onclick="ClearDisplay();"  class="btn btn-default btn-lg">C</button>
		<button id="0" value="0" onclick="NumberPressed(0);"  class="btn btn-default btn-lg">0</button>
		<button id="Dial" value="Dial" onclick="Dial();"  class="btn btn-default btn-lg">D</button>

	</div>


</div>