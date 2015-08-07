package pt.memplus.web.daos;

import pt.memplus.web.models.CareGiver;

public interface CareGiverDao {
	void save(CareGiver cg);
	void delete(int id);
	void update(CareGiver cg);
	Iterable<CareGiver> getAll();
	CareGiver getById(int id);
}
