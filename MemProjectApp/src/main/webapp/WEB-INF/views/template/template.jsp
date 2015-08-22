<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE-edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><tiles:getAsString name="title" /></title>
<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="${pageContext.request.contextPath}/resources/static/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- Timeline CSS -->
<link href="${pageContext.request.contextPath}/resources/static/css/plugins/timeline.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/resources/static/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="${pageContext.request.contextPath}/resources/static/css/plugins/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="${pageContext.request.contextPath}/resources/static/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

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
	<div class="content">
		<!-- Header -->
		<tiles:insertAttribute name="header" />
		<!-- Menu Page -->
		<div class="navbar-default sidebar" role="navigation">
			<tiles:insertAttribute name="menu" />
		</div>
		<!-- Body Page -->
		<div class="container">
			<tiles:insertAttribute name="body" />
		</div>
		<!-- Footer Page -->
		<div class="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="${pageContext.request.contextPath}/resources/static/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/static/js/npm.js"></script>

	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
</body>
</html>