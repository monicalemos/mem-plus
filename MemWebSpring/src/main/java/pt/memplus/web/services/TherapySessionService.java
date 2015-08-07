package pt.memplus.web.services;

import pt.memplus.web.models.TherapySession;

public interface TherapySessionService {
	void save(TherapySession ts);
	void delete(int id);
	void update(TherapySession ts);
	Iterable<TherapySession> getAll();
	TherapySession getById(int id);
}
