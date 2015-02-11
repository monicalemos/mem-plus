package db.dao;

import java.util.List;

import db.models.Interacao;

public interface InteracaoDAO {

	void save (Interacao at);
	void delete(Interacao at);
	void deleteById(int id);
	void update (Interacao at);
	Interacao findById(int id);
	Iterable<Interacao> getAll();
	List findByExample(Interacao at);
	
}
