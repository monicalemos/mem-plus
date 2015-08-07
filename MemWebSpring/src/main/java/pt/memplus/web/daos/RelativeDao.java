package pt.memplus.web.daos;

import pt.memplus.web.models.Relative;

public interface RelativeDao {
	void save(Relative rel);
	void delete(int id);
	void update(Relative rel);
	Iterable<Relative> getAll();
	Relative getById(int id);
}
