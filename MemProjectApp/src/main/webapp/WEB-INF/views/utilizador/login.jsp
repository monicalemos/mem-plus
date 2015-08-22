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
	margin-top: -130px;
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
			<form class="form-signin" role="form" action="login" method="post">
				<h2 class="form-signin-heading">Please sign in</h2>
				
				<label for="j_username" class="sr-only">Username</label>
				<input
					id="j_username" name="j_username" type="text" class="form-control"
					placeholder="username" required="required" autofocus> 
				
				<label for="j_password" class="sr-only">Password</label>
				<input
					id="j_password" name="j_password" type="password" class="form-control"
					placeholder="password" required="required">
					
				<div class="checkbox">
					<label> <input type="checkbox" value="remember-me">
						Remember me
					</label>
				</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
					in</button>
			</form>
		</div>
		<c:if test="${not empty param.error}">
			<div id="login-error">
				<font color="red"> <c:out
						value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
				</font>

			</div>
		</c:if>
</div>