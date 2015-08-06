package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Paciente;


public interface PacienteDAO {

	void saveOrUpdate(Paciente paciente);

	void delete(int pacienteId);

	Paciente get(int pacienteId);

	List<Paciente> list(int idTecnico);
	
}
