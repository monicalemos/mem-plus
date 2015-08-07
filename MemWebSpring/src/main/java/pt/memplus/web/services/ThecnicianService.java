package pt.memplus.web.services;

import pt.memplus.web.models.Technician;

public interface ThecnicianService {
	void save(Technician tec);
	void delete(int id);
	void update(Technician tec);
	Iterable<Technician> getAll();
	Technician getById(int id);
}
