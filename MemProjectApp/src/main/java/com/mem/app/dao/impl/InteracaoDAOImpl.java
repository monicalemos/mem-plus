package com.mem.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mem.app.dao.InteracaoDAO;
import com.mem.app.model.Interacao;
import com.mem.app.model.Paciente;

public class InteracaoDAOImpl implements InteracaoDAO {

	private JdbcTemplate jdbcTemplate;
	private PacienteDAOImpl pacienteImpl;


	public InteracaoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		pacienteImpl = new PacienteDAOImpl(dataSource);
	}

	@Override
	public void saveOrUpdate(Interacao interacao) {
		if (interacao.getIdInteracao() > 0) {
			// update
			String sql = "UPDATE interacao SET "
					+ "data=?, "
					+ "Paciente_idPaciente=? "
					+ "WHERE idInteracao=?";

			jdbcTemplate.update(sql, 
					interacao.getData(),
					interacao.getPaciente().getIdPaciente(), 
					interacao.getIdInteracao() );
		} else {
			// insert
			String sql = "INSERT INTO interacao "
					+ "(idInteracao, "
					+ "data, "
					+ "Paciente_idPaciente)"
					+ " VALUES (?, ?)";
			jdbcTemplate.update(sql, 
					interacao.getIdInteracao(), 
					interacao.getData(), 
					interacao.getPaciente().getIdPaciente());
		}
	}

	@Override
	public void delete(int interacaoId) {
		String sql = "DELETE FROM interacao WHERE idInteracao=?";
		jdbcTemplate.update(sql, interacaoId);
	}

	@Override
	public Interacao get(int interacaoId) {
		String sql = "SELECT * FROM interacao WHERE idInteracao=" + interacaoId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Interacao>() {

			@Override
			public Interacao extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Paciente paciente = pacienteImpl.get(rs.getInt("Paciente_idPaciente"));

					Interacao interacao = new Interacao();
					interacao.setIdInteracao(rs.getInt("idInteracao"));
					interacao.setData(rs.getDate("data"));
					interacao.setPaciente(paciente);
					return interacao;
				}

				return null;
			}

		});
	}

	@Override
	public List<Interacao> list() {
		String sql = "SELECT * FROM interacao";
		List<Interacao> listInteracao = jdbcTemplate.query(sql, new RowMapper<Interacao>() {

			@Override
			public Interacao mapRow(ResultSet rs, int rowNum) throws SQLException {
				Paciente paciente = pacienteImpl.get(rs.getInt("Paciente_idPaciente"));

				Interacao aInteracao = new Interacao();
				aInteracao.setIdInteracao(rs.getInt("idInteracao"));
				aInteracao.setData(rs.getDate("data"));
				aInteracao.setPaciente(paciente);
				return aInteracao;
			}

		});

		return listInteracao;
	}

}
