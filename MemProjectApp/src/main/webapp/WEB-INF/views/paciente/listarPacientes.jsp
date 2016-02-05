<!DOCTYPE html>

<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Tecnico tecnico = (Tecnico) request.getAttribute("currentTecnico");
%>

<link href="<c:url value="/resources/static/css/TreeListView.css" />" rel="stylesheet">

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script> 


<div id="page-wrapper">
	<h1 class="page-header">LISTAR PACIENTES</h1>
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
		pageContext.setAttribute("numPacientes", paciente.size());
	%>
<c:set var="numPac" scope = "session" value="${numPacientes}"/>
<c:if test="${numPac > 0 }">
	<c:forEach items="${pacientes}" var="current">
	<form id="editarPaciente" action="editarPaciente" method="post">
		<input type="hidden" name="idPaciente" value="${current.id}"/>
	</form>
	<ul class="treeview">
    <li class="list"> 
    	<form id="verPaciente" action="verPaciente" method="post">
    		<%-- <input type="hidden" name="idPaciente" value="${current.id}"/> --%>
		</form>
    	<span class="Collapsable"> 
    		<label> 
    			<c:out value="${current.id}" /> - 
				<c:out value="${current.nomeProprio}"/> - 
				<c:out value="${current.apelido}" /> - 
				<c:out value="${current.nivelDoenca}"/> - 
				<c:out value="${current.nivelSessao}"/>
    		</label> 
    	</span>
		<div class="hiddenDiv">
			<ul class="treeviewSub">
				<li class="buttons"> 
					<span class="Collapsable"> 
						<button form="verPaciente" type="submit" name='idPaciente' value="${current.id}" >Ver</button>
						<button form="editarPaciente" type="submit" value="editar">Editar</button>
					</span>
				</li>
			</ul>
		</div>
    </li>
</ul>	
</c:forEach>
</c:if>
<c:if test="${numPac == 0 }">
	<h4> Não há pacientes para apresentar.</h4>
</c:if>
</div>

<script type="text/javascript">
	$(".hiddenDiv").hide();
    $(".Collapsable").click(function () {
        $(this).parent().children().toggle();
        $(this).toggle();
        
      /*   alert("Class before: " + $(this).parent().parent().attr('class'));     */
        if($(this).parent().parent().attr('class') == "treeview"){
      		$(this).parent().parent().removeClass("treeview"); 
        	$(this).parent().parent().toggleClass("treeviewActive");
        }
        else if($(this).parent().parent().attr('class') == "treeviewActive"){
        	$(this).parent().parent().removeClass("treeviewActive");
        	$(this).parent().parent().toggleClass("treeview");
        }
       /*  alert("Class after: " + $(this).parent().parent().attr('class')) */
        
       /*  alert("Class sub before: " + $(this).parent().attr('class')) */
        if($(this).parent().parent().attr('class') == "treeviewSub"){
            if($(this).parent().attr('class') == "row"){
            	$(this).parent().removeClass("row");
        		$(this).parent().toggleClass("rowActive");
            }
            else if($(this).parent().attr('class') == "rowActive"){
            	$(this).parent().removeClass("rowActive");
        		$(this).parent().toggleClass("row");
            }
        }
   /*      alert("Class sub after: " + $(this).parent().attr('class')) */
    });
</script>