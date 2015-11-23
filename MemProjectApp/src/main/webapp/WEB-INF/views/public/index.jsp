<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<style>

.colap{
	cursor:pointer;
	list-style-image: url("http://www.icon100.com/up/3977/16/85-Plus-symbol-in-a-circle.png");
}
.exp{
	cursor:pointer;
	list-style-image: url("http://www.doorsan.com/css/images/admin/minus-icon.png");
}

</style>
<ul class="colap">
    <li> <span class="Collapsable">item 1</span>
    <div  class="hiddenDiv">
	    <ul class="colap" id="test"> 
	        <li><span class="Collapsable">item 1</span></li>
	        <li><span class="Collapsable">item 2</span>
	        <div  class="hiddenDiv">
		        <ul class="colap">
		            <li><span class="Collapsable">item 1</span></li>
		            <li><span class="Collapsable">item 2</span></li>
		            <li><span class="Collapsable">item 3</span></li>
		            <li><span class="Collapsable">item 4</span></li>
		        </ul>
	        </div>
	        </li>
	        <li><span class="Collapsable">item 3</span></li>
	        <li><span class="Collapsable">item 4</span>
	        <ul class="colap">
	            <li><span class="Collapsable">item 1</span></li>
	            <li><span class="Collapsable">item 2</span></li>
	            <li><span class="Collapsable">item 3</span></li>
	            <li><span class="Collapsable">item 4</span></li>
	        </ul>
	        </li>
	    </ul>
	</div>
    </li>
    <li><span class="Collapsable">item 2</span>
    <div class="hiddenDiv">
    <ul>
        <li><span class="Collapsable">item 1</span></li>
        <li><span class="Collapsable">item 2</span></li>
        <li><span class="Collapsable">item 3</span></li>
        <li><span class="Collapsable">item 4</span></li>
    </ul>
    </div>
    </li>

    <li><span class="Collapsable">item 3</span>
     <div  class="hiddenDiv">
    <ul>
        <li><span class="Collapsable">item 1</span></li>
        <li><span class="Collapsable">item 2</span></li>
        <li><span class="Collapsable">item 3</span></li>
        <li><span class="Collapsable">item 4</span></li>
    </ul>
    </div>
    </li>
    <li><span class="Collapsable">item 4</span></li>
</ul>

<script type="text/javascript">
	$(".hiddenDiv").hide();
    $(".Collapsable").click(function () {
        $(this).parent().children().toggle();
        $(this).toggle();
        $(this).find("test").toggleClass("exp");
    });
</script>