package com.mem.app.services;

import java.util.List;

import com.mem.app.model.Familiar;
import com.mem.app.model.Paciente;
import com.mem.app.model.RelacaoFamiliarFamiliar;

public interface RelacaoFamiliarFamiliarService {

	int saveOrUpdate(RelacaoFamiliarFamiliar relacaoFamiliarFamiliar);

	void delete(Familiar familiar);

	RelacaoFamiliarFamiliar get(int relacaoFamiliarFamiliarId);
	
	List<RelacaoFamiliarFamiliar> listFromPatient(Paciente paciente);
	
	RelacaoFamiliarFamiliar getWithPatientAndFamily(Paciente paciente, Familiar familiar, Familiar familiar1);
	
	RelacaoFamiliarFamiliar getWithPatientAndFamily2grau(Paciente paciente, Familiar familiar1);
	
	List<RelacaoFamiliarFamiliar> listFromFamily(Paciente paciente, Familiar familiar);
	
}
