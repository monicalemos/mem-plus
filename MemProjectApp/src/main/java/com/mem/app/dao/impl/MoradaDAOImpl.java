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

import com.mem.app.dao.MoradaDAO;
import com.mem.app.model.Morada;

@Repository
public class MoradaDAOImpl implements MoradaDAO {

	private JdbcTemplate jdbcTemplate;
	private Connection connection;

	@Autowired
	public MoradaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int saveOrUpdate(Morada morada) {
		if (morada.getIdMorada() > 0 && this.getMoradaCompleta(morada.getPais(), morada.getRegiao(), morada.getCidade()) != null) {
			// update
			String sql = "UPDATE morada SET pais=?, regiao=?, cidade=? WHERE idMorada=?";

			jdbcTemplate.update(sql, morada.getPais(), morada.getRegiao(), morada.getCidade(), morada.getIdMorada());
			return morada.getIdMorada();
		} else {
			// insert
			if (this.getMoradaCompleta(morada.getPais(), morada.getRegiao(), morada.getCidade()) != null) {
				final String INSERT_SQL = "INSERT INTO morada (pais, regiao, cidade)" + " VALUES (?, ?, ?)";
				int newId = 0;
				final String pais = morada.getPais();
				final String regiao = morada.getRegiao();
				final String cidade = morada.getCidade();

				System.out.println("variaveis definidas no insert");

				PreparedStatement ps;
				try {
					ps = connection.prepareStatement(INSERT_SQL.toString(), Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, pais);
					ps.setString(2, regiao);
					ps.setString(3, cidade);
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

				System.out.println("novo id da morada: " + newId);
				return newId;
			}
			return morada.getIdMorada();
		}
	}

	@Override
	public void delete(int moradaId) {
		String sql = "DELETE FROM morada WHERE idMorada=?";
		jdbcTemplate.update(sql, moradaId);
	}

	@Override
	public Morada get(int moradaId) {
		String sql = "SELECT * FROM morada WHERE idMorada=" + moradaId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Morada>() {

			@Override
			public Morada extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Morada morada = new Morada();
					morada.setIdMorada(rs.getInt("idMorada"));
					morada.setPais(rs.getString("pais"));
					morada.setRegiao(rs.getString("regiao"));
					morada.setCidade(rs.getString("cidade"));
					return morada;
				}

				return null;
			}

		});
	}

	@Override
	public Morada getMoradaCompleta(String pais, String regiao, String cidade) {
		String sql = "SELECT * FROM morada " + " WHERE upper(pais) = '" + pais.toUpperCase() + "' and upper(regiao) = '"
				+ regiao.toUpperCase() + "' and upper(cidade) = '" + cidade.toUpperCase() + "';";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Morada>() {

			@Override
			public Morada extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Morada morada = new Morada();
					morada.setIdMorada(rs.getInt("idMorada"));
					morada.setPais(rs.getString("pais"));
					morada.setRegiao(rs.getString("regiao"));
					morada.setCidade(rs.getString("cidade"));
					return morada;
				}

				return null;
			}

		});
	}

	@Override
	public List<Morada> list() {
		String sql = "SELECT * FROM morada";
		List<Morada> listMorada = jdbcTemplate.query(sql, new RowMapper<Morada>() {

			@Override
			public Morada mapRow(ResultSet rs, int rowNum) throws SQLException {
				Morada aMorada = new Morada();
				aMorada.setIdMorada(rs.getInt("idMorada"));
				aMorada.setPais(rs.getString("pais"));
				aMorada.setRegiao(rs.getString("regiao"));
				aMorada.setCidade(rs.getString("cidade"));
				return aMorada;
			}

		});

		return listMorada;
	}

	@Override
	public List<Integer> listIds() {
		String sql = "SELECT idMorada FROM morada";
		List<Integer> listIdMoradas = jdbcTemplate.query(sql, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int id = rs.getInt("idMorada");
				return id;
			}
		});

		return listIdMoradas;
	}

	@Override
	public Integer getLastId() {
		String sql = "SELECT idMorada FROM morada order by idMorada desc limit 1";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					int id = rs.getInt("idMorada");
					return id;
				}

				return null;
			}

		});
	}
}
