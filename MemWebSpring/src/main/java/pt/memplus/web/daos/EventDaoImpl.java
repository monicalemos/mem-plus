package pt.memplus.web.daos;

import org.springframework.stereotype.Repository;

import pt.memplus.web.models.Event;
@Repository("Event")
public class EventDaoImpl extends AbstractDao implements EventDao {

	@Override
	public void save(Event ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Event ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<Event> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
