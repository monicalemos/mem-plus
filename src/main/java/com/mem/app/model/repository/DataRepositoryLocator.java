package com.mem.app.model.repository;

import com.mem.app.model.*;
import com.mem.app.model.repository.memory.*;

public class DataRepositoryLocator {
	private static final IRepository<Paciente> pacienteRepository = new PacienteRepository();
	private static final IRepository<Familiar> familiarRepository = new FamiliarRepository();
	private static final IRepository<Evento> eventoRepository = new EventoRepository();
	
	
	public static IRepository<Paciente> getPacientRepository() {
		return pacienteRepository;
	}
	public static IRepository<Familiar> getRelativeRepository() {
		return familiarRepository;
	}
	public static IRepository<Evento> getEventRepository(){
		return eventoRepository;
	}

}
