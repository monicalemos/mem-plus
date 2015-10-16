package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Familiar;
import com.mem.app.model.Paciente;
import com.mem.app.model.RelacaoFamiliarFamiliar;

public interface RelacaoFamiliarFamiliarDAO {

	int saveOrUpdate(RelacaoFamiliarFamiliar relacaoFamiliarFamiliar);

	void delete(Familiar familiar);

	RelacaoFamiliarFamiliar get(int relacaoFamiliarFamiliarId);
	
	RelacaoFamiliarFamiliar getWithPatientAndFamily(Paciente paciente, Familiar familiar, Familiar familiar1);
	
	RelacaoFamiliarFamiliar getWithPatientAndFamily2grau(Paciente paciente, Familiar familiar1);
	
	List<RelacaoFamiliarFamiliar> listFromPatient(Paciente paciente);
	
	List<RelacaoFamiliarFamiliar> listFromFamily(Paciente paciente, Familiar familiar);
	
}
