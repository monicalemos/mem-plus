package db.dao;

import java.util.List;

import db.models.Morada;

public interface MoradaDAO {

	void save (Morada morada);
	void delete(Morada morada);
	void deleteById(int id);
	void update (Morada morada);
	Morada findById(int id);
	Iterable<Morada> getAll();
	List findByExample(Morada morada);
	
}
