<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Tecnico tecnico = (Tecnico) request.getAttribute("currentTecnico");
	@SuppressWarnings("unchecked")
	HashMap<String,Familiar> listFamiliar = (HashMap<String,Familiar>)(session.getAttribute("listFamiliares"));
%>

<!-- TODO: MOVE TO CSS FILE -->
<style>
#loginForm {
	border: .2em dotted gray;
	padding: 10px;
	width: 700px;
	height: 650px;
	position: fixed;
	left: 530px;
	margin-left: -140px;
	overflow: auto;
	margin-top: 10px;
	margin-right: 5px;
}

.checkbox {
	/*width: 7000px;*/
	height: 20px;
	margin-left: 3%;
}

.checkbox label {
	margin-left: -20px;	
}
/*
.checkbox label:after {
	opacity: 0;
	content: '';
	position: absolute;
	width: 9px;
	height: 5px;
	background: transparent;
	top: 4px;
	left: 4px;
	border: 3px solid #fcfff4;
	border-top: none;
	border-right: none;

	-webkit-transform: rotate(-45deg);
	-moz-transform: rotate(-45deg);
	-o-transform: rotate(-45deg);
	-ms-transform: rotate(-45deg);
	transform: rotate(-45deg);
}

.checkbox label:hover::after {

	opacity: 0.3;
} */

.checkbox input[type=checkbox]:checked + label:after {

	opacity: 1;
}
</style>

<script type="text/javascript">
function show(group)
{
	 /*var visSetting = (checkbox.checked) ? "visible" : "hidden";*/ 
	 document.getElementById(group).style.visibility = "visible"; 
}
</script>


<div id="page-wrapper">
	<h1 class="page-header">Novo Evento</h1>
	<div class="row-fluid" id="LoginForm">
		<form:form method="post" modelAttribute="eventoModel" action="inserirEvento">
		
			<h2 class="form-signin-heading">Dados do Novo Evento</h2>
			
			<form:label path="id.idEvento" class="sr-only">idEvento </form:label>
			<form:input path="id.idEvento" id="idEvento"
			class="form-control" required="required" type="hidden" />
		
			<form:label path="paciente.idPaciente" class="sr-only">idPaciente </form:label>
			<form:input path="paciente.idPaciente" id="idPaciente"
			class="form-control" required="required" type="hidden" />
			
			<br>
			<form:label path="data" class="sr-only">Data do Evento</form:label>
			<fmt:formatDate value="${evento.data}" var="dateString" pattern="dd/MM/yyyy" />
			<form:input path="data" id="data" class="form-control" required="required"
				placeholder="Data do Evento" />

			<br>
			<form:label path="tipoEvento">Tipo de Evento:</form:label>
			<form:select path="tipoEvento" class="form-control">
				<form:option value="Nascimento">Nascimento</form:option>
				<form:option value="Baptizado">Baptizado</form:option>
				<form:option value="Aniversário">Aniversário</form:option>
				<form:option value="Noivado">Noivado</form:option>
				<form:option value="Casamento">Casamento</form:option>
				<form:option value="Divorcio">Divórcio</form:option>
				<form:option value="Festa">Festa</form:option>
				<form:option value="Morte">Morte</form:option>
				<form:option value="Funeral">Funeral</form:option>
			</form:select>

			<br>
			<form:label path="descricao" class="sr-only">Descrição do Evento</form:label>
			<form:textarea path="descricao" id="descricao" class="form-control"
				placeholder="Descrição do Evento" />

			<form:label path="morada">Morada:</form:label>
			<br>
			<form:label path="morada.pais" class="sr-only">Pais</form:label>
			<form:input path="morada.pais" id="pais"
				class="form-control" required="required" placeholder="Pais" />
			<br>
			<form:label path="morada.regiao" class="sr-only">Região</form:label>
			<form:input path="morada.regiao" id="regiao"
				class="form-control" required="required" placeholder="Regiao" />
			<br>
			<form:label path="morada.cidade" class="sr-only">Cidade</form:label>
			<form:input path="morada.cidade" id="cidade"
				class="form-control" required="required" placeholder="Cidade" />

			<br>
			<div class="checkbox">
					<input type="checkbox" value="1" id="temFamiliar" onclick="show('myGroup');"/>
					<label for="temFamiliar">Esteve um familiar presente</label>
					<label for="checkboxInput"></label>
				</div><br>
				
				<span id="myGroup" style="visibility:hidden" >
					<form:label path="familiar.idFamiliar">Nome do Familiar</form:label>
				<!-- 	<label for="nome_familiar">Nome do Familiar</label>  -->
					<form:select path="familiar.idFamiliar" class="form-control">
					 <% 			
						ArrayList<Map<String, Object>> familiares = new ArrayList<Map<String, Object>>();
						
						for (Map.Entry<String, Familiar> entry : listFamiliar.entrySet()) {
						    String parentesco = entry.getKey();
						    Familiar f = entry.getValue();
						    Map<String, Object> pac = new HashMap<String, Object>();

					        pac.put("id", f.getIdFamiliar());
			        		pac.put("nomeProprio", f.getNomeProprio());
			         		pac.put("apelido", f.getApelido());
			         		pac.put("parentesco", parentesco);
			         		familiares.add(pac);
			        		
						}
						pageContext.setAttribute("familiares", familiares);
						%>
				
						<c:forEach items="${familiares}" var="current">
							<form:option value="${current.id}">${current.nomeProprio} ${current.apelido} - ${current.parentesco}</form:option>
				  		</c:forEach>
					</form:select> 
				</span>

			<br>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Inserir</button>

		</form:form>
	</div>
</div>
<!-- </div> -->