package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Interacao;

public interface InteracaoDAO {
	
	void saveOrUpdate(Interacao interacao);

	void delete(int interacaoId);

	Interacao get(int interacaoId);

	List<Interacao> list();

}
