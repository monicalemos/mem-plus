package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Familiar;
import com.mem.app.model.Paciente;
import com.mem.app.model.Relacaofamiliarfamiliar;

public interface RelacaofamiliarfamiliarDAO {

	void saveOrUpdate(Relacaofamiliarfamiliar relacaoFamiliarFamiliar);

	void delete(Familiar familiar);

	Relacaofamiliarfamiliar get(int relacaoFamiliarFamiliarId);
	
	List<Relacaofamiliarfamiliar> listFromPatient(Paciente paciente);
	
	List<Relacaofamiliarfamiliar> listFromFamily(Paciente paciente, Familiar familiar);
	
}
