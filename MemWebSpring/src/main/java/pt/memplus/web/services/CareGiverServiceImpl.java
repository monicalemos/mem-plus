package pt.memplus.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.memplus.web.models.CareGiver;
@Service("CareGiverService")
@Transactional
public class CareGiverServiceImpl implements CareGiverService {
	@Autowired
//    private CareGiverDao careGiverDao;
	@Override
	public void save(CareGiver cg) {
		//Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// Auto-generated method stub

	}

	@Override
	public void update(CareGiver cg) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<CareGiver> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareGiver getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
