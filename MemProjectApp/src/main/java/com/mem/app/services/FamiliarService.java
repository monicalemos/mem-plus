package com.mem.app.services;

import java.util.List;

import com.mem.app.model.Familiar;

public interface FamiliarService {

	int saveOrUpdate(Familiar familiar);

	void delete(int familiarId);

	Familiar get(int familiarId);

	Familiar getFromUserName(String user);

	List<Familiar> list(int idPaciente);
}
