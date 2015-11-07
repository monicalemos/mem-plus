package com.mem.app.services;

import java.util.List;

import com.mem.app.model.Evento;
import com.mem.app.model.Paciente;

public interface EventoService {

	int saveOrUpdateWithFamily(Evento evento);
	
	int saveOrUpdateWithoutFamily(Evento evento);

	void delete(int eventoId);

	Evento get(int eventoId);

	List<Evento> list(Paciente paciente);
	
}
