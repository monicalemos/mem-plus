<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<header>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"> 
				<img alt="Brand" src="${pageContext.request.contextPath}/resources/static/img/logo.png">
				</a>
				
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right container-fluid">
					<li class="active"> 
						<spring:url value="/" var="homeUrl" htmlEscape="true" /> 
						<a href="${homeUrl}">Home</a>
					</li>
					<li>
						<spring:url value="/Sobre" var="sobreUrl" htmlEscape="true" /> 
						<a href="${sobreUrl}">Sobre nós</a>
					</li>
					<li>
						<spring:url value="/Contactos" var="contactosUrl" htmlEscape="true" /> 
						<a href="${contactosUrl}">Contactos</a>
					</li>
					<li>
					<spring:url value="/Utilizador/login" var="loginUrl" htmlEscape="true" /> 
						<a href="${loginUrl}">Login</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</header>