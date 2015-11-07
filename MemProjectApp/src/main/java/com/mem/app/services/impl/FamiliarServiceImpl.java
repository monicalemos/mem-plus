package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.FamiliarDAO;
import com.mem.app.model.Familiar;
import com.mem.app.model.Morada;
import com.mem.app.model.Utilizador;
import com.mem.app.model.enumerates.TipoUtilizador;
import com.mem.app.services.FamiliarService;
import com.mem.app.services.MoradaService;
import com.mem.app.services.UtilizadorService;

@Service("familiarService")
public class FamiliarServiceImpl implements FamiliarService {
	
	FamiliarDAO familiarDao;
	MoradaService moradaService;
	UtilizadorService utilizadorService;
	
	@Autowired
	public void setFamiliarDao(FamiliarDAO familiarDao){
		this.familiarDao = familiarDao;
	}
	@Autowired
	public void setMoradaService(MoradaService moradaService) {
		this.moradaService = moradaService;
	}
	@Autowired
	public void setUtilizadorService(UtilizadorService utilizadorService) {
		this.utilizadorService = utilizadorService;
	}
	
	
	@Override
	public int saveOrUpdate(Familiar familiar) {
		System.out.println("vou verificar as moradas do familiar");

		int idLocalNascimento = 0;
		int idMorada = 0;

		if (familiar.getMoradaByIdLocalNascimento() != null) {
			System.out.println("tem morada de nascimento");

			Morada localNascimento = familiar.getMoradaByIdLocalNascimento();
			if (moradaService.getMoradaCompleta(localNascimento.getPais(), localNascimento.getRegiao(),
					localNascimento.getCidade()) == null
					&& (localNascimento.getIdMorada() == 0
							|| moradaService.get(localNascimento.getIdMorada()) == null)) {
				System.out.println("tem local nascimento definido na app mas n na BD");
				int newid = moradaService.saveOrUpdate(localNascimento);
				System.out.println("novo id de localNascimento: " + newid);
				System.out.println("dados da localNascimento: " + localNascimento.getCidade());
				localNascimento.setIdMorada(newid);
			} else {
				System.out.println("Já tem o local de nascimento na BD");
				System.out.println(
						"idLocalNascimento no paciente: " + familiar.getMoradaByIdLocalNascimento().getIdMorada());
				
				idLocalNascimento = moradaService.getMoradaCompleta(localNascimento.getPais(),
						localNascimento.getRegiao(), localNascimento.getCidade()).getIdMorada();
				System.out.println("idLocalMorada encontrado: " + idLocalNascimento);
				familiar.getMoradaByIdLocalNascimento().setIdMorada(idLocalNascimento);
			}
		}
		else{
			System.out.println("algo deu errado com o local de nascimento");
			return 0;
		}

		if (familiar.getMoradaByIdMorada() != null) {
			System.out.println("tem morada actual");

			Morada morada = familiar.getMoradaByIdMorada();
			if (moradaService.getMoradaCompleta(morada.getPais(), morada.getRegiao(), morada.getCidade()) == null
					&& (morada.getIdMorada() == 0 || moradaService.get(morada.getIdMorada()) == null)) {
				System.out.println("tem morada definido na app mas n na BD");
				int newid = moradaService.saveOrUpdate(morada);
				System.out.println("novo id de morada: " + newid);
				System.out.println("dados da morada: " + morada.getCidade());
				morada.setIdMorada(newid);
			} else {
				System.out.println("Já tem morada na BD");
				System.out.println("idMorada: " + familiar.getMoradaByIdMorada().getIdMorada());
				idMorada = moradaService.getMoradaCompleta(morada.getPais(), morada.getRegiao(), morada.getCidade())
						.getIdMorada();
				System.out.println("idMorada encontrado: " + idMorada);
				familiar.getMoradaByIdMorada().setIdMorada(idMorada);
			}
		}
		else{
			System.out.println("algo deu errado com a morada");
			return 0;
		}
		
		if(familiar.getEcuidador() == true){
			System.out.println("É cuidador e precisa de utilizador");
			if (familiar.getUtilizador() != null) {
				Utilizador utilizador = familiar.getUtilizador();
				System.out.println("Utilizador: " + utilizador.getEmail() + ", " + utilizador.getNomeUtilizador() + " - " + utilizador.getPassword() + " - " + utilizador.getTipoUtilizador());

				if (utilizador.getIdUtilizador() == 0 || utilizadorService.get(utilizador.getIdUtilizador()) == null) {
					System.out.println("tem utilizador definido na app mas n na BD");
					 utilizador.setTipoUtilizador(TipoUtilizador.CUIDADOR.toString());
					 int newid = utilizadorService.saveOrUpdate(utilizador);
					 utilizador.setIdUtilizador(newid);					
				}
				else{
					familiar.getUtilizador().setIdUtilizador(0);
					System.out.println("Já tem o utilizador definido na BD");
				}
			}else{
				System.out.println("algo deu errado com o utilizador");
				return 0;
			}
		}
		else{
			System.out.println("Não é cuidador");
			familiar.setUtilizador(null);
		}
		if (familiar.getMoradaByIdLocalNascimento().getIdMorada() != 0 && familiar.getMoradaByIdMorada().getIdMorada() != 0) {
			System.out.println("Vou inserir ou atualizar o paciente");
			int newId = this.familiarDao.saveOrUpdate(familiar);
			familiar.setIdFamiliar(newId);
			System.out.println("inseri " + this.get(familiar.getIdFamiliar()));
			return newId;
		} else {
			System.out.println("algo deu erro");
			return 0;
		}

	}

	@Override
	public void delete(int familiarId) {
		this.familiarDao.delete(familiarId);
	}

	@Override
	public Familiar get(int familiarId) {
		return this.familiarDao.get(familiarId);
	}
	
	@Override
	public Familiar getFromUserName(String user) {
		return this.familiarDao.getFromUserName(user);
	}

	@Override
	public List<Familiar> list(int idPaciente) {
		return this.familiarDao.list(idPaciente);
	}
}