package pt.memplus.web.services;

import pt.memplus.web.models.Doctor;

public interface DoctorService {
	void save(Doctor doc);
	void delete(int id);
	void update(Doctor doc);
	Iterable<Doctor> getAll();
	Doctor getById(int id);
}
