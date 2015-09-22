<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Tecnico tecnico = (Tecnico) request.getAttribute("currentTecnico");
%>

<style>
#container {
	position: absolute;
	width:75%;
	margin-top: 10px;
	margin-left: 5px;
	margin-right: 5px;
	background: #fff;
	border-radius: 5px;
	border: 1px solid #ccc;
	box-shadow: 0 1px 2px rgba(0, 0, 0, .1);
	-webkit-animation-name: bounceIn;
	-webkit-animation-fill-mode: both;
	-webkit-animation-duration: 1s;
	-webkit-animation-iteration-count: 1;
	-webkit-animation-timing-function: linear;
	-moz-animation-name: bounceIn;
	-moz-animation-fill-mode: both;
	-moz-animation-duration: 1s;
	-moz-animation-iteration-count: 1;
	-moz-animation-timing-function: linear;
	animation-name: bounceIn;
	animation-fill-mode: both;
	animation-duration: 1s;
	animation-iteration-count: 1;
	animation-timing-function: linear;
}
.line {
	margin-bottom: 1%;
	position: relative;
	height: 1px;
	background-color: #CCCCCC;
	border-bottom: 1px solid #CCCCCC;
}
table{
	width: 100%;
	border-collapse: collapse;
	border:5px solid #CCCCCC;
}

th, td {
    border: 2px solid black;
}
th{
	background-color: #CCCCCC;
	height: 30px;
	color: #555;
	text-align:center;
	vertical-align: middle;
	font-size: 13px;
}
td{
	height: 25px;
	color: #555;
	margin-left: 18px;
	font-size: 12px;
	text-align: center;
	vertical-align: middle;
}


label {
	color: #555;
	display: inline-block;
	margin-left: 18px;
	padding-top: 10px;
	font-size: 14px;
}

</style>

<div id="page-wrapper">
	<h1 class="page-header">LISTAR PACIENTES</h1>
	<div id="container">
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
</div>