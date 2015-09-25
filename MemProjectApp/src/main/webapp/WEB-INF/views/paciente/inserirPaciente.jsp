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
</style>

<div id="page-wrapper">
	<h1 class="page-header">INSERIR PACIENTE</h1>
<!-- 	<div class="container"> -->
		<div class="row-fluid" id="LoginForm">
			<form:form method="post" modelAttribute="pacienteModel"
				action="inserirPaciente">
				<h2 class="form-signin-heading">Novo Paciente</h2>

				<form:label path="nomeProprio" class="sr-only">Nome Próprio</form:label>
				<form:input path="nomeProprio" id="nomeProprio" class="form-control"
					required="required" placeholder="Nome Próprio" />

				<br>
				<form:label path="apelido" class="sr-only">Apelido</form:label>
				<form:input path="apelido" id="apelido" class="form-control"
					required="required" placeholder="Apelido" />

				<br>
				<form:label path="dataNascimento" class="sr-only">Data de Nascimento</form:label>
				<form:input path="dataNascimento" id="dataNascimento"
					class="form-control" required="required"
					placeholder="Data de Nascimento" />

				<br>
				<form:label path="moradaByIdLocalNascimento">Local de Nascimento:</form:label>
				<br>
				<form:label path="moradaByIdLocalNascimento.pais" class="sr-only">Pais</form:label>
				<form:input path="moradaByIdLocalNascimento.pais"
					id="paisNascimento" class="form-control" required="required"
					placeholder="Pais Nascimento" />
				<br>
				<form:label path="moradaByIdLocalNascimento.regiao" class="sr-only">Região</form:label>
				<form:input path="moradaByIdLocalNascimento.regiao"
					id="regiaoNascimento" class="form-control" required="required"
					placeholder="Regiao Nascimento" />
				<br>
				<form:label path="moradaByIdLocalNascimento.cidade" class="sr-only">Cidade</form:label>
				<form:input path="moradaByIdLocalNascimento.cidade"
					id="cidadeNascimento" class="form-control" required="required"
					placeholder="Cidade Nascimento" />

				<form:label path="moradaByIdMorada">Morada:</form:label>
				<br>
				<form:label path="moradaByIdMorada.pais" class="sr-only">Pais</form:label>
				<form:input path="moradaByIdMorada.pais" id="pais"
					class="form-control" required="required" placeholder="Pais" />
				<br>
				<form:label path="moradaByIdMorada.regiao" class="sr-only">Região</form:label>
				<form:input path="moradaByIdMorada.regiao" id="regiao"
					class="form-control" required="required" placeholder="Regiao" />
				<br>
				<form:label path="moradaByIdMorada.cidade" class="sr-only">Cidade</form:label>
				<form:input path="moradaByIdMorada.cidade" id="cidade"
					class="form-control" required="required" placeholder="Cidade" />

				<br>
				<form:label path="genero">Género</form:label>
				<form:select path="genero" placeholder="Género" class="form-control">
					<form:option value="Masculino">Masculino</form:option>
					<form:option value="Feminino">Feminino</form:option>
				</form:select>

				<br>
				<form:label path="profissao" class="sr-only">Profissão</form:label>
				<form:input path="profissao" id="profissao" class="form-control"
					required="required" placeholder="Profissão" />

				<br>
				<form:label path="escolaridade">Escolaridade:</form:label>
				<form:select path="escolaridade" placeholder="Escolaridade"
					class="form-control">
					<form:option value="Ensino_Basico">Ensino Básico</form:option>
					<form:option value="Ensino_Secundario">Ensino Secundário</form:option>
					<form:option value="Licenciatura">Licenciatura</form:option>
					<form:option value="Pos_Graduacao">Pós-Graduação</form:option>
					<form:option value="Mestrado">Mestrado</form:option>
					<form:option value="Douturamento">Douturamento</form:option>
				</form:select>

				<br>
				<form:label path="estadoCivil">Estado Civil:</form:label>
				<form:select path="estadoCivil" class="form-control">
					<form:option value="Solteiro">Solteiro</form:option>
					<form:option value="Casado">Casado</form:option>
					<form:option value="Divorciado">Divorciado</form:option>
					<form:option value="Viuvo">Viúvo</form:option>
				</form:select>

				<br>
				<form:label path="nivelDoenca">Nível de Doença:</form:label>
				<form:select path="nivelDoenca" class="form-control">
					<form:option value="1">Leve</form:option>
					<form:option value="2">Moderado</form:option>
					<form:option value="3">Avançado</form:option>
				</form:select>

				<%-- 	<br>
			<form:label path="fotografia">Insira uma fotografia:</form:label>
			<form:input path="fotografia" type="file" name="pic" accept="image/*"/> --%>

				<br>
				<form:label path="nomeMedico" class="sr-only">Nome do Médico</form:label>
				<form:input path="nomeMedico" id="nomeMedico" class="form-control"
					required="required" placeholder="Nome do Médico" />
				<br>
				<form:label path="especialidadeMedico">Especialidade do Médico:</form:label>
				<form:select path="especialidadeMedico" class="form-control">
					<form:option value="Neurologista">Neurologista</form:option>
					<form:option value="Psiquiatra">Psiquiatra</form:option>
				</form:select>

				<br>
				<form:label path="nivelSessao">Nível de Sessão Inicial Esperado:</form:label>
				<form:select path="nivelSessao" class="form-control">
					<form:option value="1">1</form:option>
					<form:option value="2">2</form:option>
					<form:option value="3">3</form:option>
					<form:option value="4">4</form:option>
					<form:option value="5">5</form:option>
				</form:select>

				<br>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Inserir</button>

			</form:form>
		</div>
	</div>
<!-- </div> -->