<!DOCTYPE html>
<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	Tecnico tecnico = (Tecnico) request.getAttribute("currentTecnico");
%>

<div id="page-wrapper">
	<h1 class="page-header">Dados do Utilizador</h1>
	<p> <label for="tupoUtilizador"> Tipo de Utilizador:</label> <%=tecnico.getUtilizador().getTipoUtilizador()%></p>
	<p> <label for="nomeProprio"> Nome Próprio:</label> <%=tecnico.getNomeProprio()%></p> 
	<p> <label for="telefone"> Telefone:</label> <%=tecnico.getTelefone()%></p>
	<p> <label for="email"> Email:</label> <%=tecnico.getUtilizador().getEmail()%></p>
	<p> <label for="nomeUtilizador"> Nome de Utilizador:</label> <%=tecnico.getUtilizador().getNomeUtilizador()%></p>
   	<p> <label for="password"> Password:</label> <%=tecnico.getUtilizador().getPassword()%></p>
</div>
