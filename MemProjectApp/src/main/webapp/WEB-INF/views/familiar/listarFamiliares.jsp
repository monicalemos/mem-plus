<!DOCTYPE html>

<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	@SuppressWarnings("unchecked")
	List<RelacaoPacienteFamiliar> listFamiliares = (List<RelacaoPacienteFamiliar>) (request
			.getAttribute("listFamiliares"));
%>

<link href="<c:url value="/resources/static/css/TreeListView.css" />" rel="stylesheet">

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script> 

<div id="page-wrapper">
	<h1 class="page-header">LISTAR FAMILIARES </h1>
	<%
		System.out.println("listFamiliar: " + listFamiliares.size());
		for (RelacaoPacienteFamiliar relacao1 : listFamiliares) {
			System.out.println("familiar id: " + relacao1.getFamiliar().getIdFamiliar());
			System.out.println("listFamiliar 2 : "
					+ relacao1.getFamiliar().getRelacaoFamiliarFamiliarsForIdFamiliar1().size());
		}

		pageContext.setAttribute("familiares", listFamiliares);
	%>
<c:forEach items="${familiares}" var="current" varStatus="loop">
<form id="verFamiliar" action="verFamiliar" method="get">
	<input type="hidden" name="idFamiliar" value="${current.getFamiliar().getIdFamiliar()}" />
</form>
<form id="editarFamiliar" action="editarFamiliar" method="get">
	<input type="hidden" name="idFamiliar" value="${current.getFamiliar().getIdFamiliar()}" />
</form>

<ul class="treeview">
    <li class="list"> 
    	<span class="Collapsable"> 
    		<label> 
    			<c:out value="${current.getFamiliar().getIdFamiliar()}"/> - 
    			<c:out value="${current.getFamiliar().getNomeProprio()}"/> - 
    			<c:out value="${current.getFamiliar().getApelido()}"/> - 
    			<c:out value="${current.getFamiliar().getTelefone()}"/> - 
    			<c:out value="${current.getFamiliar().getDataNascimento()}"/> - 
    			<c:out value="${current.getFamiliar().getProfissao()}"/> - 
    			<c:out value="${current.getTipoRelacao()}"/>
    		</label> 
    	</span>
		<div class="hiddenDiv">
			<ul class="treeviewSub">
				<li class="buttons"> 
					<span class="Collapsable"> 
						<button form="verFamiliar" type="submit" value="Ver">Ver</button>
						<button form="editarFamiliar" type="submit" value="editar">Editar</button>
					</span>
				</li>
				<c:forEach items="${current.getFamiliar().getRelacaoFamiliarFamiliarsForIdFamiliar1()}" var="grau" varStatus="innerLoop">  
				<form id="verSegundoGrauFamiliar" action="verSegundoGrauFamiliar" method="post">
					<%-- <input type="hidden" name="idFamiliarSegundoGrau" value="${grau.getFamiliarByIdFamiliar1().getIdFamiliar()}" /> --%> 
				</form>
				<li class="row">
					<span class="Collapsable"> 
						<label> 
							<c:out value="${grau.getFamiliarByIdFamiliar1().getIdFamiliar()}"/> - 
							<c:out value="${grau.getFamiliarByIdFamiliar1().getNomeProprio()}"/> - 
							<c:out value="${grau.getFamiliarByIdFamiliar1().getApelido()}"/> - 
							<c:out value="${grau.getFamiliarByIdFamiliar1().getTelefone()}"/> - 
							<c:out value="${grau.getFamiliarByIdFamiliar1().getDataNascimento()}"/> - 
							<c:out value="${grau.getFamiliarByIdFamiliar1().getProfissao()}"/> -
							<c:out value="${grau.getTipoRelacao()}" /> 
						</label> 
					</span>
				<div  class="hiddenDiv">
					<ul>
						<li class="subButtons"> 
							<span class="Collapsable"> 
								<button form="verSegundoGrauFamiliar" type="submit" name='idFamiliarSegundoGrau' value="${grau.getFamiliarByIdFamiliar1().getIdFamiliar()}" >Ver</button>
								<button form="editarFamiliar" type="submit" value="editar">Editar</button>
							</span>
						</li>
					</ul>
				</div>
				</li>
				</c:forEach>
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
