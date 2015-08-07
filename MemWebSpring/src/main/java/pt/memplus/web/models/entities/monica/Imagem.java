package pt.memplus.web.models.entities.monica;

import java.io.File;
import java.io.Serializable;

public class Imagem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File foto;
	private int id;
	private String nome;
	
	public Imagem() {
		// TODO Auto-generated constructor stub
	}
	
	public Imagem(int id, String nome, File foto) {
		this.id = id;
		this.nome = nome;
		this.foto = foto;
	}
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	public File getFoto() {
		return foto;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setFoto(File foto) {
		this.foto = foto;
	}
}
