package pt.memplus.web.daos;

import pt.memplus.web.models.Event;

public interface EventDao {
	void save(Event ev);
	void delete(int id);
	void update(Event ev);
	Iterable<Event> getAll();
	Event getById(int id);
}
