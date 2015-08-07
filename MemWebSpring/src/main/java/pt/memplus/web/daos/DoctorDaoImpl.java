package pt.memplus.web.daos;

import org.springframework.stereotype.Repository;

import pt.memplus.web.models.Doctor;
@Repository("Doctor")
public class DoctorDaoImpl extends AbstractDao implements DoctorDao {

	@Override
	public void save(Doctor doc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Doctor doc) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<Doctor> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Doctor getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
