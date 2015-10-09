package com.mem.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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
import com.mem.app.model.EventoId;
import com.mem.app.model.Familiar;
import com.mem.app.model.Morada;
import com.mem.app.model.Paciente;
@Repository
public class EventoDAOImpl implements EventoDAO {


	private JdbcTemplate jdbcTemplate;
	private MoradaDAOImpl moradaImpl;
	private PacienteDAOImpl pacienteImpl;
	private FamiliarDAOImpl familiarImpl;
	private Connection connection;
	
	@Autowired
	public EventoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		moradaImpl = new MoradaDAOImpl(dataSource);
		pacienteImpl = new PacienteDAOImpl(dataSource);
		familiarImpl = new FamiliarDAOImpl(dataSource);
		
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int saveOrUpdateWithoutFamily(Evento evento) {
		
		if (evento.getId().getIdEvento() > 0) {
			// update
			System.out.println("UPDATE");
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
			
			return evento.getId().getIdEvento();
		} else {
			// insert
			final String INSERT_SQL = "INSERT INTO evento ("
					+ "data, "
					+ "tipoEvento, "
					+ "idLocalEvento, "
					+ "descricao, "
					+ "idPaciente)"
					+ " VALUES (?, ?, ?, ?, ?)";

			int newId = 0;
			final Date data = (Date) evento.getData();
			final String tipoEvento = evento.getTipoEvento();
			final int  idLocalEvento = evento.getMorada().getIdMorada();
			final String descricao  = evento.getDescricao();
			final int idPaciente = evento.getPaciente().getIdPaciente();
			
			System.out.println("Variáveis definidas no insert: " + data + " - " + tipoEvento + " - " + idLocalEvento + " - " + descricao + " - " + idPaciente);
			
			PreparedStatement ps;
			try {
				ps = connection.prepareStatement(INSERT_SQL.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setDate(1, new java.sql.Date(data.getTime()));
				ps.setString(2, tipoEvento);
				ps.setInt(3, idLocalEvento);
				ps.setString(4, descricao);
				ps.setInt(5,  idPaciente);
				ps.executeUpdate();

				System.out.println("executou a query");
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					System.out.println("tem resultados");
					newId = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("novo id do evento: " + newId);
			return newId;	
		}
	}

	@Override
	public int saveOrUpdateWithFamily(Evento evento) {
		if (evento.getId().getIdEvento() > 0) {
			// update
			System.out.println("UPDATE");
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
			return evento.getId().getIdEvento();
		} else {
			// insert
			final String INSERT_SQL = "INSERT INTO evento ("
					+ "data, "
					+ "tipoEvento, "
					+ "idLocalEvento, "
					+ "descricao, "
					+ "idFamiliar, "
					+ "idPaciente)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			int newId = 0;
			final Date data = (Date) evento.getData();
			final String tipoEvento = evento.getTipoEvento();
			final int  idLocalEvento = evento.getMorada().getIdMorada();
			final String descricao  = evento.getDescricao();
			final int idFamiliar = evento.getFamiliar().getIdFamiliar();
			final int idPaciente = evento.getPaciente().getIdPaciente();
			
			System.out.println("Variáveis definidas no insert: " + data + " - " + tipoEvento + " - " + idLocalEvento + 
					" - " + descricao + " - " + idPaciente + " - " + idFamiliar);
			
			PreparedStatement ps;
			try {
				ps = connection.prepareStatement(INSERT_SQL.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setDate(1, new java.sql.Date(data.getTime()));
				ps.setString(2, tipoEvento);
				ps.setInt(3, idLocalEvento);
				ps.setString(4, descricao);
				ps.setInt(5, idFamiliar);
				ps.setInt(6, idPaciente);
				ps.executeUpdate();

				System.out.println("executou a query");
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					System.out.println("tem resultados");
					newId = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("novo id do evento: " + newId);
			return newId;	
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
		
		System.out.println("evento id " + eventoId);
		return jdbcTemplate.query(sql, new ResultSetExtractor<Evento>() {
			@Override
			public Evento extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				
				if (rs.next()) {
					
					Morada morada = moradaImpl.get(rs.getInt("idLocalEvento"));
					Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
					Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));
					
					EventoId eventoId = new EventoId(rs.getInt("idEvento"), paciente.getIdPaciente());

					Evento evento = new Evento();
					evento.setId(eventoId);
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
	public List<Evento> list(int idPaciente) {
		System.out.println("id paciente " + idPaciente);
		
		String sql = "SELECT * FROM Evento WHERE Evento.idPaciente = " + idPaciente;

		List<Evento> listEventos = jdbcTemplate.query(sql, new RowMapper<Evento>() {

			@Override
			public Evento mapRow(ResultSet rs, int rowNum) throws SQLException {

				Morada morada = moradaImpl.get(rs.getInt("idLocalEvento"));
				Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
				Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));
				EventoId eventoId = new EventoId(rs.getInt("idEvento"), paciente.getIdPaciente());

				Evento evento = new Evento();
				evento.setId(eventoId);
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
