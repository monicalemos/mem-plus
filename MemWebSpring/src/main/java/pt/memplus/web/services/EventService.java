package pt.memplus.web.services;

import pt.memplus.web.models.Event;

public interface EventService {
	void save(Event ev);
	void delete(int id);
	void update(Event ev);
	Iterable<Event> getAll();
	Event getById(int id);
}
