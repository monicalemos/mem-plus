package com.mem.app.model.repository;

import com.mem.app.model.*;
import com.mem.app.model.repository.memory.*;

public class DataRepositoryLocator {
	private static final IRepository<Paciente> pacienteRepository = new PacienteRepository();
	private static final IRepository<Familiar> familiarRepository = new FamiliarRepository();
	private static final IRepository<Evento> eventoRepository = new EventoRepository();
	private static final IRepository<Tecnico> tecnicoRepository = new TecnicoRepository();
	private static final IRepository<Utilizador> utilizadorRepository = new UtilizadorRepository();
	
	public static IRepository<Paciente> getPacientRepository() {
		return pacienteRepository;
	}
	public static IRepository<Familiar> getFamiliarRepository() {
		return familiarRepository;
	}
	public static IRepository<Evento> getEventoRepository(){
		return eventoRepository;
	}
	public static IRepository<Tecnico> getTecnicoRepository(){
		return tecnicoRepository;
	}
	public static IRepository<Utilizador> getUtilizadorRepository(){
		return utilizadorRepository;
	}
}
