<!DOCTYPE html>
<html>
<%@ page import="java.util.*"%>
<%@ page import="classesDados.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<head>
<link rel="stylesheet" href="Styles/Registo.css">
<title>MEM+</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	
	<header>
		<img id="logo" src="images/mem+.png"
			onclick="window.location.href='LoggedPage.jsp'" />

		<div id="containerLogout">
			<h2>Olá : <%= session.getAttribute("nome_utilizador") %></h2>
			 <form action="./Inicial?accao=logout" method="post">
				<div id="lower">
					 <input type="submit" name="logout" value="Logout" />
				</div>
			</form>
		</div>
		
	</header>
	<div id="page">
		<div id="menu">
			<div class="line"></div>
			<ul>
				<li><a href="./LoggedPage.jsp">Home</a></li>
				<li><a href="./ServletInicial?accao=verPacientes">Ver Pacientes</a></li>
				<li><a class="selected" href="./RegistrarPaciente.jsp">Registrar Novo Paciente</a></li>
				<li><a href="./ServletInicial?accao=evolucaoPacientes">Evolução dos Pacientes</a></li>
			</ul>
		</div>
		<div id="container">
			<form action="Inicial">
				<div id="lower">
					<input type="submit" name="voltarInicio" value="voltar ao Inicio">
				</div>
			</form>
			
			<form id="EditarPaciente" action="EditarPaciente">
				<header id="header" class="info">
					<h2>Dados do Paciente</h2>
				</header>
                                
                                 <%
				Paciente p = (Paciente) session.getAttribute("paciente");
                                session.setAttribute("paciente", p);
                           
                                Map pac = new HashMap();
                                pac.put("genero", p.getGenero().toString());
                                pac.put("escolaridade", p.getEscolaridade().toString());
                                pac.put("estado_civil", p.getEstadoCivil().toString());
                                pac.put("nivel_doenca", p.getNivel_doenca());
                                pac.put("nivel_sessao", p.getNivel_sessao());
                                pac.put("especialidade", p.getEspecialidade_medico().toString());
                                pageContext.setAttribute("paciente", pac);
                            %>
                                %>
                                
				<label for="nome">Nome Completo: <%=p.getNome_completo()%></label> 
				<input type="text" id="nome" name="nome" size="40px" value='<%=p.getNomeProprio() + " " + p.getApelido()%>'> 
				<br> 
				
				<label for="data_nascimento">Data de Nascimento: (aaaa-mm-dd)</label> 
				<input type="text" id="data_nascimento" name="data_nascimento" size="40px" value='<%=p.getData_de_nascimento()%>'>
				<br> 
				
				<label for="local_nascimento">Local de Nascimento:</label> 
				<label for="pais">País:</label> 
				<input type="text" id="pais_nascimento" name="pais_nascimento" size="40px" value='<%=p.getLocal_nascimento().getPais()%>'> 
				<br>
				<label for="regiao">Região: </label> 
				<input type="text" id="regiao_nascimento" name="regiao_nascimento" size="40px" value='<%=p.getLocal_nascimento().getRegiao()%>'>
				<br> 
				<label for="cidade">Cidade: </label> 
				<input type="text" id="cidade_nascimento" name="cidade_nascimento" size="40px" value='<%=p.getLocal_nascimento().getCidade()%>'>
				<br> 
				
				<label for="morada_atual">Morada Atual: </label> 
				<label  for="pais">País:</label> 
				<input type="text" id="pais_atual" name="pais_atual" size="40px" value='<%=p.getMorada().getPais()%>'> 
				<br> 
				<label for="regiao">Região:</label> 
				<input type="text" id="regiao_atual" name="regiao_atual" size="40px" value='<%=p.getMorada().getRegiao()%>'>
				<br> 
				<label for="cidade">Cidade:</label> 
				<input type="text" id="cidade_atual" name="cidade_atual" size="40px" value='<%=p.getMorada().getCidade()%>'>
				<br> 
				
				<label for="genero">Género</label> 
					<select name="genero">
                                            <option value="Masculino"  ${paciente.genero== 'MASCULINO' ? 'selected' : ''}>Masculino</option>
                                            <option value="Feminino"  ${paciente.genero== 'FEMININO' ? 'selected' : ''}>Feminino</option>
						<!--<option value="Masculino">Masculino</option>
						<option value="Feminino">Feminino</option> -->
					</select> 
				<br> 
				
				<label for="profissao">Profissão:</label> 
				<input type="text" id="profissao" name="profissao" size="40px" value='<%=p.getProfissao()%>'> 
				<br>

				<label for="escolaridade">Escolaridade:</label> 
				<select name="escolaridade">
					<option value="Ensino_Basico" ${paciente.escolaridade== 'ENSINO_BASICO' ? 'selected' : ''}>Ensino Básico</option>
					<option value="Ensino_Secundario" ${paciente.escolaridade== 'ENSINO_SECUNDARIO' ? 'selected' : ''}>Ensino Secundário</option>
					<option value="Licenciatura" ${paciente.escolaridade== 'LICENCIATURA' ? 'selected' : ''}>Licenciatura</option>
					<option value="Pos_Graduacao" ${paciente.escolaridade== 'POS_GRADUCAO' ? 'selected' : ''}>Pós-Graduação</option>
					<option value="Mestrado" ${paciente.escolaridade== 'MESTRADO' ? 'selected' : ''}>Mestrado</option>
					<option value="Douturamento" ${paciente.escolaridade== 'DOUTURAMENTO' ? 'selected' : ''}>Douturamento</option>
				</select> 
				<br> 
				
				<label for="estado_civil">Estado Civil:</label> 
				<select name="estado_civil">
					<option value="Solteiro" ${paciente.estado_civil== 'SOLTEIRO' ? 'selected' : ''}>Solteiro</option>
					<option value="Casado" ${paciente.estado_civil== 'CASADO' ? 'selected' : ''}>Casado</option>
					<option value="Divorciado" ${paciente.estado_civil== 'DIVORCIADO' ? 'selected' : ''}>Divorciado</option>
					<option value="Viuvo" ${paciente.estado_civil== 'SOLTEIRO' ? 'selected' : ''}>Viúvo</option>
				</select> 
				<br> 
				
				<label for="nivel_doenca">Nível de Doença:</label> 
				<select name="nivel_doenca">
					<option value="1" ${paciente.nivel_doenca== '1' ? 'selected' : ''}>1</option>
					<option value="2" ${paciente.nivel_doenca== '2' ? 'selected' : ''}>2</option>
					<option value="3" ${paciente.nivel_doenca== '3' ? 'selected' : ''}>3</option>
					<option value="4" ${paciente.nivel_doenca== '4' ? 'selected' : ''}>4</option>
					<option value="5" ${paciente.nivel_doenca== '5' ? 'selected' : ''}>5</option>
					<option value="6" ${paciente.nivel_doenca== '6' ? 'selected' : ''}>6</option>
					<option value="7" ${paciente.nivel_doenca== '7' ? 'selected' : ''}>7</option>
					<option value="8" ${paciente.nivel_doenca== '8' ? 'selected' : ''}>8</option>
					<option value="9" ${paciente.nivel_doenca== '9' ? 'selected' : ''}>9</option>
					<option value="10" ${paciente.nivel_doenca== '10' ? 'selected' : ''}>10</option>
				</select> 
				<br>
                                
                                <label for="fotografia">Insira uma fotografia:</label>
                                <input type="file" name="pic" accept="image/*">
                                <br>
				
				<label for="nivel_sessao">Nível de Sessão Inicial Esperado:</label> 
				<select name="nivel_sessao">
					<option value="1" ${paciente.nivel_sessao== '1' ? 'selected' : ''}>1</option>
					<option value="2" ${paciente.nivel_sessao== '2' ? 'selected' : ''}>2</option>
					<option value="3" ${paciente.nivel_sessao== '3' ? 'selected' : ''}>3</option>
					<option value="4" ${paciente.nivel_sessao== '4' ? 'selected' : ''}>4</option>
					<option value="5" ${paciente.nivel_sessao== '5' ? 'selected' : ''}>5</option>
				</select> 
				<br>
				
				<label for="nome_medico">Nome do Médico:</label> 
				<input type="text" id="nome_medico" name="nome_medico" size="40px"  value='<%=p.getNome_medico()%>'>
				<br> 
				
				<label for="especialidade_medico">Especialidade do Médico:</label> 
				<select name="especialidade_medico">
					<option value="Neurologista" ${paciente.especialidade== 'NEUROLOGISTA' ? 'selected' : ''}>Neurologista</option>
					<option value="Psiquiatra" ${paciente.especialidade== 'PSIQUIATRIA' ? 'selected' : ''}>Psiquiatra</option>
				</select> 
				<br> 
				

				<div id="lower">
					<input type="submit" value="Editar">
				</div>
			</form>
			<!-- <form action="${pageContext.request.contextPath}/Home.jsp"> -->

		</div>
	</div>

	<footer>
		<p>Copyright 2014 - MEM +</p>
	</footer>
</body>
</html>