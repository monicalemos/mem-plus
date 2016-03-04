package com.mem.app.services;

import java.util.List;

import com.mem.app.model.Tecnico;

public interface TecnicoService {
	
	int saveOrUpdate(Tecnico tecnico);

	void delete(int tecnicoId);

	Tecnico get(int tecnicoId);
	
	List<Tecnico> list();
}
