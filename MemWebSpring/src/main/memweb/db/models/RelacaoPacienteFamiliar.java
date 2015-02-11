package db.models;

// default package
// Generated 10/fev/2015 20:18:28 by Hibernate Tools 4.3.1

/**
 * RelacaoPacienteFamiliar generated by hbm2java
 */
public class RelacaoPacienteFamiliar implements java.io.Serializable {

	private RelacaoPacienteFamiliarId id;
	private Familiar familiar;
	private Paciente paciente;
	private String tipoRelacao;

	public RelacaoPacienteFamiliar() {
	}

	public RelacaoPacienteFamiliar(RelacaoPacienteFamiliarId id,
			Familiar familiar, Paciente paciente) {
		this.id = id;
		this.familiar = familiar;
		this.paciente = paciente;
	}

	public RelacaoPacienteFamiliar(RelacaoPacienteFamiliarId id,
			Familiar familiar, Paciente paciente, String tipoRelacao) {
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