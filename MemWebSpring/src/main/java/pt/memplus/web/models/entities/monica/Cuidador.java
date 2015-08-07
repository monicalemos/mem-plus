package pt.memplus.web.models.entities.monica;

import pt.memplus.web.models.enumerates.TipoUtilizador;

public class Cuidador {
	
	private String nome_utilizador;
	private String password;
	private TipoUtilizador tipo_utilizador;
	private Paciente paciente;
	private Familiar familiar;
	
	public Cuidador() {
		
	}
	
	public Cuidador(String nome_utilizador, String password, Paciente paciente, Familiar familiar) {
		super();
		this.nome_utilizador = nome_utilizador;
		this.password = password;
		this.tipo_utilizador = TipoUtilizador.CUIDADOR;
		this.paciente = paciente;
		this.familiar = familiar;
	}
	
	//GETTERS:
	public String getNome_utilizador() {
		return nome_utilizador;
	}
	public String getPassword() {
		return password;
	}
	public TipoUtilizador getTipo_utilizador() {
		return tipo_utilizador;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public Familiar getFamiliar() {
		return familiar;
	}
	
	//SETTERS:
	public void setNome_utilizador(String nome_utilizador) {
		this.nome_utilizador = nome_utilizador;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}
}
