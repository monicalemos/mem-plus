package db.dao;

import java.util.List;

import db.models.RelacaoPacienteFamiliar;

public interface RelacaoPacienteFamiliarDAO {

	void save (RelacaoPacienteFamiliar relacao);
	void delete(RelacaoPacienteFamiliar relacao);
	void deleteById(int id);
	void update (RelacaoPacienteFamiliar relacao);
	RelacaoPacienteFamiliar findById(int id);
	Iterable<RelacaoPacienteFamiliar> getAll();
	List findByExample(RelacaoPacienteFamiliar relacao);
	
}
