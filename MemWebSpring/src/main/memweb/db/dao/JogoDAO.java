package db.dao;

import java.util.List;

import db.models.Jogo;

public interface JogoDAO {

	void save (Jogo jogo);
	void delete(Jogo jogo);
	void deleteById(int id);
	void update (Jogo jogo);
	Jogo findById(int id);
	Iterable<Jogo> getAll();
	List findByExample(Jogo jogo);
	
}
