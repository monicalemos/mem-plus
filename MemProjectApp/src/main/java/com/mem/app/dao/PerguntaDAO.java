package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Pergunta;

public interface PerguntaDAO {

	int saveOrUpdate(Pergunta pergunta);

	void delete(int idPergunta);

	Pergunta get(int idPergunta);

	List<Pergunta> listFromQuestionnaire(int idQuestionario);

	List<Pergunta> list();

}
