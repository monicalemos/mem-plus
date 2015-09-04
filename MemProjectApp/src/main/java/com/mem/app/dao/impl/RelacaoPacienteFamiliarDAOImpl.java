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

import com.mem.app.dao.RelacaoPacienteFamiliarDAO;
import com.mem.app.model.Familiar;
import com.mem.app.model.Paciente;
import com.mem.app.model.RelacaoPacienteFamiliar;
import com.mem.app.model.RelacaoPacienteFamiliarId;
@Repository
public class RelacaoPacienteFamiliarDAOImpl implements RelacaoPacienteFamiliarDAO {

	private JdbcTemplate jdbcTemplate;
	private PacienteDAOImpl pacienteImpl;
	private FamiliarDAOImpl familiarImpl;

	@Autowired
	public RelacaoPacienteFamiliarDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		pacienteImpl = new PacienteDAOImpl(dataSource);
		familiarImpl = new FamiliarDAOImpl(dataSource);
	}

	@Override
	public void saveOrUpdate(RelacaoPacienteFamiliar relacaoPacienteFamiliar) {
		if (relacaoPacienteFamiliar.getId().getIdRelacaoPacienteFamiliar() > 0) {
			// update
			String sql = "UPDATE relacaoPacienteFamiliar SET " 
					+ "idFamiliar=?, " 
					+ "idPaciente=?, "
					+ "tipoRelacao=? " 
					+ "WHERE idRelacaoPacienteFamiliar=?";

			jdbcTemplate.update(sql, 
					relacaoPacienteFamiliar.getFamiliar().getIdFamiliar(),
					relacaoPacienteFamiliar.getPaciente().getIdPaciente(), 
					relacaoPacienteFamiliar.getTipoRelacao(),
					relacaoPacienteFamiliar.getId().getIdRelacaoPacienteFamiliar());
		} else {
			// insert
			String sql = "INSERT INTO relacaoPacienteFamiliar " 
					+ "(idRelacaoPacienteFamiliar, "
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
	public RelacaoPacienteFamiliar get(int idRelacaoPacienteFamiliar) {
		String sql = "SELECT * FROM relacaoPacienteFamiliar " + "WHERE idRelacaoPacienteFamiliar="
				+ idRelacaoPacienteFamiliar;

		return jdbcTemplate.query(sql, new ResultSetExtractor<RelacaoPacienteFamiliar>() {

			@Override
			public RelacaoPacienteFamiliar extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
					Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));

					RelacaoPacienteFamiliarId relId = new RelacaoPacienteFamiliarId(
							rs.getInt("idRelacaoPacienteFamiliar"), paciente.getIdPaciente(), familiar.getIdFamiliar());

					RelacaoPacienteFamiliar relacaoPacienteFamiliar = new RelacaoPacienteFamiliar();
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
	public RelacaoPacienteFamiliar getWithPatientAndFamily(Paciente paciente, Familiar familiar) {
		String sql = "SELECT * FROM relacaoPacienteFamiliar " 
				+ "WHERE idPaciente=" + paciente.getIdPaciente()
				+ "AND idFamiliar=" + familiar.getIdFamiliar();

		return jdbcTemplate.query(sql, new ResultSetExtractor<RelacaoPacienteFamiliar>() {

			@Override
			public RelacaoPacienteFamiliar extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
					Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));

					RelacaoPacienteFamiliarId relId = new RelacaoPacienteFamiliarId(
							rs.getInt("idRelacaoPacienteFamiliar"), paciente.getIdPaciente(), familiar.getIdFamiliar());

					RelacaoPacienteFamiliar relacaoPacienteFamiliar = new RelacaoPacienteFamiliar();
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
	public List<RelacaoPacienteFamiliar> list(Paciente paciente) {
		String sql = "SELECT * FROM relacaoPacienteFamiliar " 
				+ "WHERE idPaciente=" + paciente.getIdPaciente();

		List<RelacaoPacienteFamiliar> listRelacaoPacienteFamiliar = jdbcTemplate.query(sql,
				new RowMapper<RelacaoPacienteFamiliar>() {

					@Override
					public RelacaoPacienteFamiliar mapRow(ResultSet rs, int rowNum) throws SQLException {

						Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
						Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));

						RelacaoPacienteFamiliarId relId = new RelacaoPacienteFamiliarId(
								rs.getInt("idRelacaoPacienteFamiliar"), paciente.getIdPaciente(),
								familiar.getIdFamiliar());

						RelacaoPacienteFamiliar relacaoPacienteFamiliar = new RelacaoPacienteFamiliar();
						relacaoPacienteFamiliar.setId(relId);
						relacaoPacienteFamiliar.setFamiliar(familiar);
						relacaoPacienteFamiliar.setPaciente(paciente);
						relacaoPacienteFamiliar.setTipoRelacao(rs.getString("tipoRelacao"));
						return relacaoPacienteFamiliar;
					}

				});

		return listRelacaoPacienteFamiliar;
	}

}
