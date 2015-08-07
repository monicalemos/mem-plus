package servlets;

import gestor.Utilitario;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classesDados.Tecnico;

/**
 * Servlet implementation class verDadosServlet
 */
@WebServlet("/verDadosServlet")
public class ServletVerDados extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Tecnico tecnico;
	HttpSession session = null;
    public ServletVerDados() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		tecnico = (Tecnico) session.getAttribute("tecnico");
		Utilitario utilitario = new Utilitario();
		PrintWriter out = response.getWriter();
		out.println ( 
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">\n" +
						"<html> \n" +
		   "<head> \n" +
		   "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"> \n" +
		   "<title> Dados do Cliente  </title> \n" +
		   "</head> \n" +
		   "<body> \n" + "<h1> Dados do Paciente:</h1><br> \n" + 
		   "Todos os doentes:<br>" + 
		   utilitario.verTodos_Pacientes(tecnico.getId()) +
		   "<form action = \"Inicial\">" + 
		   "<input type =\"submit\" name=\"voltarInicio\" value =\"Voltar ao Inicio\">"+ 
		   "</form>" + 
		   "</body> \n" +
		"</html>" );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
