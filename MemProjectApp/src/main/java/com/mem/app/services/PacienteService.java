package com.mem.app.services;

import java.util.List;

import com.mem.app.model.Paciente;


public interface PacienteService {

	int saveOrUpdate(Paciente paciente);

	void delete(int pacienteId);

	Paciente get(int pacienteId);

	List<Paciente> list(int idTecnico);
	
}
