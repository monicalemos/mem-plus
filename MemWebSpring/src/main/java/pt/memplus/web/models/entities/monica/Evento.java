package pt.memplus.web.models.entities.monica;

import java.io.Serializable;
import java.util.Date;

public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date data;
	private String tipo_de_evento;
	private Paciente paciente;
	private Morada local_evento;
	private Familiar familiar;
	private String descricao;
	
	public Evento() {}
	
	public Evento(int id, Date data, String tipo_evento, Morada local, String descricao, Paciente p, Familiar f) {
		this.id =id;
		this.data = data;
		this.tipo_de_evento = tipo_evento;
		this.local_evento = local;
		this.descricao = descricao;
		this.paciente = p;
		this.familiar = f;
	}
	
	public Evento(int id, Date data, String tipo_evento, Morada local, String descricao, Paciente p) {
		this.id =id;
		this.data = data;
		this.tipo_de_evento = tipo_evento;
		this.local_evento = local;
		this.descricao = descricao;
		this.paciente = p;
	}

	//GETTERS
	public int getId() {
		return id;
	}
	
	public Date getData() {
		return data;
	}
	
	public String getTipo_de_evento() {
		return tipo_de_evento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public Morada getLocal_evento() {
		return local_evento;
	}

	public Familiar getFamiliar() {
		return familiar;
	}

	public String getDescricao() {
		return descricao;
	}

	//SETTERS
	public void setData(Date data) {
		this.data = data;
	}
	
	public void setTipo_de_evento(String tipo_de_evento) {
		this.tipo_de_evento = tipo_de_evento;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void setLocal_evento(Morada local_evento) {
		this.local_evento = local_evento;
	}

	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
