package pt.memplus.web.daos;

import pt.memplus.web.models.MedicalChart;

public interface MedicalChartDao {
	void save(MedicalChart mc);
	void delete(int id);
	void update(MedicalChart mc);
	Iterable<MedicalChart> getAll();
	MedicalChart getById(int id);
}
