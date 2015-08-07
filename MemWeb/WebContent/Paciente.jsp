<!DOCTYPE html>
<html>
<%@ page import="java.util.*"%>
<%@ page import="classesDados.Paciente"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<head>
<link rel="stylesheet" href="Styles/Paciente.css">
<title>MEM+</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="content">
	<header>
		<img id="logo" src="images/mem+.png"
			onclick="window.location.href='LoggedPage.jsp'" />

		<div id="containerLogout">
			<h2>
				Olá :
				<%=session.getAttribute("nome_utilizador")%></h2>
			<form action="./Inicial?accao=logout" method="post">
				<div id="lower">
					<input type="submit" name="logout" value="Logout" />
				</div>
			</form>
		</div>

	</header>
	
		<div id="menu">
			<div class="line"></div>
			<ul>
				<li><a href="./LoggedPage.jsp">Home</a></li>
				<li><a class="selected"
					href="./ServletInicial?accao=verPacientes">Ver Pacientes</a></li>
				<li><a href="./RegistrarPaciente.jsp">Registrar Novo
						Paciente</a></li>
				<li><a href="./ServletInicial?accao=evolucaoPacientes">Evolução
						dos Pacientes</a></li>
			</ul>
		</div>
		
		<div id="menu-lateral">
			<div class="line"></div>
			<ul>
				<li><a class="selected"
					href="./ServletInicial?accao=verPaciente">Dados do Paciente</a></li>
				<!--<li><a href="./ServletInicial?accao=registrarFamiliar">Inserir
						Familiar</a></li>-->
				<li><a href="./ServletInicial?accao=verFamiliares">Ver
						Familiares</a></li>
				<!--<li><a href="./ServletInicial?accao=novoEvento">Inserir
						Evento</a></li>-->
				<li><a href="./ServletInicial?accao=verEventos">Ver Eventos</a></li>
			</ul>
		</div>

		<div id="container">
			<h2>Dados do Paciente:</h2>
			<%
				Paciente p = (Paciente) session.getAttribute("paciente");
                                System.out.println(p.getNome_completo());
                                session.setAttribute("paciente", p);
			%>
			<label for="nome"> Nome: <%=p.getNome_completo()%></label><br> 
                        <label for="data_nascimento"> Data de Nascimento: <%=p.getData_de_nascimento()%></label><br>
			<label for="local_nascimento"> Local de Nascimento: <%=p.getLocal_nascimento()%></label><br>
			<label for="morada_atual"> Morada Atual: <%=p.getMorada()%></label><br>
			<label for="profissao"> Profissão: <%=p.getProfissao()%></label><br>
			<label for="escolaridade"> Escolaridade: <%=p.getEscolaridade()%></label><br>
			<label for="estado_civil"> Estado civil: <%=p.getEstadoCivil()%></label><br>
			<label for="nivel_doenca"> Nível da Doença: <%=p.getNivel_doenca()%></label><br>
			<label for="nome_medico"> Nome do Médico: <%=p.getNome_medico()%></label><br>
			<label for="especialidade_medico"> Especialidade do Médico: <%=p.getEspecialidade_medico()%></label><br>
			<label for="nivel_sessao"> Nível de Sessão: <%=p.getNivel_sessao()%></label><br>
                        
                        <form id="EditarPaciente" action="./Inicial?accao=editarPaciente" method="post">
                          <input type="submit" value="Editar Paciente">
                        </form>

		</div>
	</div>

	<footer>
		<p>Copyright 2014 - MEM +</p>
	</footer>
</body>
</html>
