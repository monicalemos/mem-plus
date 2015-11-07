<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Tecnico tecnico = (Tecnico) request.getAttribute("currentTecnico");
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
	function showHide(checkbox, group) {
		var visSetting = (checkbox.checked) ? "visible" : "hidden";
		document.getElementById(group).style.visibility = visSetting;
	}
</script>


<div id="page-wrapper">
	<h1 class="page-header">Novo Familiar</h1>
	<!-- 	<div class="container"> -->
	<div class="row-fluid" id="LoginForm">
		<form:form method="post" modelAttribute="relacaoModel" action="inserirSegundoGrauFamiliar">
		
			<h2 class="form-signin-heading">Dados do Novo Familiar</h2>

			<form:label path="familiarByIdFamiliar.idFamiliar" class="sr-only">Id Familiar: </form:label>
			<form:input type="hidden" path="familiarByIdFamiliar.idFamiliar" id="idFamiliar" class="form-control"
				required="required" placeholder="Id Familiar" />
				
			<form:label path="familiarByIdFamiliar1.nomeProprio" class="sr-only">Nome Próprio</form:label>
			<form:input path="familiarByIdFamiliar1.nomeProprio" id="nomeProprio" class="form-control"
				required="required" placeholder="Nome Próprio" />

			<br>
			<form:label path="familiarByIdFamiliar1.apelido" class="sr-only">Apelido</form:label>
			<form:input path="familiarByIdFamiliar1.apelido" id="apelido" class="form-control"
				required="required" placeholder="Apelido" />

			<br>
			<form:label path="familiarByIdFamiliar1.dataNascimento" class="sr-only">Data de Nascimento</form:label>
			<form:input path="familiarByIdFamiliar1.dataNascimento" id="dataNascimento"
				class="form-control" required="required"
				placeholder="Data de Nascimento" />

			<br>
			<form:label path="familiarByIdFamiliar1.moradaByIdLocalNascimento">Local de Nascimento:</form:label>
			<br>
			<form:label path="familiarByIdFamiliar1.moradaByIdLocalNascimento.pais" class="sr-only">Pais</form:label>
			<form:input path="familiarByIdFamiliar1.moradaByIdLocalNascimento.pais" id="paisNascimento"
				class="form-control" required="required"
				placeholder="Pais Nascimento" />
			<br>
			<form:label path="familiarByIdFamiliar1.moradaByIdLocalNascimento.regiao" class="sr-only">Região</form:label>
			<form:input path="familiarByIdFamiliar1.moradaByIdLocalNascimento.regiao"
				id="regiaoNascimento" class="form-control" required="required"
				placeholder="Regiao Nascimento" />
			<br>
			<form:label path="familiarByIdFamiliar1.moradaByIdLocalNascimento.cidade" class="sr-only">Cidade</form:label>
			<form:input path="familiarByIdFamiliar1.moradaByIdLocalNascimento.cidade"
				id="cidadeNascimento" class="form-control" required="required"
				placeholder="Cidade Nascimento" />

			<form:label path="familiarByIdFamiliar1.moradaByIdMorada">Morada:</form:label>
			<br>
			<form:label path="familiarByIdFamiliar1.moradaByIdMorada.pais" class="sr-only">Pais</form:label>
			<form:input path="familiarByIdFamiliar1.moradaByIdMorada.pais" id="pais"
				class="form-control" required="required" placeholder="Pais" />
			<br>
			<form:label path="familiarByIdFamiliar1.moradaByIdMorada.regiao" class="sr-only">Região</form:label>
			<form:input path="familiarByIdFamiliar1.moradaByIdMorada.regiao" id="regiao"
				class="form-control" required="required" placeholder="Regiao" />
			<br>
			<form:label path="familiarByIdFamiliar1.moradaByIdMorada.cidade" class="sr-only">Cidade</form:label>
			<form:input path="familiarByIdFamiliar1.moradaByIdMorada.cidade" id="cidade"
				class="form-control" required="required" placeholder="Cidade" />

			<br>
			<form:label path="familiarByIdFamiliar1.genero">Género</form:label>
			<form:select path="familiarByIdFamiliar1.genero" placeholder="Género" class="form-control">
				<form:option value="Masculino">Masculino</form:option>
				<form:option value="Feminino">Feminino</form:option>
			</form:select>

			<br>
			<form:label path="familiarByIdFamiliar1.profissao" class="sr-only">Profissão</form:label>
			<form:input path="familiarByIdFamiliar1.profissao" id="profissao" class="form-control"
				required="required" placeholder="Profissão" />

			<br>
			<form:label path="familiarByIdFamiliar1.estadoCivil">Estado Civil:</form:label>
			<form:select path="familiarByIdFamiliar1.estadoCivil" class="form-control">
				<form:option value="Solteiro">Solteiro</form:option>
				<form:option value="Casado">Casado</form:option>
				<form:option value="Divorciado">Divorciado</form:option>
				<form:option value="Viuvo">Viúvo</form:option>
			</form:select>

			<br>
			<form:label path="familiarByIdFamiliar1.telefone" class="sr-only">Telefone</form:label>
			<form:input path="familiarByIdFamiliar1.telefone" id="telefone" class="form-control"
				required="required" placeholder="Telefone" />

			<br>
			<form:label path="tipoRelacao">Parentesco</form:label>
			<form:select path="tipoRelacao" placeholder="Parentesco" class="form-control">
				<form:option value="Filho">Filho</form:option>
				<form:option value="Filha">Filha</form:option>
				<form:option value="Irmão">irmão</form:option>
				<form:option value="Irmã">Irmã</form:option>
				<form:option value="Esposo">Esposo</form:option>
				<form:option value="Esposa">Esposa</form:option>
				<form:option value="Pai">Pai</form:option>
				<form:option value="Mãe">Mãe</form:option>
			</form:select>

			<br>
			<div class="checkbox">
				<form:label path="familiarByIdFamiliar1.ecuidador"> É Cuidador</form:label>
				<span style="padding-left:2em">
					<form:checkbox path="familiarByIdFamiliar1.ecuidador"  value="1" 
					id="eCuidador" onclick="showHide(this, 'myGroup');" />
				</span>
			</div>

			<span id="myGroup" style="visibility: hidden"> 
			<form:label path="familiarByIdFamiliar1.utilizador.email" class="sr-only">Email</form:label> 
			<form:input path="familiarByIdFamiliar1.utilizador.email" id="email" class="form-control" 
						placeholder="Email" /> 
					
			<br> 
			<form:label path="familiarByIdFamiliar1.utilizador.nomeUtilizador" class="sr-only">Nome Utilizador</form:label>
			<form:input path="familiarByIdFamiliar1.utilizador.nomeUtilizador" id="nomeUtilizador" class="form-control" 
						placeholder="Telefone" />

			<br> 
			<form:label path="familiarByIdFamiliar1.utilizador.password" class="sr-only">Password</form:label>
			<form:password path="familiarByIdFamiliar1.utilizador.password" id="password"
					class="form-control" placeholder="Password" />
			</span>

			<br>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Inserir</button>

		</form:form>
	</div>
</div>
<!-- </div> -->