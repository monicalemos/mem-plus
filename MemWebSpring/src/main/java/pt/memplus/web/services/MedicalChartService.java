package pt.memplus.web.services;

import pt.memplus.web.models.MedicalChart;

public interface MedicalChartService {
	void save(MedicalChart mc);
	void delete(int id);
	void update(MedicalChart mc);
	Iterable<MedicalChart> getAll();
	MedicalChart getById(int id);
}
