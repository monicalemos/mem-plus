package com.mem.app.services;

import java.util.List;

import com.mem.app.model.Pergunta;

public interface PerguntaService {
	int saveOrUpdate(Pergunta pergunta);

	void delete(int idPergunta);

	Pergunta get(int idPergunta);

	List<Pergunta> listFromQuestionnaire(int idQuestionario);

	List<Pergunta> list();
}
