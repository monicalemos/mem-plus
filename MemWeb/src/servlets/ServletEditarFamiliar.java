package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classesDados.Familiar;
import classesDados.Morada;
import classesDados.Paciente;
import classesDados.Relacao;
import classesDados.Tecnico;
import enumerados.TipoEstadoCivil;
import enumerados.TipoGenero;
import enumerados.TipoRelacao;
import gestor.Utilitario;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/ServletEditarFamiliar")
public class ServletEditarFamiliar extends HttpServlet {

    private static final long serialVersionUID = 1L;
    HttpSession session = null;

    public ServletEditarFamiliar() {
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

        Familiar familiar = (Familiar) session.getAttribute("familiar");
        System.out.println("tem familiar?" + session.getAttribute("familiar"));
        System.out.println("nome familiar? " + familiar.getNome_completo());
        System.out.println("Tem id familiar? " + familiar.getId());

        // reading the user input
        TipoRelacao tipoRelacao = TipoRelacao.valueOf(request.getParameter("tipo_relacao").toUpperCase());

        String nome = request.getParameter("nome");
        
        String dataDeNascimento = request.getParameter("data_nascimento");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         java.util.Date data_nascimento = null;
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
        TipoEstadoCivil estado_civil = TipoEstadoCivil.valueOf(request.getParameter("estado_civil").toUpperCase());       

        String profissao = request.getParameter("profissao");

        boolean eCuidador = request.getParameter("eCuidador") != null;

        String nomeUtilizador = null;
        String password = null;
        if (request.getParameter("e_cuidador") != null) {
            eCuidador = true;
            nomeUtilizador = request.getParameter("nome_utilizador");
            password = request.getParameter("password");
        }

        int idLocalNascimento = 0;
        int idMorada = 0;
        int idFamiliar = 0;
        int idRelacao = 0;
        //		int idFotoLocalNascimento = 0;
        //		int idFotoMorada = 0;
        //		int idFotoPaciente = 0;

        Relacao relacao = null;

        familiar.getLocal_nascimento().setPais(paisNascimento);
        familiar.getLocal_nascimento().setRegiao(regiaoNascimento);
        familiar.getLocal_nascimento().setCidade(cidadeNascimento);
        Morada localNascimento = familiar.getLocal_nascimento();
        utilitario.edita_Morada(localNascimento);
        System.out.println("idLocalNascimemto " + idLocalNascimento);

        familiar.getMorada().setPais(paisMorada);
        familiar.getMorada().setRegiao(regiaoMorada);
        familiar.getMorada().setCidade(cidadeMorada);
        Morada morada = familiar.getMorada();
        utilitario.edita_Morada(morada);
        System.out.println("idMorada " + idMorada);

        System.out.println("id familiar: " + familiar.getId());
        if (eCuidador) {
            familiar.setNomeCompleto(nome);
            familiar.setData_de_nascimento(data_nascimento);
            familiar.setGenero(genero);
            familiar.setEstadoCivil(estado_civil);
            familiar.setProfissao(profissao);
            familiar.seteCuidador(eCuidador);
            familiar.setNome_utilizador(nomeUtilizador);
            familiar.setPassword(password);
        } else {
            familiar.setNomeCompleto(nome);
            familiar.setData_de_nascimento(data_nascimento);
            familiar.setGenero(genero);
            familiar.setEstadoCivil(estado_civil);
            familiar.setProfissao(profissao);
            familiar.seteCuidador(eCuidador);
        }
        utilitario.registo_Familiar(familiar);
        System.out.println("registou familiar");

//        try {
//            idRelacao = utilitario.novoId_Relacao_Paciente_Familiar();
//            relacao = new Relacao(idRelacao, paciente, tecnico, familiar, tipoRelacao);
//
//            utilitario.registo_Relacao_Paciente_Familiar(relacao);
//            System.out.println("registou relacao");
//
//            if (familiar.getRelacoes() != null & !familiar.getRelacoes().contains(relacao)) {
//                familiar.novaRelacao(relacao);
//                System.out.println("Nova relacao no familiar " + relacao.getId());
//            } else {
//                System.out.println("esta relacao ja existe no familiar");
//            }
//
//            if (!paciente.getRelacoes().contains(relacao)) {
//                paciente.novaRelacao(relacao);
//                System.out.println("Nova relacao no paciente " + relacao.getId());
//            } else {
//                System.out.println("esta relacao ja existe no paciente");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        ServletContext sc = this.getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/Familiar.jsp");

        if (rd != null) {
            session = request.getSession();
            utilitario.devolve_Paciente(paciente.getId());
            session.setAttribute("paciente", paciente);
            utilitario.devolve_Familiar(familiar.getId());
            System.out.println(familiar);
            rd.forward(request, response);
        }
    }
}
