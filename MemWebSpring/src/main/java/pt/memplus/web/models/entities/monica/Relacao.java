package pt.memplus.web.models.entities.monica;

import java.io.Serializable;

import pt.memplus.web.models.enumerates.TipoRelacao;


public class Relacao implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Paciente paciente;
	private Familiar familiar_nivel1;
	private Familiar familiar_nivel2;
	private TipoRelacao tipo_relacao;
	
	
	public Relacao() {
		// TODO Auto-generated constructor stub
	}
	public Relacao(int id, Paciente paciente, Familiar  familiar_nivel1, TipoRelacao tipo_relacao) {
		this.id = id;
		this.paciente = paciente;
		this.familiar_nivel1 = familiar_nivel1;
		this.tipo_relacao = tipo_relacao;
	}

	public Relacao(int id, Paciente paciente, Familiar familiar1, Familiar familiar2, TipoRelacao tipo){
		this.id=id;
		this.paciente = paciente;
		this.familiar_nivel1 = familiar1;
		this.familiar_nivel2 = familiar2;
		this.tipo_relacao = tipo;
	}
	//GETTERS:
	public int getId() {
		return id;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	public Familiar getFamiliar_nivel1() {
		return familiar_nivel1;
	}
	public Familiar getFamiliar_nivel2() {
		return familiar_nivel2;
	}
	public TipoRelacao getTipo_relacao() {
		return tipo_relacao;
	}

	//SETTERS:
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public void setTipo_relacao(TipoRelacao tipo_relacao) {
		this.tipo_relacao = tipo_relacao;
	}
	public void setFamiliar_nivel1(Familiar familiar_nivel1) {
		this.familiar_nivel1 = familiar_nivel1;
	}
	public void setFamiliar_nivel2(Familiar familiar_nivel2) {
		this.familiar_nivel2 = familiar_nivel2;
	}	
}
