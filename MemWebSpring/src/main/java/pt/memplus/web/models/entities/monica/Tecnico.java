package pt.memplus.web.models.entities.monica;

import java.io.Serializable;
import java.util.ArrayList;

import pt.memplus.web.models.enumerates.TipoUtilizador;

public class Tecnico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome_completo;
	private String[] nome;
	private String nomeProprio;
	private String apelido;
	private String nome_utilizador;
	private String password;
	private String email;
	private TipoUtilizador tipo_utilizador;

	private ArrayList<Paciente> pacientes;

	public Tecnico() {
		// TODO Auto-generated constructor stub
	}
	public Tecnico(int id, String nome, String nome_utilizador, String password, String email) {
		this.id = id;
		this.nome_completo = nome;
		setNomeProprio(nome_completo);
		setApelido(nome_completo);
		this.nome_utilizador = nome_utilizador;
		this.password = password;
		this.email = email;

		this.tipo_utilizador = TipoUtilizador.TECNICO;
		this.pacientes = new ArrayList<Paciente>();
	}
	//LISTA PACIENTES:
	public void adicionaPaciente (Paciente p){
		pacientes.add(p);
	}
	public Paciente getPaciente(String nomeCompleto){
		for(Paciente pac : pacientes){
			if( pac.getNome_completo() == nomeCompleto)
				return pac;
		}
		return null;
	}
	public boolean encontraPaciente(String nome){
		boolean encontrou = false;
		for(Paciente pac : pacientes){
			if( pac.getNome_completo() == nome){
				System.out.println("Esse paciente existe: \n");
				encontrou = true;
			}
		}
		System.out.println("Esse paciente não existe: \n");
		return encontrou;
	}
	public boolean eliminaPaciente(Paciente p){
		boolean eliminou = false;
		if(pacientes.contains(p)){
			System.out.println("Eliminou paciente \n");
			pacientes.remove(p);
		}
		System.out.println("Não eliminou o paciente \n");
		return eliminou;
	}

	//GETTERS:
	public String getNome_completo() {
		return nome_completo;
	}

	public String[] getNome() {
		return nome;
	}

	public String getNomeProprio() {
		return nomeProprio;
	}

	public String getApelido() {
		return apelido;
	}
	public String getNome_utilizador() {
		return nome_utilizador;
	}
	public String getPassword() {
		return password;
	}
	public TipoUtilizador getTipo_utilizador() {
		return tipo_utilizador;
	}
	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}
	public int getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}

	//SETTERS:
	public void setNomeCompleto(String nome) {
		this.nome_completo = nome;
	}
	
	public void setApelido(String nome){
		String[] nomeTemp = nome.split(" ");
		this.apelido = nomeTemp[nomeTemp.length - 1];
	}

	public void setNomeProprio(String nome){
		String[] nomeTemp = nome.split(" ");
		this.nomeProprio = nomeTemp[0];
	}
	public void setNome_utilizador(String nome_utilizador) {
		this.nome_utilizador = nome_utilizador;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String emailL) {
		this.email = emailL;
	}
}
