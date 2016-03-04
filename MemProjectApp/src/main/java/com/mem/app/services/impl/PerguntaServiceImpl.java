package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.PerguntaDAO;
import com.mem.app.model.Pergunta;
import com.mem.app.services.PerguntaService;

@Service("perguntaService")
public class PerguntaServiceImpl implements PerguntaService{

	PerguntaDAO perguntaDao;

	@Autowired
	public void setPerguntaDao(PerguntaDAO perguntaDao){
		this.perguntaDao = perguntaDao;
	}
	
	@Override
	public int saveOrUpdate(Pergunta pergunta) {
		return perguntaDao.saveOrUpdate(pergunta);
	}

	@Override
	public void delete(int idPergunta) {
		perguntaDao.delete(idPergunta);
	}

	@Override
	public Pergunta get(int idPergunta) {
		return perguntaDao.get(idPergunta);
	}

	@Override
	public List<Pergunta> listFromQuestionnaire(int idQuestionario) {
		return perguntaDao.listFromQuestionnaire(idQuestionario);
	}

	@Override
	public List<Pergunta> list() {
		return perguntaDao.list();
	}
}
