package com.mem.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mem.app.dao.EventoDAO;
import com.mem.app.model.Evento;
import com.mem.app.model.Familiar;
import com.mem.app.model.Morada;
import com.mem.app.model.Paciente;

public class EventoDAOImpl implements EventoDAO {


	private JdbcTemplate jdbcTemplate;
	private MoradaDAOImpl moradaImpl;
	private PacienteDAOImpl pacienteImpl;
	private FamiliarDAOImpl familiarImpl;

	public EventoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		moradaImpl = new MoradaDAOImpl(dataSource);
		pacienteImpl = new PacienteDAOImpl(dataSource);
		familiarImpl = new FamiliarDAOImpl(dataSource);
	}

	@Override
	public void saveOrUpdateWithoutFamily(Evento evento) {
		if (evento.getIdEvento() > 0) {
			// update
			String sql = "UPDATE evento SET "
					+ "data=?, "
					+ "tipo_de_evento=?, "
					+ "local_Evento_idMorada=?, "
					+ "descricao=? "
					+ "WHERE idEvento=?";

			jdbcTemplate.update(sql, 
					evento.getData(), 
					evento.getTipoDeEvento(), 
					evento.getMorada().getIdMorada(),
					evento.getDescricao(), 
					evento.getIdEvento());
		} else {
			// insert
			String sql = "INSERT INTO evento (data, "
					+ "tipo_de_evento, "
					+ "local_Evento_idMorada,"
					+ "descricao, "
					+ "idEvento)"
					+ " VALUES (?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, 
					evento.getData(), 
					evento.getTipoDeEvento(), 
					evento.getTipoDeEvento(), 
					evento.getMorada().getIdMorada(),
					evento.getDescricao(), 
					evento.getIdEvento());
		}
	}

	@Override
	public void saveOrUpdateWithFamily(Evento evento) {
		if (evento.getIdEvento() > 0) {
			// update
			String sql = "UPDATE evento SET "
					+ "data=?, "
					+ "tipo_de_evento=?, "
					+ "local_Evento_idMorada=?, "
					+ "descricao=?, "
					+ "Familiar_idFamiliar=? "
					+ "WHERE idEvento=?";

			jdbcTemplate.update(sql, 
					evento.getData(), 
					evento.getTipoDeEvento(), 
					evento.getMorada().getIdMorada(),
					evento.getDescricao(), 
					evento.getFamiliar().getIdFamiliar(),
					evento.getIdEvento());
		} else {
			// insert
			String sql = "INSERT INTO evento ("
					+ "idEvento, "
					+ "data, "
					+ "tipo_de_evento, "
					+ "local_Evento_idMorada,"
					+ "descricao, "
					+ "Familiar_idFamiliar)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, 
					evento.getIdEvento(), 
					evento.getData(), 
					evento.getTipoDeEvento(), 
					evento.getMorada().getIdMorada(),
					evento.getDescricao(), 
					evento.getFamiliar().getIdFamiliar());
		}
	}

	@Override
	public void delete(int eventoId) {
		String sql = "DELETE FROM evento WHERE idEvento=?";
		jdbcTemplate.update(sql, eventoId);

	}

	@Override
	public Evento get(int eventoId) {
		String sql = "SELECT * FROM evento WHERE idEvento=" + eventoId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Evento>() {

			@Override
			public Evento extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Morada morada = moradaImpl.get(rs.getInt("Morada_idMorada"));
					Paciente paciente = pacienteImpl.get(rs.getInt("Paciente_idPaciente"));
					Familiar familiar = familiarImpl.get(rs.getInt("Familiar_idFamiliar"));

					Evento evento = new Evento();
					evento.setIdEvento(rs.getInt("idEvento"));
					evento.setData(rs.getDate("data"));
					evento.setTipoDeEvento(rs.getString("tipo_de_evento"));
					evento.setMorada(morada);
					evento.setDescricao(rs.getString("descricao"));
					evento.setFamiliar(familiar);
					evento.setPaciente(paciente);
					return evento;
				}

				return null;
			}

		});
	}


	@Override
	public List<Evento> list(Paciente paciente) {
		String sql = "SELECT * FROM Evento, morada "
				+ "WHERE Evento.Morada_idMorada = Morada.idMorada"
				+ "and paciente.Paciente_idPaciente=" + paciente.getIdPaciente();

		List<Evento> listEventos = jdbcTemplate.query(sql, new RowMapper<Evento>() {

			@Override
			public Evento mapRow(ResultSet rs, int rowNum) throws SQLException {

				Morada morada = moradaImpl.get(rs.getInt("Morada_idMorada"));
				Paciente paciente = pacienteImpl.get(rs.getInt("Paciente_idPaciente"));
				Familiar familiar = familiarImpl.get(rs.getInt("Familiar_idFamiliar"));

				Evento evento = new Evento();
				evento.setIdEvento(rs.getInt("idEvento"));
				evento.setData(rs.getDate("data"));
				evento.setTipoDeEvento(rs.getString("tipo_de_evento"));
				evento.setMorada(morada);
				evento.setDescricao(rs.getString("descricao"));
				evento.setFamiliar(familiar);
				evento.setPaciente(paciente);
				return evento;
			}

		});

		return listEventos;
	}

}
