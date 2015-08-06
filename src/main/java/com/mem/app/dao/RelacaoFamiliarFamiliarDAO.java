package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Familiar;
import com.mem.app.model.Paciente;
import com.mem.app.model.RelacaoFamiliarFamiliar;

public interface RelacaoFamiliarFamiliarDAO {

	void saveOrUpdate(RelacaoFamiliarFamiliar relacaoFamiliarFamiliar);

	void delete(Familiar familiar);

	RelacaoFamiliarFamiliar get(int relacaoFamiliarFamiliarId);
	
	List<RelacaoFamiliarFamiliar> listFromPatient(Paciente paciente);
	
	List<RelacaoFamiliarFamiliar> listFromFamily(Paciente paciente, Familiar familiar);
	
}
