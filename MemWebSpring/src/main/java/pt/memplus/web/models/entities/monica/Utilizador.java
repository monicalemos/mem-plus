package pt.memplus.web.models.entities.monica;

import pt.memplus.web.models.enumerates.TipoUtilizador;

public class Utilizador {

	private String nome_utilizador;
	private String password;
//	private Paciente paciente;
	private Familiar familiar;
	private Tecnico tecnico;
	private TipoUtilizador tipo;

	public Utilizador() {
		// TODO Auto-generated constructor stub
	}

	public Utilizador(String nome_utilizador, String pass, Familiar f) {
		this.nome_utilizador = nome_utilizador;
		this.password = pass;
		this.familiar = f;
		f.setNome_utilizador(this.nome_utilizador);
		f.setPassword(this.password);
		this.tipo = TipoUtilizador.CUIDADOR;
	}

	public Utilizador(String nome_utilizador, String pass, Tecnico t) {
		this.nome_utilizador = nome_utilizador;
		this.password = pass;
		this.tecnico = t;
		t.setNome_utilizador(this.nome_utilizador);
		t.setPassword(this.password);
		this.tipo = TipoUtilizador.TECNICO;
	}

	public String getNome_utilizador() {
		return nome_utilizador;
	}

	public String getPassword() {
		return password;
	}

	public TipoUtilizador getTipo() {
		return tipo;
	}

	public String getUtilizador() {
		if (tipo == TipoUtilizador.CUIDADOR) {
			// getFamiliar();
			return "familiar";
		}

		else if (tipo == TipoUtilizador.TECNICO) {
			// getTecnico();
			return "tecnico";
		}
		return null;
	}

	public Familiar getFamiliar() {
		return familiar;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setNome_utilizador(String nome_utilizador) {
		this.nome_utilizador = nome_utilizador;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
