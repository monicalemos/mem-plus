package db.dao;

import java.util.List;

import db.models.Tecnico;

public interface TecnicoDAO {

	void save (Tecnico quest);
	void delete(Tecnico quest);
	void deleteById(int id);
	void update (Tecnico quest);
	Tecnico findById(int id);
	Iterable<Tecnico> getAll();
	List findByExample(Tecnico quest);
}
