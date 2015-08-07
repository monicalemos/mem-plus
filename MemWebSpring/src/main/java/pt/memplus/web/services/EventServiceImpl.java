package pt.memplus.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.memplus.web.daos.EventDao;
import pt.memplus.web.models.Event;
@Service("EventService")
@Transactional
public class EventServiceImpl implements EventService {
	@Autowired
    private EventDao eventDao;
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
