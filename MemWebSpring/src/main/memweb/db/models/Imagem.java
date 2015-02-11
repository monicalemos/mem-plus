package db.models;
// default package
// Generated 10/fev/2015 20:18:28 by Hibernate Tools 4.3.1

/**
 * Imagem generated by hbm2java
 */
public class Imagem implements java.io.Serializable {

	private int idImagem;
	private String nome;
	private byte[] imagem;

	public Imagem() {
	}

	public Imagem(int idImagem) {
		this.idImagem = idImagem;
	}

	public Imagem(int idImagem, String nome, byte[] imagem) {
		this.idImagem = idImagem;
		this.nome = nome;
		this.imagem = imagem;
	}

	public int getIdImagem() {
		return this.idImagem;
	}

	public void setIdImagem(int idImagem) {
		this.idImagem = idImagem;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getImagem() {
		return this.imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

}
