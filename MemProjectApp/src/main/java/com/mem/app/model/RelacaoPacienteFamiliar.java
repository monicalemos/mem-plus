package com.mem.app.model;
// Generated 21/Ago/2015 19:35:12 by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Relacaopacientefamiliar generated by hbm2java
 */
@Entity
@Table(name = "relacaopacientefamiliar", catalog = "memdb")
public class Relacaopacientefamiliar implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RelacaopacientefamiliarId id;
	private Familiar familiar;
	private Paciente paciente;
	private String tipoRelacao;

	public Relacaopacientefamiliar() {
	}

	public Relacaopacientefamiliar(RelacaopacientefamiliarId id, Familiar familiar, Paciente paciente) {
		this.id = id;
		this.familiar = familiar;
		this.paciente = paciente;
	}

	public Relacaopacientefamiliar(RelacaopacientefamiliarId id, Familiar familiar, Paciente paciente,
			String tipoRelacao) {
		this.id = id;
		this.familiar = familiar;
		this.paciente = paciente;
		this.tipoRelacao = tipoRelacao;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idRelacaoPacienteFamiliar", column = @Column(name = "idRelacaoPacienteFamiliar", nullable = false) ),
			@AttributeOverride(name = "idPaciente", column = @Column(name = "idPaciente", nullable = false) ),
			@AttributeOverride(name = "idFamiliar", column = @Column(name = "idFamiliar", nullable = false) ) })
	public RelacaopacientefamiliarId getId() {
		return this.id;
	}

	public void setId(RelacaopacientefamiliarId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idFamiliar", nullable = false, insertable = false, updatable = false)
	public Familiar getFamiliar() {
		return this.familiar;
	}

	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPaciente", nullable = false, insertable = false, updatable = false)
	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@Column(name = "tipoRelacao", length = 45)
	public String getTipoRelacao() {
		return this.tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

}
