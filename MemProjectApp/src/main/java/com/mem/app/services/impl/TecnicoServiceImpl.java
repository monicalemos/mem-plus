package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.TecnicoDAO;
import com.mem.app.model.Tecnico;
import com.mem.app.model.Utilizador;
import com.mem.app.services.TecnicoService;
import com.mem.app.services.UtilizadorService;

@Service("tecnicoService")
public class TecnicoServiceImpl implements TecnicoService {

	TecnicoDAO tecnicoDao;

	UtilizadorService utilizadorService;

	@Autowired
	public void setTecnicoDao(TecnicoDAO tecnicoDao) {
		this.tecnicoDao = tecnicoDao;
	}
	@Autowired
	public void setUtilizadorService(UtilizadorService utilizadorService) {
		this.utilizadorService = utilizadorService;
	}
	

	@Override
	public int saveOrUpdate(Tecnico tecnico) {
		System.out.println("vou verificar o utilizador do tecnico");
		if (tecnico.getUtilizador() != null) {
			Utilizador utilizador = tecnico.getUtilizador();
			System.out.println("Utilizador: " + utilizador.getEmail() + ", " + utilizador.getNomeUtilizador() + " - " + utilizador.getPassword() + " - " + utilizador.getTipoUtilizador());

			if (utilizador.getIdUtilizador() == 0 || utilizadorService.get(utilizador.getIdUtilizador()) == null) {
				System.out.println("tem utilizador definido na app mas n na BD");
				 int newid = utilizadorService.saveOrUpdate(utilizador);
				 utilizador.setIdUtilizador(newid);
			}
		}
		System.out.println("Vou inserir o tecnico");
		int newId = this.tecnicoDao.saveOrUpdate(tecnico);
		tecnico.setIdTecnico(newId);
		System.out.println("inseri " + this.get(tecnico.getIdTecnico()));
		
		return newId;
	}

	@Override
	public void delete(int tecnicoId) {
		this.tecnicoDao.delete(tecnicoId);
	}

	@Override
	public Tecnico get(int tecnicoId) {
		return this.tecnicoDao.get(tecnicoId);
	}

	@Override
	public List<Tecnico> list() {
		return this.tecnicoDao.list();
	}
}
