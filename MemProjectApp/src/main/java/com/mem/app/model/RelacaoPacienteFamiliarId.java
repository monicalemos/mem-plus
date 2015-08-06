package com.mem.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class RelacaoPacienteFamiliarId implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idRelacaoPacienteFamiliar;
	@Id
	@GeneratedValue
	private int familiarIdFamiliar;
	private int pacienteIdPaciente;

	public RelacaoPacienteFamiliarId() {
	}

	public RelacaoPacienteFamiliarId(int idRelacaoPacienteFamiliar, int familiarIdFamiliar, int pacienteIdPaciente) {
		this.idRelacaoPacienteFamiliar = idRelacaoPacienteFamiliar;
		this.familiarIdFamiliar = familiarIdFamiliar;
		this.pacienteIdPaciente = pacienteIdPaciente;
	}

	public int getIdRelacaoPacienteFamiliar() {
		return this.idRelacaoPacienteFamiliar;
	}

	public void setIdRelacaoPacienteFamiliar(int idRelacaoPacienteFamiliar) {
		this.idRelacaoPacienteFamiliar = idRelacaoPacienteFamiliar;
	}

	public int getFamiliarIdFamiliar() {
		return this.familiarIdFamiliar;
	}

	public void setFamiliarIdFamiliar(int familiarIdFamiliar) {
		this.familiarIdFamiliar = familiarIdFamiliar;
	}

	public int getPacienteIdPaciente() {
		return this.pacienteIdPaciente;
	}

	public void setPacienteIdPaciente(int pacienteIdPaciente) {
		this.pacienteIdPaciente = pacienteIdPaciente;
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
				&& (this.getFamiliarIdFamiliar() == castOther.getFamiliarIdFamiliar())
				&& (this.getPacienteIdPaciente() == castOther.getPacienteIdPaciente());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdRelacaoPacienteFamiliar();
		result = 37 * result + this.getFamiliarIdFamiliar();
		result = 37 * result + this.getPacienteIdPaciente();
		return result;
	}

}
