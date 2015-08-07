<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Styles/Registo.css">
<title>MEM+</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<header>
		<hgroup>
			<h1>
				<img id="logo" src="images/mem+.png"
					onclick="window.location.href='Home.jsp'" />
			</h1>
		</hgroup>
	</header>
	<div id="page">
		<div id="menu">
			<div class="line"></div>
			<ul>
				<li><a href="Home.jsp">Home</a></li>
				<li><a class="selected" href="./RegistrarTecnico.jsp">Novo Registo</a></li>
				<li><a href="./ServletInicial?accao=sobreNos">Sobre Nós</a></li>
				<li><a href="./ServletInicial?accao=contactos">Contactos</a></li>
			</ul>
		</div>
		<div id="container">
			<form id="RegistrarTecnico" action="RegistrarTecnico">
				<header id="header" class="info">
					<h2>Novo Registo</h2>
				</header>

				<label for="nome">Nome Completo:</label> 
				<input type="text" id="nome" name="nome"> 
				<br> 
				<label for="nome_utilizador">Nome de Utilizador:</label> 
				<input type="text" id="nome_utilizador" name="nome_utilizador"><br> 
				
				<label for="password">Password:</label> 
				<input type="password" id="password" name="password"><br> 			
				
				<label for="email">Email:</label> 
				<input type="text" id="email" name="email"><br> 
				
                                <label for="fotografia">Insira uma fotografia:</label>
                                <input type="file" name="pic" accept="image/*">
                                <br>
                                
				<div id="lower">
					<input type="submit" value="Registar">
				</div>
			</form>
			<!-- <form action="${pageContext.request.contextPath}/Home.jsp"> -->
		</div>
	</div>
	
	<footer>
		<p>Copyright 2014 - MEM +</p>
	</footer>
</body>
</html>