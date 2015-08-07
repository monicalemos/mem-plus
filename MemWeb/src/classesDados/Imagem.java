package classesDados;

import java.io.File;
import java.io.Serializable;

public class Imagem implements Serializable{

	private File foto;
	private int id;
	private String nome;
	
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
