package com.mem.app.model.repository.memory;

import java.util.LinkedList;

import com.mem.app.model.Tecnico;
import com.mem.app.model.Utilizador;
import com.mem.app.model.repository.IRepository;

public class TecnicoRepository implements IRepository<Tecnico> {

private LinkedList<Tecnico> repository;


	
	private Tecnico getNewTecnico()
	{
		Utilizador utilizador = new Utilizador();
		utilizador.setIdUtilizador(1);
				utilizador.setNomeUtilizador("admin");
				utilizador.setPassword("admin");
				utilizador.setTipoUtilizador("tecnico");
				utilizador.setEmail("adminEmail");
				
		Tecnico model = new Tecnico();
		model.setIdTecnico(1);
		model.setNomeProprio("Tecnico");
		model.setApelido("Apelido");
		model.setTelefone(123456789);;
		model.setUtilizador(utilizador);
		return model;
	}

	public TecnicoRepository() {
		repository = new LinkedList<Tecnico>();
		repository.add(getNewTecnico()); 
	}
	@Override
	public Iterable<Tecnico> getAll() {
		// TODO Auto-generated method stub
		return repository;
	}

	@Override
	public Iterable<Tecnico> getAllPaged(int currentpage, int nextNbrItems) {
		// TODO Auto-generated method stub
		return repository;
	}

	@Override
	public Tecnico select(int id) {
		// TODO Auto-generated method stub
		return repository.getFirst();
	}

	@Override
	public boolean update(Tecnico obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Tecnico obj) {
		
		System.out.println(obj.getNomeProprio() + " - " + obj.getApelido() + " - " + obj.getTelefone() + " - " + 
		obj.getUtilizador().getEmail() + " - "+ obj.getUtilizador().getNomeUtilizador() + " - " + obj.getUtilizador().getPassword());
		// TODO Auto-generated method stub
		return repository.add(obj);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tecnico selectObject(Tecnico obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
