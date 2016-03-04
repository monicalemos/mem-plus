package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.InteracaoDAO;
import com.mem.app.model.Interacao;
import com.mem.app.services.InteracaoService;

@Service("interacaoService")
public class InteracaoServiceImpl implements InteracaoService {
	
	InteracaoDAO interacaoDao;

	@Autowired
	public void setInteracaoDao(InteracaoDAO interacaoDao){
		this.interacaoDao = interacaoDao;
	}
	
	@Override
	public int saveOrUpdate(Interacao interacao) {
		return this.interacaoDao.saveOrUpdate(interacao);
	}

	@Override
	public void delete(int interacaoId) {
		this.interacaoDao.delete(interacaoId);
	}

	@Override
	public Interacao get(int interacaoId) {
		return this.interacaoDao.get(interacaoId);
	}

	@Override
	public List<Interacao> list(int idPaciente) {
		// TODO Auto-generated method stub
		return this.interacaoDao.list(idPaciente);
	}
}