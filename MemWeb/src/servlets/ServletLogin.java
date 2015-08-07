package servlets;

import enumerados.TipoUtilizador;
import gestor.Utilitario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classesDados.Familiar;
import classesDados.Paciente;
import classesDados.Tecnico;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;
	
	private Tecnico tecnico;
	private Paciente paciente;
	private Familiar familiar;
	
//	private Utilizador utilizador;

	public ServletLogin() {
		super();
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ENTREI...");
		session = request.getSession();
		session.setAttribute("msgErroAutenticacao", "");

		System.out.println(request.getParameter("accao"));

		if(request.getParameter("accao").equals("validar")){
			System.out.println("entrei no login");
			String user = request.getParameter("nome_utilizador").trim();
			String pass = request.getParameter("password").trim();

			Utilitario utilitario = new Utilitario();
			
			familiar = utilitario.devolve_Familiar(user, pass);
			tecnico = utilitario.devolve_Tecnico(user, pass);

			if(familiar != null) {
				System.out.println("encontrei o familiar");

				session = request.getSession();
				session.setAttribute("tipo_utilizador", TipoUtilizador.CUIDADOR);
				session.setAttribute("username", familiar.getNome_utilizador());
				session.setAttribute("nome_utilizador", familiar.getNome_completo());
				session.setAttribute("idUtilizador", familiar.getId());
				session.setAttribute("validado", "sim");
				System.out.println(session.getAttribute("nome_utilizador"));
				String ret = "LoggedPage.jsp";
				response.sendRedirect(ret);
			}
			
			else if(tecnico != null) {
				System.out.println("encontrei o tecnico");

				session = request.getSession();
				session.setAttribute("tipo_utilizador", TipoUtilizador.TECNICO);
				session.setAttribute("username", tecnico.getNome_utilizador());
				session.setAttribute("nome_utilizador", tecnico.getNome_completo());
				session.setAttribute("idUtilizador", tecnico.getId());
				session.setAttribute("validado", "sim");
				System.out.println(session.getAttribute("nome_utilizador"));
				String ret = "LoggedPage.jsp";
				response.sendRedirect(ret);
			}


			else{
				session.setAttribute("msgErroAutenticacao", "Email e/ou password incorrectos");
				String ret = "Home.jsp";
				response.sendRedirect(ret);
			}
		}
		else if (request.getParameter("accao").equals("logout")){
			session = request.getSession();
			session.setAttribute("validado", "nao");
			session.setAttribute("msgErroAutenticacao", "");
			String ret = "Home.jsp";
			response.sendRedirect(ret);
		}

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
