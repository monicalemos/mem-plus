package com.mem.app.model.repository.memory;

import java.util.LinkedList;

import com.mem.app.model.Familiar;
import com.mem.app.model.repository.IRepository;

import com.mem.app.utils.GenericUtils;

public class FamiliarRepository implements IRepository<Familiar> {
private LinkedList<Familiar> repository;
	
	private Familiar getNewFamiliar()
	{
		Familiar model = new Familiar();
		model.setIdFamiliar(1234);
		model.setDataDeNascimento(GenericUtils.getDate("1977-05-27"));
		model.setNomeProprio("Nuno");
		model.setGenero("Masculino");
		model.setApelido("Cancelo");
		model.setEstadoCivil("Casado");
		model.setProfissao("Informático");
		model.getMoradaByLocalNascimentoIdMorada().setCidade("Lisboa");
		model.getMoradaByLocalNascimentoIdMorada().setPais("Portugal");
		model.getMoradaByLocalNascimentoIdMorada().setRegiao("Lisboa");
		model.setECuidador(false);
		return model;
	}

	public FamiliarRepository() {
		repository = new LinkedList<Familiar>();
		repository.add(getNewFamiliar()); 
	}
	@Override
	public Iterable<Familiar> getAll() {
		return repository;
	}

	@Override
	public Iterable<Familiar> getAllPaged(int currentpage, int nextNbrItems) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Familiar select(int id) {
		return repository.getFirst();
	}

	@Override
	public boolean update(Familiar obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Familiar obj) {
		return repository.add(obj);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
