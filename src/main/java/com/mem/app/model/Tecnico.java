package com.mem.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Tecnico implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int idTecnico;
	@ManyToOne
	private Utilizador utilizador;
	private String apelido;
	private String email;
	private List<Paciente> pacientes = new ArrayList<Paciente>(0);

	public Tecnico() {
	}

	public Tecnico(int idTecnico, Utilizador utilizador) {
		this.idTecnico = idTecnico;
		this.utilizador = utilizador;
	}

	public Tecnico(int idTecnico, Utilizador utilizador, String apelido, String email, List<Paciente> pacientes) {
		this.idTecnico = idTecnico;
		this.utilizador = utilizador;
		this.apelido = apelido;
		this.email = email;
		this.pacientes = pacientes;
	}

	public int getIdTecnico() {
		return this.idTecnico;
	}

	public void setIdTecnico(int idTecnico) {
		this.idTecnico = idTecnico;
	}

	public Utilizador getUtilizador() {
		return this.utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public String getApelido() {
		return this.apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Paciente> getPacientes() {
		return this.pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

}
