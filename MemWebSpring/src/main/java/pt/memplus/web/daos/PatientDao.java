package pt.memplus.web.daos;

import pt.memplus.web.models.Patient;

public interface PatientDao {
	void save(Patient pat);
	void delete(int id);
	void update(Patient ev);
	Iterable<Patient> getAll();
	Patient getById(int id);
}
