package com.mem.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	private Connection connection;
	
	@Autowired
	public RelacaoPacienteFamiliarDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		pacienteImpl = new PacienteDAOImpl(dataSource);
		familiarImpl = new FamiliarDAOImpl(dataSource);
		
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int saveOrUpdate(RelacaoPacienteFamiliar relacaoPacienteFamiliar) {
		if (relacaoPacienteFamiliar.getId().getIdRelacao() > 0) {
			// update
			System.out.println("UPDATE");
			String sql = "UPDATE relacaoPacienteFamiliar SET " 
					+ "idFamiliar=?, " 
					+ "idPaciente=?, "
					+ "tipoRelacao=? " 
					+ "WHERE idRelacaoPacienteFamiliar=?";

			jdbcTemplate.update(sql, 
					relacaoPacienteFamiliar.getFamiliar().getIdFamiliar(),
					relacaoPacienteFamiliar.getPaciente().getIdPaciente(), 
					relacaoPacienteFamiliar.getTipoRelacao(),
					relacaoPacienteFamiliar.getId().getIdRelacao());
			
			return relacaoPacienteFamiliar.getId().getIdRelacao();
		} else {
			// insert
			System.out.println("INSERT");
			final String INSERT_SQL = "INSERT INTO relacaoPacienteFamiliar " 
					+ "(idFamiliar, " 
					+ "idPaciente," 
					+ "tipoRelacao) " 
					+ " VALUES (?, ?, ?)";
			
			int newId = 0;
			final int idFamiliar = relacaoPacienteFamiliar.getFamiliar().getIdFamiliar();
			final int idPaciente = relacaoPacienteFamiliar.getPaciente().getIdPaciente();
			final String tipoRelacao = relacaoPacienteFamiliar.getTipoRelacao(); 
			
			System.out.println("Variáveis definidas no insert: " + idFamiliar + " - " + idPaciente + " - " + tipoRelacao);
			
			PreparedStatement ps;
			try {
				ps = connection.prepareStatement(INSERT_SQL.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, idFamiliar);
				ps.setInt(2, idPaciente);
				ps.setString(3, tipoRelacao);
				ps.executeUpdate();

				System.out.println("executou a query");
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					System.out.println("tem resultados");
					newId = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("novo id da relacao: " + newId);
			return newId;	
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
				+ " AND idFamiliar=" + familiar.getIdFamiliar();

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
