package com.mem.app.model;
// Generated 21/Ago/2015 19:35:12 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

/**
 * RelacaoPacienteFamiliarId generated by hbm2java
 */
@Embeddable
public class RelacaoPacienteFamiliarId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idRelacaoPacienteFamiliar;
	private int idPaciente;
	private int idFamiliar;

	public RelacaoPacienteFamiliarId() {
	}

	public RelacaoPacienteFamiliarId(int idRelacaoPacienteFamiliar, int idPaciente, int idFamiliar) {
		this.idRelacaoPacienteFamiliar = idRelacaoPacienteFamiliar;
		this.idPaciente = idPaciente;
		this.idFamiliar = idFamiliar;
	}

	@GeneratedValue
	@Column(name = "idRelacaoPacienteFamiliar", nullable = false)
	public int getIdRelacaoPacienteFamiliar() {
		return this.idRelacaoPacienteFamiliar;
	}

	public void setIdRelacaoPacienteFamiliar(int idRelacaoPacienteFamiliar) {
		this.idRelacaoPacienteFamiliar = idRelacaoPacienteFamiliar;
	}

	@Column(name = "idPaciente", nullable = false)
	public int getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	@Column(name = "idFamiliar", nullable = false)
	public int getIdFamiliar() {
		return this.idFamiliar;
	}

	public void setIdFamiliar(int idFamiliar) {
		this.idFamiliar = idFamiliar;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RelacaoPacienteFamiliarId))
			return false;
		RelacaoPacienteFamiliarId castOther = (RelacaoPacienteFamiliarId) other;

		return (this.getIdRelacaoPacienteFamiliar() == castOther.getIdRelacaoPacienteFamiliar())
				&& (this.getIdPaciente() == castOther.getIdPaciente())
				&& (this.getIdFamiliar() == castOther.getIdFamiliar());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdRelacaoPacienteFamiliar();
		result = 37 * result + this.getIdPaciente();
		result = 37 * result + this.getIdFamiliar();
		return result;
	}

	@Override
	public String toString() {
		return "RelacaoPacienteFamiliarId [idRelacaoPacienteFamiliar=" + idRelacaoPacienteFamiliar + ", idPaciente="
				+ idPaciente + ", idFamiliar=" + idFamiliar + "]";
	}

}
