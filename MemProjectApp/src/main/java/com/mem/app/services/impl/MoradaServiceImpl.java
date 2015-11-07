package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.MoradaDAO;
import com.mem.app.model.Morada;
import com.mem.app.services.MoradaService;

@Service("moradaService")
public class MoradaServiceImpl implements MoradaService {
	
	MoradaDAO moradaDao;

	@Autowired
	public void setMoradaDao(MoradaDAO moradaDao){
		this.moradaDao = moradaDao;
	}
	
	@Override
	public int saveOrUpdate(Morada morada) {
		return this.moradaDao.saveOrUpdate(morada);
	}

	@Override
	public void delete(int moradaId) {
		this.moradaDao.delete(moradaId);
	}

	@Override
	public Morada get(int moradaId) {
		return this.moradaDao.get(moradaId);
	}

	@Override
	public List<Morada> list() {
		return this.moradaDao.list();
	}

	@Override
	public List<Integer> listIds() {
		return this.moradaDao.listIds();
	}

	@Override
	public Integer getLastId() {
		return this.moradaDao.getLastId();
	}

	@Override
	public Morada getMoradaCompleta(String pais, String regiao, String cidade) {
		return this.moradaDao.getMoradaCompleta(pais, regiao, cidade);
	}
}