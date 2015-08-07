<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- <%@ page session="false"%> --%>
<fieldset>
	<div class="control-group">
		<label for="firstName" class="control-label">Nome:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${doctorModel.firstName}" /> <form:hidden path="firstName" /></span>
		</div>
		<label for="middleName" class="control-label">Nome do Meio:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${doctorModel.middleName}" /> <form:hidden path="middleName" /></span>
		</div>
		<label for="lastName" class="control-label">Apelido:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${doctorModel.lastName}" /> <form:hidden path="lastName" /></span>
		</div>
	</div>		
	
	<div class="control-group">
		<label for="dateOfBirth" class="control-label">Data de Nascimento:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${doctorModel.dateOfBirth}" /> <form:hidden path="dateOfBirth" /></span>
		</div>
	</div>
	<div class="control-group">
		<label for="gender" class="control-label">Género:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${doctorModel.gender}" /> <form:hidden path="gender" /></span>
		</div>
	</div>
	<div class="control-group">
		<label for="maritalStatus" class="control-label">Estado Civil:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${doctorModel.maritalStatus}" /> <form:hidden path="maritalStatus" /></span>
		</div>
	</div>
	<div class="control-group">
		<label for="profession" class="control-label">Profissão:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${doctorModel.profession}" /> <form:hidden path="profession" /></span>
		</div>
	</div>
</fieldset>