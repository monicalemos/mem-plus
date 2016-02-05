<!DOCTYPE html>
<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
%>

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
	<h1 class="page-header">Dados do Paciente</h1>
	<div class="newspaper">
		<%-- <p> <label for="idPaciente"> idPaciente:</label> <%=paciente.getIdPaciente()%></p> --%> 
		<p> <label for="nomeCompleto"> Nome Completo:</label> <%=paciente.getApelido() + ", " + paciente.getNomeProprio()%></p> 
		<p> <label for="nomeProprio"> Nome Próprio:</label> <%=paciente.getNomeProprio()%></p> 
		<p> <label for="apelido"> Apelido:</label> <%=paciente.getApelido()%></p> 
		<p> <label for="dataNascimento"> Data de Nascimento:</label> <fmt:formatDate pattern="dd-MM-yyyy" value="<%=paciente.getDataNascimento()%>"/></p>
		<p> <label for="genero"> Genero:</label> <%=paciente.getGenero()%></p>
	   	<p> <label for="profissao"> Profissão:</label> <%=paciente.getProfissao()%></p>
	   	
		<p> <label for="moradaByIdLocalNascimento"> Local de Nascimento:</label></p>
		<p> <span style="padding-left:2em"> <label for="moradaByIdLocalNascimento.pais"> Pais:</label> <%=paciente.getMoradaByIdLocalNascimento().getPais()%></span></p>
		<p> <span style="padding-left:2em"> <label for="moradaByIdLocalNascimento.regiao"> Região:</label> <%=paciente.getMoradaByIdLocalNascimento().getRegiao()%></span></p>
		<p> <span style="padding-left:2em"> <label for="moradaByIdLocalNascimento.cidade"> Cidade:</label> <%=paciente.getMoradaByIdLocalNascimento().getCidade()%></span></p>
		
		<p> <label for="moradaByIdMorada"> Morada:</label></p>
		<p> <span style="padding-left:2em"> <label for="moradaByIdMorada.pais"> Pais:</label> <%=paciente.getMoradaByIdMorada().getPais()%></span></p>
		<p> <span style="padding-left:2em"> <label for="moradaByIdMorada.regiao"> Região:</label> <%=paciente.getMoradaByIdMorada().getRegiao()%></span></p>
		<p> <span style="padding-left:2em"> <label for="moradaByIdMorada.cidade"> Cidade:</label> <%=paciente.getMoradaByIdMorada().getCidade()%></span></p>	
	
	   	<p> <label for="escolaridade"> Escolaridade:</label> <%=paciente.getEscolaridade()%></p>
	   	<p> <label for="estadoCivil"> EstadoCivil:</label> <%=paciente.getEstadoCivil()%></p>
	   	<p> <label for="nivelDoenca"> Nível de Doença:</label> <%=paciente.getNivelDoenca()%></p>
	   	<p> <label for="nomeMedico"> Nome do Médico:</label> <%=paciente.getNomeMedico()%></p>
	   	<p> <label for="especialidadeMedico"> Especialidade do Médico:</label> <%=paciente.getEspecialidadeMedico()%></p>
	   	<p> <label for="nivelSessao"> Nível de Sessão:</label> <%=paciente.getNivelSessao()%></p>
   	 </div>    
</div>