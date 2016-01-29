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
		      <div class="subMenu"><a href="#SubMenu1" class="dropdown list-group-item" data-toggle="collapse" data-parent="#SubMenu1">Do Paciente <i class="fa fa-caret-down"></i></a></div>
		      <div class="collapse list-group-submenu" id="SubMenu1">
       			<spring:url value="/Familiar/listarFamiliares" var="listarFamiliaresUrl" htmlEscape="true" /> <a href="${listarFamiliaresUrl}" class="list-group-item" data-parent="#SubMenu1">Listar</a>
				<spring:url value="/Familiar/inserirFamiliar" var="inserirFamiliarUrl" htmlEscape="true" /> <a href="${inserirFamiliarUrl}" class="list-group-item" data-parent="#SubMenu1">Novo</a>
		       </div>
		       
		       <spring:url value="/Familiar/verFamiliarAscendente" var="verFamiliarPaiUrl" htmlEscape="true" /> <a href="${verFamiliarPaiUrl}" class="list-group-item">Ascendente</a>
			   <spring:url value="/Familiar/verFamiliar" var="verFamiliarUrl" htmlEscape="true" /> <a href="${verFamiliarUrl}" class="list-group-item">Ver</a>
		       <spring:url value="/Familiar/editarFamiliar" var="editarFamiliarUrl" htmlEscape="true" /> <a href="${editarFamiliarUrl}" class="list-group-item">Editar</a>
		       
		       <div class="subMenu"><a href="#SubMenu2" class="dropdown list-group-item" data-toggle="collapse" data-parent="#SubMenu1">Descendentes <i class="fa fa-caret-down"></i></a></div>
		       <div class=" collapse list-group-submenu" id="SubMenu2">
       			<spring:url value="/Familiar/listarSegundoGrauFamiliares" var="listarSegundoGrauFamiliaresUrl" htmlEscape="true" /><a href="${listarSegundoGrauFamiliaresUrl}" class="list-group-item" data-parent="#SubMenu2">Listar</a>
				<spring:url value="/Familiar/inserirSegundoGrauFamiliar" var="inserirSegundoGrauFamiliarUrl" htmlEscape="true" /><a href="${inserirSegundoGrauFamiliarUrl}" class="list-group-item" data-parent="#SubMenu2" >Novo</a>
		       </div>	    
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
.list-group-submenu {
  margin-left:20px;
}
.list-group-submenu > .list-group-item{
   border-left: 0 solid transparent;
}

.list-group.panel > .list-group-submenu {
  border-bottom-right-radius: 4px;
  border-bottom-left-radius: 4px;
  background-color: red;
  border: 0 solid transparent;
  border-bottom: 1px solid #2c78b7;
  margin-bottom: 2px;
}

.subMenu > .list-group-item{
   background-color: #fafafa;
   /*fafafa*/
}
</style>

<!-- 
<div class="tf">
	<ul class="nav nav-pills nav-stacked" id="side-menu">
	<li><spring:url value="/Paciente/verPaciente" var="verPacienteUrl" htmlEscape="true" /> 
		<a href="${verPacienteUrl}">Dados do Paciente</a>
		</li>

		<li> <a>Familiares</a>
			<ul>
				<li><spring:url value="/Familiar/listarFamiliares" var="listarFamiliaresUrl" htmlEscape="true" /> 
					<a href="${listarFamiliaresUrl}">Ver Familiares do Paciente</a>
				</li>
				<li><spring:url value="/Familiar/inserirFamiliar" var="inserirFamiliarUrl" htmlEscape="true" /> 
					<a href="${inserirFamiliarUrl}">Inserir Familiar directo ao Paciente</a>
				</li>
				
				<li role="separator" class="divider"></li>
				<li><spring:url value="/Familiar/verFamiliarAscendente" var="verFamiliarPaiUrl" htmlEscape="true" /> 
					<a href="${verFamiliarPaiUrl}">Ver Familiar Ascendente</a>
				</li>
				<li role="separator" class="divider"></li>
				<li><spring:url value="/Familiar/listarSegundoGrauFamiliares" var="listarSegundoGrauFamiliaresUrl" htmlEscape="true" /> 
					<a href="${listarSegundoGrauFamiliaresUrl}">Ver Familiares Descendentes</a>
				</li>
				<li><spring:url value="/Familiar/inserirSegundoGrauFamiliar" var="inserirSegundoGrauFamiliarUrl" htmlEscape="true" /> 
					<a href="${inserirSegundoGrauFamiliarUrl}">Inserir Familiar Descendente</a>
				</li>
			</ul>
		</li>
		
		<li> <a>Eventos</a>
			<ul>
				<li><spring:url value="/Evento/listarEventos" var="listarEventosUrl" htmlEscape="true" /> 
					<a href="${listarEventosUrl}">Ver Eventos</a>
				</li>
		
				<li><spring:url value="/Evento/inserirEvento" var="inserirEventoUrl" htmlEscape="true" /> 
					<a href="${inserirEventoUrl}">Inserir Evento</a>
				</li>
			</ul>
		</li>
		
	</ul>
</div>
 -->
