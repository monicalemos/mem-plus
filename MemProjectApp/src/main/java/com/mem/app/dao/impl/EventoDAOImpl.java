package com.mem.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mem.app.dao.EventoDAO;
import com.mem.app.model.Evento;
import com.mem.app.model.Familiar;
import com.mem.app.model.Morada;
import com.mem.app.model.Paciente;
@Repository
public class EventoDAOImpl implements EventoDAO {


	private JdbcTemplate jdbcTemplate;
	private MoradaDAOImpl moradaImpl;
	private PacienteDAOImpl pacienteImpl;
	private FamiliarDAOImpl familiarImpl;

	@Autowired
	public EventoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		moradaImpl = new MoradaDAOImpl(dataSource);
		pacienteImpl = new PacienteDAOImpl(dataSource);
		familiarImpl = new FamiliarDAOImpl(dataSource);
	}

	@Override
	public void saveOrUpdateWithoutFamily(Evento evento) {
		
		if (evento.getId().getIdEvento() > 0) {
			// update
			String sql = "UPDATE evento SET "
					+ "data=?, "
					+ "tipoEvento=?, "
					+ "idLocalEvento=?, "
					+ "descricao=?, "
					+ "idPaciente=? "
					+ "WHERE idEvento=?";

			jdbcTemplate.update(sql, 
					evento.getData(), 
					evento.getTipoEvento(), 
					evento.getMorada().getIdMorada(),
					evento.getDescricao(), 
					evento.getPaciente().getIdPaciente(),
					evento.getId().getIdEvento());
		} else {
			// insert
			String sql = "INSERT INTO evento ("
					+ "idEvento, "
					+ "data, "
					+ "tipoEvento, "
					+ "idLocalEvento, "
					+ "descricao, "
					+ "idPaciente)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, 
					evento.getId().getIdEvento(),
					evento.getData(), 
					evento.getTipoEvento(), 
					evento.getTipoEvento(), 
					evento.getMorada().getIdMorada(),
					evento.getDescricao(), 
					evento.getPaciente().getIdPaciente());
		}
	}

	@Override
	public void saveOrUpdateWithFamily(Evento evento) {
		if (evento.getId().getIdEvento() > 0) {
			// update
			String sql = "UPDATE evento SET "
					+ "data=?, "
					+ "tipoEvento=?, "
					+ "idLocalEvento=?, "
					+ "descricao=?, "
					+ "idFamiliar=?, "
					+ "idPaciente=? "
					+ "WHERE idEvento=?";

			jdbcTemplate.update(sql, 
					evento.getData(), 
					evento.getTipoEvento(), 
					evento.getMorada().getIdMorada(),
					evento.getDescricao(), 
					evento.getFamiliar().getIdFamiliar(),
					evento.getPaciente().getIdPaciente(),
					evento.getId().getIdEvento());
		} else {
			// insert
			String sql = "INSERT INTO evento ("
					+ "idEvento, "
					+ "data, "
					+ "tipoEvento, "
					+ "idLocalEvento, "
					+ "descricao, "
					+ "idFamiliar, "
					+ "idPaciente)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, 
					evento.getId().getIdEvento(), 
					evento.getData(), 
					evento.getTipoEvento(), 
					evento.getMorada().getIdMorada(),
					evento.getDescricao(), 
					evento.getFamiliar().getIdFamiliar(),
					evento.getPaciente().getIdPaciente());
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
					Morada morada = moradaImpl.get(rs.getInt("idLocalEvento"));
					Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
					Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));

					Evento evento = new Evento();
					evento.getId().setIdEvento(rs.getInt("idEvento"));
					evento.setData(rs.getDate("data"));
					evento.setTipoEvento(rs.getString("tipoEvento"));
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
				+ "WHERE Evento.idLocalEvento = Morada.idMorada"
				+ "and paciente.idPaciente=" + paciente.getIdPaciente();

		List<Evento> listEventos = jdbcTemplate.query(sql, new RowMapper<Evento>() {

			@Override
			public Evento mapRow(ResultSet rs, int rowNum) throws SQLException {

				Morada morada = moradaImpl.get(rs.getInt("idLocalEvento"));
				Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
				Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));

				Evento evento = new Evento();
				evento.getId().setIdEvento(rs.getInt("idEvento"));
				evento.setData(rs.getDate("data"));
				evento.setTipoEvento(rs.getString("tipoEvento"));
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
