package com.mem.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.app.dao.PacienteDAO;
import com.mem.app.model.Morada;
import com.mem.app.model.Paciente;
import com.mem.app.services.PacienteService;
import com.mem.app.services.MoradaService;

@Service("pacienteService")
public class PacienteServiceImpl implements PacienteService {

	PacienteDAO pacienteDao;

	MoradaService moradaService;

	@Autowired
	public void setPacienteDao(PacienteDAO pacienteDao) {
		this.pacienteDao = pacienteDao;
	}

	@Autowired
	public void setMoradaService(MoradaService moradaService) {
		this.moradaService = moradaService;
	}

	@Override
	public int saveOrUpdate(Paciente paciente) {
		System.out.println("vou verificar as moradas do paciente");

		int idLocalNascimento = 0;
		int idMorada = 0;

		if (paciente.getMoradaByIdLocalNascimento() != null) {
			System.out.println("tem morada de nascimento");

			Morada localNascimento = paciente.getMoradaByIdLocalNascimento();
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
						"idLocalNascimento no paciente: " + paciente.getMoradaByIdLocalNascimento().getIdMorada());
				
				idLocalNascimento = moradaService.getMoradaCompleta(localNascimento.getPais(),
						localNascimento.getRegiao(), localNascimento.getCidade()).getIdMorada();
				System.out.println("idLocalMorada encontrado: " + idLocalNascimento);
				paciente.getMoradaByIdLocalNascimento().setIdMorada(idLocalNascimento);
			}
		}
		else{
			System.out.println("algo deu errado com o local de nascimento");
			return 0;
		}

		if (paciente.getMoradaByIdMorada() != null) {
			System.out.println("tem morada actual");

			Morada morada = paciente.getMoradaByIdMorada();
			if (moradaService.getMoradaCompleta(morada.getPais(), morada.getRegiao(), morada.getCidade()) == null
					&& (morada.getIdMorada() == 0 || moradaService.get(morada.getIdMorada()) == null)) {
				System.out.println("tem morada definido na app mas n na BD");
				int newid = moradaService.saveOrUpdate(morada);
				System.out.println("novo id de morada: " + newid);
				System.out.println("dados da morada: " + morada.getCidade());
				morada.setIdMorada(newid);
			} else {
				System.out.println("Já tem morada na BD");
				System.out.println("idMorada: " + paciente.getMoradaByIdMorada().getIdMorada());
				idMorada = moradaService.getMoradaCompleta(morada.getPais(), morada.getRegiao(), morada.getCidade())
						.getIdMorada();
				System.out.println("idMorada encontrado: " + idMorada);
				paciente.getMoradaByIdMorada().setIdMorada(idMorada);
			}
		}
		else{
			System.out.println("algo deu errado com a morada");
			return 0;
		}

		if (paciente.getMoradaByIdLocalNascimento().getIdMorada() != 0
				&& paciente.getMoradaByIdMorada().getIdMorada() != 0) {
			System.out.println("Vou inserir ou atualizar o paciente");
			int newId = this.pacienteDao.saveOrUpdate(paciente);
			paciente.setIdPaciente(newId);
			System.out.println("inseri " + this.get(paciente.getIdPaciente()));
			return newId;
		} else {
			System.out.println("algo deu erro");
			return 0;
		}

	}

	@Override
	public void delete(int pacienteId) {
		this.pacienteDao.delete(pacienteId);
	}

	@Override
	public Paciente get(int pacienteId) {
		return this.pacienteDao.get(pacienteId);
	}

	@Override
	public List<Paciente> list(int idPaciente) {
		return this.pacienteDao.list(idPaciente);
	}
}