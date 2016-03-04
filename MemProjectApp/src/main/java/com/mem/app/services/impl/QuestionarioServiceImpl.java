package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.QuestionarioDAO;
import com.mem.app.model.Questionario;
import com.mem.app.services.QuestionarioService;

@Service("questionarioService")
public class QuestionarioServiceImpl implements QuestionarioService {
	
	QuestionarioDAO questionarioDao;

	@Autowired
	public void setQuestionarioDao(QuestionarioDAO questionarioDao){
		this.questionarioDao = questionarioDao;
	}
	
	@Override
	public int saveOrUpdate(Questionario questionario) {
		return this.questionarioDao.saveOrUpdate(questionario);
	}

	@Override
	public void delete(int questionarioId) {
		this.questionarioDao.delete(questionarioId);
	}

	@Override
	public Questionario get(int questionarioId) {
		return this.questionarioDao.get(questionarioId);
	}

	@Override
	public List<Questionario> list() {
		return this.questionarioDao.list();
	}

	@Override
	public List<Questionario> listByInteracao(int idInteracao) {
		return this.questionarioDao.listByInteracao(idInteracao);
	}

	@Override
	public List<Questionario> listByCategoria(int idCategoria) {
		return this.questionarioDao.listByCategoria(idCategoria);
	}
}