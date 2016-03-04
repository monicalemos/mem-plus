package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.RespostaDAO;
import com.mem.app.model.Resposta;
import com.mem.app.services.RespostaService;

@Service("respostaService")
public class RespostaServiceImpl implements RespostaService {

	RespostaDAO respostaDao;

	@Autowired
	public void setRespostaDao(RespostaDAO respostaDao){
		this.respostaDao = respostaDao;
	}
	
	@Override
	public int saveOrUpdate(Resposta pergunta) {
		return respostaDao.saveOrUpdate(pergunta);
	}

	@Override
	public void delete(int idResposta) {
		respostaDao.delete(idResposta);
	}

	@Override
	public Resposta get(int idResposta) {
		return respostaDao.get(idResposta);
	}

	@Override
	public List<Resposta> listFromQuestion(int idPergunta) {
		return respostaDao.listFromQuestion(idPergunta);
	}

	@Override
	public List<Resposta> list() {
		return respostaDao.list();
	}

}
