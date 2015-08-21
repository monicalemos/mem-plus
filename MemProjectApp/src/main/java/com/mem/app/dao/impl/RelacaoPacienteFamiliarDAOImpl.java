package com.mem.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mem.app.dao.RelacaopacientefamiliarDAO;
import com.mem.app.model.Familiar;
import com.mem.app.model.Paciente;
import com.mem.app.model.Relacaopacientefamiliar;
import com.mem.app.model.RelacaopacientefamiliarId;

public class RelacaopacientefamiliarDAOImpl implements RelacaopacientefamiliarDAO {

	private JdbcTemplate jdbcTemplate;
	private PacienteDAOImpl pacienteImpl;
	private FamiliarDAOImpl familiarImpl;

	public RelacaopacientefamiliarDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		pacienteImpl = new PacienteDAOImpl(dataSource);
		familiarImpl = new FamiliarDAOImpl(dataSource);
	}

	@Override
	public void saveOrUpdate(Relacaopacientefamiliar relacaoPacienteFamiliar) {
		if (relacaoPacienteFamiliar.getId().getIdRelacaoPacienteFamiliar() > 0) {
			// update
			String sql = "UPDATE relacaoPacienteFamiliar SET " 
					+ "idFamiliar=?, " 
					+ "idPaciente=?, "
					+ "tipoRelacao=? " 
					+ "WHERE idRelacaopacientefamiliar=?";

			jdbcTemplate.update(sql, 
					relacaoPacienteFamiliar.getFamiliar().getIdFamiliar(),
					relacaoPacienteFamiliar.getPaciente().getIdPaciente(), 
					relacaoPacienteFamiliar.getTipoRelacao(),
					relacaoPacienteFamiliar.getId().getIdRelacaoPacienteFamiliar());
		} else {
			// insert
			String sql = "INSERT INTO relacaoPacienteFamiliar " 
					+ "(idRelacaopacientefamiliar, "
					+ "idFamiliar, " 
					+ "idPaciente," 
					+ "tipoRelacao) " 
					+ " VALUES (?, ?, ?, ?)";
			jdbcTemplate.update(sql, 
					relacaoPacienteFamiliar.getId().getIdRelacaoPacienteFamiliar(),
					relacaoPacienteFamiliar.getFamiliar().getIdFamiliar(),
					relacaoPacienteFamiliar.getPaciente().getIdPaciente(), 
					relacaoPacienteFamiliar.getTipoRelacao());
		}
	}

	@Override
	public void delete(Familiar familiar) {
		String sql = "DELETE FROM relacaoPacienteFamiliar WHERE idFamiliar=?";
		jdbcTemplate.update(sql, familiar.getIdFamiliar());

	}

	@Override
	public Relacaopacientefamiliar get(int idRelacaopacientefamiliar) {
		String sql = "SELECT * FROM relacaoPacienteFamiliar " + "WHERE idRelacaopacientefamiliar="
				+ idRelacaopacientefamiliar;

		return jdbcTemplate.query(sql, new ResultSetExtractor<Relacaopacientefamiliar>() {

			@Override
			public Relacaopacientefamiliar extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
					Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));

					RelacaopacientefamiliarId relId = new RelacaopacientefamiliarId(
							rs.getInt("idRelacaopacientefamiliar"), paciente.getIdPaciente(), familiar.getIdFamiliar());

					Relacaopacientefamiliar relacaoPacienteFamiliar = new Relacaopacientefamiliar();
					relacaoPacienteFamiliar.setId(relId);
					relacaoPacienteFamiliar.setFamiliar(familiar);
					relacaoPacienteFamiliar.setPaciente(paciente);
					relacaoPacienteFamiliar.setTipoRelacao(rs.getString("tipoRelacao"));
					return relacaoPacienteFamiliar;
				}

				return null;
			}

		});
	}

	@Override
	public Relacaopacientefamiliar getWithPatientAndFamily(Paciente paciente, Familiar familiar) {
		String sql = "SELECT * FROM relacaoPacienteFamiliar " 
				+ "WHERE idPaciente=" + paciente.getIdPaciente()
				+ "AND idFamiliar=" + familiar.getIdFamiliar();

		return jdbcTemplate.query(sql, new ResultSetExtractor<Relacaopacientefamiliar>() {

			@Override
			public Relacaopacientefamiliar extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
					Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));

					RelacaopacientefamiliarId relId = new RelacaopacientefamiliarId(
							rs.getInt("idRelacaopacientefamiliar"), paciente.getIdPaciente(), familiar.getIdFamiliar());

					Relacaopacientefamiliar relacaoPacienteFamiliar = new Relacaopacientefamiliar();
					relacaoPacienteFamiliar.setId(relId);
					relacaoPacienteFamiliar.setFamiliar(familiar);
					relacaoPacienteFamiliar.setPaciente(paciente);
					relacaoPacienteFamiliar.setTipoRelacao(rs.getString("tipoRelacao"));
					return relacaoPacienteFamiliar;
				}

				return null;
			}

		});
	}

	@Override
	public List<Relacaopacientefamiliar> list(Paciente paciente) {
		String sql = "SELECT * FROM relacaoPacienteFamiliar " 
				+ "WHERE idPaciente=" + paciente.getIdPaciente();

		List<Relacaopacientefamiliar> listRelacaopacientefamiliar = jdbcTemplate.query(sql,
				new RowMapper<Relacaopacientefamiliar>() {

					@Override
					public Relacaopacientefamiliar mapRow(ResultSet rs, int rowNum) throws SQLException {

						Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
						Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));

						RelacaopacientefamiliarId relId = new RelacaopacientefamiliarId(
								rs.getInt("idRelacaopacientefamiliar"), paciente.getIdPaciente(),
								familiar.getIdFamiliar());

						Relacaopacientefamiliar relacaoPacienteFamiliar = new Relacaopacientefamiliar();
						relacaoPacienteFamiliar.setId(relId);
						relacaoPacienteFamiliar.setFamiliar(familiar);
						relacaoPacienteFamiliar.setPaciente(paciente);
						relacaoPacienteFamiliar.setTipoRelacao(rs.getString("tipoRelacao"));
						return relacaoPacienteFamiliar;
					}

				});

		return listRelacaopacientefamiliar;
	}

}
