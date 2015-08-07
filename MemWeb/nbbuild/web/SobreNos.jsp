<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Styles/Estilos.css">
<title>MEM+</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<header>
		<img id="logo" src="images/mem+.png"
			onclick="window.location.href='Home.jsp'" />

		<div id=messagemEntrada>
			Bem-vindo(a)
			<%=session.getAttribute("nome_completo")%>
		</div>
	</header>

	<section id="page">
		<div id="menu">
			<div class="line"></div>
			<ul>
				<li><a href="Home.jsp">Home</a></li>
				<li><a class="selected" href="SobreNos.jsp">Sobre Nós</a></li>
				<li><a href="./ServletInicial?accao=contactos">Contactos</a></li>
				<li><a href="./ServletInicial?accao=logout">Logout</a></li>
			</ul>
		</div>
		<div id=textoInformacao>
		
		</div>
		<footer>
			<p>Copyright 2014 - MEM +</p>
		</footer>
</body>
</html>
