package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Questionario;

public interface QuestionarioDAO {

	void saveOrUpdate(Questionario questionario);

	void delete(int questionarioId);

	Questionario get(int questionarioId);

	List<Questionario> list();
	
}
