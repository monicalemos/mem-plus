package pt.memplus.web.daos;

import org.springframework.stereotype.Repository;

import pt.memplus.web.models.TherapySession;

@Repository("TherapySessions")
public class TherapySessionDaoImpl extends AbstractDao implements
		TherapySessionDao {

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
