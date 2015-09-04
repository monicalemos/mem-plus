<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Tecnico tecnico = (Tecnico) request.getAttribute("currentTecnico");
%>

<div id="page-wrapper">
	<h1 class="page-header">LISTAR PACIENTES</h1>
	<table>
		<%
			System.out.println("continua c tecnico " + tecnico);
			ArrayList<Paciente> list = new ArrayList<Paciente>();
			if (tecnico.getPacientes() != null) {
				list = (ArrayList<Paciente>) tecnico.getPacientes();
			}

			ArrayList<Map<String, Object>> paciente = new ArrayList<Map<String, Object>>();
			for (Paciente p : list) {
				Map<String, Object> pac = new HashMap<String, Object>();
				pac.put("id", p.getIdPaciente());
				pac.put("nomeProprio", p.getNomeProprio());
				pac.put("apelido", p.getApelido());
				pac.put("nivelDoenca", p.getNivelDoenca());
				pac.put("nivelSessao", p.getNivelSessao());
				paciente.add(pac);
			}
			pageContext.setAttribute("pacientes", paciente);
		%>
		<tr>
			<th>Id</th>
			<th>Nome Paciente</th>
			<th>Apelido</th>
			<th>Nível de Doença</th>
			<th>Nível de Sessão</th>
			<th></th>
		</tr>
		<c:forEach items="${pacientes}" var="current">
			<tr>
				<td><c:out value="${current.id}" /></td>
				<td><c:out value="${current.nomeProprio}" /></td>
				<td><c:out value="${current.apelido}" /></td>
				<td><c:out value="${current.nivelDoenca}" /></td>
				<td><c:out value="${current.nivelSessao}" /></td>
				<td>
			</tr>
		</c:forEach>
	</table>
</div>