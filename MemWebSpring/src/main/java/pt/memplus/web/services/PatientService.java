package pt.memplus.web.services;

import pt.memplus.web.models.Patient;

public interface PatientService {
	void save(Patient pat);
	void delete(int id);
	void update(Patient ev);
	Iterable<Patient> getAll();
	Patient getById(int id);
}
