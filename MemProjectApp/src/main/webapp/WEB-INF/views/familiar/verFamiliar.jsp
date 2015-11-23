<%@page import="java.util.*"%>
<%@page import="com.mem.app.model.*"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	RelacaoPacienteFamiliar relacao = (RelacaoPacienteFamiliar) request.getAttribute("currentRelacao");
	Familiar familiar = relacao.getFamiliar();

	@SuppressWarnings("unchecked")
	HashMap<String, Familiar> listFamiliar = (HashMap<String, Familiar>) (session
			.getAttribute("listFamiliares"));

	System.out.println("Current Familiar " + familiar);
%>

<style>
.newspaper {
	-webkit-column-count: 2; /* Chrome, Safari, Opera */
	-moz-column-count: 2; /* Firefox */
	column-count: 2;
}

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
<div id="page-wrapper">
	<h1 class="page-header">Dados do Familiar</h1>
	<div > <!-- class="newspaper" -->
		<p>
			<label for="Partentesco:"> Parentesco com Paciente:</label>
			<%=relacao.getTipoRelacao()%></p>
		<p>
			<label for="nomeCompleto"> Nome Completo:</label>
			<%=(familiar.getNomeCompleto() == null ? familiar.getApelido() + ", " + familiar.getNomeProprio()
					: familiar.getNomeCompleto())%></p>
		<p>
			<label for="nomeProprio"> Nome Próprio:</label>
			<%=familiar.getNomeProprio()%></p>
		<p>
			<label for="apelido"> Apelido:</label>
			<%=familiar.getApelido()%></p>
		<p>
			<label for="dataNascimento"> Data de Nascimento:</label>
			<%=familiar.getDataNascimento()%></p>

		<p>
			<label for="moradaByIdLocalNascimento"> Local de Nascimento:</label>
		</p>
		<p>
			<span style="padding-left: 2em"> <label
				for="moradaByIdLocalNascimento.pais"> Pais:</label> <%=familiar.getMoradaByIdLocalNascimento().getPais()%></span>
		</p>
		<p>
			<span style="padding-left: 2em"> <label
				for="moradaByIdLocalNascimento.regiao"> Região:</label> <%=familiar.getMoradaByIdLocalNascimento().getRegiao()%></span>
		</p>
		<p>
			<span style="padding-left: 2em"> <label
				for="moradaByIdLocalNascimento.cidade"> Cidade:</label> <%=familiar.getMoradaByIdLocalNascimento().getCidade()%></span>
		</p>

		<p>
			<label for="moradaByIdMorada"> Morada:</label>
		</p>
		<p>
			<span style="padding-left: 2em"> <label
				for="moradaByIdMorada.pais"> Pais:</label> <%=familiar.getMoradaByIdMorada().getPais()%></span>
		</p>
		<p>
			<span style="padding-left: 2em"> <label
				for="moradaByIdMorada.regiao"> Região:</label> <%=familiar.getMoradaByIdMorada().getRegiao()%></span>
		</p>
		<p>
			<span style="padding-left: 2em"> <label
				for="moradaByIdMorada.cidade"> Cidade:</label> <%=familiar.getMoradaByIdMorada().getCidade()%></span>
		</p>

		<p>
			<label for="genero"> Genero:</label>
			<%=familiar.getGenero()%></p>
		<p>
			<label for="estadoCivil"> Estado Civil:</label>
			<%=familiar.getEstadoCivil()%></p>
		<p>
			<label for="profissao"> Profissão:</label>
			<%=familiar.getProfissao()%></p>
		<p>
			<label for="ecuidador"> É cuidador?:</label>
			<%=familiar.getEcuidador() == false ? "Não" : "Sim"%></p>
		<p>
			<label for="telefone"> Telefone:</label>
			<%=familiar.getTelefone()%></p>
	</div>
	<div class="newspaper">
		<form action="editarFamiliar" method="get">
			<input type="hidden" name="idFamiliar"
				value="<%=familiar.getIdFamiliar()%>" />
			<button type="submit">Editar</button>
		</form>
		<form action="inserirSegundoGrauFamiliar" method="get">
			<input type="hidden" name="idFamiliar"
				value="<%=familiar.getIdFamiliar()%>" />
			<button type="submit">Inserir Familiar</button>
		</form>
	</div>

	<div class="row-fluid" id="tableCss">
		<table>
			<%
				ArrayList<Map<String, Object>> familiares = new ArrayList<Map<String, Object>>();

				for (Map.Entry<String, Familiar> entry : listFamiliar.entrySet()) {
					String parentesco = entry.getKey();
					Familiar f = entry.getValue();
					Map<String, Object> pac = new HashMap<String, Object>();

					pac.put("id", f.getIdFamiliar());
					pac.put("nomeProprio", f.getNomeProprio());
					pac.put("apelido", f.getApelido());
					pac.put("dataNascimento", f.getDataNascimento());
					pac.put("profissao", f.getProfissao());
					pac.put("parentesco", parentesco);
					familiares.add(pac);

					pageContext.setAttribute("familiares", familiares);
				}
			%>
			<tr>
				<th>Id</th>
				<th>Nome Familiar</th>
				<th>Apelido</th>
				<th>Telefone</th>
				<th>Data de Nascimento</th>
				<th>Profissão</th>
				<th>Parentesco</th>
				<th>Dados do Familiar</th>
			</tr>
			<c:forEach items="${familiares}" var="current">
				<tr class="normal" id="${current.id}">
					<td><c:out value="${current.id}" /></td>
					<td><c:out value="${current.nomeProprio}" /></td>
					<td><c:out value="${current.apelido}" /></td>
					<td><c:out value="${current.telefone}" /></td>
					<td><c:out value="${current.dataNascimento}" /></td>
					<td><c:out value="${current.profissao}" /></td>
					<td><c:out value="${current.parentesco}" /></td>
					<td>
						<form action="verSegundoGrauFamiliar" method="get">
							<input type="hidden" name="idFamiliar" value="${current.id}" /> <input
								type="submit" value="Ver" />
						</form>
						<form action="editarSegundoGrausFamiliar" method="get">
							<input type="hidden" name="idFamiliar" value="${current.id}" /> <input
								type="submit" value="Editar" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>


		                          
	</div>
</div>