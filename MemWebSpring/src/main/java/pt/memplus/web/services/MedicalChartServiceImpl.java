package pt.memplus.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.memplus.web.models.MedicalChart;
@Service("MedicalChartService")
@Transactional
public class MedicalChartServiceImpl implements MedicalChartService {
	@Autowired
//    private MedicalChartDao medicalChartDao;
	
	@Override
	public void save(MedicalChart mc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MedicalChart mc) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<MedicalChart> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MedicalChart getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
