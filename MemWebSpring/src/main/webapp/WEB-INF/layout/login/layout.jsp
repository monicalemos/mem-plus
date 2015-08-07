<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><tiles:getAsString name="title" /></title>
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css" rel="stylesheet">
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
body {
	padding-top: 70px;
	padding-bottom: 70px;
}
</style>
</head>
<body>
	<div>
		<tiles:insertAttribute name="header" />
	</div>

	<div>
		<tiles:insertAttribute name="body" />
	</div>


	<div>
		<tiles:insertAttribute name="footer" />
	</div>



	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="${pageContext.request.contextPath}/resources/static/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/static/js/npm.js"></script>
</body>
</html>
