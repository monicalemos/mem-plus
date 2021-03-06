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
 * RelacaoPacienteFamiliar generated by hbm2java
 */
@Entity
@Table(name = "RelacaoPacienteFamiliar", catalog = "memdb")
public class RelacaoPacienteFamiliar implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RelacaoPacienteFamiliarId id;
	private Familiar familiar;
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

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idRelacaoPacienteFamiliar", column = @Column(name = "idRelacaoPacienteFamiliar", nullable = false) ),
			@AttributeOverride(name = "idPaciente", column = @Column(name = "idPaciente", nullable = false) ),
			@AttributeOverride(name = "idFamiliar", column = @Column(name = "idFamiliar", nullable = false) ) })
	public RelacaoPacienteFamiliarId getId() {
		return this.id;
	}

	public void setId(RelacaoPacienteFamiliarId id) {
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

	@Override
	public String toString() {
		return "RelacaoPacienteFamiliar [\nid=" + id + ", \nfamiliar=" + familiar + ", \npaciente=" + paciente
				+ ", tipoRelacao=" + tipoRelacao + "]";
	}

}
