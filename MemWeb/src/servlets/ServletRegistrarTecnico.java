package servlets;

import gestor.Utilitario;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classesDados.Tecnico;

@WebServlet("/ServletRegistrarTecnico")
public class ServletRegistrarTecnico extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;

	private String nome;
	private String nome_utilizador;
	private String password;
	private String email;
	//private TipoUtilizador tipoUtilizador;

	public ServletRegistrarTecnico() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilitario utilitario = new Utilitario();
		
		// reading the user input
		nome = request.getParameter("nome");
		nome_utilizador = request.getParameter("nome_utilizador");
		password = request.getParameter("password");
		email = request.getParameter("email");
		//tipoUtilizador = TipoUtilizador.valueOf(request.getParameter("tipoUtilizador").toUpperCase());

		int idUtilizador = 0;
		
		try {
			idUtilizador = utilitario.novoId_Tecnico();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Tecnico tecnico = new Tecnico(idUtilizador, nome, nome_utilizador, password, email);
	
		utilitario.registo_Tecnico(tecnico);
		session = request.getSession();
		session.setAttribute("nome", tecnico.getNome());
		session.setAttribute("nome_utilizador", tecnico.getNome_utilizador());
		session.setAttribute("password", tecnico.getPassword());
		session.setAttribute("email", tecnico.getEmail());
		session.setAttribute("tipo_utilizador", tecnico.getTipo_utilizador());
		session.setAttribute("utilizador", tecnico);
                session.setAttribute("idUtilizador", tecnico.getId());
		System.out.println("registei utilizador");
		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/LoggedPage.jsp");
				dispatcher.forward(request,response);
		
//		PrintWriter out = response.getWriter();
//		out.println ( 
//				"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">\n" +
//						"<html> \n" +
//						"<head> \n" +
//						"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"> \n" +
//						"<title> Dados da Pessoa </title> \n" +
//						"</head> \n" +
//						"<body> \n" + "<h1> Dados da Pessoa:</h1><br> \n" + 
//						"Nome: " + nome + "<br>\n" +
//						"Data de Nascimento: " + dataDeNascimento + "<br>\n" +
//						"Género: " + genero + "<br>\n" +
//						"Telefone: " + telefone + "<br>\n" + 
//						"Email: " + email + "<br>\n" + 
//						"Morada: " + pais + ", " + cidade + ", rua " + rua +"<br>\n" +
//						"Tipo de Responsabilidade Médica: " + tipoUtilizador + "<br>\n" + 
//						"<form action = \"ConfirmarRegisto\">" + 
//						"<p>Confirma os seus dados?</p>" +
//						"<input type =\"submit\" name=\"simConfirma\" value =\"Sim\">"+ 
//						"<input type =\"submit\" name=\"naoConfirma\" value =\"Não\">"+ 
//						"</form>" + 
//						"</body> \n" +
//				"</html>" );
	}
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
