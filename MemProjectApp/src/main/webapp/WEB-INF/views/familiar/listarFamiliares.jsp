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

/* Tree View */
 .css-treeview ul, .css-treeview li {
    padding: 0;
    margin: 0;
    list-style: none;
}
.css-treeview input {
    position: absolute;
    opacity: 0;
}
.css-treeview {
    font: normal 11px"Segoe UI", Arial, Sans-serif;
    -moz-user-select: none;
    -webkit-user-select: none;
    user-select: none;
}
.css-treeview a {
    color: #00f;
    text-decoration: none;
}
.css-treeview a:hover {
    text-decoration: underline;
}
.css-treeview input + label + ul {
    margin: 0 0 0 22px;
}
.css-treeview input ~ ul {
    display: none;
}
.css-treeview label, .css-treeview label::before {
    cursor: pointer;
}
.css-treeview input:disabled + label {
    cursor: default;
    opacity: .6;
}
.css-treeview input:checked:not(:disabled) ~ ul {
    display: block;
}
.css-treeview label, .css-treeview label::before {
    background: url("../pages/css3-treeview/example/icons.png") no-repeat;
}
.css-treeview label, .css-treeview a, .css-treeview label::before {
    display: inline-block;
    height: 16px;
    line-height: 16px;
    vertical-align: middle;
}
.css-treeview label {
    background-position: 18px 0;
}
.css-treeview label::before {
    content:"";
    width: 16px;
    margin: 0 22px 0 0;
    vertical-align: middle;
    background-position: 0 -32px;
}
.css-treeview input:checked + label::before {
    background-position: 0 -16px;
}
/* webkit adjacent element selector bugfix */
 @media screen and (-webkit-min-device-pixel-ratio:0) {
    .css-treeview {
        -webkit-animation: webkit-adjacent-element-selector-bugfix infinite 1s;
    }
    @-webkit-keyframes webkit-adjacent-element-selector-bugfix {
        from {
            padding: 0;
        }
        to {
            padding: 0;
        }
    }
}

label {
	word-spacing: 30px;
}
</style>

<html>

<script type="text/javascript">
	function onRow(rowID) {
		var row = document.getElementById(rowID);
		var curr = row.className;
		if (curr.indexOf("normal") >= 0)
			row.className = "highlight";
		else
			row.className = "normal";
	}
</script>

<div id="page-wrapper">
	<h1 class="page-header">LISTAR PACIENTES</h1>
	<%
		System.out.println("listFamiliar: " + listFamiliares.size());
		for(RelacaoPacienteFamiliar relacao1: listFamiliares){
			System.out.println("familiar id: " + relacao1.getFamiliar().getIdFamiliar());
			System.out.println("listFamiliar 2 : " + relacao1.getFamiliar().getRelacaoFamiliarFamiliarsForIdFamiliar1().size());
		}
		
		pageContext.setAttribute("familiares", listFamiliares);
	%>
	<div class="css-treeview">
		<c:forEach items="${familiares}" var="current" varStatus="loop">
		<p></p>
			<form id="verFamiliar" action="verFamiliar" method="get">
				<input type="hidden" name="idFamiliar" value="${current.getFamiliar().getIdFamiliar()}"/>
			</form>
			<ul>
				<li>
					<input type="hidden" name="idFamiliar" value="${current.getFamiliar().getIdFamiliar()}"/> 
					<input type="checkbox" id="item-${loop.index}"/>
					<label for="item-${loop.index}">
						<c:out value="${current.getFamiliar().getIdFamiliar()}"/> - 
						<c:out value="${current.getFamiliar().getNomeProprio()}"/> - 
						<c:out value="${current.getFamiliar().getApelido()}"/> - 
						<c:out value="${current.getFamiliar().getTelefone()}"/> - 
						<c:out value="${current.getFamiliar().getDataNascimento()}"/> - 
						<c:out value="${current.getFamiliar().getProfissao()}"/> - 
						<c:out value="${current.getTipoRelacao()}"/>
					</label>
					<ul>
						<li>
							<button form="verFamiliar" type="submit" value="Ver"> Ver</button> 
							<input type="checkbox" id="item-${loop.index}-1" /> 
							<label for="item-${loop.index}-1"> cenas 1 </label>
						</li>
					</ul>
					<c:forEach items="${current.getFamiliar().getRelacaoFamiliarFamiliarsForIdFamiliar1()}" var="grau" varStatus="innerLoop">  
					<form id="verSegundoGrauFamiliar" action="verSegundoGrauFamiliar" method="get">
							<input type="hidden" name="idFamiliarSegundoGrau" value="${grau.getFamiliarByIdFamiliar1().getIdFamiliar()}"/>
					</form>
					<ul>
						<li>
							<input type="hidden" name="idFamiliarSegundoGrau" value="${grau.getFamiliarByIdFamiliar1().getIdFamiliar()}"/> 
							<input type="checkbox" id="item-${loop.index}-${innerLoop.index}"/>
							<label for="item-${loop.index}-${innerLoop.index}">
									item-${loop.index}-${innerLoop.index}
									<c:out value="${grau.getFamiliarByIdFamiliar1().getIdFamiliar()}"/>	- 
									<c:out value="${grau.getFamiliarByIdFamiliar1().getNomeProprio()}"/> - 
									<c:out value="${grau.getFamiliarByIdFamiliar1().getApelido()}"/> -
									<c:out value="${grau.getFamiliarByIdFamiliar1().getTelefone()}"/> - 
									<c:out value="${grau.getFamiliarByIdFamiliar1().getDataNascimento()}"/> - 
									<c:out value="${grau.getFamiliarByIdFamiliar1().getProfissao()}"/> - 
									<c:out value="${grau.getTipoRelacao()}"/>
								</label>                    
						<ul>
							<li>
								<button form="verSegundoGrauFamiliar" type="submit" value="${current.getFamiliar().getIdFamiliar()}">Ver Segundo Grau</button> 
								<input type="checkbox" id="item-${loop.index+1}-${innerLoop.index}-1" />
								<label for="item-${loop.index+1}-${innerLoop.index}-1"> cenas 2 </label>
							</li>
						</ul>
						</li>
					</ul>
					</c:forEach> 					
				</li>
			</ul> 
		</c:forEach>
	</div>
</div>