package pt.memplus.web.services;

import pt.memplus.web.models.CareGiver;

public interface CareGiverService {
	void save(CareGiver cg);
	void delete(int id);
	void update(CareGiver cg);
	Iterable<CareGiver> getAll();
	CareGiver getById(int id);
}
