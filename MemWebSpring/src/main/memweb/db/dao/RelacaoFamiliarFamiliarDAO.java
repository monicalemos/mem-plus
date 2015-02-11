package db.dao;

import java.util.List;

import db.models.RelacaoFamiliarFamiliar;

public interface RelacaoFamiliarFamiliarDAO {

	void save (RelacaoFamiliarFamiliar relacao);
	void delete(RelacaoFamiliarFamiliar relacao);
	void deleteById(int id);
	void update (RelacaoFamiliarFamiliar relacao);
	RelacaoFamiliarFamiliar findById(int id);
	Iterable<RelacaoFamiliarFamiliar> getAll();
	List findByExample(RelacaoFamiliarFamiliar relacao);
	
}
