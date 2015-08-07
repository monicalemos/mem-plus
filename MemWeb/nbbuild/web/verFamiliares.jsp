<!DOCTYPE html>
<html>
    <%@ page import="enumerados.TipoRelacao"%>
    <%@ page import="java.util.*" %>
    <%@ page import="classesDados.*" %>
    <%@ page import="gestor.Utilitario" %>
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
                <h2>Ol� : <%= session.getAttribute("nome_utilizador")%></h2>
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
                <li><a href="./ServletInicial?accao=evolucaoPacientes">Evolu��o
                        dos Pacientes</a></li>
            </ul>
        </div>

        <div id="menu-lateral">
            <div class="line"></div>
            <ul>
                <li><a href="./ServletInicial?accao=dadosPaciente">Dados do Paciente</a></li>
              <!--  <li><a href="./ServletInicial?accao=registrarFamiliar">Inserir
                        Familiar</a></li>-->
                <li><a class="selected"  href="./ServletInicial?accao=verFamiliares">Ver
                        Familiares</a></li>
               <!-- <li><a href="./ServletInicial?accao=novoEvento">Inserir
                        Evento</a></li>-->
                <li><a href="./ServletInicial?accao=verEventos">Ver Eventos</a></li>
            </ul>
        </div>

        <div id="container">

            <table>
                <% ArrayList<Familiar> list = new ArrayList<Familiar>();
                    Utilitario utilitario = new Utilitario();

                    Tecnico tecnico = (Tecnico) session.getAttribute("utilizador");

                    Object lista_familiares = session.getAttribute("lista_familiares");
                    Paciente paciente = (Paciente) session.getAttribute("paciente");
                    list = (ArrayList<Familiar>) lista_familiares;

                    ArrayList familiares = new ArrayList();
                    for (Familiar f : list) {
                        Map pac = new HashMap();

                               //Relacao relacao = utilitario.devolve_Relacao_Paciente_Familiar(tecnico.getId(), paciente.getId(), f.getId());
                        //TipoRelacao parentesco = relacao.getTipo_relacao();
                        TipoRelacao parentesco = null;

                        pac.put("id", f.getId());
                        pac.put("nome_proprio", f.getNomeProprio());
                        pac.put("apelido", f.getApelido());
                        pac.put("data_nascimento", f.getData_de_nascimento());
                        pac.put("profissao", f.getProfissao());
                        pac.put("parentesco", parentesco);
                        familiares.add(pac);
                    }
                    pageContext.setAttribute("familiares", familiares);
                %>
                <tr>
                    <th>Nome</th>
                    <th>Apelido</th>
                    <th>Data de Nascimento</th>
                    <th>Profiss�o</th>
                    <th>Parentesco</th>
                    <th></th>
                </tr>
                <c:forEach items="${familiares}" var="current">
                    <tr>
                        <td><c:out value="${current.nome_proprio}" /></td>
                        <td><c:out value="${current.apelido}"/></td>
                        <td><c:out value="${current.data_nascimento}" /></td>
                        <td><c:out value="${current.profissao}" /></td>
                        <td><c:out value="${current.parentesco}" /></td>
                        <td>
                            <form action="./Inicial?accao=verFamiliar" method="post">
                                <input type="hidden" name="linhaId" value="${current.id}"/>
                                <input type="submit" value="Ver"/>
                            </form>

                            <form id="EditarFamiliar" action="./Inicial?accao=editarFamiliar" method="post">
                                <input type="hidden" name="linhaId" value="${current.id}"/>
                                <input type="submit" value="Editar">
                            </form>

                            <form id="ApagarFamiliar" action="./Inicial?accao=apagarFamiliar" method="post">
                                <input type="hidden" name="linhaId" value="${current.id}"/>
                                <input type="submit" value="Apagar">
                            </form>

                        </td>
                    </tr>	
                </c:forEach>
            </table>
            <form action="./ServletInicial?accao=registrarFamiliar" method="post">
                <input type="submit" value="Novo"/>
            </form>
        </div>
    </div>

    <footer>
        <p>Copyright 2014 - MEM +</p>
    </footer>
</body>
</html>