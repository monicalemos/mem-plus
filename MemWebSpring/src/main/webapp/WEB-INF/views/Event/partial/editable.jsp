<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- <%@ page session="false"%> --%>
<fieldset>
	<div class="control-group">
		<div class="controls"><form:hidden path="id" /></div>
		<div class="controls"><form:hidden path="relatedToPersonId" /></div>
	</div>
	<div class="control-group">
		<label for="title" class="control-label">Titulo:</label>
		<div class="controls">
			<form:input path="title" cssClass="input-sm focused" id="title"/>
			<font color="red"><form:errors path="title" /></font><br/>
		</div>
		<label for="description" class="control-label">Descrição:</label>
		<div class="controls">
			<form:input path="description" cssClass="input-sm focused" id="description"/>
			<font color="red"><form:errors path="description" /></font><br/>
		</div>
		<label for="dateOfOccurance" class="control-label">Apelido:</label>
		<div class="controls">
			<form:input path="dateOfOccurance" cssClass="input-sm focused" id="dateOfOccurance"/>
			<font color="red"><form:errors path="dateOfOccurance" /></font><br/>
		</div>
	</div>		
</fieldset>