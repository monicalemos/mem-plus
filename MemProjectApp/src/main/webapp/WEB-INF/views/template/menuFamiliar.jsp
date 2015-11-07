<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
.sidebarRight .sidebarRight-nav.navbar-collapse {
    padding-right: 0;
    padding-left: 0;
     float: right;
}

.sidebarRight .sidebar-search {
    padding: 15px;
}

.sidebarRight ul li {
    border-bottom: 1px solid #e7e7e7;
}

.sidebarRight ul li a.active {
    background-color: #eee;
}

.sidebarRight .arrow {
    float: right;
}

.sidebarRight .fa.arrow:before {
    content: "\f104";
}

.sidebarRight .active>a>.fa.arrow:before {
    content: "\f107";
}

.sidebarRight .nav-second-level li,
.sidebarRight .nav-third-level li {
    border-bottom: 0!important;
}

.sidebarRight .nav-second-level li a {
    padding-left: 37px;
}

.sidebarRight .nav-third-level li a {
    padding-left: 52px;
}

@media(min-width:768px) {
    .sidebarRight {
        z-index: 1;
        position: absolute;
        width: 150px;
        margin-top: 0px;
    }

    .navbar-top-links .dropdown-messages,
    .navbar-top-links .dropdown-tasks,
    .navbar-top-links .dropdown-alerts {
        margin-left: auto;
    }
}
</style>
<!-- <div class="sidebarRight-nav navbar-collapse"> -->
<div class="tf">
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
		
		<li/>
		
		<li><spring:url value="/Familiar/listarSegundoGrauFamiliares" var="listarSegundoGrauFamiliaresUrl" htmlEscape="true" /> 
		<a href="${listarSegundoGrauFamiliaresUrl}">Ver Familiares em Segundo Grau</a>
		</li>
		
		<li><spring:url value="/Familiar/inserirSegundoGrauFamiliar" var="inserirSegundoGrauFamiliarUrl" htmlEscape="true" /> 
		<a href="${inserirSegundoGrauFamiliarUrl}">Inserir Familiar em Segundo Grau</a>
		</li>

	</ul>
</div>
