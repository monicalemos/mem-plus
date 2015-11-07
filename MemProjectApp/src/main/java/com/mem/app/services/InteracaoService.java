package com.mem.app.services;

import java.util.List;

import com.mem.app.model.Interacao;

public interface InteracaoService {
	
	void saveOrUpdate(Interacao interacao);

	void delete(int idInteracao);

	Interacao get(int idInteracao);

	List<Interacao> list(int idPaciente);

}
