package com.mem.app.model.repository.memory;

import java.util.LinkedList;

import com.mem.app.model.Paciente;
import com.mem.app.model.repository.IRepository;
import com.mem.app.utils.GenericUtils;



public class PacienteRepository implements IRepository<Paciente> {
	private LinkedList<Paciente> repository;
	
	private Paciente getNewPaciente()
	{
		Paciente model = new Paciente();
		model.setIdPaciente(1234);
		model.setDataNascimento(GenericUtils.getDate("1977-05-27"));
		model.setNomeProprio("Nuno");
		model.setGenero("Masculino");
		model.setApelido("Cancelo");
		model.setEstadoCivil("Casado");
		model.setProfissao("Informático");
		model.getMoradaByIdLocalNascimento().setCidade("Lisboa");
		model.getMoradaByIdLocalNascimento().setPais("Portugal");
		model.getMoradaByIdLocalNascimento().setRegiao("Lisboa");
		return model;
	}

	public PacienteRepository() {
		repository = new LinkedList<Paciente>();
		repository.add(getNewPaciente()); 
	}
	@Override
	public Iterable<Paciente> getAll() {
		// TODO Auto-generated method stub
		return repository;
	}

	@Override
	public Iterable<Paciente> getAllPaged(int currentpage, int nextNbrItems) {
		// TODO Auto-generated method stub
		return repository;
	}

	@Override
	public Paciente select(int id) {
		// TODO Auto-generated method stub
		return repository.getFirst();
	}

	@Override
	public boolean update(Paciente obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Paciente obj) {
		// TODO Auto-generated method stub
		return repository.add(obj);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
