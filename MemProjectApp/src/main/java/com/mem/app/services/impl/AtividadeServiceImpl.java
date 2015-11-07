package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.AtividadeDAO;
import com.mem.app.model.Atividade;
import com.mem.app.services.AtividadeService;

@Service("atividadeService")
public class AtividadeServiceImpl implements AtividadeService {
	
	AtividadeDAO atividadeDao;

	@Autowired
	public void setAtividadeDao(AtividadeDAO atividadeDao){
		this.atividadeDao = atividadeDao;
	}
	
	@Override
	public void saveOrUpdate(Atividade atividade) {
		this.atividadeDao.saveOrUpdate(atividade);
	}

	@Override
	public void delete(int atividadeId) {
		this.atividadeDao.delete(atividadeId);
	}

	@Override
	public Atividade get(int atividadeId) {
		return this.atividadeDao.get(atividadeId);
	}

	@Override
	public List<Atividade> list() {
		return this.atividadeDao.list();
	}
}
