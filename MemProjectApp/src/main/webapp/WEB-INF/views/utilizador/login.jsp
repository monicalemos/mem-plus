<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- TODO: MOVE TO CSS FILE -->
<style>
#loginForm {
	border: .2em dotted gray;
	padding: 10px;
	width: 280px;
	height: 260px;
	position: fixed;
	top: 50%;
	left: 50%;
	margin-top: -350px;
	margin-left: -140px;
}

#login-error {
	border: .2em dotted red;
	padding: 10px;
	width: 280px;
	position: fixed;
	top: 78%;
	left: 50%;
	margin-left: -140px;
}
</style>


<div class="container">
		<div class="row-fluid" id="LoginForm">
		
			<form:form class="form-signin" role="form" method="post" modelAttribute="utilizadorModel" action="login">
				<h2 class="form-signin-heading">Please sign in</h2>
				
			<form:label path="nomeUtilizador" class="sr-only">Nome Utilizador</form:label>
			<form:input path="nomeUtilizador" id="nomeUtilizador"
				class="form-control" required="required"  placeholder="Telefone" />

			<br>
			<form:label path="password" class="sr-only">Password</form:label>
			<form:password path="password" id="password" class="form-control" required="required"  placeholder="Password"/>
					
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me"> Remember me </label>
			</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			
			<br>
			<spring:url value="/Utilizador/registrarUtilizador" var="registrarUtilizadorUrl" htmlEscape="true" /> 
			<a href="${registrarUtilizadorUrl}">Registrar no site</a>
			
			</form:form>
		</div>
		<c:if test="${not empty param.error}">
			<div id="login-error">
				<font color="red"> <c:out
						value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
				</font>

			</div>
		</c:if>
</div>