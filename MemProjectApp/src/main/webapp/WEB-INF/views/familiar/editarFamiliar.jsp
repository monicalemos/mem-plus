<!DOCTYPE html>
<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	RelacaoPacienteFamiliar relacao = (RelacaoPacienteFamiliar) request.getAttribute("relacaoModel");
	Familiar familiar = relacao.getFamiliar();
	Integer id = familiar.getIdFamiliar(); 
	request.setAttribute("idFamiliar", id);
	System.out.println("Tem o familiar na página? " + id);
%>

<style>
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
.checkbox input[type=checkbox]:checked+label:after {
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
	<form:form method="post" modelAttribute="relacaoModel" action="editarFamiliar">

		<h2 class="form-signin-heading">Editar Dados do Familiar</h2>

		<form:label path="familiar.idFamiliar" class="sr-only">idFamiliar </form:label>
		<form:input type="hidden" path="familiar.idFamiliar" id="idFamiliar"
			class="form-control" required="required" />
	
		<form:label path="id.idRelacaoPacienteFamiliar" class="sr-only">idRelacao </form:label>
		<form:input type="hidden" path="id.idRelacaoPacienteFamiliar" id="idRelacaoPacienteFamiliar"
			class="form-control" required="required" />
		
		<form:label path="paciente.idPaciente" class="sr-only">idPaciente </form:label>
		<form:input type="hidden" path="paciente.idPaciente" id="idPaciente"
			class="form-control" required="required" />
			
 		<form:label path="familiar.nomeProprio" class="sr-only">Nome Próprio</form:label>
		<form:input path="familiar.nomeProprio" id="nomeProprio" class="form-control"
			required="required" placeholder="Nome Próprio" />

		<br>
		<form:label path="familiar.apelido" class="sr-only">Apelido</form:label>
		<form:input path="familiar.apelido" id="apelido" class="form-control"
			required="required" placeholder="Apelido" />

		<br>
		<form:label path="familiar.dataNascimento" class="sr-only">Data de Nascimento</form:label>
		<form:input path="familiar.dataNascimento" id="dataNascimento"
			class="form-control" required="required"
			placeholder="Data de Nascimento" />

		<br>
		<form:label path="familiar.moradaByIdLocalNascimento">Local de Nascimento:</form:label>
		<br>
		<form:label path="familiar.moradaByIdLocalNascimento.pais" class="sr-only">Pais</form:label>
		<form:input path="familiar.moradaByIdLocalNascimento.pais" id="paisNascimento"
			class="form-control" required="required"
			placeholder="Pais Nascimento" />
		<br>
		<form:label path="familiar.moradaByIdLocalNascimento.regiao" class="sr-only">Região</form:label>
		<form:input path="familiar.moradaByIdLocalNascimento.regiao"
			id="regiaoNascimento" class="form-control" required="required"
			placeholder="Regiao Nascimento" />
		<br>
		<form:label path="familiar.moradaByIdLocalNascimento.cidade" class="sr-only">Cidade</form:label>
		<form:input path="familiar.moradaByIdLocalNascimento.cidade"
			id="cidadeNascimento" class="form-control" required="required"
			placeholder="Cidade Nascimento" />

		<form:label path="familiar.moradaByIdMorada">Morada:</form:label>
		<br>
		<form:label path="familiar.moradaByIdMorada.pais" class="sr-only">Pais</form:label>
		<form:input path="familiar.moradaByIdMorada.pais" id="pais"
			class="form-control" required="required" placeholder="Pais" />
		<br>
		<form:label path="familiar.moradaByIdMorada.regiao" class="sr-only">Região</form:label>
		<form:input path="familiar.moradaByIdMorada.regiao" id="regiao"
			class="form-control" required="required" placeholder="Regiao" />
		<br>
		<form:label path="familiar.moradaByIdMorada.cidade" class="sr-only">Cidade</form:label>
		<form:input path="familiar.moradaByIdMorada.cidade" id="cidade"
			class="form-control" required="required" placeholder="Cidade" />

		<br>
		<form:label path="familiar.genero">Género</form:label>
		<form:select path="familiar.genero" placeholder="Género" class="form-control">
			<form:option value="Masculino">Masculino</form:option>
			<form:option value="Feminino">Feminino</form:option>
		</form:select>

		<br>
		<form:label path="familiar.profissao" class="sr-only">Profissão</form:label>
		<form:input path="familiar.profissao" id="profissao" class="form-control"
			required="required" placeholder="Profissão" />

		<br>
		<form:label path="familiar.estadoCivil">Estado Civil:</form:label>
		<form:select path="familiar.estadoCivil" class="form-control">
			<form:option value="Solteiro">Solteiro</form:option>
			<form:option value="Casado">Casado</form:option>
			<form:option value="Divorciado">Divorciado</form:option>
			<form:option value="Viuvo">Viúvo</form:option>
		</form:select>

		<br>
		<form:label path="familiar.telefone" class="sr-only">Telefone</form:label>
		<form:input path="familiar.telefone" id="telefone" class="form-control"
			required="required" placeholder="Telefone" />

		<br>
		<form:label path="tipoRelacao">Parentesco</form:label>
		<form:select path="tipoRelacao" placeholder="Parentesco" class="form-control" 
			required="required">
			<option value="filho">Filho</option>
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
			<form:label path="familiar.ecuidador"> É Cuidador</form:label>
			<span style="padding-left:2em"><form:checkbox path="familiar.ecuidador"  value="1" 
				id="eCuidador" onclick="showHide(this, 'myGroup');" /></span>
		</div>

		<span id="myGroup" style="visibility: hidden"> 
		<form:label path="familiar.utilizador.email" class="sr-only">Email</form:label> 
		<form:input path="familiar.utilizador.email" id="email" class="form-control" 
					placeholder="Email" /> 
				
		<br> 
		<form:label path="familiar.utilizador.nomeUtilizador" class="sr-only">Nome Utilizador</form:label>
		<form:input path="familiar.utilizador.nomeUtilizador" id="nomeUtilizador" class="form-control" 
					placeholder="Telefone" />

		<br> 
		<form:label path="familiar.utilizador.password" class="sr-only">Password</form:label>
		<form:password path="familiar.utilizador.password" id="password"
				class="form-control" placeholder="Password" />
		</span> 
		<br>
		<button type="submit">Editar</button>

		<form action="verFamiliar" method="get">
			<input type="hidden" name="idFamiliar" value="<%=relacao.getFamiliar().getIdFamiliar()%>" />
			<button type="submit">Cancelar</button>
		</form>
	</form:form>
</div>