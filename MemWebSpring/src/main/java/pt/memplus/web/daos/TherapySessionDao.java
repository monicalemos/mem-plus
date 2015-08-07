package pt.memplus.web.daos;

import pt.memplus.web.models.TherapySession;

public interface TherapySessionDao {
	void save(TherapySession ts);
	void delete(int id);
	void update(TherapySession ts);
	Iterable<TherapySession> getAll();
	TherapySession getById(int id);
}
