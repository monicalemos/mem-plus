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

function show(group)
{
	 /*var visSetting = (checkbox.checked) ? "visible" : "hidden";*/ 
	 document.getElementById(group).style.visibility = "visible"; 
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
				<li><a href="./ServletInicial?accao=dadosPaciente">Dados do Paciente</a></li>
				<!--<li><a href="./ServletInicial?accao=registrarFamiliar">Inserir
						Familiar</a></li>-->
				<li><a href="./ServletInicial?accao=verFamiliares">Ver
						Familiares</a></li>
				<!--<li><a class="selected" href="./ServletInicial?accao=novoEvento">Inserir
						Evento</a></li>-->
				<li><a href="./ServletInicial?accao=verEventos">Ver Eventos</a></li>
			</ul>
		</div>

		<div id="container">
			<form id="EditarEvento" action="EditarEvento">
				<header id="header" class="info">
					<h2>Novo Evento</h2>
				</header>
								
				<%
				Evento e = (Evento) session.getAttribute("evento");
                                Map pac2 = new HashMap();
                                pac2.put("tipo_evento", e.getTipo_de_evento());
                                pac2.put("nome_familiar", e.getFamiliar().getNome_completo());
                                pageContext.setAttribute("paciente", pac2);
                                %>
                            
				<label for="data_evento">Data do Evento: (aaaa-mm-dd)</label> 
                                <input type="text" id="data_evento" name="data_evento" value='<%=e.getData()%>'> 
				<br> 
				
				<label for="tipo_evento">Tipo de Evento</label> 
				<select name="tipo_evento" id="tipo_evento">
						<option value="Nascimento" ${paciente.tipo_evento== 'Nascimento' ? 'selected' : ''}>Nascimento</option>
						<option value="Baptizado" ${paciente.tipo_evento== 'Baptizado' ? 'selected' : ''}>Baptizado</option>
						<option value="Aniversário" ${paciente.tipo_evento== 'Aniversário' ? 'selected' : ''}>Aniversário</option>
						<option value="Noivado" ${paciente.tipo_evento== 'Noivado' ? 'selected' : ''}>Noivado</option>
						<option value="Casamento" ${paciente.tipo_evento== 'Casamento' ? 'selected' : ''}>Casamento</option>
						<option value="Festa" ${paciente.tipo_evento== 'Festa' ? 'selected' : ''}>Festa</option>
						<option value="Morte" ${paciente.tipo_evento== 'Morte' ? 'selected' : ''}>Morte</option>
						<option value="Funeral" ${paciente.tipo_evento== 'Funeral' ? 'selected' : ''}>Funeral</option>
					</select> 
				<br> 
				
				<label for="morada_evento">Morada do Evento: </label> 
				<br>
				<label2 for="pais">País:</label2>
				<input type="text" id="pais_evento" name="pais_nascimento" size="40px" value='<%=e.getLocal_evento().getPais()%>'> 
				<br>
				<label2 for="regiao">Região: </label2>
				<input type="text" id="regiao_evento" name="regiao_nascimento" size="40px" value='<%=e.getLocal_evento().getRegiao()%>'> 
				<br>
				<label2 for="cidade">Cidade: </label2>
				<input type="text" id="cidade_evento" name="cidade_nascimento" size="40px" value='<%=e.getLocal_evento().getCidade()%>'> 
				<br> 
				
				<label for="descricao_evento">Descrição do Evento:</label> 
				<textarea id="descricao_evento" name="descricao_evento" rows="5" cols="60" value='<%=e.getDescricao()%>'>
				</textarea>
				<br> 
				
				<label for="fotografia">Insira uma fotografia do evento:</label>
                                <input type="file" name="pic" accept="image/*">
                                <input type="submit">
                                <br>
                                
				<div class="checkbox">
					<input type="checkbox" value="1" id="temFamiliar" onclick="show('myGroup');"/>
                                        <label for="temFamiliar">Esteve um familiar presente</label>
					<label for="checkboxInput"></label>
				</div><br>
				
				<span id="myGroup" style="visibility:hidden" >
					<label for="nome_familiar">Nome do Familiar</label> 
					
					<select name="nome_familiar" id="nome_familiar">
					 <% ArrayList<Familiar> list = new ArrayList<Familiar>(); 
						Object lista_familiares = session.getAttribute("lista_familiares");  
						Paciente paciente = (Paciente)session.getAttribute("paciente");
						list = (ArrayList<Familiar>)lista_familiares; 
					
						ArrayList familiares = new ArrayList(); 
						for(Familiar f : list){
							Map pac = new HashMap();			
							pac.put("id", f.getId());
                                                        pac.put("nome completo", f.getNome_completo());
							pac.put("nome_proprio", f.getNomeProprio());
							pac.put("apelido", f.getApelido());
							familiares.add(pac);
						}
						pageContext.setAttribute("familiares", familiares);
						%>
				
						<c:forEach items="${familiares}" var="current">
							
								<option value="${current.id}" ${paciente.nome_familiar== current.nomeCompelto ? 'selected' : ''}>${current.nome_proprio} ${current.apelido}</option>
								
				
				  		</c:forEach>
					</select> 
				</span>
				
				<div id="lower">
					<input type="submit" value="Registar">
				</div>

			</form>
		</div>
	</div>

	<footer>
		<p>Copyright 2014 - MEM +</p>
	</footer>
</body>
</html>