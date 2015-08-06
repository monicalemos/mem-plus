package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Evento;
import com.mem.app.model.Paciente;

public interface EventoDAO {

	void saveOrUpdateWithFamily(Evento evento);
	
	void saveOrUpdateWithoutFamily(Evento evento);

	void delete(int eventoId);

	Evento get(int eventoId);

	List<Evento> list(Paciente paciente);
	
}
