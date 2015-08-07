package servlets;

import enumerados.TipoUtilizador;
import gestor.Utilitario;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classesDados.*;

@WebServlet("/ServletInicial")
public class ServletInit extends HttpServlet {

    private static final long serialVersionUID = 1L;
    HttpSession session = null;

    private Tecnico tecnico;
    private Paciente paciente;
    private Familiar familiar;
    private Utilitario utilitario = new Utilitario();
    //	private Utilizador utilizador;

    public ServletInit() {

        super();
    }

    @SuppressWarnings("unchecked")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("entrei no inicial");
        System.out.println("acao:" + request.getParameter("accao"));

       
//        if (request.getParameter("RegistrarFamiliar") != null) {
//
//            System.out.println("Cliquei no registrar Familiar");
//            request.setAttribute("RegistrarFamiliar", request.getServletPath());
//            RequestDispatcher rd = request.getRequestDispatcher("/RegistrarFamiliar.jsp");
//            if (rd != null) {
//                rd.forward(request, response);
//            }
//
//        } else if (request.getParameter("verDados") != null) {
//            System.out.println("Cliquei no ver Dados");
//            request.setAttribute("verDados", request.getServletPath());
//            RequestDispatcher rd = request.getRequestDispatcher("/VerDados");
//            if (rd != null) {
//                rd.forward(request, response);
//            }
//        } else if (request.getParameter("voltarInicio") != null) {
//            System.out.println("cliquei no voltar ao inicio");
//            ServletContext sc = this.getServletContext();
//            RequestDispatcher rd = sc.getRequestDispatcher("/LoggedPage.jsp");
//            //			//request.setAttribute("voltarInicio", request.get);
//            //			RequestDispatcher rd = request.getRequestDispatcher("${pageContext.request.contextPath}/Home.jsp");
//            if (rd != null) {
//                rd.forward(request, response);
//            }
//        } //		else if(request.getParameter("accao").equals("registrarUtilizador")){
//        //			System.out.println("Cliquei no registrar Utilizador");
//        //			request.setAttribute("registrarUtilizador", request.getServletPath());
//        //			RequestDispatcher rd = request.getRequestDispatcher("/RegistrarUtilizador.jsp");
//        //			if (rd != null)
//        //				rd.forward(request, response);
//        //		}
//         
            
