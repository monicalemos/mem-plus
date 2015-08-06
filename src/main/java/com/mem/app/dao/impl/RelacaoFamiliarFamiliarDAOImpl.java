package com.mem.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mem.app.dao.RelacaoFamiliarFamiliarDAO;
import com.mem.app.model.Familiar;
import com.mem.app.model.RelacaoFamiliarFamiliar;
import com.mem.app.model.RelacaoFamiliarFamiliarId;
import com.mem.app.model.Paciente;

public class RelacaoFamiliarFamiliarDAOImpl implements RelacaoFamiliarFamiliarDAO {

	private JdbcTemplate jdbcTemplate;
	private PacienteDAOImpl pacienteImpl;
	private FamiliarDAOImpl familiarImpl;


	public RelacaoFamiliarFamiliarDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		pacienteImpl = new PacienteDAOImpl(dataSource);
		familiarImpl = new FamiliarDAOImpl(dataSource);
	}

	@Override
	public void saveOrUpdate(RelacaoFamiliarFamiliar relacaoFamiliarFamiliar) {
		if (relacaoFamiliarFamiliar.getId().getIdRelacaoFamiliarFamiliar() > 0) {
			// update
			String sql = "UPDATE relacaoFamiliarFamiliar SET " 
					+ "Familiar_idFamiliar=?, " 
					+ "Familiar_idFamiliar1=?, " 
					+ "tipo_relacao=?, " 
					+ "Paciente_idPaciente=? "
					+ "WHERE idRelacao_Familiar_Familiar=?";

			jdbcTemplate.update(sql, 
					relacaoFamiliarFamiliar.getFamiliarByFamiliarIdFamiliar().getIdFamiliar(),
					relacaoFamiliarFamiliar.getFamiliarByFamiliarIdFamiliar1().getIdFamiliar(),
					relacaoFamiliarFamiliar.getPaciente().getIdPaciente(), 
					relacaoFamiliarFamiliar.getTipoRelacao(),
					relacaoFamiliarFamiliar.getId().getIdRelacaoFamiliarFamiliar());
		} else {
			// insert
			String sql = "INSERT INTO relacaoFamiliarFamiliar " 
					+ "(idRelacao_Familiar_Familiar, "
					+ "Familiar_idFamiliar, " 
					+ "Familiar_idFamiliar1, "
					+ "Paciente_idPaciente," 
					+ "tipo_relacao) " 
					+ " VALUES (?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, 
					relacaoFamiliarFamiliar.getId().getIdRelacaoFamiliarFamiliar(),
					relacaoFamiliarFamiliar.getFamiliarByFamiliarIdFamiliar().getIdFamiliar(),
					relacaoFamiliarFamiliar.getFamiliarByFamiliarIdFamiliar1().getIdFamiliar(),
					relacaoFamiliarFamiliar.getPaciente().getIdPaciente(), 
					relacaoFamiliarFamiliar.getTipoRelacao());
		}

	}



	@Override
	public void delete(Familiar familiar) {
		String sql = "DELETE FROM relacaoFamiliarFamiliar "
				+ "WHERE Familiar_idFamiliar=? or Familiar_idFamiliar=?";
		jdbcTemplate.update(sql, familiar.getIdFamiliar());

	}


	@Override
	public RelacaoFamiliarFamiliar get(int idRelacaoFamiliarFamiliar) {
		String sql = "SELECT * FROM relacaoFamiliarFamiliar " + "WHERE idRelacao_Familiar_Familiar="
				+ idRelacaoFamiliarFamiliar;

		return jdbcTemplate.query(sql, new ResultSetExtractor<RelacaoFamiliarFamiliar>() {

			@Override
			public RelacaoFamiliarFamiliar extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Paciente paciente = pacienteImpl.get(rs.getInt("Paciente_idPaciente"));
					Familiar familiar = familiarImpl.get(rs.getInt("Familiar_idFamiliar"));
					Familiar familiar1 = familiarImpl.get(rs.getInt("Familiar_idFamiliar1"));

					RelacaoFamiliarFamiliarId relId = new RelacaoFamiliarFamiliarId(
							rs.getInt("idRelacao_Familiar_Familiar"), paciente.getIdPaciente(), familiar.getIdFamiliar());

					RelacaoFamiliarFamiliar relacaoFamiliarFamiliar = new RelacaoFamiliarFamiliar();
					relacaoFamiliarFamiliar.setId(relId);
					relacaoFamiliarFamiliar.setFamiliarByFamiliarIdFamiliar(familiar);
					relacaoFamiliarFamiliar.setFamiliarByFamiliarIdFamiliar1(familiar1);
					relacaoFamiliarFamiliar.setPaciente(paciente);
					relacaoFamiliarFamiliar.setTipoRelacao(rs.getString("tipo_relacao"));
					return relacaoFamiliarFamiliar;
				}

				return null;
			}

		});
	}


	@Override
	public List<RelacaoFamiliarFamiliar> listFromPatient(Paciente paciente) {
		String sql = "SELECT * FROM relacaoFamiliarFamiliar " 
					+ "WHERE Paciente_idPaciente=" + paciente.getIdPaciente();

		List<RelacaoFamiliarFamiliar> listRelacaoFamiliarFamiliar = jdbcTemplate.query(sql,
				new RowMapper<RelacaoFamiliarFamiliar>() {

					@Override
					public RelacaoFamiliarFamiliar mapRow(ResultSet rs, int rowNum) throws SQLException {

						Paciente paciente = pacienteImpl.get(rs.getInt("Paciente_idPaciente"));
						Familiar familiar = familiarImpl.get(rs.getInt("Familiar_idFamiliar"));
						Familiar familiar1 = familiarImpl.get(rs.getInt("Familiar_idFamiliar1"));

						RelacaoFamiliarFamiliarId relId = new RelacaoFamiliarFamiliarId(
								rs.getInt("idRelacao_Familiar_Familiar"), paciente.getIdPaciente(), familiar.getIdFamiliar());

						RelacaoFamiliarFamiliar relacaoFamiliarFamiliar = new RelacaoFamiliarFamiliar();
						relacaoFamiliarFamiliar.setId(relId);
						relacaoFamiliarFamiliar.setFamiliarByFamiliarIdFamiliar(familiar);
						relacaoFamiliarFamiliar.setFamiliarByFamiliarIdFamiliar1(familiar1);
						relacaoFamiliarFamiliar.setPaciente(paciente);
						relacaoFamiliarFamiliar.setTipoRelacao(rs.getString("tipo_relacao"));
						return relacaoFamiliarFamiliar;
					}

				});

		return listRelacaoFamiliarFamiliar;
	}
	

	@Override
	public List<RelacaoFamiliarFamiliar> listFromFamily(Paciente paciente, Familiar familiar) {
		String sql = "SELECT * FROM relacaoFamiliarFamiliar " 
				+ "WHERE Paciente_idPaciente=" + paciente.getIdPaciente()
				+ "AND (Familiar_idFamiliar = " +  familiar.getIdFamiliar() 
				+ "OR Familiar_idFamiliar1 = " + familiar.getIdFamiliar() + ")";

	List<RelacaoFamiliarFamiliar> listRelacaoFamiliarFamiliar = jdbcTemplate.query(sql,
			new RowMapper<RelacaoFamiliarFamiliar>() {

				@Override
				public RelacaoFamiliarFamiliar mapRow(ResultSet rs, int rowNum) throws SQLException {

					Paciente paciente = pacienteImpl.get(rs.getInt("Paciente_idPaciente"));
					Familiar familiar = familiarImpl.get(rs.getInt("Familiar_idFamiliar"));
					Familiar familiar1 = familiarImpl.get(rs.getInt("Familiar_idFamiliar1"));

					RelacaoFamiliarFamiliarId relId = new RelacaoFamiliarFamiliarId(
							rs.getInt("idRelacao_Familiar_Familiar"), paciente.getIdPaciente(), familiar.getIdFamiliar());

					RelacaoFamiliarFamiliar relacaoFamiliarFamiliar = new RelacaoFamiliarFamiliar();
					relacaoFamiliarFamiliar.setId(relId);
					relacaoFamiliarFamiliar.setFamiliarByFamiliarIdFamiliar(familiar);
					relacaoFamiliarFamiliar.setFamiliarByFamiliarIdFamiliar1(familiar1);
					relacaoFamiliarFamiliar.setPaciente(paciente);
					relacaoFamiliarFamiliar.setTipoRelacao(rs.getString("tipo_relacao"));
					return relacaoFamiliarFamiliar;
				}

			});

	return listRelacaoFamiliarFamiliar;
	}
}
