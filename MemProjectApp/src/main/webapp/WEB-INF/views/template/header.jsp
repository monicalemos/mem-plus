<%@ page import="java.util.*"%>
<%@ page import="com.mem.app.model.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<header>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
			<% 
			Tecnico tecnico = (Tecnico)session.getAttribute("currentTecnico"); 
			%> 
				<a class="navbar-brand" href="#"> 
				<img alt="Brand" src="${pageContext.request.contextPath}/resources/static/img/logo.png">
				</a>
				
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right container-fluid">
					<li> 
						<spring:url value="/home-private" var="homeUrl" htmlEscape="true" /> 
						<a href="${homeUrl}">Home</a>
					</li>
					<li>
						<spring:url value="/Paciente/listarPacientes" var="listarPacientesUrl" htmlEscape="true" /> 
						<a href="${listarPacientesUrl}">Ver Pacientes</a>
					</li>
					<li>
						<spring:url value="/Paciente/inserirPaciente" var="inserirPacienteUrl" htmlEscape="true" /> 
						<a href="${inserirPacienteUrl}">Registrar Novo Paciente</a>
					</li>
					<li>
					<spring:url value="/Utilizador/logout" var="logout" htmlEscape="true" /> 
						<a href="${logout}">Logout <%-- <%= tecnico.getNomeProprio() %> --%> </a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</header>