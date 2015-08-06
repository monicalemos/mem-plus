package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Tecnico;

public interface TecnicoDAO {
	
	void saveOrUpdate(Tecnico tecnico);

	void delete(int tecnicoId);

	Tecnico get(int tecnicoId);
	
	Tecnico getFromEmail(String email);
	
	Tecnico getFromUserName(String user);

	List<Tecnico> list();
}
