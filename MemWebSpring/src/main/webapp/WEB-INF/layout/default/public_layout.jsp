<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><tiles:getAsString name="title" /></title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
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

	<div class="navbar-default sidebar" role="navigation">
		<tiles:insertAttribute name="menu" />
	</div>
	<div id="page-wrapper">
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
	
	    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>

</body>
</html>
