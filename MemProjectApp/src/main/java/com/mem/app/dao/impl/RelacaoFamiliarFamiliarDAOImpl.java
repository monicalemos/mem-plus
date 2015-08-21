package com.mem.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mem.app.dao.RelacaofamiliarfamiliarDAO;
import com.mem.app.model.Familiar;
import com.mem.app.model.Relacaofamiliarfamiliar;
import com.mem.app.model.RelacaofamiliarfamiliarId;
import com.mem.app.model.Paciente;

public class RelacaofamiliarfamiliarDAOImpl implements RelacaofamiliarfamiliarDAO {

	private JdbcTemplate jdbcTemplate;
	private PacienteDAOImpl pacienteImpl;
	private FamiliarDAOImpl familiarImpl;


	public RelacaofamiliarfamiliarDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		pacienteImpl = new PacienteDAOImpl(dataSource);
		familiarImpl = new FamiliarDAOImpl(dataSource);
	}

	@Override
	public void saveOrUpdate(Relacaofamiliarfamiliar relacaoFamiliarFamiliar) {
		if (relacaoFamiliarFamiliar.getId().getIdRelacaoFamiliarFamiliar() > 0) {
			// update
			String sql = "UPDATE relacaoFamiliarFamiliar SET " 
					+ "idFamiliar=?, " 
					+ "idFamiliar1=?, " 
					+ "tipoRelacao=?, " 
					+ "idPaciente=? "
					+ "WHERE idRelacaofamiliarfamiliar=?";

			jdbcTemplate.update(sql, 
					relacaoFamiliarFamiliar.getFamiliarByIdFamiliar().getIdFamiliar(),
					relacaoFamiliarFamiliar.getFamiliarByIdFamiliar1().getIdFamiliar(),
					relacaoFamiliarFamiliar.getPaciente().getIdPaciente(), 
					relacaoFamiliarFamiliar.getTipoRelacao(),
					relacaoFamiliarFamiliar.getId().getIdRelacaoFamiliarFamiliar());
		} else {
			// insert
			String sql = "INSERT INTO relacaoFamiliarFamiliar " 
					+ "(idRelacaofamiliarfamiliar, "
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
		String sql = "DELETE FROM relacaoFamiliarFamiliar "
				+ "WHERE idFamiliar=? or idFamiliar=?";
		jdbcTemplate.update(sql, familiar.getIdFamiliar());

	}


	@Override
	public Relacaofamiliarfamiliar get(int idRelacaofamiliarfamiliar) {
		String sql = "SELECT * FROM relacaoFamiliarFamiliar " + "WHERE idRelacaofamiliarfamiliar="
				+ idRelacaofamiliarfamiliar;

		return jdbcTemplate.query(sql, new ResultSetExtractor<Relacaofamiliarfamiliar>() {

			@Override
			public Relacaofamiliarfamiliar extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
					Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));
					Familiar familiar1 = familiarImpl.get(rs.getInt("idFamiliar1"));

					RelacaofamiliarfamiliarId relId = new RelacaofamiliarfamiliarId(
							rs.getInt("idRelacaofamiliarfamiliar"), paciente.getIdPaciente(), familiar.getIdFamiliar());

					Relacaofamiliarfamiliar relacaoFamiliarFamiliar = new Relacaofamiliarfamiliar();
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
	public List<Relacaofamiliarfamiliar> listFromPatient(Paciente paciente) {
		String sql = "SELECT * FROM relacaoFamiliarFamiliar " 
					+ "WHERE idPaciente=" + paciente.getIdPaciente();

		List<Relacaofamiliarfamiliar> listRelacaofamiliarfamiliar = jdbcTemplate.query(sql,
				new RowMapper<Relacaofamiliarfamiliar>() {

					@Override
					public Relacaofamiliarfamiliar mapRow(ResultSet rs, int rowNum) throws SQLException {

						Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
						Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));
						Familiar familiar1 = familiarImpl.get(rs.getInt("idFamiliar1"));

						RelacaofamiliarfamiliarId relId = new RelacaofamiliarfamiliarId(
								rs.getInt("idRelacaofamiliarfamiliar"), paciente.getIdPaciente(), familiar.getIdFamiliar());

						Relacaofamiliarfamiliar relacaoFamiliarFamiliar = new Relacaofamiliarfamiliar();
						relacaoFamiliarFamiliar.setId(relId);
						relacaoFamiliarFamiliar.setFamiliarByIdFamiliar(familiar);
						relacaoFamiliarFamiliar.setFamiliarByIdFamiliar1(familiar1);
						relacaoFamiliarFamiliar.setPaciente(paciente);
						relacaoFamiliarFamiliar.setTipoRelacao(rs.getString("tipoRelacao"));
						return relacaoFamiliarFamiliar;
					}

				});

		return listRelacaofamiliarfamiliar;
	}
	

	@Override
	public List<Relacaofamiliarfamiliar> listFromFamily(Paciente paciente, Familiar familiar) {
		String sql = "SELECT * FROM relacaoFamiliarFamiliar " 
				+ "WHERE idPaciente=" + paciente.getIdPaciente()
				+ "AND (idFamiliar = " +  familiar.getIdFamiliar() 
				+ "OR idFamiliar1 = " + familiar.getIdFamiliar() + ")";

	List<Relacaofamiliarfamiliar> listRelacaofamiliarfamiliar = jdbcTemplate.query(sql,
			new RowMapper<Relacaofamiliarfamiliar>() {

				@Override
				public Relacaofamiliarfamiliar mapRow(ResultSet rs, int rowNum) throws SQLException {

					Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
					Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));
					Familiar familiar1 = familiarImpl.get(rs.getInt("idFamiliar1"));

					RelacaofamiliarfamiliarId relId = new RelacaofamiliarfamiliarId(
							rs.getInt("idRelacaofamiliarfamiliar"), paciente.getIdPaciente(), familiar.getIdFamiliar());

					Relacaofamiliarfamiliar relacaoFamiliarFamiliar = new Relacaofamiliarfamiliar();
					relacaoFamiliarFamiliar.setId(relId);
					relacaoFamiliarFamiliar.setFamiliarByIdFamiliar(familiar);
					relacaoFamiliarFamiliar.setFamiliarByIdFamiliar1(familiar1);
					relacaoFamiliarFamiliar.setPaciente(paciente);
					relacaoFamiliarFamiliar.setTipoRelacao(rs.getString("tipoRelacao"));
					return relacaoFamiliarFamiliar;
				}

			});

	return listRelacaofamiliarfamiliar;
	}
}
