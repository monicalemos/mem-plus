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

import com.mem.app.dao.RelacaoFamiliarFamiliarDAO;
import com.mem.app.model.Familiar;
import com.mem.app.model.Paciente;
import com.mem.app.model.RelacaoFamiliarFamiliar;
import com.mem.app.model.RelacaoFamiliarFamiliarId;
@Repository	
public class RelacaoFamiliarFamiliarDAOImpl implements RelacaoFamiliarFamiliarDAO {

	private JdbcTemplate jdbcTemplate;
	private PacienteDAOImpl pacienteImpl;
	private FamiliarDAOImpl familiarImpl;

	@Autowired
	public RelacaoFamiliarFamiliarDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		pacienteImpl = new PacienteDAOImpl(dataSource);
		familiarImpl = new FamiliarDAOImpl(dataSource);
	}

	@Override
	public void saveOrUpdate(RelacaoFamiliarFamiliar relacaoFamiliarFamiliar) {
		if (relacaoFamiliarFamiliar.getId().getIdRelacaoFamiliarFamiliar() > 0) {
			// update
			String sql = "UPDATE RelacaoFamiliarFamiliar SET " 
					+ "idFamiliar=?, " 
					+ "idFamiliar1=?, " 
					+ "tipoRelacao=?, " 
					+ "idPaciente=? "
					+ "WHERE idRelacaoFamiliarFamiliar=?";

			jdbcTemplate.update(sql, 
					relacaoFamiliarFamiliar.getFamiliarByIdFamiliar().getIdFamiliar(),
					relacaoFamiliarFamiliar.getFamiliarByIdFamiliar1().getIdFamiliar(),
					relacaoFamiliarFamiliar.getPaciente().getIdPaciente(), 
					relacaoFamiliarFamiliar.getTipoRelacao(),
					relacaoFamiliarFamiliar.getId().getIdRelacaoFamiliarFamiliar());
		} else {
			// insert
			String sql = "INSERT INTO RelacaoFamiliarFamiliar " 
					+ "(idRelacaoFamiliarFamiliar, "
					+ "idFamiliar, " 
					+ "idFamiliar1, "
					+ "idPaciente," 
					+ "tipoRelacao) " 
					+ " VALUES (?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, 
					relacaoFamiliarFamiliar.getId().getIdRelacaoFamiliarFamiliar(),
					relacaoFamiliarFamiliar.getFamiliarByIdFamiliar().getIdFamiliar(),
					relacaoFamiliarFamiliar.getFamiliarByIdFamiliar1().getIdFamiliar(),
					relacaoFamiliarFamiliar.getPaciente().getIdPaciente(), 
					relacaoFamiliarFamiliar.getTipoRelacao());
		}

	}



	@Override
	public void delete(Familiar familiar) {
		String sql = "DELETE FROM RelacaoFamiliarFamiliar "
				+ "WHERE idFamiliar=? or idFamiliar=?";
		jdbcTemplate.update(sql, familiar.getIdFamiliar());

	}


	@Override
	public RelacaoFamiliarFamiliar get(int idRelacaoFamiliarFamiliar) {
		String sql = "SELECT * FROM RelacaoFamiliarFamiliar " + "WHERE idRelacaoFamiliarFamiliar="
				+ idRelacaoFamiliarFamiliar;

		return jdbcTemplate.query(sql, new ResultSetExtractor<RelacaoFamiliarFamiliar>() {

			@Override
			public RelacaoFamiliarFamiliar extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
					Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));
					Familiar familiar1 = familiarImpl.get(rs.getInt("idFamiliar1"));

					RelacaoFamiliarFamiliarId relId = new RelacaoFamiliarFamiliarId(
							rs.getInt("idRelacaoFamiliarFamiliar"), paciente.getIdPaciente(), familiar.getIdFamiliar());

					RelacaoFamiliarFamiliar relacaoFamiliarFamiliar = new RelacaoFamiliarFamiliar();
					relacaoFamiliarFamiliar.setId(relId);
					relacaoFamiliarFamiliar.setFamiliarByIdFamiliar(familiar);
					relacaoFamiliarFamiliar.setFamiliarByIdFamiliar1(familiar1);
					relacaoFamiliarFamiliar.setPaciente(paciente);
					relacaoFamiliarFamiliar.setTipoRelacao(rs.getString("tipoRelacao"));
					return relacaoFamiliarFamiliar;
				}

				return null;
			}

		});
	}


	@Override
	public List<RelacaoFamiliarFamiliar> listFromPatient(Paciente paciente) {
		String sql = "SELECT * FROM RelacaoFamiliarFamiliar " 
					+ "WHERE idPaciente=" + paciente.getIdPaciente();

		List<RelacaoFamiliarFamiliar> listRelacaoFamiliarFamiliar = jdbcTemplate.query(sql,
				new RowMapper<RelacaoFamiliarFamiliar>() {

					@Override
					public RelacaoFamiliarFamiliar mapRow(ResultSet rs, int rowNum) throws SQLException {

						Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
						Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));
						Familiar familiar1 = familiarImpl.get(rs.getInt("idFamiliar1"));

						RelacaoFamiliarFamiliarId relId = new RelacaoFamiliarFamiliarId(
								rs.getInt("idRelacaoFamiliarFamiliar"), paciente.getIdPaciente(), familiar.getIdFamiliar());

						RelacaoFamiliarFamiliar relacaoFamiliarFamiliar = new RelacaoFamiliarFamiliar();
						relacaoFamiliarFamiliar.setId(relId);
						relacaoFamiliarFamiliar.setFamiliarByIdFamiliar(familiar);
						relacaoFamiliarFamiliar.setFamiliarByIdFamiliar1(familiar1);
						relacaoFamiliarFamiliar.setPaciente(paciente);
						relacaoFamiliarFamiliar.setTipoRelacao(rs.getString("tipoRelacao"));
						return relacaoFamiliarFamiliar;
					}

				});

		return listRelacaoFamiliarFamiliar;
	}
	

	@Override
	public List<RelacaoFamiliarFamiliar> listFromFamily(Paciente paciente, Familiar familiar) {
		String sql = "SELECT * FROM RelacaoFamiliarFamiliar " 
				+ "WHERE idPaciente=" + paciente.getIdPaciente()
				+ "AND (idFamiliar = " +  familiar.getIdFamiliar() 
				+ "OR idFamiliar1 = " + familiar.getIdFamiliar() + ")";

	List<RelacaoFamiliarFamiliar> listRelacaoFamiliarFamiliar = jdbcTemplate.query(sql,
			new RowMapper<RelacaoFamiliarFamiliar>() {

				@Override
				public RelacaoFamiliarFamiliar mapRow(ResultSet rs, int rowNum) throws SQLException {

					Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
					Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));
					Familiar familiar1 = familiarImpl.get(rs.getInt("idFamiliar1"));

					RelacaoFamiliarFamiliarId relId = new RelacaoFamiliarFamiliarId(
							rs.getInt("idRelacaoFamiliarFamiliar"), paciente.getIdPaciente(), familiar.getIdFamiliar());

					RelacaoFamiliarFamiliar relacaoFamiliarFamiliar = new RelacaoFamiliarFamiliar();
					relacaoFamiliarFamiliar.setId(relId);
					relacaoFamiliarFamiliar.setFamiliarByIdFamiliar(familiar);
					relacaoFamiliarFamiliar.setFamiliarByIdFamiliar1(familiar1);
					relacaoFamiliarFamiliar.setPaciente(paciente);
					relacaoFamiliarFamiliar.setTipoRelacao(rs.getString("tipoRelacao"));
					return relacaoFamiliarFamiliar;
				}

			});

	return listRelacaoFamiliarFamiliar;
	}

}
