package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Familiar;
import com.mem.app.model.Paciente;
import com.mem.app.model.RelacaoPacienteFamiliar;

public interface RelacaoPacienteFamiliarDAO {

	void saveOrUpdate(RelacaoPacienteFamiliar relacaoPacienteFamiliar);

	void delete(Familiar familiar);

	RelacaoPacienteFamiliar get(int idRelacaoPacienteFamiliar);
	
	RelacaoPacienteFamiliar getWithPatientAndFamily(Paciente paciente, Familiar familiar);

	List<RelacaoPacienteFamiliar> list(Paciente paciente);
}
