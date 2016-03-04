<!DOCTYPE html>

<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	/* List<?> listFamiliares = (List<?>) (request
			.getAttribute("listFamiliares"));
	 List<RelacaoPacienteFamiliar> listFamiliaresPF = null;
	 List<RelacaoFamiliarFamiliar> listFamiliaresFF = null;
	System.out.println("listFamiliar JSP" + listFamiliares.size());
	
	Familiar familiar = null;
	String tipoRelacao = "";
	
	if(listFamiliares.size() > 0){
		if(listFamiliares.get(0) instanceof RelacaoPacienteFamiliar){
			System.out.println("É lista de relacao Paciente Familiar");
			tipoRelacao = "relacaoPacienteFamiliar";
			listFamiliaresPF = (List<RelacaoPacienteFamiliar>) (request.getAttribute("listFamiliares"));
		}else if(listFamiliares.get(0) instanceof RelacaoFamiliarFamiliar){
			System.out.println("É liista de relacao Familiar Familiar");
			tipoRelacao = "relacaoFamiliarFamiliar";
			listFamiliaresFF = (List<RelacaoFamiliarFamiliar>) (request.getAttribute("listFamiliares"));
		}
	}
	pageContext.setAttribute("tipoRelacao", tipoRelacao); */
	@SuppressWarnings("unchecked")
	List<RelacaoFamiliarFamiliar> listFamiliares = (List<RelacaoFamiliarFamiliar>) (request
			.getAttribute("listFamiliares"));
	// List<RelacaoPacienteFamiliar> listFamiliaresPF = null;
	// List<RelacaoFamiliarFamiliar> listFamiliaresFF = null;
	//System.out.println("listFamiliar JSP" + listFamiliares.size());
	
	//Familiar familiar = null;
	//String tipoRelacao = "";
	
/* 	if(listFamiliares.size() > 0){
		if(listFamiliares.get(0) instanceof RelacaoPacienteFamiliar){
			System.out.println("É lista de relacao Paciente Familiar");
			tipoRelacao = "relacaoPacienteFamiliar";
			listFamiliaresPF = (List<RelacaoPacienteFamiliar>) (request.getAttribute("listFamiliares"));
		}else if(listFamiliares.get(0) instanceof RelacaoFamiliarFamiliar){
			System.out.println("É liista de relacao Familiar Familiar");
			tipoRelacao = "relacaoFamiliarFamiliar";
			listFamiliaresFF = (List<RelacaoFamiliarFamiliar>) (request.getAttribute("listFamiliares"));
		}
	} 
	pageContext.setAttribute("tipoRelacao", tipoRelacao);
	*/
	pageContext.setAttribute("familiares", listFamiliares);
	pageContext.setAttribute("numFamiliares", listFamiliares.size());
%>

<link href="<c:url value="/resources/static/css/TreeListView.css" />" rel="stylesheet">

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script> 

<div id="page-wrapper">
	<h1 class="page-header">LISTAR FAMILIARES </h1>
<%-- 	<%
		if(null != listFamiliaresFF){
			pageContext.setAttribute("familiares", listFamiliaresFF);
			pageContext.setAttribute("numFamiliares", listFamiliaresFF.size());
		}
		else if(null != listFamiliaresPF){
			pageContext.setAttribute("familiares", listFamiliaresPF);
			pageContext.setAttribute("numFamiliares", listFamiliaresPF.size());
		}
		else{
			pageContext.setAttribute("numFamiliares", 0);
		} 
	%> --%>
