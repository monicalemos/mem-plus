<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Styles/Home.css">
<title>MEM+</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<header>
		<img id="logo" src="images/mem+.png"
			onclick="window.location.href='Home.jsp'" />

		<div id="container">
			<h2>Olá <%= session.getAttribute("nome_utilizador") %> </h2>
			 <form action="./Inicial?accao=logout" method="post">
				<div id="lower">
					 <input type="submit" name="logout" value="Logout" />
				</div>
			</form>
		</div>
		
	</header>
	<section id="page">
		<div id="menu">
			<div class="line"></div>
			<ul>
				<li><a class="selected" href="LoggedPage.jsp">Home</a></li>
				<li><a href="./ServletInicial?accao=verPacientes">Ver Pacientes</a></li>
				<li><a href="./Inicial?accao=registrarPaciente">Registrar Novo Paciente</a></li>
				<li><a href="./ServletInicial?accao=evolucaoPacientes">Evolução dos Pacientes</a></li>
			</ul>
		</div>
	</section>
<!-- <h1 >Olá : <%= session.getAttribute("tecnico") %></h1>
 <form action="Inicial">
    <input type="submit" name="RegistrarPaciente" value="Registrar Paciente" /><br>
    <input type="submit" name="RegistrarFamiliar" value="Registrar Familiar" /><br>
    <input type="submit" name="verDados" value="ver dados" />
    <input type="submit" name="logout" value="Logout" />
</form> -->

	<footer>
		<p>Copyright 2014 - MEM +</p>
	</footer>

</body>
</html>
