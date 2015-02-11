package db.dao;

import java.util.List;

import db.models.Imagem;

public interface ImagemDAO {

	void save (Imagem img);
	void delete(int id);
	void update (Imagem img);
	Imagem findById(int id);
	Iterable<Imagem> getAll();
	List findByExample(Imagem img);
	
}
