package com.mem.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Questionario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int idQuestionario;
	@ManyToOne
	private Categoria categoria;
	@ManyToOne
	private Interacao interacao;
	private int numPerguntas;
	private String nivel;

	public Questionario() {
	}

	public Questionario(int idQuestionario, Categoria categoria, Interacao interacao, int numPerguntas, String nivel) {
		this.idQuestionario = idQuestionario;
		this.categoria = categoria;
		this.interacao = interacao;
		this.numPerguntas = numPerguntas;
		this.nivel = nivel;
	}

	public int getIdQuestionario() {
		return this.idQuestionario;
	}

	public void setIdQuestionario(int idQuestionario) {
		this.idQuestionario = idQuestionario;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Interacao getInteracao() {
		return this.interacao;
	}

	public void setInteracao(Interacao interacao) {
		this.interacao = interacao;
	}

	public int getNumPerguntas() {
		return this.numPerguntas;
	}

	public void setNumPerguntas(int numPerguntas) {
		this.numPerguntas = numPerguntas;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

}
