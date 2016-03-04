package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Resposta;

public interface RespostaDAO {
	int saveOrUpdate(Resposta pergunta);

	void delete(int idResposta);

	Resposta get(int idResposta);

	List<Resposta> listFromQuestion(int idPergunta);

	List<Resposta> list();
}
