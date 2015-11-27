<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	@SuppressWarnings("unchecked")
	List<RelacaoPacienteFamiliar> listFamiliares = (List<RelacaoPacienteFamiliar>) (request
			.getAttribute("listFamiliares"));
%>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script> 
<style> 
label {
	word-spacing: 30px !important;
	cursor:pointer;
}
.treeview {
	cursor:pointer;
	list-style-type: none;
	border: 2px solid #eae5e5;
	background: #F8F8F8;
    
}
ul .buttons, .row, .subButtons {
	cursor:pointer;
    background: #fcfcfc;
    list-style-type: none;
   	padding: 10px;
   	padding-left:20px;
   	padding-top:7px;
   	padding-bottom: 7px;
   	margin-top:8px;
   	margin-left:8px;
   	margin-left:-50px;
   	margin-right:30px;
   	border: 2px solid white;
    /*  border: 5px solid red; */
}
ul .row{
   	margin-top:-25px;
   	margin-right:30px;
   	/* border: 2px solid red; */
    /*  border: 5px solid red; */
}
ul .subButtons{ 
	background: #ffffff;
	border: 2px solid #eae5e5;
}
ul .list {
	cursor:pointer;
	list-style-type: none;
    margin:10px;
    /*  border: 5px solid red; */
}
</style>

<div id="page-wrapper">
	<h1 class="page-header">LISTAR PACIENTES </h1>
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
			<ul>
				<li class="buttons"> 
					<span class="Collapsable"> 
						<button form="verFamiliar" type="submit" value="Ver">Ver</button>
						<button form="editarFamiliar" type="submit" value="editar">Editar</button>
					</span>
				</li>
				<c:forEach items="${current.getFamiliar().getRelacaoFamiliarFamiliarsForIdFamiliar1()}" var="grau" varStatus="innerLoop">  
				<form id="verSegundoGrauFamiliar" action="verSegundoGrauFamiliar" method="get">
					<input type="hidden" name="idFamiliarSegundoGrau" value="${grau.getFamiliarByIdFamiliar1().getIdFamiliar()}" /> 
				</form>
				<li class="sublist row">
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
								<button form="verSegundoGrauFamiliar" type="submit" value="Ver">Ver</button>
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
    });
</script>