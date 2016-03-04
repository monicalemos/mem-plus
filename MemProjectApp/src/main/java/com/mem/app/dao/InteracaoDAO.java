package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Interacao;

public interface InteracaoDAO {
	
	int saveOrUpdate(Interacao interacao);

	void delete(int idInteracao);

	Interacao get(int idInteracao);

	List<Interacao> list(int idPaciente);

}
