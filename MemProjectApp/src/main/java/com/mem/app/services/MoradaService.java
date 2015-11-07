package com.mem.app.services;

import java.util.List;

import com.mem.app.model.Morada;

public interface MoradaService {

	int saveOrUpdate(Morada morada);

	void delete(int moradaId);

	Morada get(int moradaId);

	List<Morada> list();
	
	List<Integer> listIds();
	
	Integer getLastId();

	Morada getMoradaCompleta(String pais, String regiao, String cidade);
}
