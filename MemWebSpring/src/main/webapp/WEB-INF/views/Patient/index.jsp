<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath()+"/resources/static/img";
	String p16  = path + "/16x16";
	String p24  = path + "/24x24";
	String editImg = p16+"/Actions-document-edit-icon.png";
	String deleteImg = p16+"/File-Delete-icon.png";
	String newImg = p16 + "/File-New-icon.png";
	String viewImg = p16+"/Document-icon.png";
%>
<h1>INDEX</h1>


<div class="bs-example" >
		<c:if test="${fn:length(patientModelCollection) > 0}">
		<table class="table" style="position:relative; width:80%;">
			<thead align="center">
				<tr>
					<th colspan="3"><a class="brand"
						href="Patient/New">							
						<img
							src="<%=newImg%>"
							alt="Add Record" title="Add Record" />
					</a></th>
					<th>Primeiro Nome</th>
					<th>Ultimo Nome</th>
					<th>Data de nascimento</th>
					<th>Relatives</th>
					<th>Events</th>
				</tr>
			</thead>
			<c:forEach var="prod" items="${patientModelCollection}">
			<tr>
					<td width="16px">
						<a class="brand" href="Patient/View?id=<c:out value="${prod.id}" />"> 
							<img src="<%=viewImg%>" alt="View Record" title="View Record" />
						</a>
					</td>
					<td width="16px">
						<a class="brand" href="Patient/Edit?id=<c:out value="${prod.id}" />"> 
							<img src="<%=editImg%>" alt="Edit Record" title="Edit Record" />
						</a>
					</td>
					<td width="16px">
						<a class="brand" href="Patient/Delete?id=<c:out value="${prod.id}" />"> 
							<img src="<%=deleteImg%>" alt="Delete Record" title="Delete Record" />
						</a>
					</td>
					<td><c:out value="${prod.firstName}" /></td>
					<td><c:out value="${prod.lastName}" /></td>
					<td><c:out value="${prod.dateOfBirth}" /></td>
					<td><a class="brand" href="Patient/Relative?patientId=<c:out value="${prod.id}" />">Relatives </td>
					<td><a class="brand" href="Patient/Event?personId=<c:out value="${prod.id}" />">Events</td>
					
			</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>