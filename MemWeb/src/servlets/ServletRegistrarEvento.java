package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
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
import gestor.Utilitario;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/ServletRegistrarEvento")
public class ServletRegistrarEvento extends HttpServlet {

    private static final long serialVersionUID = 1L;
    HttpSession session = null;

    public ServletRegistrarEvento() {
        super();
    }

    @SuppressWarnings("deprecation")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utilitario utilitario = new Utilitario();
        session = request.getSession();

        Tecnico tecnico = (Tecnico) session.getAttribute("utilizador");
        System.out.println("tem tecnico?" + session.getAttribute("utilizador"));
        System.out.println("nome tecnico? " + tecnico.getNome_completo());
        System.out.println("Tem id tecnico? " + session.getAttribute("idUtilizador"));

        Paciente paciente = (Paciente) session.getAttribute("paciente");
        System.out.println("tem paciente?" + session.getAttribute("paciente"));
        System.out.println("nome paciente? " + paciente.getNome_completo());
        System.out.println("Tem id paciente? " + paciente.getId());

		// reading the user input
        String dataDeNascimento = request.getParameter("data_nascimento");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date data_evento = null;
        try {
            data_evento = sdf.parse(dataDeNascimento);
        } catch (ParseException e) {

        }
//		String 	dataDoEvento = request.getParameter("data_evento");
//		String[] dataEvento = dataDoEvento.split("/");
//		Date data_evento = new Date(Integer.parseInt(dataEvento[2]), Integer.parseInt(dataEvento[1]), Integer.parseInt(dataEvento[0]));

        String tipo_evento = request.getParameter("tipo_evento");

        String paisMorada = request.getParameter("pais_evento");
        String regiaoMorada = request.getParameter("regiao_evento");
        String cidadeMorada = request.getParameter("cidade_evento");

        String descricao = request.getParameter("descricao_evento");

        String nomeFamiliar = null;

        int idFamiliar = Integer.parseInt(request.getParameter("nome_familiar"));
        System.out.println("id do familiar " + nomeFamiliar);

        int idEvento = 0;
        int idMorada = 0;

        Familiar familiar = null;
        Morada morada = null;
        Evento evento = null;

        ArrayList<Familiar> familiares = utilitario.verTodos_Familiares(paciente.getId());
        for (Familiar f : familiares) {
            if (f.getId() == idFamiliar) {
                familiar = f;
            }
        }

        try {
            idMorada = utilitario.novoId_Morada();
            morada = new Morada(idMorada, paisMorada, regiaoMorada, cidadeMorada);
            utilitario.registo_Morada(morada);

            System.out.println("idMorada " + idMorada);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            idEvento = utilitario.novoId_Evento();
            if (familiar != null) {
                evento = new Evento(idEvento, data_evento, tipo_evento, morada, descricao, paciente, familiar);
            } else {
                evento = new Evento(idEvento, data_evento, tipo_evento, morada, descricao, paciente);
            }
            utilitario.registo_Evento(evento);
            System.out.println("idEvento " + idEvento);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ServletContext sc = this.getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/Paciente.jsp");

        if (rd != null) {
            session = request.getSession();
            utilitario.devolve_Paciente(paciente.getId());
            session.setAttribute("paciente", paciente);
            System.out.println(paciente);
            rd.forward(request, response);
        }
    }
}
