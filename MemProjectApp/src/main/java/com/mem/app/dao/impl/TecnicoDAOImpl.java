package com.mem.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.mem.app.dao.TecnicoDAO;
import com.mem.app.model.Tecnico;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class TecnicoDAOImpl implements TecnicoDAO {
	private JdbcTemplate jdbcTemplate;

	public TecnicoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Tecnico tecnico) {
		if (tecnico.getIdTecnico() > 0) {
			// update
			String sql = "UPDATE tecnico SET name_completo=?, nome_proprio=?, apelido=?, "
					+ "nome_utilizador=?, password=?, email=? WHERE idTecnico=?";

			jdbcTemplate.update(sql, tecnico.getNomeCompleto(), tecnico.getNomeProprio(),
					tecnico.getApelido(), tecnico.getNomeUtilizador(), tecnico.getPassword(), tecnico.getEmail(), tecnico.getIdTecnico() );
		} else {
			// insert
			String sql = "INSERT INTO tecnico (name_completo, nome_proprio, apelido, nome_utilizador, password, email, idTecnico)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, tecnico.getNomeCompleto(), tecnico.getNomeProprio(),
					tecnico.getApelido(), tecnico.getNomeUtilizador(), tecnico.getPassword(), tecnico.getEmail(), tecnico.getIdTecnico() );
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

				aTecnico.setIdTecnico(rs.getInt("idTecnico"));
				aTecnico.setNomeCompleto(rs.getString("nome_cmpleto"));
				aTecnico.setNomeProprio(rs.getString("nome_proprio"));
				aTecnico.setApelido(rs.getString("apelido"));
				aTecnico.setNomeUtilizador(rs.getString("nome_utilizador"));
				aTecnico.setPassword(rs.getString("password"));
				aTecnico.setEmail(rs.getString("email"));

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
			public Tecnico extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Tecnico tecnico = new Tecnico();
					tecnico.setIdTecnico(rs.getInt("idTecnico"));
					tecnico.setNomeCompleto(rs.getString("nomeCmpleto"));
					tecnico.setNomeProprio(rs.getString("nomeProprio"));
					tecnico.setApelido(rs.getString("apelido"));
					tecnico.setNomeUtilizador(rs.getString("nomeUtilizador"));
					tecnico.setPassword(rs.getString("password"));
					tecnico.setEmail(rs.getString("email"));

					return tecnico;
				}

				return null;
			}

		});
	}

	@Override
	public Tecnico getFromEmail(String email) {
		String sql = "SELECT * FROM tecnico WHERE email=" + email;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Tecnico>() {

			@Override
			public Tecnico extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Tecnico tecnico = new Tecnico();
					tecnico.setIdTecnico(rs.getInt("idTecnico"));
					tecnico.setNomeCompleto(rs.getString("nomeCmpleto"));
					tecnico.setNomeProprio(rs.getString("nomeProprio"));
					tecnico.setApelido(rs.getString("apelido"));
					tecnico.setNomeUtilizador(rs.getString("nomeUtilizador"));
					tecnico.setPassword(rs.getString("password"));
					tecnico.setEmail(rs.getString("email"));

					return tecnico;
				}

				return null;
			}

		});
	}

	@Override
	public Tecnico getFromUserName(String user) {
		String sql = "SELECT * FROM tecnico WHERE user_name=" + user;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Tecnico>() {

			@Override
			public Tecnico extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Tecnico tecnico = new Tecnico();
					tecnico.setIdTecnico(rs.getInt("idTecnico"));
					tecnico.setNomeCompleto(rs.getString("nomeCmpleto"));
					tecnico.setNomeProprio(rs.getString("nomeProprio"));
					tecnico.setApelido(rs.getString("apelido"));
					tecnico.setNomeUtilizador(rs.getString("nomeUtilizador"));
					tecnico.setPassword(rs.getString("password"));
					tecnico.setEmail(rs.getString("email"));

					return tecnico;
				}

				return null;
			}

		});
	}

}
