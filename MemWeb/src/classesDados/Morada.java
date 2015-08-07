package classesDados;

import java.io.Serializable;

public class Morada implements Serializable {
	private int id;
	private String pais;
	private String cidade;
	private String regiao;
	private Imagem foto;
	
	public Morada(int id, String pais, String cidade, String regiao) {
		this.id = id;
		this.pais = pais;
		this.cidade = cidade;
		this.regiao = regiao;
	}
	
	public void setFoto(Imagem foto) {
		this.foto = foto;
	}
	
	public Imagem getFoto() {
		return foto;
	}
	
	public int getId() {
		return id;
	}
	public String getPais() {
		return pais;
	}
	public String getCidade() {
		return cidade;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	
	@Override
	public String toString() {
		return pais + ", " + cidade + " - " + regiao;
	}
	
}
