package com.mem.app.model;
// Generated 2/Ago/2015 23:24:41 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Interacao generated by hbm2java
 */
@Entity
public class Interacao implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int idInteracao;
	@ManyToOne
	private Paciente paciente;
	private Date data;
	
	private List<Questionario> questionarios = new ArrayList<Questionario>(0);
	private List<Jogo> jogos = new ArrayList<Jogo>(0);

	public Interacao() {
	}

	public Interacao(int idInteracao, Paciente paciente, Date data) {
		this.idInteracao = idInteracao;
		this.paciente = paciente;
		this.data = data;
	}

	public Interacao(int idInteracao, Paciente paciente, Date data, List<Questionario> questionarios, List<Jogo> jogos) {
		this.idInteracao = idInteracao;
		this.paciente = paciente;
		this.data = data;
		this.questionarios = questionarios;
		this.jogos = jogos;
	}

	public int getIdInteracao() {
		return this.idInteracao;
	}

	public void setIdInteracao(int idInteracao) {
		this.idInteracao = idInteracao;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Questionario> getQuestionarios() {
		return this.questionarios;
	}

	public void setQuestionarios(List<Questionario> questionarios) {
		this.questionarios = questionarios;
	}

	public List<Jogo> getJogos() {
		return this.jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

}