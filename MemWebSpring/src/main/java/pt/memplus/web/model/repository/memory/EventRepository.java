package pt.memplus.web.model.repository.memory;

import pt.memplus.web.model.repository.IRepository;
import pt.memplus.web.models.Event;

public class EventRepository implements IRepository<Event> {

	@Override
	public Iterable<Event> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Event> getAllPaged(int currentpage, int nextNbrItems) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Event obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Event obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
