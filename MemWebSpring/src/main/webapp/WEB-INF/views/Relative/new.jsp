<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div>
	<div class="container-fluid">
		<div>
			<h1>New Record</h1>
			<a class="brand"
				href="<%=request.getContextPath()%>/Patient/Relative"> <img
				src="<%=request.getContextPath()%>/resources/static/img/24x24/go-back-icon.png"
				alt="Go Back" title="Go Back" />
			</a>
		</div>
		<div class="row-fluid">

			<form:form action="Create" modelAttribute="relativeModel">
				<%@include file="partial/editable.jsp"%>
				<input type="submit" value="Submit" />
			</form:form>

		</div>
	</div>
</div>