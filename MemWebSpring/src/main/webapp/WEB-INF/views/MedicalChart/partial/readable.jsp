<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- <%@ page session="false"%> --%>
<fieldset>
	<div class="control-group">
		<label for="firstName" class="control-label">Nome:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${pacientModel.firstName}" /> <form:hidden path="firstName" /></span>
		</div>
		<label for="middleName" class="control-label">Nome do Meio:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${pacientModel.middleName}" /> <form:hidden path="middleName" /></span>
		</div>
		<label for="lastName" class="control-label">Apelido:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${pacientModel.lastName}" /> <form:hidden path="lastName" /></span>
		</div>
	</div>		
	
	<div class="control-group">
		<label for="dateOfBirth" class="control-label">Data de Nascimento:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${pacientModel.dateOfBirth}" /> <form:hidden path="dateOfBirth" /></span>
		</div>
	</div>
	<div class="control-group">
		<label for="gender" class="control-label">Género:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${pacientModel.gender}" /> <form:hidden path="gender" /></span>
		</div>
	</div>
	<div class="control-group">
		<label for="maritalStatus" class="control-label">Estado Civil:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${pacientModel.maritalStatus}" /> <form:hidden path="maritalStatus" /></span>
		</div>
	</div>
	<div class="control-group">
		<label for="profession" class="control-label">Profissão:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${pacientModel.profession}" /> <form:hidden path="profession" /></span>
		</div>
	</div>
</fieldset>

<fieldset>
	<div class="control-group">
		<label for="address.country" class="control-label">País:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${pacientModel.address.country}" /> <form:hidden path="address.country" /></span>
		</div>
		<label for="address.region" class="control-label">Região:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${pacientModel.address.region}" /> <form:hidden path="address.region" /></span>
		</div>
		<label for="address.city" class="control-label">Cidade:</label>
		<div class="controls">
		<span class="input-sm uneditable-input"><c:out value="${pacientModel.address.city}" /> <form:hidden path="address.city" /></span>
		</div>
		
	</div>

</fieldset>