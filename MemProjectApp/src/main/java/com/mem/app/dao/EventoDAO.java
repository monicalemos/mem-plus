package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Evento;

public interface EventoDAO {

	int saveOrUpdateWithFamily(Evento evento);
	
	int saveOrUpdateWithoutFamily(Evento evento);

	void delete(int eventoId);

	Evento get(int eventoId);

	List<Evento> list(int idPaciente);
	
}
