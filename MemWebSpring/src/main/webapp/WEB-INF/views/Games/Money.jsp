	<script type="text/javascript"> 
	function	NumberPressed(number) {
		var display = document.getElementById("display"); 
		var text = parseFloat(display.value);
		display.value= text+(number/100); 
	} 
	function ClearDisplay()
	{
		var display = document.getElementById("display");
		display.value="0.0";
	}
	function Dial(){}
	</script>


<div>

	<input id="display" type="text" disabled="disabled" maxlength="9" style="text-align:right;" value="0.0" >

	<div>
		<button id="1" value="1" onclick="NumberPressed(1);" class="btn btn-default btn-lg">1 centimo</button>
		<button id="2" value="2" onclick="NumberPressed(2);" class="btn btn-default btn-lg">2 centimos</button>
		<button id="3" value="5" onclick="NumberPressed(5);" class="btn btn-default btn-lg">5 centimos</button>
	</div>
	<div>
		<button id="4" value="10" onclick="NumberPressed(10);" class="btn btn-default btn-lg">10 centimos</button>
		<button id="5" value="20" onclick="NumberPressed(20);" class="btn btn-default btn-lg">20 centimos</button>
		<button id="6" value="50" onclick="NumberPressed(50);" class="btn btn-default btn-lg">50 centimos</button>
	</div>
	<div>
		<button id="7" value="100" onclick="NumberPressed(100);"  class="btn btn-default btn-lg">1 euro</button>
		<button id="8" value="200" onclick="NumberPressed(200);"  class="btn btn-default btn-lg">2 euros</button>
	</div>
	<div>
		<button id="0" value="0" onclick="ClearDisplay();"  class="btn btn-default btn-lg">C</button>
		<button id="0" value="0" onclick="NumberPressed(0);"  class="btn btn-default btn-lg">0</button>
		<button id="Dial" value="Dial" onclick="Dial();"  class="btn btn-default btn-lg">Confirm</button>

	</div>

</div>