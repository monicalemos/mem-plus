package servlets;

import gestor.Utilitario;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.BCrypt;
import classesDados.Morada;
import classesDados.Tecnico;

/**
 * Servlet implementation class ServletConfirmaRegisto
 */
@WebServlet("/ServletConfirmaRegisto")
public class ServletConfirmarRegisto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;

	public ServletConfirmarRegisto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		

		if (request.getParameter("simConfirma") != null){


			System.out.println("Clicou no sim");
			Utilitario utilitario = new Utilitario();

			int idMorada=0;
			int idTecnico = 0;

			try {
				idMorada = utilitario.novoId_Morada();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				idTecnico = utilitario.novoId_Tecnico();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			Morada morada = new Morada(idMorada, session.getAttribute("pais").toString(), 
					session.getAttribute("regiao").toString(), 
					session.getAttribute("cidade").toString());
			
			utilitario.registo_Morada(morada);
			String hashed = BCrypt.hashpw(session.getAttribute("password").toString(), BCrypt.gensalt());
			
			Tecnico	tecnico = new Tecnico(idTecnico, session.getAttribute("nome").toString(), 
					session.getAttribute("nome_utilizador").toString(),
					hashed, session.getAttribute("email").toString());
			
			utilitario.registo_Tecnico(tecnico);
			
			session = request.getSession();
			session.setAttribute("email", tecnico.getEmail());
			session.setAttribute("nome_completo", tecnico.getNome_completo());
			session.setAttribute("idTecnico", tecnico.getId());
			session.setAttribute("validado", "sim");
			session.setAttribute("tecnico", tecnico);
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/PaginaOperacao.jsp");
			if (rd != null)
				rd.forward(request, response);
		}

		else if (request.getParameter("naoConfirma") != null){
			System.out.println("Cliquei no não confirmar");
			request.setAttribute("naoConfirmo", request.getServletPath());
			RequestDispatcher rd = request.getRequestDispatcher("/RegistrarRespMedico.jsp");
			if (rd != null)
				rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
