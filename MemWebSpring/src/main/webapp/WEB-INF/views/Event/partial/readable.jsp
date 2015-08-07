<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- <%@ page session="false"%> --%>
<fieldset>
	<div class="control-group">
		<label for="title" class="control-label">Titulo:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${eventModel.title}" /> 
		<form:hidden path="title" /></span>
		</div>
		<label for="description" class="control-label">Descrição:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${eventModel.description}" /> 
		<form:hidden path="description" /></span>
		</div>
		<label for="dateOfOccurance" class="control-label">Data de Ocorrência:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${eventModel.dateOfOccurance}" /> 
		<form:hidden path="dateOfOccurance" /></span>
		</div>
	</div>		
</fieldset>