package pt.memplus.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.memplus.web.models.Relative;
@Service("RelativeService")
@Transactional
public class RelativeServiceImpl implements RelativeService {
	@Autowired
//    private RelativeDao relativeDao;
	@Override
	public void save(Relative rel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Relative rel) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<Relative> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Relative getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
