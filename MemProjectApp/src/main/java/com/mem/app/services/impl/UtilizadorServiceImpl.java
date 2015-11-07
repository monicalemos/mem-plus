package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.TecnicoDAO;
import com.mem.app.dao.UtilizadorDAO;
import com.mem.app.model.Utilizador;
import com.mem.app.model.enumerates.TipoUtilizador;
import com.mem.app.services.UtilizadorService;

@Service("utilizadorService")
public class UtilizadorServiceImpl implements UtilizadorService {

	UtilizadorDAO utilizadorDao;
	TecnicoDAO tecnicoDAO;

	@Autowired
	public void setUtilizadorDao(UtilizadorDAO utilizadorDao) {
		this.utilizadorDao = utilizadorDao;
	}

	@Autowired
	public void setTecnicoDAO(TecnicoDAO tecnicoDAO) {
		this.tecnicoDAO = tecnicoDAO;
	}

	@Override
	public int saveOrUpdate(Utilizador utilizador) {
		System.out.println("Vou inserir o utilizador");
		int newId = this.utilizadorDao.saveOrUpdate(utilizador);
		utilizador.setIdUtilizador(newId);
		System.out.println("Inseri: " + this.get(utilizador.getIdUtilizador()));
		return newId;
	}

	@Override
	public void delete(int utilizadorId) {
		this.utilizadorDao.delete(utilizadorId);
	}

	@Override
	public Utilizador get(int utilizadorId) {
		return this.utilizadorDao.get(utilizadorId);
	}

	@Override
	public List<Utilizador> list() {
		return this.utilizadorDao.list();
	}

	@Override
	public Utilizador getFromEmail(String email) {
		return this.utilizadorDao.getFromEmail(email);
	}

	@Override
	public Utilizador getFromUserName(String user) {
		return this.utilizadorDao.getFromUserName(user);
	}

	@Override
	public Utilizador matchUser(String username, String password) {
		return this.utilizadorDao.matchUser(username, password);
	}

	@Override
	public Object encontrarUtilizador(Utilizador utilizador) {
		Object resultado = null;
		
		
		if (utilizador.getTipoUtilizador().equals(TipoUtilizador.TECNICO.toString())
				&& this.tecnicoDAO.getByUtilizador(utilizador.getIdUtilizador()) != null) {
			resultado = this.tecnicoDAO.getByUtilizador(utilizador.getIdUtilizador());
		}

		System.out.println("object resultado " + resultado);
		return resultado;
	}

}
