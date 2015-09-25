<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link rel="stylesheet" href="WEB-INF/resources/css/Home.css" type="text/css">
<section id="page">
	<div id="menu">
		<div class="line">
			<ul>
				<li>
					<spring:url value="/home" var="homeUrl" htmlEscape="true" /> 
					<a href="${homeUrl}">Home</a>
				</li>
				<li>
					<spring:url value="/listarPacientes" var="listarPacientesUrl" htmlEscape="true" />
					<a href="${listarPacientesUrl}">Ver Pacientes</a>
				</li>

				<li>
					<spring:url value="/inserirPaciente" var="inserirPacienteUrl" htmlEscape="true" /> 
					<a href="${inserirPacienteUrl}">Registrar Novo Paciente</a>
				</li>
			</ul>
		</div>
	</div>
</section>
