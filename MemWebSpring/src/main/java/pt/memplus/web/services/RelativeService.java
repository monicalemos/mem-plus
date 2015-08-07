package pt.memplus.web.services;

import pt.memplus.web.models.Relative;

public interface RelativeService {
	void save(Relative rel);
	void delete(int id);
	void update(Relative rel);
	Iterable<Relative> getAll();
	Relative getById(int id);
}
