package pt.memplus.web.daos;

import pt.memplus.web.models.Doctor;

public interface DoctorDao {
	void save(Doctor doc);
	void delete(int id);
	void update(Doctor doc);
	Iterable<Doctor> getAll();
	Doctor getById(int id);
}
