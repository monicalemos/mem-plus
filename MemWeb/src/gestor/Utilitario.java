package gestor;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.BCrypt;
import baseDados.LigacaoBD;
import classesDados.Evento;
import classesDados.Familiar;
import classesDados.Imagem;
import classesDados.Morada;
import classesDados.Paciente;
import classesDados.Relacao;
import classesDados.Tecnico;


public class Utilitario {
	private final LigacaoBD ligacaoBD;
	private final GestorBD gestorBD;

	public Utilitario() {
		ligacaoBD = new LigacaoBD("root","");
		ligacaoBD.connect();
		gestorBD = new GestorBD(ligacaoBD.getConnection());
	}

	//IMAGEM
	
	public void registo_Imagem(Imagem foto) throws FileNotFoundException{
		gestorBD.insert_Imagem(foto);
	}
	
	public int novoId_Imagem() throws SQLException{
		int iVelho = gestorBD.verificaId_Imagem();
		return iVelho+1;
	}
	
	
	//PACIENTE
	public void registo_Paciente(Paciente paciente){
		gestorBD.insert_Paciente(paciente);
	}

	public int novoId_Paciente() throws SQLException{
		int iVelho = gestorBD.verificaId_Paciente();
		return iVelho+1;
	}

	public ArrayList<Paciente> verTodos_Pacientes(int idTecnico){
		ArrayList<Paciente> pacientes = gestorBD.selectAll_Pacientes(idTecnico);
//		for(Paciente p : pacientes ){
//			return p.toString() + "\n";
//		}
		return pacientes;
	}
	
	public Paciente devolve_Paciente(int id_paciente){
		return gestorBD.select_PacienteId(id_paciente);
	}
		
	public void apaga_PacienteNome(String nomeCompleto){
		gestorBD.delete_PacienteNome(nomeCompleto);
	}
        public void edita_Paciente(Paciente p){
            gestorBD.update_Paciente(p);
        }
	public void apaga_Paciente(Paciente p){
		gestorBD.delete_Paciente(p);
	}

	//MORADA
	public void registo_Morada(Morada morada){
		gestorBD.insert_Morada(morada);
	}
	
	public int novoId_Morada() throws SQLException {
		int iVelho = gestorBD.verificaId_Morada();
		//System.out.println("iVelho " + iVelho);
		int iNovo = iVelho+1;	
		//System.out.println("iNovo " + iNovo);
		return iNovo;
		
	}

	public String verTodos_Moradas() {
		ArrayList<Morada> moradas = gestorBD.selectAll_Moradas();
		for(Morada p : moradas ){
			return p.toString() + "\n";
		}
		return "Não há pacientes a imprimir";
	}

        public void edita_Morada(Morada m){
            gestorBD.update_Morada(m);
        }
	
        public void apaga_Morada(Morada m){
		gestorBD.delete_Morada(m);
	}

	//FAMILIAR
	public void registo_Familiar(Familiar pessoa) {
		gestorBD.insert_Familiar(pessoa);		
	}

	public int novoId_Familiar() throws SQLException {
		int iVelho = gestorBD.verificaId_Familiar();
		int iNovo = iVelho+1;	
		return iNovo;
	}

	public ArrayList<Familiar> verTodos_Familiares(int idPaciente){
		ArrayList<Familiar> familiares = gestorBD.selectAll_Familiares(idPaciente);
		System.out.println("tamanho familiares: " + familiares.size());
		return familiares;
	}
	
	public Familiar devolve_Familiar(int id){
		return gestorBD.select_FamiliarId(id);
	}
	
	public Familiar devolve_Familiar(String user, String pass){
		Familiar f = gestorBD.select_FamiliarNomeUtilizador(user);

		if (f != null) {
			if (BCrypt.checkpw(pass, f.getPassword())) {
				System.out.println("Password matches");
				return f;
			} else {
				System.out.println("Password does not match");
			}
		}

		return f;
	}
	
         public void edita_Familiar(Familiar f){
            gestorBD.update_Familiar(f);
        }
          
	public void apaga_Familiar(Familiar pessoa){
		gestorBD.delete_Familiar(pessoa);
	}

	//TECNICO
	public int registo_Tecnico(Tecnico utilizador) {
		return gestorBD.insert_Tecnico(utilizador);

	}

