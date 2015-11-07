package com.mem.app.services;

import java.util.List;

import com.mem.app.model.Familiar;
import com.mem.app.model.Paciente;
import com.mem.app.model.RelacaoPacienteFamiliar;

public interface RelacaoPacienteFamiliarService {

	int saveOrUpdate(RelacaoPacienteFamiliar relacaoPacienteFamiliar);

	void delete(Familiar familiar);

	RelacaoPacienteFamiliar get(int idRelacaoPacienteFamiliar);
	
	RelacaoPacienteFamiliar getWithPatientAndFamily(Paciente paciente, Familiar familiar);

	List<RelacaoPacienteFamiliar> list(Paciente paciente);
}
