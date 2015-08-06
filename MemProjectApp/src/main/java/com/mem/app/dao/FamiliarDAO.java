package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Familiar;

public interface FamiliarDAO {

	void saveOrUpdate(Familiar familiar);

	void delete(int familiarId);

	Familiar get(int familiarId);

	Familiar getFromUserName(String user);

	List<Familiar> list(int idPaciente);
}
