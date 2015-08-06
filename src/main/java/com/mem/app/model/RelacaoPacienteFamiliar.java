package com.mem.app.model;
// Generated 2/Ago/2015 23:24:41 by Hibernate Tools 4.3.1

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class RelacaoPacienteFamiliar implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private RelacaoPacienteFamiliarId id;
	@ManyToOne
	private Familiar familiar;
	@ManyToOne
	private Paciente paciente;
	private String tipoRelacao;

	public RelacaoPacienteFamiliar() {
	}

	public RelacaoPacienteFamiliar(RelacaoPacienteFamiliarId id, Familiar familiar, Paciente paciente) {
		this.id = id;
		this.familiar = familiar;
		this.paciente = paciente;
	}

	public RelacaoPacienteFamiliar(RelacaoPacienteFamiliarId id, Familiar familiar, Paciente paciente,
			String tipoRelacao) {
		this.id = id;
		this.familiar = familiar;
		this.paciente = paciente;
		this.tipoRelacao = tipoRelacao;
	}

	public RelacaoPacienteFamiliarId getId() {
		return this.id;
	}

	public void setId(RelacaoPacienteFamiliarId id) {
		this.id = id;
	}

	public Familiar getFamiliar() {
		return this.familiar;
	}

	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getTipoRelacao() {
		return this.tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

}
