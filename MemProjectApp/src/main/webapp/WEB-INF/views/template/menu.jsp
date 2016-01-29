<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="sidebar-nav navbar-collapse" id="MainMenu">
	<div class="nav nav-pills nav-stacked" id="side-menu"> 	
  		<div class="list-group panel">
		    <a href="#demo1" class="dropdown list-group-item" data-toggle="collapse" data-parent="#MainMenu">Paciente <span class="caret"></span></a>
		    <div class="collapse" id="demo1">
		   		<spring:url value="/Paciente/verPaciente" var="verPacienteUrl" htmlEscape="true" /> <a href="${verPacienteUrl}" class="list-group-item">Ver</a>
				<spring:url value="/Paciente/editarPaciente" var="editarPacienteUrl" htmlEscape="true" /> <a href="${editarPacienteUrl}" class="list-group-item">Editar</a>
		  	</div>
	
		    <a href="#demo2" class="dropdown list-group-item" data-toggle="collapse" data-parent="#MainMenu">Familiares <span class="caret"></span></a>
		    <div class="collapse" id="demo2">
		   		<spring:url value="/Familiar/listarFamiliares" var="listarFamiliaresUrl" htmlEscape="true" /> <a href="${listarFamiliaresUrl}" class="list-group-item">Listar</a>
				<spring:url value="/Familiar/inserirFamiliar" var="inserirFamiliarUrl" htmlEscape="true" /> <a href="${inserirFamiliarUrl}" class="list-group-item">Novo</a>
		    </div>
		    <a href="#demo3" class="dropdown list-group-item" data-toggle="collapse" data-parent="#MainMenu">Evento <span class="caret"></span></a>
		    <div class="collapse" id="demo3">
		        <spring:url value="/Evento/listarEventos" var="listarEventosUrl" htmlEscape="true" /> <a href="${listarEventosUrl}" class="list-group-item">Listar</a>
		      	<spring:url value="/Evento/inserirEvento" var="inserirEventoUrl" htmlEscape="true" /> <a href="${inserirEventoUrl}" class="list-group-item">Novo</a>
		    </div>
		</div>
	</div>
</div>

<style>
.list-group.panel > .list-group-item {
  border-bottom-right-radius: 4px;
  border-bottom-left-radius: 4px;
  background-color: #F8F8F8;
  border: 0 solid transparent;
  border-bottom: 1px solid #2c78b7;
  margin-bottom: 2px;
}

.list-group.panel > .dropdown{
	color:#428bca;
}
</style>

<!-- 
<div class="sidebar-nav navbar-collapse">
	 <ul class="nav nav-pills nav-stacked" id="side-menu"> 	
		 <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Paciente 
	   	 <span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li>
					<spring:url value="/Paciente/verPaciente" var="verPacienteUrl" htmlEscape="true" /> 
					<a href="${verPacienteUrl}">Ver</a>
				</li>
				<li>
					<spring:url value="/Paciente/verPaciente" var="verPacienteUrl" htmlEscape="true" /> 
					<a href="${verPacienteUrl}">Editar</a>
				</li>
			</ul>
		</li>
		<li role="separator" class="divider"></li>
	    
	    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Familiares 
	    <span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li>
					<spring:url value="/Familiar/listarFamiliares" var="listarFamiliaresUrl" htmlEscape="true" /> 
					<a href="${listarFamiliaresUrl}">Listar</a>
				</li>
				<li><spring:url value="/Familiar/inserirFamiliar" var="inserirFamiliarUrl" htmlEscape="true" /> 
					<a href="${inserirFamiliarUrl}">Novo</a>
				</li>
			</ul>
		</li>
		
		<li role="separator" class="divider"></li>

		<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Eventos 
	    <span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li>
					<spring:url value="/Evento/listarEventos" var="listarEventosUrl" htmlEscape="true" /> 
					<a href="${listarEventosUrl}">Listar</a>
				</li>
		
				<li>
					<spring:url value="/Evento/inserirEvento" var="inserirEventoUrl" htmlEscape="true" /> 
					<a href="${inserirEventoUrl}">Novo</a>
				</li>
			</ul>
		</li>
	</ul>
</div> -->