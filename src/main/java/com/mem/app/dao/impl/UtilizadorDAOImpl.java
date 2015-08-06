package com.mem.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mem.app.dao.UtilizadorDAO;
import com.mem.app.model.Utilizador;

public class UtilizadorDAOImpl implements UtilizadorDAO {
	private JdbcTemplate jdbcTemplate;


	public UtilizadorDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Override
	public void saveOrUpdate(Utilizador utilizador) {
		if (utilizador.getIdUtilizador() > 0) {
			// update
			String sql = "UPDATE utilizador SET nameUtilizador=?, password=?, tipoUtilizador=?, email=? WHERE idUtilizador=?";

			jdbcTemplate.update(sql, utilizador.getNomeUtilizador(), utilizador.getPassword(), utilizador.getTipoUtilizador(), utilizador.getEmail(), utilizador.getIdUtilizador() );
		} else {
			// insert
			String sql = "INSERT INTO utilizador (idUtilizador, nomeUtilizador, password, tipoUtilizador, email)"
					+ " VALUES (?, ?)";
			jdbcTemplate.update(sql, utilizador.getIdUtilizador(), utilizador.getNomeUtilizador(), utilizador.getPassword(), utilizador.getTecnicos(), utilizador.getEmail());
		}
	}

	@Override
	public void delete(int utilizadorId) {
		String sql = "DELETE FROM utilizador WHERE idUtilizador=?";
		jdbcTemplate.update(sql, utilizadorId);

	}

	@Override
	public Utilizador get(int utilizadorId) {
		String sql = "SELECT * FROM utilizador WHERE idUtilizador=" + utilizadorId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Utilizador>() {

			@Override
			public Utilizador extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Utilizador utilizador = new Utilizador();
					utilizador.setIdUtilizador(rs.getInt("idUtilizador"));
					utilizador.setNomeUtilizador(rs.getString("nomeUtilizador"));
					utilizador.setPassword(rs.getString("password"));
					utilizador.setTipoUtilizador(rs.getString("tipoUtilizador"));
					utilizador.setEmail(rs.getString("email"));
					return utilizador;
				}

				return null;
			}

		});
	}
	//idUtilizador, String nome, Set<?> pacientes

	@Override
	public List<Utilizador> list() {
		String sql = "SELECT * FROM Utilizador";
		List<Utilizador> listUtilizador = jdbcTemplate.query(sql, new RowMapper<Utilizador>() {

			@Override
			public Utilizador mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilizador aUtilizador = new Utilizador();

				aUtilizador.setIdUtilizador(rs.getInt("idUtilizador"));
				aUtilizador.setNomeUtilizador(rs.getString("nomeUtilizador"));
				aUtilizador.setPassword(rs.getString("password"));
				aUtilizador.setTipoUtilizador(rs.getString("tipoUtilizador"));
				aUtilizador.setEmail(rs.getString("email"));
				return aUtilizador;
			}

		});

		return listUtilizador;
	}

}
