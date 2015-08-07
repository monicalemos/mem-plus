package servlets;

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

import classesDados.Morada;
import classesDados.Paciente;
import classesDados.Tecnico;
import enumerados.EspecialidadeMedico;
import enumerados.TipoEscolaridade;
import enumerados.TipoEstadoCivil;
import enumerados.TipoGenero;
import gestor.Utilitario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/ServletEditarPaciente")
public class ServletEditarPaciente extends HttpServlet {

    private static final long serialVersionUID = 1L;
    HttpSession session = null;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @SuppressWarnings("deprecation")
    @Override
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
        String nome = request.getParameter("nome");

        String dataDeNascimento = request.getParameter("data_nascimento");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         Date data_nascimento = null;
        try {
            data_nascimento = sdf.parse(dataDeNascimento);
        } catch (ParseException e) {

        }

//        String dataDeNascimento = request.getParameter("data_nascimento");
//        String[] dataNascimento = dataDeNascimento.split("/");
//        Date data_nascimento = new Date(Integer.parseInt(dataNascimento[2]), Integer.parseInt(dataNascimento[1]), Integer.parseInt(dataNascimento[0]));
        
        String paisNascimento = request.getParameter("pais_nascimento");
        String regiaoNascimento = request.getParameter("regiao_nascimento");
        String cidadeNascimento = request.getParameter("cidade_nascimento");

        String paisMorada = request.getParameter("pais_atual");
        String regiaoMorada = request.getParameter("regiao_atual");
        String cidadeMorada = request.getParameter("cidade_atual");

        TipoGenero genero = TipoGenero.valueOf(request.getParameter("genero").toUpperCase());
        String profissao = request.getParameter("profissao");
        TipoEscolaridade escolaridade = TipoEscolaridade.valueOf(request.getParameter("escolaridade").toUpperCase());
        TipoEstadoCivil estadoCivil = TipoEstadoCivil.valueOf(request.getParameter("estado_civil").toUpperCase());

        String nivelDoenca = request.getParameter("nivel_doenca");
        String nomeMedico = request.getParameter("nome_medico");
        EspecialidadeMedico especialidadeMedico = EspecialidadeMedico.valueOf(request.getParameter("especialidade_medico").toUpperCase());

        int nivelSessao = Integer.parseInt(request.getParameter("nivel_sessao"));

        int idLocalNascimento = 0;
        int idMorada = 0;
        int idPaciente = 0;
        //		int idFotoLocalNascimento = 0;
        //		int idFotoMorada = 0;
        //		int idFotoPaciente = 0;

        paciente.getLocal_nascimento().setPais(paisNascimento);
        paciente.getLocal_nascimento().setRegiao(regiaoNascimento);
        paciente.getLocal_nascimento().setCidade(cidadeNascimento);
        Morada localNascimento = paciente.getLocal_nascimento();
        utilitario.edita_Morada(localNascimento);
        System.out.println("idLocalNascimemto " + idLocalNascimento);

        paciente.getMorada().setPais(paisMorada);
        paciente.getMorada().setRegiao(regiaoMorada);
        paciente.getMorada().setCidade(cidadeMorada);
        Morada morada = paciente.getMorada();
        utilitario.edita_Morada(morada);
        System.out.println("idMorada " + idMorada);

        paciente.setNomeCompleto(nome);
        paciente.setData_de_nascimento(data_nascimento);
        paciente.setGenero(genero);
        paciente.setProfissao(profissao);
        paciente.setEscolaridade(escolaridade);
        paciente.setEstadoCivil(estadoCivil);
        paciente.setNivel_doenca(nivelDoenca);
        paciente.setNome_medico(nomeMedico);
        paciente.setEspecialidade_medico(especialidadeMedico);
        paciente.setNivel_sessao(nivelSessao);

        utilitario.edita_Paciente(paciente);

        ServletContext sc = this.getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/Paciente.jsp");

        if (rd != null) {
            session = request.getSession();
            utilitario.devolve_Paciente(idPaciente);
            session.setAttribute("paciente", paciente);
            System.out.println(paciente);
            rd.forward(request, response);
        }
    }
}
