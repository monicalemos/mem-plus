package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Tecnico;

public interface TecnicoDAO {
	
	int saveOrUpdate(Tecnico tecnico);

	void delete(int tecnicoId);

	Tecnico get(int tecnicoId);
	
	Tecnico getByUtilizador(int utilizadorId);
	
	List<Tecnico> list();
}
