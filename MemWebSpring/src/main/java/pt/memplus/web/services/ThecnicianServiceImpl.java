package pt.memplus.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.memplus.web.models.Technician;
@Service("ThecnicianService")
@Transactional
public class ThecnicianServiceImpl implements ThecnicianService {
	@Autowired
//    private ThecnicianDao thecnicianDao;
	@Override
	public void save(Technician tec) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Technician tec) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<Technician> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Technician getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
