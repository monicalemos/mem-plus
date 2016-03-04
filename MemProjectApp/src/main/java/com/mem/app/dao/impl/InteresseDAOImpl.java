package com.mem.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

import com.mem.app.dao.InteresseDAO;
import com.mem.app.model.Interesse;
import com.mysql.jdbc.Statement;
@Repository
public class InteresseDAOImpl implements InteresseDAO {

	private JdbcTemplate jdbcTemplate;
	private Connection connection;

	@Autowired
	public InteresseDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int saveOrUpdate(Interesse interesse) {
		if (interesse.getIdInteresse() > 0) {
			// update
			String sql = "UPDATE interesse SET nome=? WHERE idInteresse=?";

			jdbcTemplate.update(sql, interesse.getNome(), interesse.getIdInteresse() );
			return interesse.getIdInteresse();
		} else {
			// insert
			final String INSERT_SQL = "INSERT INTO interesse (nome)"
					+ " VALUES (?, ?)";
			
			int newId = 0;
			final String nome = interesse.getNome();
			
			System.out.println("Variaveis definidas no insert do interesse " + nome);
			
			PreparedStatement ps;
			try{
				ps = connection.prepareStatement(INSERT_SQL.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,  nome);
				
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

			System.out.println("novo id do interesse: " + newId);
			return newId;
		}
	}

	@Override
	public void delete(int interesseId) {
		String sql = "DELETE FROM interesse WHERE idInteresse=?";
		jdbcTemplate.update(sql, interesseId);
	}

	@Override
	public Interesse get(int interesseId) {
		String sql = "SELECT * FROM interesse WHERE idInteresse=" + interesseId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Interesse>() {

			@Override
			public Interesse extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Interesse interesse = new Interesse();
					interesse.setIdInteresse(rs.getInt("idInteresse"));
					interesse.setNome(rs.getString("nome"));
					return interesse;
				}

				return null;
			}

		});
	}

	@Override
	public List<Interesse> list() {
		String sql = "SELECT * FROM interesse";
		List<Interesse> listInteresse = jdbcTemplate.query(sql, new RowMapper<Interesse>() {

			@Override
			public Interesse mapRow(ResultSet rs, int rowNum) throws SQLException {
				Interesse aInteresse = new Interesse();

				aInteresse.setIdInteresse(rs.getInt("idInteresse"));
				aInteresse.setNome(rs.getString("nome"));
				return aInteresse;
			}

		});

		return listInteresse;
	}

}
