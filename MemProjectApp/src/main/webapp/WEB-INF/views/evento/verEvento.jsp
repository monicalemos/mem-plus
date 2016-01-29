<!DOCTYPE html>
<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	Evento evento = (Evento)request.getAttribute("currentEvento");
	Familiar familiar = evento.getFamiliar();
	
	String familiarNome = session.getAttribute("familiarEvento").toString();
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
	<h1 class="page-header">Dados do Evento</h1>
	<div class="newspaper">
		<p> <label for="data">Data do Evento:</label> <fmt:formatDate pattern="dd-MM-yyyy" value="<%=evento.getData()%>"/></p>
		<p> <label for="morada">Local do Evento:</label></p>
		<p> <span style="padding-left:2em"> <label for="morada.pais"> Pais:</label> <%=evento.getMorada().getPais()%></span></p>
		<p> <span style="padding-left:2em"> <label for="moradao.regiao"> Região:</label> <%=evento.getMorada().getRegiao()%></span></p>
		<p> <span style="padding-left:2em"> <label for="morada.cidade"> Cidade:</label> <%=evento.getMorada().getCidade()%></span></p>
		<p> <label for="tipoEvento">Tipo de Evento:</label> <%=evento.getTipoEvento()%></p> 
		<p> <label for="descricao">Descricao:</label> <%=evento.getDescricao()%></p> 
		<p> <label for="familiar">Familiar Envolvido:</label> <%=(familiar == null ? "Nenhum" : (familiar.getNomeProprio() != null ? familiar.getNomeProprio() +  " " + familiar.getApelido() : familiarNome))%></p>
	</div>
   	 <form action="editarEvento" method="get">
         <input type="hidden" name="idEvento" value="<%=evento.getId().getIdEvento()%>"/>
         <button type="submit">Editar</button>
     </form>   	
</div>