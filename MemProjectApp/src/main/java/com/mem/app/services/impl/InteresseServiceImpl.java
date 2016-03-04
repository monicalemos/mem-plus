package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.InteresseDAO;
import com.mem.app.model.Interesse;
import com.mem.app.services.InteresseService;

@Service("interesseService")
public class InteresseServiceImpl implements InteresseService {
	
	InteresseDAO interesseDao;

	@Autowired
	public void setInteresseDao(InteresseDAO interesseDao){
		this.interesseDao = interesseDao;
	}
	
	@Override
	public int saveOrUpdate(Interesse interesse) {
		return this.interesseDao.saveOrUpdate(interesse);
	}

	@Override
	public void delete(int interesseId) {
		this.interesseDao.delete(interesseId);
	}

	@Override
	public Interesse get(int interesseId) {
		return this.interesseDao.get(interesseId);
	}

	@Override
	public List<Interesse> list() {
		return this.interesseDao.list();
	}
}