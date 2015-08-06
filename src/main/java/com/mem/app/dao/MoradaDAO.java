package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Morada;

public interface MoradaDAO {

	void saveOrUpdate(Morada morada);

	void delete(int moradaId);

	Morada get(int moradaId);

	List<Morada> list();
	
	List<Integer> listIds();
	
	Integer getLastId();
}
