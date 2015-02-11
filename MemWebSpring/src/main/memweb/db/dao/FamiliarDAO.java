package db.dao;

import java.util.List;

import db.models.Familiar;

public interface FamiliarDAO {

	void save (Familiar familiar);
	void delete(Familiar familiar);
	void deleteById(int id);
	void update (Familiar familiar);
	Familiar findById(int id);
	Iterable<Familiar> getAll();
	List findByExample(Familiar familiar);
	
}
