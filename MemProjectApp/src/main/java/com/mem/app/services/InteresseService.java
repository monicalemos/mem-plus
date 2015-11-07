package com.mem.app.services;

import java.util.List;

import com.mem.app.model.Interesse;

public interface InteresseService {

	void saveOrUpdate(Interesse interesse);

	void delete(int interesseId);

	Interesse get(int interesseId);

	List<Interesse> list();
	
}
