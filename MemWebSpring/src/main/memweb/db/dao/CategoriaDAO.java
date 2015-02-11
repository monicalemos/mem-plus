package db.dao;

import java.util.List;

import db.models.Categoria;

public interface CategoriaDAO {

	void save (Categoria cat);
	void delete(Categoria cat);
	void deleteById(int id);
	void update (Categoria cat);
	Categoria findById(int id);
	Iterable<Categoria> getAll();
	List findByExample(Categoria cat);
	
}
