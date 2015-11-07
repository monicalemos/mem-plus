package com.mem.app.services;

import java.util.List;

import com.mem.app.model.Questionario;

public interface QuestionarioService {

	void saveOrUpdate(Questionario questionario);

	void delete(int idQuestionario);

	Questionario get(int idQuestionario);

	List<Questionario> list();
	
	List<Questionario> listByInteracao(int idInteracao);
	
	List<Questionario> listByCategoria(int idCategoria);
	
}
