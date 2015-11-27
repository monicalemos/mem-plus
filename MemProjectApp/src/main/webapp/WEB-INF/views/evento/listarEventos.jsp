<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	@SuppressWarnings("unchecked")
	List<Evento> listEvento = (List<Evento>)(request.getAttribute("listEventos"));	
%>

<div id="page-wrapper">
	<h1 class="page-header">LISTAR EVENTOS</h1>

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
	<c:forEach items="${eventos}" var="current">
	<form id="verEvento" action="verEvento" method="get">
   		<input type="hidden" name="idEvento" value="${current.id}"/>
   	</form> 
   	<form id="editarEvento" action="editarEvento" method="get">
		<input type="hidden" name="idEvento" value="${current.id}"/>
	</form> 
	<ul class="treeview">
    <li class="list"> 
    	<span class="Collapsable"> 
    		<label> 
    			<c:out value="${current.id}" /> - 
				<c:out value="${current.data}" /> - 
				<c:out value="${current.localEvento}"/> - 
				<c:out value="${current.tipoEvento}"/> - 
				<c:out value="${current.familiar}"/>
			</label> 
    	</span>
		<div class="hiddenDiv">
			<ul class="treeviewSub">
				<li class="buttons"> 
					<span class="Collapsable"> 
						<button form="verEvento" type="submit" value="Ver">Ver</button>
						<button form="editarEvento" type="submit" value="editar">Editar</button>
					</span>
				</li>
			</ul>
		</div>
    </li>
</ul>
				
</c:forEach>

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