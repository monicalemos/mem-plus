package pt.memplus.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.memplus.web.models.TherapySession;
@Service("TherapySessionService")
@Transactional
public class TherapySessionServiceImpl implements TherapySessionService {
	@Autowired
//    private TherapySessionDao therpySessionDao;
	@Override
	public void save(TherapySession ts) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(TherapySession ts) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<TherapySession> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TherapySession getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
