package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.EventoDAO;
import com.mem.app.model.Evento;
import com.mem.app.model.EventoId;
import com.mem.app.model.Familiar;
import com.mem.app.model.Morada;
import com.mem.app.model.Paciente;
import com.mem.app.services.EventoService;
import com.mem.app.services.MoradaService;

@Service("eventoService")
public class EventoServiceImpl implements EventoService {
	
	EventoDAO eventoDao;
	MoradaService moradaService;
	
	@Autowired
	public void setEventoDao(EventoDAO eventoDao){
		this.eventoDao = eventoDao;
	}
	@Autowired
	public void setMoradaService(MoradaService moradaService) {
		this.moradaService = moradaService;
	}
	
	@Override
	public int saveOrUpdateWithFamily(Evento evento) {

		Paciente paciente = null;
		Familiar familiar = null;
		
		EventoId eventoId = evento.getId();
		if(evento.getPaciente() != null){
			if(evento.getFamiliar() != null){
				System.out.println("vou verificar os dados do evento");
				
				paciente = evento.getPaciente();
				System.out.println("Tem o paciente: " + paciente);
				familiar = evento.getFamiliar();
				System.out.println("Tem o familiar: " + familiar);
				
				System.out.println("Tem eventoId: " + eventoId);
				if(eventoId.getIdPaciente() == 0){
					eventoId.setIdPaciente(paciente.getIdPaciente());
				}
				
				int idMorada = 0;
				
				if(evento.getMorada() != null){
					System.out.println("tem morada");
					
					Morada morada = evento.getMorada();
					if (moradaService.getMoradaCompleta(morada.getPais(), morada.getRegiao(), morada.getCidade()) == null
							&& (morada.getIdMorada() == 0 || moradaService.get(morada.getIdMorada()) == null)) {
						System.out.println("tem morada definido na app mas n na BD");
						int newid = moradaService.saveOrUpdate(morada);
						System.out.println("novo id de morada: " + newid);
						System.out.println("dados da morada: " + morada.getCidade());
						morada.setIdMorada(newid);
					} else {
						System.out.println("Já tem morada na BD");
						System.out.println("idMorada: " + evento.getMorada().getIdMorada());
						idMorada = moradaService.getMoradaCompleta(morada.getPais(), morada.getRegiao(), morada.getCidade())
								.getIdMorada();
						System.out.println("idMorada encontrado: " + idMorada);
						evento.getMorada().setIdMorada(idMorada);
					}
				}
				
				System.out.println("Vou inserir ou atualizar o evento");
				int newId = this.eventoDao.saveOrUpdateWithFamily(evento);
				
				eventoId.setIdEvento(newId);
				System.out.println("tem id? " + eventoId.getIdEvento());
				evento.setId(eventoId);
				System.out.println("tem eventoId? " + evento.getId());
				
				System.out.println("inseri " + this.get(newId));
				return newId;
				
			}else{
				return this.saveOrUpdateWithoutFamily(evento);
			}
		}else{
			System.out.println("Não tem paciente");
			return 0;
		}
	}
	
	@Override
	public int saveOrUpdateWithoutFamily(Evento evento) {

		Paciente paciente = null;		
		EventoId eventoId = evento.getId();
		
		if(evento.getPaciente() != null){
			if(evento.getFamiliar() == null){
				System.out.println("vou verificar os dados do evento");
				paciente = evento.getPaciente();
				System.out.println("Tem o paciente: " + paciente);
								
				System.out.println("Tem eventoId: " + eventoId);
				if(eventoId.getIdPaciente() == 0){
					eventoId.setIdPaciente(paciente.getIdPaciente());
				}
				
				int idMorada = 0;
				
				if(evento.getMorada() != null){
					System.out.println("tem morada");
					
					Morada morada = evento.getMorada();
					if (moradaService.getMoradaCompleta(morada.getPais(), morada.getRegiao(), morada.getCidade()) == null
							&& (morada.getIdMorada() == 0 || moradaService.get(morada.getIdMorada()) == null)) {
						System.out.println("tem morada definido na app mas n na BD");
						int newid = moradaService.saveOrUpdate(morada);
						System.out.println("novo id de morada: " + newid);
						System.out.println("dados da morada: " + morada.getCidade());
						morada.setIdMorada(newid);
					} else {
						System.out.println("Já tem morada na BD");
						System.out.println("idMorada: " + evento.getMorada().getIdMorada());
						idMorada = moradaService.getMoradaCompleta(morada.getPais(), morada.getRegiao(), morada.getCidade())
								.getIdMorada();
						System.out.println("idMorada encontrado: " + idMorada);
						evento.getMorada().setIdMorada(idMorada);
					}
				}
				
				System.out.println("Vou inserir ou atualizar o evento");
				int newId = this.eventoDao.saveOrUpdateWithoutFamily(evento);
				
				eventoId.setIdEvento(newId);
				evento.setId(eventoId);
				
				System.out.println("inseri " + this.get(evento.getId().getIdEvento()));
				return newId;
				
			}else{
				return this.saveOrUpdateWithFamily(evento);
			}
		}else{
			System.out.println("Não tem paciente");
			return 0;
		}
	}
	
	@Override
	public void delete(int eventoId) {
		this.eventoDao.delete(eventoId);
	}

	@Override
	public Evento get(int eventoId) {
		return this.eventoDao.get(eventoId);
	}

	@Override
	public List<Evento> list(Paciente paciente) {
		return this.eventoDao.list(paciente.getIdPaciente());
	}
}