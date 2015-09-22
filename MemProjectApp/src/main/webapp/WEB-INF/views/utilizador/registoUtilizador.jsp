<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- TODO: MOVE TO CSS FILE -->
<style>
#loginForm {
	border: .2em dotted gray;
	padding: 10px;
	width: 400px;
	height: 460px;
	position: fixed;
	top: 20%;
	left: 50%;
	margin-left: -140px;
}
</style>


<div class="container">
	<div class="row-fluid" id="LoginForm">
		<form:form method="post" modelAttribute="tecnicoModel" action="registrarUtilizador">
			<h2 class="form-signin-heading">Registe-se</h2>

			<form:label path="nomeProprio" class="sr-only">Nome Próprio</form:label>
			<form:input path="nomeProprio" id="nomeProprio" class="form-control"
				required="required" placeholder="Nome Próprio"/>

			<br>
			<form:label path="apelido" class="sr-only">Apelido</form:label>
			<form:input path="apelido" id="apelido" class="form-control"
				required="required" placeholder="Apelido"/>

			<br>
			<form:label path="telefone" class="sr-only">Telefone</form:label>
			<form:input path="telefone" id="telefone" class="form-control"
				required="required"  placeholder="Telefone"/>

			 <br>
			<form:label path="utilizador.email" class="sr-only">Email</form:label>
			<form:input path="utilizador.email" id="email" class="form-control"
				required="required"  placeholder="Email"/>
			
			<br>
			<form:label path="utilizador.nomeUtilizador" class="sr-only">Nome Utilizador</form:label>
			<form:input path="utilizador.nomeUtilizador" id="nomeUtilizador"
				class="form-control" required="required"  placeholder="Telefone" />

			<br>
			<form:label path="utilizador.password" class="sr-only">Password</form:label>
			<form:password path="utilizador.password" id="password" class="form-control" required="required"  placeholder="Password"/>

			<br>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Registrar</button>
	
		</form:form>
	</div>
</div>