package com.mem.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "resposta", catalog = "memdb")
public class Resposta implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idResposta;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date data;
	private Integer tipo;
	private String resposta;
	private boolean acertou;
	private Pergunta pergunta;

	public Resposta(){
	}

	public Resposta(int idResposta, Date data, Integer tipo, String resposta, boolean acertou, Pergunta pergunta) {
		super();
		this.idResposta = idResposta;
		this.data = data;
		this.tipo = tipo;
		this.resposta = resposta;
		this.acertou = acertou;
		this.pergunta = pergunta;
	}

	@Id
	@GeneratedValue
	@Column(name = "idResposta", unique = true, nullable = false)
	public int getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(int idResposta) {
		this.idResposta = idResposta;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "data", length = 10)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	@Column(name = "tipo")
	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	@Column(name = "resposta", length = 100)
	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	@Column(name = "acertou", length = 2)
	public boolean isAcertou() {
		return acertou;
	}

	public void setAcertou(boolean acertou) {
		this.acertou = acertou;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPergunta", nullable = false)
	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	
	
}
