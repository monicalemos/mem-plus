package db.models;
// default package
// Generated 10/fev/2015 20:18:28 by Hibernate Tools 4.3.1

/**
 * Jogo generated by hbm2java
 */
public class Jogo implements java.io.Serializable {

	private int idJogo;
	private Categoria categoria;
	private Interacao interacao;
	private String nome;
	private String nivel;

	public Jogo() {
	}

	public Jogo(int idJogo, Categoria categoria, Interacao interacao,
			String nome, String nivel) {
		this.idJogo = idJogo;
		this.categoria = categoria;
		this.interacao = interacao;
		this.nome = nome;
		this.nivel = nivel;
	}

	public int getIdJogo() {
		return this.idJogo;
	}

	public void setIdJogo(int idJogo) {
		this.idJogo = idJogo;
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

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

}
