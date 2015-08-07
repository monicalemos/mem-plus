<!DOCTYPE html>
<html>
<%@ page import="java.util.*" %>
<%@ page import="classesDados.Paciente" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<head>
<link rel="stylesheet" href="Styles/Tabelas.css">
<title>MEM+</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	
	<header>
		<img id="logo" src="images/mem+.png"
			onclick="window.location.href='LoggedPage.jsp'" />

		<div id="containerLogout">
			<h2>Olá : <%= session.getAttribute("nome_utilizador") %></h2>
			 <form action="./Inicial?accao=logout" method="post">
				<div id="lower">
					 <input type="submit" name="logout" value="Logout" />
				</div>
			</form>
		</div>
		
	</header>
	<div id="page">
		<div id="menu">
			<div class="line"></div>
			<ul>
				<li><a href="./LoggedPage.jsp">Home</a></li>
				<li><a class="selected" href="./ServletInicial?accao=verPacientes">Ver Pacientes</a></li>
				<li><a href="./RegistrarPaciente.jsp">Registrar Novo Paciente</a></li>
				<li><a href="./ServletInicial?accao=evolucaoPacientes">Evolução dos Pacientes</a></li>
			</ul>
		</div>

	<div id="container">
	
	<table>
	 <% ArrayList<Paciente> list = new ArrayList<Paciente>(); 
		Object lista_pacientes = session.getAttribute("lista_pacientes");  
		list = (ArrayList<Paciente>)lista_pacientes; 
	
		ArrayList paciente = new ArrayList(); 
		for(Paciente p : list){
			Map pac = new HashMap();
			pac.put("id", p.getId());
			pac.put("nome_proprio", p.getNomeProprio());
			pac.put("apelido", p.getApelido());
			pac.put("nivel_doenca", p.getNivel_doenca());
			pac.put("nivel_sessao", p.getNivel_sessao());
			paciente.add(pac);
		}
		pageContext.setAttribute("pacientes", paciente);
		%>
		<tr>
			<th> Id </th>
			<th>Nome Paciente</th>
			<th>Apelido</th>
			<th>Nível de Doença</th>
			<th>Nível de Sessão</th>
			<th></th>
			</tr>
		<c:forEach items="${pacientes}" var="current">
			<tr>
			<td><c:out value="${current.id}" /></td>
			 <td><c:out value="${current.nome_proprio}" /></td>
			 <td><c:out value="${current.apelido}"/></td>
			 <td><c:out value="${current.nivel_doenca}" /></td>
			 <td><c:out value="${current.nivel_sessao}" /></td>	
			 <td>
                            <form action="./Inicial?accao=verPaciente" method="post">
                            <input type="hidden" name="linhaId" value="${current.id}"/>
                            <input type="submit" value="Ver"/>
                            </form>

                            <form id="EditarPaciente" action="./Inicial?accao=editarPaciente" method="post">
                                <input type="hidden" name="linhaId" value="${current.id}"/>
                                <input type="submit" value="Editar">
                           </form>

                           <form id="ApagarPaciente" action="./Inicial?accao=apagarPaciente" method="post">
                                <input type="hidden" name="linhaId" value="${current.id}"/>
                                <input type="submit" value="Apagar">
                           </form>                        
			 </td>
  			</tr>	
  		</c:forEach>
</table>
</form>
	</div>

	<footer>
		<p>Copyright 2014 - MEM +</p>
	</footer>
</body>
</html>	