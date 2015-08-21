package com.mem.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.mem.app.dao.TecnicoDAO;
import com.mem.app.model.Tecnico;
import com.mem.app.model.Utilizador;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class TecnicoDAOImpl implements TecnicoDAO {
	private JdbcTemplate jdbcTemplate;
	private UtilizadorDAOImpl utilizadorImpl;

	public TecnicoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		utilizadorImpl = new UtilizadorDAOImpl(dataSource);
	}

	@Override
	public void saveOrUpdate(Tecnico tecnico) {
		if (tecnico.getIdTecnico() > 0) {
			// update
			String sql = "UPDATE tecnico SET nome_proprio=?, apelido=?, "
					+ "telefone=?, idUtilizador=? WHERE idTecnico=?";

			jdbcTemplate.update(sql, tecnico.getNomeProprio(), tecnico.getApelido(), tecnico.getTelefone(),
					tecnico.getUtilizador().getIdUtilizador(), tecnico.getIdTecnico());
		} else {
			// insert
			String sql = "INSERT INTO tecnico (idUtilizador, nome_proprio, apelido, telefone)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, tecnico.getIdTecnico(), tecnico.getNomeProprio(), tecnico.getApelido(),
					tecnico.getTelefone(), tecnico.getUtilizador().getIdUtilizador());
		}
	}

	@Override
	public void delete(int tecnicoId) {
		String sql = "DELETE FROM tecnico WHERE idTecnico=?";
		jdbcTemplate.update(sql, tecnicoId);
	}

	@Override
	public List<Tecnico> list() {

		String sql = "SELECT * FROM tecnico";
		List<Tecnico> listTecnicos = jdbcTemplate.query(sql, new RowMapper<Tecnico>() {

			@Override
			public Tecnico mapRow(ResultSet rs, int rowNum) throws SQLException {
				Tecnico aTecnico = new Tecnico();
				Utilizador utilizador = utilizadorImpl.get(rs.getInt("idUtilizaodr"));
				
				aTecnico.setIdTecnico(rs.getInt("idTecnico"));
				aTecnico.setNomeProprio(rs.getString("nome_proprio"));
				aTecnico.setApelido(rs.getString("apelido"));
				aTecnico.setTelefone(rs.getInt("telefone"));
				aTecnico.setUtilizador(utilizador);

				return aTecnico;
			}

		});

		return listTecnicos;
	}

	@Override
	public Tecnico get(int tecnicoId) {
		String sql = "SELECT * FROM tecnico WHERE idTecnico=" + tecnicoId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Tecnico>() {

			@Override
			public Tecnico extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Utilizador utilizador = utilizadorImpl.get(rs.getInt("idUtilizaodr"));

					Tecnico tecnico = new Tecnico();
					tecnico.setIdTecnico(rs.getInt("idTecnico"));
					tecnico.setNomeProprio(rs.getString("nome_proprio"));
					tecnico.setApelido(rs.getString("apelido"));
					tecnico.setTelefone(rs.getInt("telefone"));
					tecnico.setUtilizador(utilizador);

					return tecnico;
				}

				return null;
			}

		});
	}

	@Override
	public Tecnico getFromEmail(String email) {
		String sql = "SELECT t.* FROM tecnico t, Utilizador u WHERE t.idUtilizador = u.idUtilizador and u.email="
				+ email;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Tecnico>() {

			@Override
			public Tecnico extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Utilizador utilizador = utilizadorImpl.get(rs.getInt("idUtilizaodr"));
					
					Tecnico tecnico = new Tecnico();
					tecnico.setIdTecnico(rs.getInt("idTecnico"));
					tecnico.setNomeProprio(rs.getString("nome_proprio"));
					tecnico.setApelido(rs.getString("apelido"));
					tecnico.setTelefone(rs.getInt("telefone"));
					tecnico.setUtilizador(utilizador);

					return tecnico;
				}

				return null;
			}

		});
	}

	@Override
	public Tecnico getFromUserName(String user) {
		String sql = "SELECT t.* FROM tecnico t, Utilizador u WHERE t.idUtilizador = u.idUtilizador and nomeUtilizador="
				+ user;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Tecnico>() {

			@Override
			public Tecnico extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Utilizador utilizador = utilizadorImpl.get(rs.getInt("idUtilizaodr"));
					
					Tecnico tecnico = new Tecnico();
					tecnico.setIdTecnico(rs.getInt("idTecnico"));
					tecnico.setNomeProprio(rs.getString("nome_proprio"));
					tecnico.setApelido(rs.getString("apelido"));
					tecnico.setTelefone(rs.getInt("telefone"));
					tecnico.setUtilizador(utilizador);

					return tecnico;
				}

				return null;
			}

		});
	}

}
