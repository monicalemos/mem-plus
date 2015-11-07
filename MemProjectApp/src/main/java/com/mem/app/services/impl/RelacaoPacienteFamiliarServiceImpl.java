package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.RelacaoPacienteFamiliarDAO;
import com.mem.app.model.Familiar;
import com.mem.app.model.Paciente;
import com.mem.app.model.RelacaoPacienteFamiliar;
import com.mem.app.model.RelacaoPacienteFamiliarId;
import com.mem.app.services.FamiliarService;
import com.mem.app.services.RelacaoPacienteFamiliarService;

@Service("relacaoPacienteFamiliarService")
public class RelacaoPacienteFamiliarServiceImpl implements RelacaoPacienteFamiliarService {

	RelacaoPacienteFamiliarDAO relacaoPacienteFamiliarDao;

	FamiliarService familiarService;

	@Autowired
	public void setRelacaoPacienteFamiliarDao(RelacaoPacienteFamiliarDAO relacaoPacienteFamiliarDao) {
		this.relacaoPacienteFamiliarDao = relacaoPacienteFamiliarDao;
	}

	@Autowired
	public void setFamiliarService(FamiliarService familiarService) {
		this.familiarService = familiarService;
	}

	@Override
	public int saveOrUpdate(RelacaoPacienteFamiliar relacao) {
		System.out.println("vou verificar os dados da relacao");

		Paciente paciente = null;
		int idFamiliar = 0;
		
		RelacaoPacienteFamiliarId relacaoId = relacao.getId();

		if (relacao.getPaciente() != null) {
			paciente = relacao.getPaciente();
			System.out.println("Tem paciente definido com id "+ paciente.getIdPaciente());
			System.out.println("no relacaoId " + relacaoId.getIdPaciente());
			if(relacaoId.getIdPaciente() == 0){
				relacaoId.setIdPaciente(paciente.getIdPaciente());
			}
		} else {
			System.out.println("Não tem paciente definido");
			return 0;
		}

		if (relacao.getFamiliar() != null) {

			Familiar familiar = relacao.getFamiliar();
			System.out.println("Tem familiar c id " + familiar.getIdFamiliar());
			
			if (familiar.getIdFamiliar() == 0 || familiarService.get(familiar.getIdFamiliar()) == null) {
				System.out.println("tem familiar definido na app mas n na BD");
				
				int newid = familiarService.saveOrUpdate(familiar);
				System.out.println("novo id de familiar: " + newid);
				idFamiliar = newid;
				familiar.setIdFamiliar(newid);
				
				System.out.println("no relacaoId " + relacaoId.getIdFamiliar());
				if(relacaoId.getIdFamiliar() == 0){
					relacaoId.setIdFamiliar(familiar.getIdFamiliar());
				}
			} else {
				System.out.println("Já tem o familiar na BD");
				idFamiliar = relacao.getFamiliar().getIdFamiliar(); 
			}
		} else {
			System.out.println("Não tem familiar definido");
			return 0;
		}
		
		if(paciente.getIdPaciente() != 0 && idFamiliar != 0 ){
			System.out.println("Vou inserir ou atualizar a relacao");
			int newId = this.relacaoPacienteFamiliarDao.saveOrUpdate(relacao);
			
			relacaoId.setIdRelacaoPacienteFamiliar(newId);
			relacao.setId(relacaoId);
			
			System.out.println("inseri " + this.get(relacao.getId().getIdRelacaoPacienteFamiliar()));
			return newId;
		} else {
			System.out.println("algo deu erro");
			return 0;
		}
	}

	@Override
	public RelacaoPacienteFamiliar get(int relacaoPacienteFamiliarId) {
		return this.relacaoPacienteFamiliarDao.get(relacaoPacienteFamiliarId);
	}

	@Override
	public void delete(Familiar familiar) {
		this.relacaoPacienteFamiliarDao.delete(familiar);
	}

	@Override
	public RelacaoPacienteFamiliar getWithPatientAndFamily(Paciente paciente, Familiar familiar) {
		return this.relacaoPacienteFamiliarDao.getWithPatientAndFamily(paciente, familiar);
	}

	@Override
	public List<RelacaoPacienteFamiliar> list(Paciente paciente) {
		return this.relacaoPacienteFamiliarDao.list(paciente);
	}

}