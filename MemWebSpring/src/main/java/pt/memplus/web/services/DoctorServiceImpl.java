package pt.memplus.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.memplus.web.models.Doctor;
@Service("DoctorService")
@Transactional
public class DoctorServiceImpl implements DoctorService {
	@Autowired
//    private DoctorDao doctorDao;
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
