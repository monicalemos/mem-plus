package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.CategoriaDAO;
import com.mem.app.model.Categoria;
import com.mem.app.services.CategoriaService;

@Service("categoriaService")
public class CategoriaServiceImpl implements CategoriaService {
	
	CategoriaDAO categoriaDao;

	@Autowired
	public void setCategoriaDao(CategoriaDAO categoriaDao){
		this.categoriaDao = categoriaDao;
	}
	
	@Override
	public void saveOrUpdate(Categoria categoria) {
		this.categoriaDao.saveOrUpdate(categoria);
	}

	@Override
	public void delete(int categoriaId) {
		this.categoriaDao.delete(categoriaId);
	}

	@Override
	public Categoria get(int categoriaId) {
		return this.categoriaDao.get(categoriaId);
	}

	@Override
	public List<Categoria> list() {
		return this.categoriaDao.list();
	}
}