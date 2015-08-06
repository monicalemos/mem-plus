package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Interesse;

public interface InteresseDAO {

	void saveOrUpdate(Interesse interesse);

	void delete(int interesseId);

	Interesse get(int interesseId);

	List<Interesse> list();
	
}
