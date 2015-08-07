package pt.memplus.web.models.entities.monica;

import java.util.Date;

import pt.memplus.web.models.enumerates.TipoEstadoCivil;
import pt.memplus.web.models.enumerates.TipoGenero;


public class Familiar extends Pessoa {
	private static final long serialVersionUID = 1L;
	private int telefone;
    private boolean eCuidador;
    private Date data_obito;
    private String nome_utilizador;
    private String password;

    public Familiar() {
		// TODO Auto-generated constructor stub
	}
    
    public Familiar(int id, String nome_completo,
            Date data_de_nascimento, Morada local_nascimento, Morada morada,
            TipoGenero genero, TipoEstadoCivil estado_civil, String profissao, int telefone, boolean eCuidador) {
        super(id, nome_completo, data_de_nascimento, local_nascimento,
                genero, estado_civil, profissao, morada);
        this.eCuidador = eCuidador;
        this.telefone = telefone;
    }

    public Familiar(int id, String nome_completo,
            Date data_de_nascimento, Morada local_nascimento, Morada morada,
            TipoGenero genero, TipoEstadoCivil estado_civil, String profissao, int telefone, boolean eCuidador,
            String nome_utilizador, String password) {
        super(id, nome_completo, data_de_nascimento, local_nascimento,
                genero, estado_civil, profissao, morada);

        this.eCuidador = eCuidador;
        this.telefone = telefone;

        if (eCuidador == true) {
            this.nome_utilizador = nome_utilizador;
            this.password = password;
        }
    }

    //GETTERS:
    public int getTelefone() {
        return telefone;
    }

    public boolean eCuidador() {
        return eCuidador;
    }

    public Date getData_obito() {
        return data_obito;
    }

    public String getNome_utilizador() {
        return nome_utilizador;
    }

    public String getPassword() {
        return password;
    }

    //SETTERS
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
    
    public void seteCuidador(boolean eCuidador) {
        this.eCuidador = eCuidador;
    }

    public void setData_obito(Date data_obito) {
        this.data_obito = data_obito;
    }

    public void setNome_utilizador(String nome_utilizador) {
        this.nome_utilizador = nome_utilizador;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
