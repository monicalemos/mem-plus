package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Jogo;

public interface JogoDAO {
	
	int saveOrUpdate(Jogo jogo);

	void delete(int idJogo);

	Jogo get(int idJogo);

	List<Jogo> list();
	
	List<Jogo> listByInteracao(int idInteracao);
	
	List<Jogo> listByCategoria(int idCategoria);
	
}
