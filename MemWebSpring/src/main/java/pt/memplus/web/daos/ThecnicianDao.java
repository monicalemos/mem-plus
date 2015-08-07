package pt.memplus.web.daos;

import pt.memplus.web.models.Technician;


public interface ThecnicianDao {
	void save(Technician tec);
	void delete(int id);
	void update(Technician tec);
	Iterable<Technician> getAll();
	Technician getById(int id);
}
