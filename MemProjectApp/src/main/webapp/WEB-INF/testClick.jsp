<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
.normal {
	background-color: #ffffff;
}

.highlight {
	background-color: #ff0000;
}
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"> 
	function onRow(rowID) { 
		var row = document.getElementById(rowID);
		var curr = row.className;
		if(curr.indexOf("normal")>=0) row.className="highlight";
		else row.className="normal";
	} 
</script>

<title>Insert title here</title>
</head>
<body>
	<table>
		<tr class="normal" id="row1" onclick="onRow(this.id)">
			<td>1</td>
			<td>2</td>
			<td>3</td>
		</tr>
		<tr class="normal" id="row2" onclick="onRow(this.id)">
			<td>4</td>
			<td>5</td>
			<td>6</td>
		</tr>
	</table>
</body>
</html>