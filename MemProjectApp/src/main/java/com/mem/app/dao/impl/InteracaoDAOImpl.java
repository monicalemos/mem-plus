package com.mem.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mem.app.dao.InteracaoDAO;
import com.mem.app.model.Interacao;
import com.mem.app.model.Paciente;
import com.mysql.jdbc.Statement;
@Repository
public class InteracaoDAOImpl implements InteracaoDAO {

	private JdbcTemplate jdbcTemplate;
	private PacienteDAOImpl pacienteImpl;
	
	private Connection connection;

	@Autowired
	public InteracaoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		pacienteImpl = new PacienteDAOImpl(dataSource);
		
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int saveOrUpdate(Interacao interacao) {
		if (interacao.getIdInteracao() > 0) {
			// update
			final String UPDATE_SQL = "UPDATE interacao SET "
					+ "data=?, "
					+ "idPaciente=? "
					+ "WHERE idInteracao=?";

			jdbcTemplate.update(UPDATE_SQL, 
					interacao.getData(),
					interacao.getPaciente().getIdPaciente(), 
					interacao.getIdInteracao() );
			
			return interacao.getIdInteracao();
		} else {
			// insert
			final String INSERT_SQL = "INSERT INTO interacao "
					+ "(data, "
					+ "idPaciente)"
					+ " VALUES (?, ?)";
			
			int newId = 0;
			final Date data = interacao.getData();
			final int idPaciente = interacao.getPaciente().getIdPaciente();
			
			System.out.println("Variaveis definidas no insert interacao " + data + ", " + idPaciente);
			
			PreparedStatement ps;
			try{
				ps = connection.prepareStatement(INSERT_SQL.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setDate(1,  new java.sql.Date(data.getTime()));
				ps.setInt(2,  idPaciente);
				
				ps.executeUpdate();
				
				System.out.println("executou a query");
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()){
					System.out.println("tem resultados");
					newId = rs.getInt(1);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("novo id da interacao: " + newId);
			return newId;
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
					Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));

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
	public List<Interacao> list(int idPaciente) {

		String sql = "SELECT * FROM interacao WHERE interacao.idPaciente " + idPaciente;
		List<Interacao> listInteracao = jdbcTemplate.query(sql, new RowMapper<Interacao>() {

			@Override
			public Interacao mapRow(ResultSet rs, int rowNum) throws SQLException {
				Paciente paciente = pacienteImpl.get(rs.getInt("idPaciente"));

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
