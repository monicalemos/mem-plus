package db.dao;

import java.util.List;

import db.models.Paciente;

public interface PacienteDAO {

	void save(Paciente pac);
	void delete(Paciente pac);
	void deleteById(int id);
	void update(Paciente pac);
	Iterable<Paciente> getAll();
	List findByExample(Paciente instance);
	Paciente getById(int id);
	Paciente getByPaciente(Paciente pac);
}
