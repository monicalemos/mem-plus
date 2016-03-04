package com.mem.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pergunta", catalog = "memdb")
public class Pergunta implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idPergunta;
	private String pergunta;
	private Questionario questionario;
	private List<Resposta> respostas = new ArrayList<Resposta>(0);;
	
	public Pergunta(){
		
	}
	
	public Pergunta(int idPergunta, String pergunta, Questionario questionario) {
		this.idPergunta = idPergunta;
		this.pergunta = pergunta;
		this.questionario = questionario;
	}
	
	public Pergunta(int idPergunta, String pergunta, Questionario questionario, List<Resposta> perguntas) {
		this.idPergunta = idPergunta;
		this.pergunta = pergunta;
		this.questionario = questionario;
		this.respostas = perguntas;
	}

	@Id
	@GeneratedValue
	@Column(name = "idPergunta", unique = true, nullable = false)
	public int getIdPergunta() {
		return idPergunta;
	}

	public void setIdPergunta(int idPergunta) {
		this.idPergunta = idPergunta;
	}

	@Column(name = "pergunta", length = 100)
	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idQuestionario", nullable = false)
	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pergunta")
	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> perguntas) {
		this.respostas = perguntas;
	}
	
	public void addResposta(Resposta resposta){
		this.respostas.add(resposta);
	}

	@Override
	public String toString() {
		return "Pergunta [idPergunta=" + idPergunta + ", questionario=" + questionario + ", respostas=" + respostas
				+ "]";
	}
}
