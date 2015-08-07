package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
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

@WebServlet("/ServletEditarEvento")
public class ServletEditarEvento extends HttpServlet {

    private static final long serialVersionUID = 1L;
    HttpSession session = null;

    public ServletEditarEvento() {
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

        Evento evento = (Evento) session.getAttribute("evento");
        System.out.println("tem evento?" + session.getAttribute("evento"));
        System.out.println("descircao evento? " + evento.getDescricao());
        System.out.println("Tem id evento? " + evento.getId());

		// reading the user input
        String dataDoEvento = request.getParameter("data_nascimento");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date data_evento = null;
        try {
            data_evento = sdf.parse(dataDoEvento);
        } catch (ParseException e) {

        }
//        String dataDoEvento = request.getParameter("data_evento");
//        String[] dataEvento = dataDoEvento.split("/");
//        Date data_evento = new Date(Integer.parseInt(dataEvento[2]), Integer.parseInt(dataEvento[1]), Integer.parseInt(dataEvento[0]));

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

        ArrayList<Familiar> familiares = utilitario.verTodos_Familiares(paciente.getId());
        for (Familiar f : familiares) {
            if (f.getId() == idFamiliar) {
                familiar = f;
            }
        }

        evento.getLocal_evento().setPais(paisMorada);
        evento.getLocal_evento().setRegiao(regiaoMorada);
        evento.getLocal_evento().setCidade(cidadeMorada);
        Morada morada = evento.getLocal_evento();
        utilitario.edita_Morada(morada);

        if (familiar != null) {
            evento.setData(data_evento);
            evento.setTipo_de_evento(tipo_evento);
            evento.setDescricao(descricao);
            evento.setFamiliar(familiar);
        } else {
            evento.setData(data_evento);
            evento.setTipo_de_evento(tipo_evento);
            evento.setDescricao(descricao);

        }

        utilitario.edita_Evento(evento);
        System.out.println("idEvento " + idEvento);

        ServletContext sc = this.getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("defaultLayout");

        if (rd != null) {
            session = request.getSession();
            utilitario.devolve_Paciente(paciente.getId());
            session.setAttribute("paciente", paciente);
            System.out.println(paciente);
            rd.forward(request, response);
        }
    }
}
