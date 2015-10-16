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
	private Connection connection;

	@Autowired
	public RelacaoFamiliarFamiliarDAOImpl(DataSource dataSource) {
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
	public int saveOrUpdate(RelacaoFamiliarFamiliar relacaoFamiliarFamiliar) {
		if (relacaoFamiliarFamiliar.getId().getIdRelacaoFamiliarFamiliar() > 0) {
			// update
			System.out.println("UPDATE");
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
			
			return relacaoFamiliarFamiliar.getId().getIdRelacaoFamiliarFamiliar();
		} else {
			// insert
			System.out.println("INSERT");
			final String INSERT_SQL = "INSERT INTO RelacaoFamiliarFamiliar " 
					+ "(idFamiliar, " 
					+ "idFamiliar1, "
					+ "idPaciente," 
					+ "tipoRelacao) " 
					+ " VALUES (?, ?, ?, ?)";
			
			int newId = 0;
			final int idFamiliar = relacaoFamiliarFamiliar.getFamiliarByIdFamiliar().getIdFamiliar();
			final int idFamiliar1 = relacaoFamiliarFamiliar.getFamiliarByIdFamiliar1().getIdFamiliar();
			final int idPaciente = relacaoFamiliarFamiliar.getPaciente().getIdPaciente();
			final String tipoRelacao = relacaoFamiliarFamiliar.getTipoRelacao(); 
			
			System.out.println("Variáveis definidas no insert: " + idFamiliar + " - " + idFamiliar1 + " - " + idPaciente + " - " + tipoRelacao);
			
			PreparedStatement ps;
			try {
				ps = connection.prepareStatement(INSERT_SQL.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, idFamiliar);
				ps.setInt(2, idFamiliar1);
				ps.setInt(3, idPaciente);
				ps.setString(4, tipoRelacao);
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
				+ " AND (idFamiliar = " +  familiar.getIdFamiliar() 
				+ " OR idFamiliar1 = " + familiar.getIdFamiliar() + ")";

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
	public RelacaoFamiliarFamiliar getWithPatientAndFamily(Paciente paciente, Familiar familiar, Familiar familiar1) {
		String sql = "SELECT * FROM relacaoFamiliarFamiliar " 
				+ "WHERE idPaciente = " + paciente.getIdPaciente()
				+ " AND idFamiliar = " + familiar.getIdFamiliar()
				+ " aND idFamiliar1 = " + familiar1.getIdFamiliar();

		return jdbcTemplate.query(sql, new ResultSetExtractor<RelacaoFamiliarFamiliar>() {

			@Override
			public RelacaoFamiliarFamiliar extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
					Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));
					Familiar familiar1 = familiarImpl.get(rs.getInt("idFamiliar1"));

					RelacaoFamiliarFamiliarId relId = new RelacaoFamiliarFamiliarId(
							rs.getInt("idRelacaoFamiliarFamiliar"), familiar.getIdFamiliar(), 
							familiar1.getIdFamiliar());

					RelacaoFamiliarFamiliar relacaoPacienteFamiliar = new RelacaoFamiliarFamiliar();
					relacaoPacienteFamiliar.setId(relId);
					relacaoPacienteFamiliar.setFamiliarByIdFamiliar(familiar);
					relacaoPacienteFamiliar.setFamiliarByIdFamiliar1(familiar1);
					relacaoPacienteFamiliar.setPaciente(paciente);
					relacaoPacienteFamiliar.setTipoRelacao(rs.getString("tipoRelacao"));
					return relacaoPacienteFamiliar;
				}

				return null;
			}

		});
	}

	@Override
	public RelacaoFamiliarFamiliar getWithPatientAndFamily2grau(Paciente paciente, Familiar familiar1) {
		String sql = "SELECT * FROM relacaoFamiliarFamiliar " 
				+ "WHERE idPaciente = " + paciente.getIdPaciente()
				+ " AND idFamiliar1 = " + familiar1.getIdFamiliar();

		return jdbcTemplate.query(sql, new ResultSetExtractor<RelacaoFamiliarFamiliar>() {

			@Override
			public RelacaoFamiliarFamiliar extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));
					Familiar familiar = familiarImpl.get(rs.getInt("idFamiliar"));
					Familiar familiar1 = familiarImpl.get(rs.getInt("idFamiliar1"));

					RelacaoFamiliarFamiliarId relId = new RelacaoFamiliarFamiliarId(
							rs.getInt("idRelacaoFamiliarFamiliar"), familiar.getIdFamiliar(), 
							familiar1.getIdFamiliar());

					RelacaoFamiliarFamiliar relacaoPacienteFamiliar = new RelacaoFamiliarFamiliar();
					relacaoPacienteFamiliar.setId(relId);
					relacaoPacienteFamiliar.setFamiliarByIdFamiliar(familiar);
					relacaoPacienteFamiliar.setFamiliarByIdFamiliar1(familiar1);
					relacaoPacienteFamiliar.setPaciente(paciente);
					relacaoPacienteFamiliar.setTipoRelacao(rs.getString("tipoRelacao"));
					return relacaoPacienteFamiliar;
				}

				return null;
			}

		});
	}

}
