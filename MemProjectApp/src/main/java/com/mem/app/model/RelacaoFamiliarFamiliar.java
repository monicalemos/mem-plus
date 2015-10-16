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
 * Relacaofamiliarfamiliar generated by hbm2java
 */
@Entity
@Table(name = "relacaofamiliarfamiliar", catalog = "memdb")
public class RelacaoFamiliarFamiliar implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RelacaoFamiliarFamiliarId id;
	private Familiar familiarByIdFamiliar;
	private Familiar familiarByIdFamiliar1;
	private Paciente paciente;
	private String tipoRelacao;

	public RelacaoFamiliarFamiliar() {
	}

	public RelacaoFamiliarFamiliar(RelacaoFamiliarFamiliarId id, Familiar familiarByIdFamiliar,
			Familiar familiarByIdFamiliar1, Paciente paciente) {
		this.id = id;
		this.familiarByIdFamiliar = familiarByIdFamiliar;
		this.familiarByIdFamiliar1 = familiarByIdFamiliar1;
		this.paciente = paciente;
	}

	public RelacaoFamiliarFamiliar(RelacaoFamiliarFamiliarId id, Familiar familiarByIdFamiliar,
			Familiar familiarByIdFamiliar1, Paciente paciente, String tipoRelacao) {
		this.id = id;
		this.familiarByIdFamiliar = familiarByIdFamiliar;
		this.familiarByIdFamiliar1 = familiarByIdFamiliar1;
		this.paciente = paciente;
		this.tipoRelacao = tipoRelacao;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idRelacaoFamiliarFamiliar", column = @Column(name = "idRelacaoFamiliarFamiliar", nullable = false) ),
			@AttributeOverride(name = "idFamiliar", column = @Column(name = "idFamiliar", nullable = false) ),
			@AttributeOverride(name = "idFamiliar1", column = @Column(name = "idFamiliar1", nullable = false) ) })
	public RelacaoFamiliarFamiliarId getId() {
		return this.id;
	}

	public void setId(RelacaoFamiliarFamiliarId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idFamiliar1", nullable = false, insertable = false, updatable = false)
	public Familiar getFamiliarByIdFamiliar1() {
		return this.familiarByIdFamiliar1;
	}

	public void setFamiliarByIdFamiliar1(Familiar familiarByIdFamiliar1) {
		this.familiarByIdFamiliar1 = familiarByIdFamiliar1;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idFamiliar", nullable = false, insertable = false, updatable = false)
	public Familiar getFamiliarByIdFamiliar() {
		return this.familiarByIdFamiliar;
	}

	public void setFamiliarByIdFamiliar(Familiar familiarByIdFamiliar) {
		this.familiarByIdFamiliar = familiarByIdFamiliar;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPaciente", nullable = false)
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
