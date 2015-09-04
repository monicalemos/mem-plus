package com.mem.app.model.repository.memory;

import java.util.LinkedList;

import com.mem.app.model.Utilizador;
import com.mem.app.model.repository.IRepository;

public class UtilizadorRepository implements IRepository<Utilizador> {

	private LinkedList<Utilizador> repository;
	//TODO
	//private UtilizadorDAOImpl utilImpl = new UtilizadorDAOImpl(null);

	private Utilizador getNewUtilizador() {
		
		Utilizador utilizador = new Utilizador();
		utilizador.setIdUtilizador(1);
		utilizador.setNomeUtilizador("admin");
		utilizador.setPassword("admin");
		utilizador.setTipoUtilizador("Utilizador");
		utilizador.setEmail("adminEmail");

		return utilizador;
	}

	public UtilizadorRepository() {
				repository = new LinkedList<Utilizador>();
				repository.add(getNewUtilizador()); 
			}

	@Override
	public Iterable<Utilizador> getAll() {
		// TODO Auto-generated method stub
		return repository;
	}

	@Override
	public Iterable<Utilizador> getAllPaged(int currentpage, int nextNbrItems) {
		// TODO Auto-generated method stub
		return repository;
	}
	@Override
	public Utilizador select(int id) {
		// TODO Auto-generated method stub
		return repository.getFirst();
	}
	
	@Override
	public Utilizador selectObject(Utilizador utilizador) {
		
		//String username = utilizador.getNomeUtilizador();
		//String password = utilizador.getPassword();
		
		
		// TODO Auto-generated method stub
		return repository.getFirst();
	}

	@Override
	public boolean update(Utilizador obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Utilizador obj) {

		System.out.println(obj.getEmail() + " - " + obj.getNomeUtilizador() + " - "
				+ obj.getPassword() + obj.getTipoUtilizador());
		// TODO Auto-generated method stub
		return repository.add(obj);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
