package db.dao;

import java.util.List;

import db.models.Evento;

public interface EventoDAO {

	void save (Evento evento);
	void delete(Evento evento);
	void deleteById(int id);
	void update (Evento evento);
	Evento findById(int id);
	Iterable<Evento> getAll();
	List findByExample(Evento evento);
}
