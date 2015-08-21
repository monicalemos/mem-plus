package com.mem.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mem.app.dao.AtividadeDAO;
import com.mem.app.model.Atividade;

public class AtividadeDAOImpl implements AtividadeDAO {
	private JdbcTemplate jdbcTemplate;


	public AtividadeDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Override
	public void saveOrUpdate(Atividade atividade) {
		if (atividade.getIdAtividade() > 0) {
			// update
			String sql = "UPDATE atividade SET name=? WHERE idAtividade=?";

			jdbcTemplate.update(sql, atividade.getNome(), atividade.getIdAtividade() );
		} else {
			// insert
			String sql = "INSERT INTO atividade (idAtividade, nome)"
					+ " VALUES (?, ?)";
			jdbcTemplate.update(sql, atividade.getIdAtividade(), atividade.getNome());
		}
	}

	@Override
	public void delete(int atividadeId) {
		String sql = "DELETE FROM atividade WHERE idAtividade=?";
		jdbcTemplate.update(sql, atividadeId);

	}

	@Override
	public Atividade get(int atividadeId) {
		String sql = "SELECT * FROM atividade WHERE idAtividade=" + atividadeId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Atividade>() {

			@Override
			public Atividade extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Atividade atividade = new Atividade();
					atividade.setIdAtividade(rs.getInt("idAtividade"));
					atividade.setNome(rs.getString("nome"));
					return atividade;
				}

				return null;
			}

		});
	}
	//idAtividade, String nome, Set<?> pacientes

	@Override
	public List<Atividade> list() {
		String sql = "SELECT * FROM Atividade";
		List<Atividade> listAtividade = jdbcTemplate.query(sql, new RowMapper<Atividade>() {

			@Override
			public Atividade mapRow(ResultSet rs, int rowNum) throws SQLException {
				Atividade aAtividade = new Atividade();

				aAtividade.setIdAtividade(rs.getInt("idAtividade"));
				aAtividade.setNome(rs.getString("nome"));
				return aAtividade;
			}

		});

		return listAtividade;
	}

}
