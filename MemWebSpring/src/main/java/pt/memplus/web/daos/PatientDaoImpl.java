package pt.memplus.web.daos;

import org.springframework.stereotype.Repository;

import pt.memplus.web.models.Patient;

@Repository("Patient")
public class PatientDaoImpl extends AbstractDao implements PatientDao {

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
