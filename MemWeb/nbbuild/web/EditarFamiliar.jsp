<!DOCTYPE html>
<html>
<%@ page import="java.util.*"%>
<%@ page import="classesDados.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<head>
<link rel="stylesheet" href="Styles/Paciente.css">
<title>MEM+</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script type="text/javascript">

function showHide(checkbox, group)
{
	 var visSetting = (checkbox.checked) ? "visible" : "hidden"; 
	 document.getElementById(group).style.visibility = visSetting; 
}

</script>
<body>
	<div class="content">
		<header>
			<img id="logo" src="images/mem+.png"
				onclick="window.location.href='LoggedPage.jsp'" />

			<div id="containerLogout">
				<h2>
					Olá :
					<%=session.getAttribute("nome_utilizador")%></h2>
				<form action="./Inicial?accao=logout" method="post">
					<div id="lower">
						<input type="submit" name="logout" value="Logout" />
					</div>
				</form>
			</div>

		</header>

		<div id="menu">
			<div class="line"></div>
			<ul>
				<li><a href="./LoggedPage.jsp">Home</a></li>
				<li><a class="selected"
					href="./ServletInicial?accao=verPacientes">Ver Pacientes</a></li>
				<li><a href="./RegistrarPaciente.jsp">Registrar Novo
						Paciente</a></li>
				<li><a href="./ServletInicial?accao=evolucaoPacientes">Evolução
						dos Pacientes</a></li>
			</ul>
		</div>

		<div id="menu-lateral">
			<div class="line"></div>
			<ul>
				<li><a 	href="./ServletInicial?accao=dadosPaciente">Dados do Paciente</a></li>
				<!--<li><a class="selected" href="./ServletInicial?accao=registrarFamiliar">Inserir
						Familiar</a></li>-->
				<li><a href="./ServletInicial?accao=verFamiliares">Ver
						Familiares</a></li>
				<!--<li><a href="./ServletInicial?accao=novoEvento">Inserir
						Evento</a></li>-->
				<li><a href="./ServletInicial?accao=verEventos">Ver Eventos</a></li>
			</ul>
		</div>

		<div id="container">
			<form id="EditarFamiliar" action="EditarFamiliar">
				<header id="header" class="info">
					<h2>Dados do Familiar</h2>
				</header>
                                
                            <%
				Familiar p = (Familiar) session.getAttribute("familiar");
                                Map pac = new HashMap();
                                pac.put("genero", p.getGenero());
                                pac.put("estado_civil", p.getEstadoCivil().toString());
                                pageContext.setAttribute("familiar", pac);
                            %>
				<label for="tipo_relacao">Parentesco: </label> 
                                
				<select name="tipo_relacao">
					<option value="filho">Filho</option>
					<option value="filho">Filha</option>
					<option value="irmao">irmão</option>
					<option value="irmao">Irmã</option>
					<option value="esposo">Esposo</option>
					<option value="esposo">Esposa</option>
					<option value="pais">Pai</option>
					<option value="pais">Mãe</option>
				</select> 
				<br> 
				
				<label for="nome">Nome Completo:</label> 
				<input type="text" id="nome" name="nome" value='<%=p.getNome_completo()%>'> 
				<br> 
				
				<label for="data_nascimento">Data de Nascimento: (aaaa-mm-dd)</label> 
				<input type="text" id="data_nascimento" name="data_nascimento" value='<%=p.getData_de_nascimento()%>'> 
				<br>

				<label for="local_nascimento">Local de Nascimento: </label> 
				<br>
				<label2 for="pais">País:</label2>
				<input type="text" id="pais_nascimento" name="pais_nascimento" size="40px" value='<%=p.getLocal_nascimento().getPais()%>'> 
				<br>
				<label2 for="regiao">Região: </label2>
				<input type="text" id="regiao_nascimento" name="regiao_nascimento" size="40px" value='<%=p.getLocal_nascimento().getRegiao()%>'> 
				<br>
				<label2 for="cidade">Cidade: </label2>
				<input type="text" id="cidade_nascimento" name="cidade_nascimento" size="40px" value='<%=p.getLocal_nascimento().getCidade()%>'> 
				<br> 
				
				<label for="morada_atual">Morada Atual: </label> 
				<br>
				<label2 for="pais">País:</label2>
				<input type="text" id="pais_atual" name="pais_atual" size="40px" value='<%=p.getMorada().getPais()%>'> 
				<br>
				<label2 for="regiao">Região: </label2>
				<input type="text" id="regiao_atual" name="regiao_atual" size="40px" value='<%=p.getMorada().getRegiao()%>'> 
				<br>
				<label2 for="cidade">Cidade: </label2>
				<input type="text" id="cidade_atual" name="cidade_atual" size="40px" value='<%=p.getMorada().getCidade()%>'> 
				<br> 
				
				<label for="genero">Género</label> 
				<select name="genero" id="genero">
					<option value="Masculino"  ${familiar.genero== 'MASCULINO' ? 'selected' : ''}>Masculino</option>
					<option value="Feminino"  ${familiar.genero== 'FEMININO' ? 'selected' : ''}>Feminino</option>
				</select> 
				<br> 
				
				<label for="profissao">Profissão: </label> 
				<input type="text" id="profissao" name="profissao" size="40px" value='<%=p.getProfissao()%>'> 
				<br>
                                
                                <label for="estado_civil">Estado Civil:</label> 
				<select name="estado_civil">
                                        <option value="ND" ${familiar.estado_civil==null ? 'selected' : ''}>--</option>
					<option value="Solteiro" ${familiar.estado_civil== 'SOLTEIRO' ? 'selected' : ''}>Solteiro</option>
					<option value="Casado" ${familiar.estado_civil== 'SOLTEIRO' ? 'selected' : ''}>Casado</option>
					<option value="Divorciado" ${familiar.estado_civil== 'SOLTEIRO' ? 'selected' : ''}>Divorciado</option>
					<option value="Viuvo" ${familiar.estado_civil== 'SOLTEIRO' ? 'selected' : ''}>Viúvo</option>
				</select> 
				<br> 
                                
                                <label for="fotografia">Insira uma fotografia:</label>
                                <input type="file" name="pic" accept="image/*">                                
                                <br>
                                
				<div class="checkbox">
					<input type="checkbox" value="1" id="eCuidador" onclick="showHide(this, 'myGroup');"/> 
					<label for="eCuidador">É cuidador?</label>
				</div><br>
				
				<span id="myGroup" style="visibility:hidden" >
				<label for="nome_utilizador" id="label_user">Nome de Utilizador: </label> 
					<input type="text" id="nome_utilizador" name="nome_utilizador" size="40px" value='<%=p.getNome_utilizador()%>'>
				<br>
				<label for="password" id= "label_password">Password: </label> 
					<input type="password" id="password" name="password" size="40px" value='<%=p.getPassword()%>'>
				</span>
				
				<div id="lower">
					<input type="submit" value="Editar">
				</div>

			</form>
		</div>
	</div>

	<footer>
		<p>Copyright 2014 - MEM +</p>
	</footer>
</body>
</html>