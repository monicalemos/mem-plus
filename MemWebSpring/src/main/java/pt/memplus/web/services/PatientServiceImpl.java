package pt.memplus.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.memplus.web.daos.PatientDao;
import pt.memplus.web.models.Patient;
@Service("PatientService")
@Transactional
public class PatientServiceImpl implements PatientService {
	@Autowired
    private PatientDao patientDao;
	@Override
	public void save(Patient pat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Patient ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<Patient> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
