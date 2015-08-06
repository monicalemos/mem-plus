package com.mem.app.model;
// Generated 2/Ago/2015 23:24:41 by Hibernate Tools 4.3.1

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Categoria generated by hbm2java
 */
@Entity
public class Categoria implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int idCategoria;
	private String nome;
	private List<Jogo> jogos = new ArrayList<Jogo>(0);
	private List<Questionario> questionarios = new ArrayList<Questionario>(0);

	public Categoria() {
	}

	public Categoria(int idCategoria, String nome) {
		this.idCategoria = idCategoria;
		this.nome = nome;
	}

	public Categoria(int idCategoria, String nome, List<Jogo> jogos, List<Questionario> questionarios) {
		this.idCategoria = idCategoria;
		this.nome = nome;
		this.jogos = jogos;
		this.questionarios = questionarios;
	}

	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Jogo> getJogos() {
		return this.jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	public List<Questionario> getQuestionarios() {
		return this.questionarios;
	}

	public void setQuestionarios(List<Questionario> questionarios) {
		this.questionarios = questionarios;
	}

}