            if (request.getParameter("accao").equals("registrarPaciente")) {
            System.out.println("cliquei no registrar Pacientes");

            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/RegistrarPaciente.jsp");

            if (rd != null) {
                session = request.getSession();
                session.getAttribute("utilizador");
                //session.setAttribute("lista_pacientes", utilitario.verTodos_Pacientes(id));
                System.out.println(session.getAttribute("utilizador"));
                rd.forward(request, response);
            }

        } else if (request.getParameter("accao").equals("registrarFamiliar")) {
            System.out.println("cliquei no registrar Familiar");

            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/RegistrarFamiliar.jsp");

            if (rd != null) {
                session = request.getSession();
                session.getAttribute("utilizador");
                session.getAttribute("paciente");
                //session.setAttribute("lista_pacientes", utilitario.verTodos_Pacientes(id));
                System.out.println(session.getAttribute("utilizador"));
                rd.forward(request, response);
            }

        } else if (request.getParameter("accao").equals("novoEvento")) {
            System.out.println("cliquei no registrar Evento");

            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/RegistrarEvento.jsp");

            if (rd != null) {
                session = request.getSession();
                session.getAttribute("utilizador");
                session.getAttribute("paciente");

                int idPaciente = (int) session.getAttribute("idPaciente");

                ArrayList<Familiar> familiares = utilitario.verTodos_Familiares(idPaciente);

                session.setAttribute("lista_familiares", familiares);

                rd.forward(request, response);
            }

        } else if (request.getParameter("accao").equals("verEventos")) {
            System.out.println("cliquei no ver Eventos");

            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/verEventos.jsp");

            if (rd != null) {
                session = request.getSession();

                Paciente paciente = (Paciente) session.getAttribute("paciente");

                ArrayList<Evento> eventos = utilitario.verTodos_Eventos(paciente);

                session.setAttribute("lista_eventos", eventos);

                rd.forward(request, response);
            }
            
        } else if (request.getParameter("accao").equals("contactos")) {
            System.out.println("carreguyei nos contactos e vim parar ao if dele");
        } else if (request.getParameter("accao").equals("logout")) {
            System.out.println("Cliquei no Logout");
            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/Home.jsp");
            if (rd != null) {
                session = request.getSession();
                session.setAttribute("validado", "nao");
                session.setAttribute("msgErroAutenticacao", "");
                rd.forward(request, response);
            }
        } else if (request.getParameter("accao").equals("verPacientes")) {
            System.out.println("cliquei no ver Pacientes");

            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/VerPacientes.jsp");

            if (rd != null) {
                session = request.getSession();
                int id = (int) session.getAttribute("idUtilizador");

                ArrayList<Paciente> pacientes = utilitario.verTodos_Pacientes(id);

                session.setAttribute("lista_pacientes", pacientes);

                rd.forward(request, response);

                ArrayList<Paciente> list = new ArrayList<Paciente>();
                list = (ArrayList<Paciente>) session.getAttribute("lista_pacientes");

                for (Paciente p : list) {
                    System.out.println(p.getNome_medico() + " - " + p.getApelido() + " - " + p.getNivel_doenca() + " - " + p.getNivel_sessao());
                }
            }
        } else if (request.getParameter("accao").equals("verPaciente")) {
            System.out.println("cliquei no ver Paciente");

            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/Paciente.jsp");

            if (rd != null) {
                session = request.getSession();
                int id = (int) session.getAttribute("idUtilizador");
                //int id_paciente = Integer.parseInt(request.getParameter("id_paciente"));

                int linhaId = Integer.parseInt(request.getParameter("linhaId"));

                System.out.println("lidaId " + linhaId);
                System.out.println("id_tecnico " + id);
                Paciente paciente = utilitario.devolve_Paciente(linhaId);
                System.out.println("saiu do utilitario");
                session.setAttribute("paciente", paciente);
                session.setAttribute("idPaciente", paciente.getId());
                System.out.println("encontra o nome completo?" + paciente.getNome_completo());
                System.out.println("encontra o paciente?" + paciente);
                rd.forward(request, response);

            }
        } else if (request.getParameter("accao").equals("dadosPaciente")) {
            System.out.println("cliquei no dados do Paciente");

            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/Paciente.jsp");

            if (rd != null) {
                session = request.getSession();
                int id = (int) session.getAttribute("idUtilizador");
                int id_paciente = (int) session.getAttribute("idPaciente");

                Paciente paciente = utilitario.devolve_Paciente(id_paciente);
                session.setAttribute("paciente", paciente);
//				session.setAttribute("idPaciente", paciente.getId());
                System.out.println(paciente);
                rd.forward(request, response);
            }
        } else if (request.getParameter("accao").equals("verFamiliares")) {
            System.out.println("cliquei no ver Familiares");

            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/verFamiliares.jsp");

            if (rd != null) {
                session = request.getSession();

                Paciente paciente = (Paciente) session.getAttribute("paciente");
                System.out.println("paciente: " + paciente.getNome_completo());
                int idPaciente = paciente.getId();
                int id = (int) session.getAttribute("idUtilizador");

                System.out.println("tamanho litsa: " + utilitario.verTodos_Relacao_Paciente_Familiar_Do_Paciente(paciente).size());
                ArrayList<Relacao> relacoes = utilitario.verTodos_Relacao_Paciente_Familiar_Do_Paciente(paciente);
                ArrayList<Familiar> familiares = new ArrayList<Familiar>();
                
                for(Relacao r: relacoes){
                    familiares.add(r.getFamiliar_nivel1());
                }
                
                session.setAttribute("lista_familiares", familiares);

                rd.forward(request, response);
            }
        } else if (request.getParameter("accao").equals("verFamiliar")) {
            System.out.println("cliquei no ver Familiar");

            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/Familiar.jsp");

            if (rd != null) {
                session = request.getSession();
                int id = (int) session.getAttribute("idUtilizador");
                //int id_paciente = Integer.parseInt(request.getParameter("id_paciente"));

                int linhaId = Integer.parseInt(request.getParameter("linhaId"));

                System.out.println("lidaId " + linhaId);
                System.out.println("id_tecnico " + id);

                Paciente paciente = (Paciente)session.getAttribute("paciente");
                //Paciente paciente = utilitario.devolve_Paciente(linhaId, id);
                Familiar familiar = utilitario.devolve_Familiar(linhaId);
                
                System.out.println("tamanho litsa: " + utilitario.verTodos_Relacao_Familiar_Familiar_Do_Familiar(paciente, familiar));
                ArrayList<Relacao> relacoes = utilitario.verTodos_Relacao_Familiar_Familiar_Do_Familiar(paciente, familiar);
                ArrayList<Familiar> familiares = new ArrayList<Familiar>();
                
                for(Relacao r: relacoes){
                    familiares.add(r.getFamiliar_nivel2());
                }
                
                session.setAttribute("lista_familiares", familiares);

                System.out.println("id tecnico : " + id );
                System.out.println("id paciente: " + paciente.getId());
                System.out.println("id familiar " + familiar.getId());

                //Relacao relacao = utilitario.devolve_Relacao_Paciente_Familiar(id, paciente.getId(), familiar.getId());
                session.setAttribute("paciente", paciente);
                session.setAttribute("idPaciente", paciente.getId());

                session.setAttribute("familiar", familiar);
                session.setAttribute("idFamiliar", familiar.getId());

//                                session.setAttribute("relacao", relacao);
//                                session.setAttribute("idRelacao", relacao.getId());
                System.out.println(familiar);
                rd.forward(request, response);

            }
        } else if (request.getParameter("accao").equals("editarPaciente")) {
            System.out.println("cliquei no editar Paciente");

            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/EditarPaciente.jsp");
            
            Paciente paciente = (Paciente)session.getAttribute("paciente");
            if (rd != null) {
                session = request.getSession();
                int id = (int) session.getAttribute("idUtilizador");
                //int id_paciente = Integer.parseInt(request.getParameter("id_paciente"));

                if (request.getParameter("linhaId") != null) {
                    int linhaId = Integer.parseInt(request.getParameter("linhaId"));

                    System.out.println("lidaId " + linhaId);
                    System.out.println("id_tecnico " + id);

                    //Paciente paciente = utilitario.devolve_Paciente(linhaId, id);
                    System.out.println("saiu do utilitario");
                    session.setAttribute("paciente", paciente);
                    session.setAttribute("idPaciente", paciente.getId());
                    System.out.println("encontra o nome completo?" + paciente.getNome_completo());
                    System.out.println("encontra o paciente?" + paciente);

                } else {
                    int id_paciente = (int) session.getAttribute("idPaciente");
                    System.out.println("id_paciente " + id_paciente);
                    System.out.println("id_tecnico " + id);
                    //Paciente paciente = utilitario.devolve_Paciente(id_paciente, id);

                    session.setAttribute("paciente", paciente);
                    session.setAttribute("idPaciente", paciente.getId());
                }
            }
            System.out.println(paciente);
            rd.forward(request, response);
            
        } else if (request.getParameter("accao").equals("editarFamiliar")) {
            System.out.println("cliquei no editar Familiares");

            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/EditarFamiliar.jsp");

            Paciente paciente = (Paciente)session.getAttribute("paciente");
            
            if (rd != null) {
                session = request.getSession();
                int id = (int) session.getAttribute("idUtilizador");

                if (request.getParameter("linhaId") != null) {
                    int linhaId = Integer.parseInt(request.getParameter("linhaId"));

                    System.out.println("lidaId " + linhaId);
                    System.out.println("id_tecnico " + id);

                    //Paciente paciente = utilitario.devolve_Paciente(linhaId, id);
                    Familiar familiar = utilitario.devolve_Familiar(linhaId);

                    System.out.println("id tecnico : " + id + "id paciente: " + paciente.getId() + " id familiar " + familiar.getId());

                    //Relacao relacao = utilitario.devolve_Relacao_Paciente_Familiar(id, paciente.getId(), familiar.getId());
                    session.setAttribute("paciente", paciente);
                    session.setAttribute("idPaciente", paciente.getId());

                    session.setAttribute("familiar", familiar);
                    session.setAttribute("idFamiliar", familiar.getId());

//                                session.setAttribute("relacao", relacao);
//                                session.setAttribute("idRelacao", relacao.getId());
                } else {
                    int id_familiar = (int) session.getAttribute("idFamiliar");
                    System.out.println("id_familiar " + id_familiar);
                    System.out.println("id_tecnico " + id);

                    Familiar familiar = utilitario.devolve_Familiar(id_familiar);

                    session.setAttribute("paciente", paciente);
                    session.setAttribute("idPaciente", paciente.getId());

                    session.setAttribute("familiar", familiar);
                    session.setAttribute("idFamiliar", familiar.getId());
                }
                System.out.println(familiar);
                rd.forward(request, response);
            }
        } else if (request.getParameter("accao").equals("editarEventos")) {
            System.out.println("cliquei no editar Eventos");
            int id = (int) session.getAttribute("idUtilizador");

            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/EditarEventos.jsp");

            if (rd != null) {
                session = request.getSession();
                int linhaId = Integer.parseInt(request.getParameter("linhaId"));

                System.out.println("lidaId " + linhaId);
                System.out.println("id_tecnico " + id);

                Evento evento = utilitario.devolve_Evento(linhaId);

                System.out.println("id evento:" + evento.getId());

                //Relacao relacao = utilitario.devolve_Relacao_Paciente_Familiar(id, paciente.getId(), familiar.getId());
                session.setAttribute("evento", evento);
                session.setAttribute("idEvento", evento.getId());

                rd.forward(request, response);
            }
        } else if (request.getParameter(
                "accao").equals("validar")) {
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("ENTREI...");
            System.out.println("Cliquei no efectuar login");

            session = request.getSession();
            session.setAttribute("msgErroAutenticacao", "");

            System.out.println(request.getParameter("accao"));

            String user = request.getParameter("nome_utilizador").trim();
            String pass = request.getParameter("password").trim();

            Utilitario utilitario = new Utilitario();

            familiar = utilitario.devolve_Familiar(user, pass);
            tecnico = utilitario.devolve_Tecnico(user, pass);

            if (familiar != null) {
                System.out.println("encontrei o familiar");
                session = request.getSession();
                session.setAttribute("tipo_utilizador", TipoUtilizador.CUIDADOR);
                session.setAttribute("username", familiar.getNome_utilizador());
                session.setAttribute("nome_utilizador", familiar.getNome_completo());
                session.setAttribute("idUtilizador", familiar.getId());
                session.setAttribute("utilizador", tecnico);
                System.out.println(session.getAttribute("utilizador"));
                session.setAttribute("validado", "sim");
                System.out.println(session.getAttribute("nome_utilizador"));
                String ret = "LoggedPage.jsp";
                response.sendRedirect(ret);
            } else if (tecnico != null) {
                System.out.println("encontrei o tecnico");
                session = request.getSession();
                session.setAttribute("tipo_utilizador", TipoUtilizador.TECNICO);
                session.setAttribute("username", tecnico.getNome_utilizador());
                session.setAttribute("nome_utilizador", tecnico.getNome_completo());
                session.setAttribute("idUtilizador", tecnico.getId());
                session.setAttribute("utilizador", tecnico);
                System.out.println(session.getAttribute("utilizador"));
                session.setAttribute("validado", "sim");
                System.out.println(session.getAttribute("nome_utilizador"));
                System.out.println(session.getAttribute("idUtilizador"));
                String ret = "LoggedPage.jsp";
                response.sendRedirect(ret);
            } else {
                System.out.println("erro a autenticar");
                session.setAttribute("msgErroAutenticacao", "Email e/ou password incorrectos");
                String ret = "Home.jsp";
                response.sendRedirect(ret);
            }

			//			if (respMedico != null) {
            //			
            //				request.setAttribute("login", request.getServletPath());
            //				RequestDispatcher rd = request.getRequestDispatcher("/PaginaOperacao.jsp");
            //				if (rd != null){
            //					session = request.getSession();
            //					session.setAttribute("email", email);
            //					session.setAttribute("nome_completo", respMedico.getNomeCompleto());
            //					session.setAttribute("idRespMedico", respMedico.getId());
            //					session.setAttribute("validado", "sim");
            //					System.out.println(session.getAttribute("nome_completo"));
            //					rd.forward(request, response);
            //				}
            //			}
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