	public int novoId_Tecnico() throws SQLException {
		int iVelho = gestorBD.verificaId_Tecnico();
		return iVelho+1;
	}

	public Tecnico devolve_Tecnico(String nome_utilizador, String pass){
		Tecnico resp = gestorBD.select_TecnicoNome_Utilizador(nome_utilizador);

		if (resp != null) {
			if(pass.equals(resp.getPassword())){
//			if (BCrypt.checkpw(pass, resp.getPassword())) {
				System.out.println("Password matches");
				return resp;
			} else {
				System.out.println("Password does not match");
			}
		}

		return resp;
	}

	public Tecnico devolve_Tecnico(int id){
		return gestorBD.select_TecnicoId(id);
	}

	public ArrayList<Tecnico> verTodos_Tecnicos() {
		ArrayList<Tecnico> tecnicos = gestorBD.selectAll_Tecnicos();
		return tecnicos;
	}
        
         public void edita_Tecnico(Tecnico u){
            gestorBD.update_Tecnico(u);
        }
          
	public void apaga_Tecnico(Tecnico u){
		gestorBD.delete_Tecnico(u);
	}
	
	
	//RELACAO_PACIENTE_FAMILIAR
	
	public int registo_Relacao_Paciente_Familiar(Relacao relacao) {
		return gestorBD.insert_Relacao_Paciente_Familiar(relacao);

	}

	public int novoId_Relacao_Paciente_Familiar() throws SQLException {
		int iVelho = gestorBD.verificaId_Relacao_Paciente_Familiar();
		return iVelho+1;
	}


	public Relacao devolve_Relacao_Paciente_Familiar(int id_paciente, int id_familiar){
		return gestorBD.select_Relacao_Paciente_Familiar(id_paciente, id_familiar);
	}

	public ArrayList<Relacao> verTodos_Relacao_Paciente_Familiar_Do_Paciente(Paciente p) {
		ArrayList<Relacao> relacoes = gestorBD.selectAll_Relacao_Paciente_Familiar(p.getId());
	
		return relacoes;
	}
	
	public void apaga_Relacao_Paciente_Familiar(Familiar f){
		gestorBD.delete_Relacao_Paciente_Familiar(f);
	}
	
	//RELACAO_Familiar_FAMILIAR
	
	public int registo_Relacao_Familiar_Familiar(Relacao relacao) {
		return gestorBD.insert_Relacao_Familiar_Familiar(relacao);

	}

	public int novoId_Relacao_Familiar_Familiar() throws SQLException {
		int iVelho = gestorBD.verificaId_Relacao_Familiar_Familiar();
		return iVelho+1;
	}


	public Relacao devolve_Relacao_Familiar_Familiar(int id){
		return gestorBD.select_Relacao_Familiar_FamiliarId(id);
	}

	public ArrayList<Relacao> verTodos_Relacao_Familiar_Familiar(Paciente pac) {
		ArrayList<Relacao> relacoes = gestorBD.selectAll_Relacao_Familiar_Familiar(pac);
		
		return relacoes;
	}

	public ArrayList<Relacao> verTodos_Relacao_Familiar_Familiar_Do_Familiar(Paciente p, Familiar f) {
		ArrayList<Relacao> relacoes = gestorBD.selectAll_Relacao_Familiar_Familiar_Do_Familiar(p, f);
		return relacoes;
	}
	
	public void apaga_Relacao_Familiar_Familiar(Familiar f){
		gestorBD.delete_Relacao_Familiar_Familiar(f);
	}
	
	//EVENTO
	public int registo_Evento(Evento evento){
		return gestorBD.insert_Evento(evento);
	}
	
	public int novoId_Evento() throws SQLException{
		int iVelho = gestorBD.verificaId_Evento();
		return iVelho+1;
	}
	
	public Evento devolve_Evento(int id){
		return gestorBD.select_EventoId(id);
	}
	
	public ArrayList<Evento> verTodos_Eventos(Paciente p){
		ArrayList<Evento> eventos = gestorBD.selectAll_Evento(p);
		return eventos;
	}
	
        public void edita_Evento(Evento e){
            gestorBD.update_Evento(e);
        }
	public void apaga_Evento(Evento e){
		gestorBD.delete_Evento(e);
	}
}
