package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.JogoDAO;
import com.mem.app.model.Jogo;
import com.mem.app.services.JogoService;

@Service("jogoService")
public class JogoServiceImpl implements JogoService {
	
	JogoDAO jogoDao;

	@Autowired
	public void setJogoDao(JogoDAO jogoDao){
		this.jogoDao = jogoDao;
	}
	
	@Override
	public void saveOrUpdate(Jogo jogo) {
		this.jogoDao.saveOrUpdate(jogo);
	}

	@Override
	public void delete(int jogoId) {
		this.jogoDao.delete(jogoId);
	}

	@Override
	public Jogo get(int jogoId) {
		return this.jogoDao.get(jogoId);
	}

	@Override
	public List<Jogo> list() {
		return this.jogoDao.list();
	}

	@Override
	public List<Jogo> listByInteracao(int idInteracao) {
		return this.jogoDao.listByInteracao(idInteracao);
	}

	@Override
	public List<Jogo> listByCategoria(int idCategoria) {
		return this.jogoDao.listByCategoria(idCategoria);
	}
}