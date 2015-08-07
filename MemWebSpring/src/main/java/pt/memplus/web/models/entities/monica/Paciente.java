package pt.memplus.web.models.entities.monica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import pt.memplus.web.models.enumerates.EspecialidadeMedico;
import pt.memplus.web.models.enumerates.TipoEscolaridade;
import pt.memplus.web.models.enumerates.TipoEstadoCivil;
import pt.memplus.web.models.enumerates.TipoGenero;
import pt.memplus.web.models.enumerates.TipoInteresses;

public class Paciente extends Pessoa implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoEscolaridade escolaridade;
    private String nivel_doenca;
    private String nome_medico;
    private EspecialidadeMedico especialidade_medico;
    private int nivel_sessao;
    private Cuidador cuidador; //Pensar como fazer
    private Tecnico tecnico;
//    private Utilitario utilitario;
    private ArrayList<String> atividades;
    private ArrayList<TipoInteresses> interesses;
    private int id;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAtividades(ArrayList<String> atividades) {
		this.atividades = atividades;
	}

	public void setInteresses(ArrayList<TipoInteresses> interesses) {
		this.interesses = interesses;
	}
public Paciente() {
	// TODO Auto-generated constructor stub
}
	public Paciente(int idPessoa, String nome_completo,
            Date data_de_nascimento, Morada local_nascimento, Morada morada,
            TipoGenero genero, String profissao, TipoEscolaridade escolaridade, 
            TipoEstadoCivil estado_civil, String nivel_doenca, String nome_medico,
            EspecialidadeMedico especialidade_medico, int nivel_sessao, Tecnico tecnico) {

        super(idPessoa, nome_completo, data_de_nascimento, local_nascimento,
                genero, estado_civil, profissao, morada);

		//setRelacoes();
        //setEventos();
        this.escolaridade = escolaridade;
        this.nivel_doenca = nivel_doenca;
        this.nome_medico = nome_medico;
        this.especialidade_medico = especialidade_medico;
        this.nivel_sessao = nivel_sessao;
        //this.cuidador = cuidador;
        this.tecnico = tecnico;
        this.atividades = new ArrayList<String>();
        this.interesses = new ArrayList<TipoInteresses>();
    }

    private void setRelacoes() {

//		if(utilitario.verTodos_Relacao_Paciente_Familiar()!=null){
//			for(Relacao r: utilitario.verTodos_Relacao_Paciente_Familiar()){
//				novaRelacao(r);
//			}
//		}
//		if(utilitario.verTodos_Relacao_Paciente_Familiar_Do_Paciente(this)!=null){
//			for(Relacao r:utilitario.verTodos_Relacao_Paciente_Familiar_Do_Paciente(this)){
//				novaRelacao(r);
//			}
//		}
//		if(utilitario.verTodos_Relacao_Familiar_Familiar(this)!=null){
//			for(Relacao r: utilitario.verTodos_Relacao_Familiar_Familiar(this)){
//				novaRelacao(r);
//			}
//		}
//		if(utilitario.verTodos_Familiares(this.getId())!=null){
//			for(Familiar f: utilitario.verTodos_Familiares(this.getId())){
//				if(utilitario.verTodos_Relacao_Familiar_Familiar_Do_Familiar(this, f)!=null){
//					for(Relacao r: utilitario.verTodos_Relacao_Familiar_Familiar_Do_Familiar(this, f))
//						if(r!=null)
//							novaRelacao(r);
//				}
//			}
//		}
    }

    public void setEventos() {
//        for (Evento e : utilitario.verTodos_Eventos(this)) {
//            novoEvento(e);
//        }
    }

    public void adicionaAtividades(String atividade) {
        this.atividades.add(atividade);
    }

    public void adicionaInteresses(TipoInteresses interesse) {
        if (!interesses.contains(interesse)) {
            this.interesses.add(interesse);
        }
    }
    
    public void removeAtividades(String atividade){
        if (atividades.contains(atividade))
            atividades.remove(atividade);
    }
    
    public void removeInteresses(TipoInteresses interesse){
        if(interesses.contains(interesse))
            interesses.remove(interesse);
    }

    //GETTERS:

    public TipoEscolaridade getEscolaridade() {
        return escolaridade;
    }

    public String getNivel_doenca() {
        return nivel_doenca;
    }

    public String getNome_medico() {
        return nome_medico;
    }

    public EspecialidadeMedico getEspecialidade_medico() {
        return especialidade_medico;
    }

    public int getNivel_sessao() {
        return nivel_sessao;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public ArrayList<TipoInteresses> getInteresses() {
        return interesses;
    }

    public ArrayList<String> getAtividades() {
        return atividades;
    }

    
    //SETTERS:
    public void setEscolaridade(TipoEscolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public void setNivel_doenca(String nivel_doenca) {
        this.nivel_doenca = nivel_doenca;
    }

    public void setNome_medico(String nome_medico) {
        this.nome_medico = nome_medico;
    }

    public void setEspecialidade_medico(EspecialidadeMedico especialidade_medico) {
        this.especialidade_medico = especialidade_medico;
    }

    public void setNivel_sessao(int nivel_sessao) {
        this.nivel_sessao = nivel_sessao;
    }

    public void setCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }
}
