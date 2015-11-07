package com.mem.app.services;

import java.util.List;

import com.mem.app.model.Categoria;

public interface CategoriaService {

	void saveOrUpdate(Categoria categoria);

	void delete(int categoriaId);

	Categoria get(int categoriaId);

	List<Categoria> list();
}
