package com.mem.app.services;

import java.util.List;

import com.mem.app.model.Jogo;

public interface JogoService {
	
	void saveOrUpdate(Jogo jogo);

	void delete(int idJogo);

	Jogo get(int idJogo);

	List<Jogo> list();
	
	List<Jogo> listByInteracao(int idInteracao);
	
	List<Jogo> listByCategoria(int idCategoria);
	
}
