package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Jogo;

public interface JogoDAO {
	
	void saveOrUpdate(Jogo jogo);

	void delete(int jogoId);

	Jogo get(int jogoId);

	List<Jogo> list();
	
}
