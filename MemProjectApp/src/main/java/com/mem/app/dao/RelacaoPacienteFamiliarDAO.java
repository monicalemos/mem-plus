package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Familiar;
import com.mem.app.model.Paciente;
import com.mem.app.model.Relacaopacientefamiliar;

public interface RelacaopacientefamiliarDAO {

	void saveOrUpdate(Relacaopacientefamiliar relacaoPacienteFamiliar);

	void delete(Familiar familiar);

	Relacaopacientefamiliar get(int idRelacaopacientefamiliar);
	
	Relacaopacientefamiliar getWithPatientAndFamily(Paciente paciente, Familiar familiar);

	List<Relacaopacientefamiliar> list(Paciente paciente);
}
