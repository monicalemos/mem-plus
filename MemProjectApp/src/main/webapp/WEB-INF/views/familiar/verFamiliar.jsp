<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	RelacaoPacienteFamiliar relacao = (RelacaoPacienteFamiliar) request.getAttribute("currentRelacao");
	Familiar familiar = relacao.getFamiliar();

	@SuppressWarnings("unchecked")
	HashMap<String, Familiar> listFamiliar = (HashMap<String, Familiar>) (session
			.getAttribute("listFamiliares"));

	System.out.println("Current Familiar " + familiar);
%>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<style>
.newspaper {
    -webkit-column-count: 2; /* Chrome, Safari, Opera */
    -moz-column-count: 2; /* Firefox */
    column-count: 2;
    -webkit-column-gap: 10px; /* Chrome, Safari, Opera */
    -moz-column-gap: 10px; /* Firefox */
    column-gap: 10px;
}
</style>
<div id="page-wrapper">
	<h1 class="page-header">Dados do Familiar</h1>
	<div  class="newspaper">
		<p>
			<label for="Partentesco:"> Parentesco com Paciente:</label>
			<%=relacao.getTipoRelacao()%></p>
		<p>
			<label for="nomeCompleto"> Nome Completo:</label>
			<%=(familiar.getNomeCompleto() == null ? familiar.getApelido() + ", " + familiar.getNomeProprio()
					: familiar.getNomeCompleto())%></p>
		<p>
			<label for="nomeProprio"> Nome Próprio:</label>
			<%=familiar.getNomeProprio()%></p>
		<p>
			<label for="apelido"> Apelido:</label>
			<%=familiar.getApelido()%></p>
		<p>
			<label for="dataNascimento"> Data de Nascimento:</label>
			<fmt:formatDate pattern="dd-MM-yyyy" value="<%=familiar.getDataNascimento()%>"/></p>

		<p>
			<label for="moradaByIdLocalNascimento"> Local de Nascimento:</label>
		</p>
		<p>
			<span style="padding-left: 2em"> <label
				for="moradaByIdLocalNascimento.pais"> Pais:</label> <%=familiar.getMoradaByIdLocalNascimento().getPais()%></span>
		</p>
		<p>
			<span style="padding-left: 2em"> <label
				for="moradaByIdLocalNascimento.regiao"> Região:</label> <%=familiar.getMoradaByIdLocalNascimento().getRegiao()%></span>
		</p>
		<p>
			<span style="padding-left: 2em"> <label
				for="moradaByIdLocalNascimento.cidade"> Cidade:</label> <%=familiar.getMoradaByIdLocalNascimento().getCidade()%></span>
		</p>

		<p>
			<label for="moradaByIdMorada"> Morada:</label>
		</p>
		<p>
			<span style="padding-left: 2em"> <label
				for="moradaByIdMorada.pais"> Pais:</label> <%=familiar.getMoradaByIdMorada().getPais()%></span>
		</p>
		<p>
			<span style="padding-left: 2em"> <label
				for="moradaByIdMorada.regiao"> Região:</label> <%=familiar.getMoradaByIdMorada().getRegiao()%></span>
		</p>
		<p>
			<span style="padding-left: 2em"> <label
				for="moradaByIdMorada.cidade"> Cidade:</label> <%=familiar.getMoradaByIdMorada().getCidade()%></span>
		</p>

		<p>
			<label for="genero"> Genero:</label>
			<%=familiar.getGenero()%></p>
		<p>
			<label for="estadoCivil"> Estado Civil:</label>
			<%=familiar.getEstadoCivil()%></p>
		<p>
			<label for="profissao"> Profissão:</label>
			<%=familiar.getProfissao()%></p>
		<p>
			<label for="ecuidador"> É cuidador?:</label>
			<%=familiar.getEcuidador() == false ? "Não" : "Sim"%></p>
		<p>
			<label for="telefone"> Telefone:</label>
			<%=familiar.getTelefone()%></p>
	</div>
	<div>
		<form action="editarFamiliar" method="get">
			<input type="hidden" name="idFamiliar"
				value="<%=familiar.getIdFamiliar()%>" />
			<button type="submit">Editar</button>
		</form>
		<form action="inserirSegundoGrauFamiliar" method="get">
			<input type="hidden" name="idFamiliar"
				value="<%=familiar.getIdFamiliar()%>" />
			<button type="submit">Inserir Familiar</button>
		</form>
	</div>

</div>