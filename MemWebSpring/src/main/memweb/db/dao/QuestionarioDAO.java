package db.dao;

import java.util.List;

import db.models.Questionario;

public interface QuestionarioDAO {

	void save (Questionario quest);
	void delete(Questionario quest);
	void deleteById(int id);
	void update (Questionario quest);
	Questionario findById(int id);
	Iterable<Questionario> getAll();
	List findByExample(Questionario quest);
	
}