<c:set var="numFam" scope = "session" value="${numFamiliares}"/>
<c:if test="${numFam > 0 }">
	<%-- <c:set var="tipoRelacao" scope = "session" value="${tipoRelacao}"/>
	<c:if test="${tipoRelacao == 'relacaoPacienteFamiliar'}"> --%>
		<c:forEach items="${familiares}" var="current" varStatus="loop">
		<form id="verFamiliar" action="verFamiliar" method="get">
			<%-- <input type="hidden" name="idFamiliar" value="${current.getFamiliar().getIdFamiliar()}" /> --%>
		</form>
		<form id="editarFamiliar" action="editarFamiliar" method="get">
			<%-- <input type="hidden" name="idFamiliar" value="${current.getFamiliar().getIdFamiliar()}" /> --%>
		</form>
			<ul class="treeview">
			    <li class="list"> 
			    	<span class="Collapsable"> 
			    		<label> 
			    			<c:out value="${current.getFamiliar1().getIdFamiliar()}"/> - 
			    			<c:out value="${current.getFamiliar1().getNomeProprio()}"/> - 
			    			<c:out value="${current.getFamiliar1().getApelido()}"/> - 
			    			<c:out value="${current.getFamiliar1().getTelefone()}"/> - 
			    			<c:out value="${current.getFamiliar1().getDataNascimento()}"/> - 
			    			<c:out value="${current.getFamiliar1().getProfissao()}"/> - 
			    			<c:out value="${current.getTipoRelacao()}"/>
			    		</label> 
			    	</span>
					<div class="hiddenDiv">
						<ul class="treeviewSub">
							<li class="buttons"> 
								<span class="Collapsable"> 
									<button form="verFamiliar" type="submit" value="${current.getFamiliar1().getIdFamiliar()}" name='idFamiliar' >Ver</button>
									<button form="editarFamiliar" type="submit" value="${current.getFamiliar1().getIdFamiliar()}" name='idFamiliar'>Editar</button>
								</span>
							</li>
							<c:forEach items="${current.getFamiliar1().getRelacaoFamiliarFamiliarsForIdFamiliar1()}" var="grau" varStatus="innerLoop">  
							<form id="verSegundoGrauFamiliar" action="verSegundoGrauFamiliar" method="post">
							</form>
							<li class="row">
								<span class="Collapsable"> 
									<label> 
										<c:out value="${grau.getFamiliar1().getIdFamiliar()}"/> - 
										<c:out value="${grau.getFamiliar1().getNomeProprio()}"/> - 
										<c:out value="${grau.getFamiliar1().getApelido()}"/> - 
										<c:out value="${grau.getFamiliar1().getTelefone()}"/> - 
										<c:out value="${grau.getFamiliar1().getDataNascimento()}"/> - 
										<c:out value="${grau.getFamiliar1().getProfissao()}"/> -
										<c:out value="${grau.getTipoRelacao()}" /> 
									</label> 
								</span>
							<div  class="hiddenDiv">
								<ul>
									<li class="subButtons"> 
										<span class="Collapsable"> 
										
											<button form="verFamiliar" type="submit" name='idFamiliar' value="${grau.getFamiliar1().getIdFamiliar()}" >Ver</button>
											<button form="editarFamiliar" type="submit" name='idFamiliar' value="${grau.getFamiliar1().getIdFamiliar()}">Editar</button>
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
	</c:if>
	<%-- <c:if test="${tipoRelacao == 'relacaoFamiliarFamiliar'}">
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
			    			<c:out value="${current.getFamiliar1().getIdFamiliar()}"/> - 
			    			<c:out value="${current.getFamiliar1().getNomeProprio()}"/> - 
			    			<c:out value="${current.getFamiliar1().getApelido()}"/> - 
			    			<c:out value="${current.getFamiliar1().getTelefone()}"/> - 
			    			<c:out value="${current.getFamiliar1().getDataNascimento()}"/> - 
			    			<c:out value="${current.getFamiliar1().getProfissao()}"/> - 
			    			<c:out value="${current.getTipoRelacao()}"/>
			    		</label> 
			    	</span>
					<div class="hiddenDiv">
						<ul class="treeviewSub">
							<li class="buttons"> 
								<span class="Collapsable"> 
									<button form="verFamiliar" type="submit" value="${current.getFamiliar1().getIdFamiliar()}" name='idFamiliar' >Ver</button>
									<button form="editarFamiliar" type="submit" value="${current.getFamiliar1().getIdFamiliar()}" name='idFamiliar'>Editar</button>
								</span>
							</li>
							<c:forEach items="${current.getFamiliar().getRelacaoFamiliarFamiliarsForIdFamiliar1()}" var="grau" varStatus="innerLoop">  
							<form id="verSegundoGrauFamiliar" action="verSegundoGrauFamiliar" method="post">
							</form>
							<li class="row">
								<span class="Collapsable"> 
									<label> 
										<c:out value="${grau.getFamiliar1().getIdFamiliar()}"/> - 
										<c:out value="${grau.getFamiliar1().getNomeProprio()}"/> - 
										<c:out value="${grau.getFamiliar1().getApelido()}"/> - 
										<c:out value="${grau.getFamiliar1().getTelefone()}"/> - 
										<c:out value="${grau.getFamiliar1().getDataNascimento()}"/> - 
										<c:out value="${grau.getFamiliar1().getProfissao()}"/> -
										<c:out value="${grau.getTipoRelacao()}" /> 
									</label> 
								</span>
							<div  class="hiddenDiv">
								<ul>
									<li class="subButtons"> 
										<span class="Collapsable"> 
											<button form="verFamiliar" type="submit" name='idFamiliar' value="${grau.getFamiliar1().getIdFamiliar()}" >Ver</button>
											<button form="editarFamiliar" type="submit" name='idFamiliar' value="${grau.getFamiliar1().getIdFamiliar()}">Editar</button>
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
	</c:if> 
</c:if>--%>
<c:if test="${numFam == 0 }">
	<h4> Não há familiares para apresentar.</h4>
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
