<!DOCTYPE html>
<%@page import="enumerados.TipoRelacao"%>
<html>
    <%@ page import="java.util.*" %>
    <%@ page import="classesDados.*" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
    <head>
        <link rel="stylesheet" href="Styles/Tabelas.css">
        <link rel="stylesheet" href="Styles/Paciente.css">
        <title>MEM+</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>

        <header>
            <img id="logo" src="images/mem+.png"
                 onclick="window.location.href = 'LoggedPage.jsp'" />

            <div id="containerLogout">
                <h2>Olá : <%= session.getAttribute("nome_utilizador")%></h2>
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
                <li><a href="./ServletInicial?accao=verPaciente">Dados do Paciente</a></li>
               <!-- <li><a href="./ServletInicial?accao=registrarFamiliar">Inserir
                        Familiar</a></li>-->
                <li><a  href="./ServletInicial?accao=verFamiliares">Ver
                        Familiares</a></li>
              <!--  <li><a href="./ServletInicial?accao=novoEvento">Inserir
                        Evento</a></li>-->
                <li><a class="selected" href="./ServletInicial?accao=verEventos">Ver Eventos</a></li>
            </ul>
        </div>

        <div id="container">

            <%
                Paciente u = (Paciente) session.getAttribute("paciente");
                session.setAttribute("paciente", u);
                // Relacao r = (Relacao) session.getAttribute("relacao");
            %>
            <table>
                <% ArrayList<Evento> list = new ArrayList<Evento>();
                    Object lista_eventos = session.getAttribute("lista_eventos");
                    Paciente paciente = (Paciente) session.getAttribute("paciente");
                    list = (ArrayList<Evento>) lista_eventos;

                    ArrayList eventos = new ArrayList();
                    for (Evento e : list) {
                        Map pac = new HashMap();

                        pac.put("id", e.getId());
                        pac.put("data_evento", e.getData());
                        pac.put("tipo_evento", e.getTipo_de_evento());
                        pac.put("morada_evento", e.getLocal_evento());
                        pac.put("descricao", e.getDescricao());
                        pac.put("familiar", e.getFamiliar());
                        eventos.add(pac);
                    }
                    pageContext.setAttribute("eventos", eventos);
                %>
                <tr>
                    <th>Data Evento</th>
                    <th>Tipo de Evento</th>
                    <th>Local do Evento</th>
                    <th>Descrição</th>
                    <th>Familiar Presente</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${eventos}" var="current">
                    <tr>
                        <td><c:out value="${current.data_evento}" /></td>
                        <td><c:out value="${current.tipo_evento}"/></td>
                        <td><c:out value="${current.morada_evento}" /></td>
                        <td><c:out value="${current.descricao}" /></td>
                        <td><c:out value="${current.familiar}" /></td>	
                        <td>
                            <form id="EditarEvento" action="EditarEvento">
                                <input type="hidden" name="linhaId" value="${current.id}"/>
                                <input type="submit" value="Editar">
                            </form>

                            <form id="ApagarEvento" action="ApagarEvento">
                                <input type="hidden" name="linhaId" value="${current.id}"/>
                                <input type="submit" value="Apagar">
                            </form>
                        </td>
                    </tr>	
                </c:forEach>
            </table>
            <form action="./ServletInicial?accao=novoEvento" method="post">
                <input type="submit" value="Novo">
            </form>
        </div>
    </div>

    <footer>
        <p>Copyright 2014 - MEM +</p>
    </footer>
</body>
</html>