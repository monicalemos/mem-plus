package db.dao;

import java.util.List;

import db.models.Atividade;

public interface AtividadeDAO {

	void save (Atividade at);
	void delete(Atividade at);
	void deleteById(int id);
	void update (Atividade at);
	Atividade findById(int id);
	Iterable<Atividade> getAll();
	List findByExample(Atividade at);

}
