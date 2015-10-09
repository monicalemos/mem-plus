<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	@SuppressWarnings("unchecked")
	List<Evento> listEvento = (List<Evento>)(request.getAttribute("listEventos"));	
%>

<style>
#tableCss {
	position: fixed;
	width: 80%;
	margin-top: 10px;
	margin-left: 5px;
	margin-rigth: 5px;
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
	overflow: auto;
}

.line {
	margin-bottom: 1%;
	position: relative;
	height: 1px;
	background-color: #CCCCCC;
	border-bottom: 1px solid #CCCCCC;
}

tr:hover {
	background-color: #8888ff
}

table {
	width: 100%;
	border-collapse: collapse;
	border: 5px solid #CCCCCC;
}

th, td {
	border: 2px solid black;
}

th {
	background-color: #CCCCCC;
	height: 30px;
	color: #555;
	text-align: center;
	vertical-align: middle;
	font-size: 13px;
}

td {
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

.normal {
	background-color: #ffffff;
}

.highlight {
	background-color: red;
}

</style>

<html>

<script type="text/javascript">
function onRow(rowID) { 
	var row = document.getElementById(rowID);
	var curr = row.className;
	if(curr.indexOf("normal")>=0) row.className="highlight";
	else row.className="normal";
} 
</script>

<div id="page-wrapper">
	<h1 class="page-header">LISTAR EVENTOS</h1>
	<div class="row-fluid" id="tableCss">
		<table>
			<%			
			ArrayList<Map<String, Object>> eventos = new ArrayList<Map<String, Object>>();
			
			for (Evento e : listEvento) {
				   Map<String, Object> pac = new HashMap<String, Object>();

		        pac.put("id", e.getId().getIdEvento());
        		pac.put("data", e.getData());
         		pac.put("localEvento", e.getMorada().getPais() + " - " + e.getMorada().getRegiao() + " - " + e.getMorada().getCidade());
         		pac.put("tipoEvento", e.getTipoEvento());
         		pac.put("familiar", (e.getFamiliar()!= null ? e.getFamiliar().getNomeProprio() +  " " + e.getFamiliar().getApelido() : ""));
         		eventos.add(pac);
        		
         		pageContext.setAttribute("eventos", eventos);
			}
			%>
			<tr>
				<th>Id</th>
				<th>Data</th>
				<th>Local Evento</th>
				<th>Tipo Evento</th>
                <th>Familiar</th>
                <th>Dados do Evento</th>
			</tr>
			<c:forEach items="${eventos}" var="current">
				<tr class="normal" id="${current.id}">
					<td><c:out value="${current.id}" /></td>
					<td><c:out value="${current.data}" /></td>
					<td><c:out value="${current.localEvento}" /></td>
					<td><c:out value="${current.tipoEvento}" /></td>
					<td><c:out value="${current.familiar}" /></td>
					<td>
					  <form action="verEvento" method="get">
                            <input type="hidden" name="idEvento" value="${current.id}"/>
                            <input type="submit" value="Ver"/>
                      </form> 
                       <form action="editarEvento" method="get">
                            <input type="hidden" name="idEvento" value="${current.id}"/>
                            <input type="submit" value="Editar"/>
                      </form> 
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>