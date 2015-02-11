package db.dao;

import java.util.List;

import db.models.Interesse;

public interface InteresseDAO {

	void save (Interesse at);
	void delete(Interesse at);
	void deleteById(int id);
	void update (Interesse at);
	Interesse findById(int id);
	Iterable<Interesse> getAll();
	List findByExample(Interesse at);
	
}
