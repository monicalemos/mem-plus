package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.RelacaoFamiliarFamiliarDAO;
import com.mem.app.model.Familiar;
import com.mem.app.model.Paciente;
import com.mem.app.model.RelacaoFamiliarFamiliar;
import com.mem.app.model.RelacaoFamiliarFamiliarId;
import com.mem.app.services.FamiliarService;
import com.mem.app.services.RelacaoFamiliarFamiliarService;

@Service("relacaoFamiliarFamiliarService")
public class RelacaoFamiliarFamiliarServiceImpl implements RelacaoFamiliarFamiliarService {
	
	RelacaoFamiliarFamiliarDAO relacaoFamiliarFamiliarDao;
	FamiliarService familiarService;
	

	@Autowired
	public void setRelacaoFamiliarFamiliarDao(RelacaoFamiliarFamiliarDAO relacaoFamiliarFamiliarDao){
		this.relacaoFamiliarFamiliarDao = relacaoFamiliarFamiliarDao;
	}
	
	@Autowired
	public void setFamiliarService(FamiliarService familiarService) {
		this.familiarService = familiarService;
	}
	
	@Override
	public int saveOrUpdate(RelacaoFamiliarFamiliar relacao) {
		System.out.println("vou verificar os dados da relacao");

		Paciente paciente = null;
		Familiar familiar = null;
		
		int idFamiliar1 = 0;
		
		RelacaoFamiliarFamiliarId relacaoId = relacao.getId();

		if (relacao.getPaciente() != null) {
			paciente = relacao.getPaciente();
			System.out.println("Tem paciente definido com id "+ paciente.getIdPaciente());
		} else {
			System.out.println("Não tem paciente definido");
			return 0;
		}
		
		if (relacao.getFamiliarByIdFamiliar() != null) {
			familiar = relacao.getFamiliarByIdFamiliar();
			System.out.println("Tem familiar definido com id "+ familiar.getIdFamiliar());
			System.out.println("no relacaoId " + relacaoId.getIdFamiliar());
			if(relacaoId.getIdFamiliar() == 0){
				relacaoId.setIdFamiliar(familiar.getIdFamiliar());
			}
		} else {
			System.out.println("Não tem familiar definido");
			return 0;
		}
		
		if (relacao.getFamiliarByIdFamiliar1() != null) {

			Familiar familiar1 = relacao.getFamiliarByIdFamiliar1();
			System.out.println("Tem familiar c id " + familiar1.getIdFamiliar());
			
			if (familiar1.getIdFamiliar() == 0 || familiarService.get(familiar1.getIdFamiliar()) == null) {
				System.out.println("tem familiar1 definido na app mas n na BD");
				
				int newid = familiarService.saveOrUpdate(familiar1);
				System.out.println("novo id de familiar1: " + newid);
				idFamiliar1 = newid;
				familiar1.setIdFamiliar(newid);
				
				System.out.println("no relacaoId " + relacaoId.getIdFamiliar1());
				if(relacaoId.getIdFamiliar1() == 0){
					relacaoId.setIdFamiliar1(familiar1.getIdFamiliar());
				}
			} else {
				System.out.println("Já tem o familiar1 na BD");
				idFamiliar1 = relacao.getFamiliarByIdFamiliar1().getIdFamiliar(); 
			}
		} else {
			System.out.println("Não tem familiar1 definido");
			return 0;
		}
		
		if(paciente.getIdPaciente() != 0 && familiar.getIdFamiliar() != 0 && idFamiliar1 != 0 ){
			System.out.println("Vou inserir ou atualizar a relacao");
			int newId = this.relacaoFamiliarFamiliarDao.saveOrUpdate(relacao);
			
			relacaoId.setIdRelacaoFamiliarFamiliar(newId);
			relacao.setId(relacaoId);
			
			System.out.println("inseri " + this.get(relacao.getId().getIdRelacaoFamiliarFamiliar()));
			return newId;
		} else {
			System.out.println("algo deu erro");
			return 0;
		}
	}


	@Override
	public RelacaoFamiliarFamiliar get(int relacaoFamiliarFamiliarId) {
		return this.relacaoFamiliarFamiliarDao.get(relacaoFamiliarFamiliarId);
	}

	@Override
	public void delete(Familiar familiar) {
		this.relacaoFamiliarFamiliarDao.delete(familiar);		
	}

	@Override
	public List<RelacaoFamiliarFamiliar> listFromPatient(Paciente paciente) {
		return this.relacaoFamiliarFamiliarDao.listFromPatient(paciente);
	}

	@Override
	public List<RelacaoFamiliarFamiliar> listFromFamily(Paciente paciente, Familiar familiar) {
		return this.relacaoFamiliarFamiliarDao.listFromFamily(paciente, familiar);
	}

	@Override
	public RelacaoFamiliarFamiliar getWithPatientAndFamily(Paciente paciente, Familiar familiar, Familiar familiar1) {
		return this.relacaoFamiliarFamiliarDao.getWithPatientAndFamily(paciente, familiar, familiar1);
	}

	@Override
	public RelacaoFamiliarFamiliar getWithPatientAndFamily2grau(Paciente paciente, Familiar familiar1) {
		return this.relacaoFamiliarFamiliarDao.getWithPatientAndFamily2grau(paciente, familiar1);
	}


}