package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Morada;

public interface MoradaDAO {

	int saveOrUpdate(Morada morada);

	void delete(int moradaId);

	Morada get(int moradaId);
	
	Morada getMoradaCompleta(String pais, String regiao, String cidade);

	List<Morada> list();
	
	List<Integer> listIds();
	
	Integer getLastId();
}
