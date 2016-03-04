package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Categoria;

public interface CategoriaDAO {

	int saveOrUpdate(Categoria categoria);

	void delete(int categoriaId);

	Categoria get(int categoriaId);

	List<Categoria> list();
}
