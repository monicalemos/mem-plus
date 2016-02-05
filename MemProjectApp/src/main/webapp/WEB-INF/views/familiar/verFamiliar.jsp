<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
/* 	RelacaoPacienteFamiliar relacao = (RelacaoPacienteFamiliar) request.getAttribute("currentRelacao");
	Familiar familiar = relacao.getFamiliar(); */
	Familiar familiar = null;
	String tipoRelacao = "";
	String tipo = "";
	
	if(request.getAttribute("currentRelacao") instanceof RelacaoPacienteFamiliar){
		System.out.println("� relacao Paciente Familiar");
		RelacaoPacienteFamiliar relacao = (RelacaoPacienteFamiliar) request.getAttribute("currentRelacao");
		familiar = relacao.getFamiliar();
		tipoRelacao = relacao.getTipoRelacao();
		tipo = "relacaoPacienteFamiliar";
	}else if(request.getAttribute("currentRelacao") instanceof RelacaoFamiliarFamiliar){
		System.out.println("� relacao Familiar Familiar");
		RelacaoFamiliarFamiliar relacao = (RelacaoFamiliarFamiliar) request.getAttribute("currentRelacao");
		familiar = relacao.getFamiliar1();
		tipoRelacao = relacao.getTipoRelacao();
		tipo = "relacaoFamiliarFamiliar";
	}
	
	System.out.println("Current Familiar " + familiar);
	Integer id = familiar.getIdFamiliar(); 
	request.setAttribute("idFamiliar", id);
	System.out.println("Tem o familiar na p�gina? " + id);
	pageContext.setAttribute("tipoRelacao", tipoRelacao);
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
			<%=tipoRelacao%></p>
		<p>
			<label for="nomeCompleto"> Nome Completo:</label>
			<%=(familiar.getNomeCompleto() == null ? familiar.getApelido() + ", " + familiar.getNomeProprio()
					: familiar.getNomeCompleto())%></p>
		<p>
			<label for="nomeProprio"> Nome Pr�prio:</label>
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
				for="moradaByIdLocalNascimento.regiao"> Regi�o:</label> <%=familiar.getMoradaByIdLocalNascimento().getRegiao()%></span>
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
				for="moradaByIdMorada.regiao"> Regi�o:</label> <%=familiar.getMoradaByIdMorada().getRegiao()%></span>
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
			<label for="profissao"> Profiss�o:</label>
			<%=familiar.getProfissao()%></p>
		<p>
			<label for="ecuidador"> � cuidador?:</label>
			<%=familiar.getEcuidador() == false ? "N�o" : "Sim"%></p>
		<p>
			<label for="telefone"> Telefone:</label>
			<%=familiar.getTelefone()%></p>
	</div>
	<div>
		<form action="inserirSegundoGrauFamiliar" method="get">
			<input type="hidden" name="idFamiliar" value="<%=familiar.getIdFamiliar()%>" />
			<button type="submit">Inserir Familiar</button>
		</form>
	</div>
</div>