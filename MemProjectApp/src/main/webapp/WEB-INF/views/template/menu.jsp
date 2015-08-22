<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="sidebar-nav navbar-collapse">
	<ul class="nav" id="side-menu">
		<li><spring:url value="/Paciente/verPaciente" var="verPacienteUrl" htmlEscape="true" /> 
		<a href="${verPacienteUrl}">Dados do Paciente</a>
		</li>

		<li><spring:url value="/Familiar/listarFamiliares" var="listarFamiliaresUrl" htmlEscape="true" /> 
		<a href="${listarFamiliaresUrl}">Ver Familiares</a>
		</li>

		<li><spring:url value="/Familiar/inserirFamiliar" var="inserirFamiliarUrl" htmlEscape="true" /> 
		<a href="${inserirFamiliarUrl}">Inserir Familiar</a>
		</li>

		<li><spring:url value="/Evento/listarEventos" var="listarEventosUrl" htmlEscape="true" /> 
		<a href="${listarEventosUrl}">Ver Eventos</a>
		</li>

		<li><spring:url value="/Evento/inserirEvento" var="inserirEventoUrl" htmlEscape="true" /> 
		<a href="${inserirEventoUrl}">Inserir Evento</a>
		</li>
	</ul>
</div>
