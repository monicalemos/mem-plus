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

import com.mem.app.dao.UtilizadorDAO;
import com.mem.app.model.Utilizador;
import com.mysql.jdbc.Statement;

@Repository
public class UtilizadorDAOImpl implements UtilizadorDAO {
	private JdbcTemplate jdbcTemplate;
	private Connection connection;

	@Autowired
	public UtilizadorDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int saveOrUpdate(final Utilizador utilizador) {
		System.out.println("id do utilizador no save or update " + utilizador.getIdUtilizador());
		if (utilizador.getIdUtilizador() > 0) {
			// update
			String sql = "UPDATE utilizador SET nameUtilizador=?, password=?, tipoUtilizador=?, email=? WHERE idUtilizador=?";

			jdbcTemplate.update(sql, utilizador.getNomeUtilizador(), utilizador.getPassword(),
					utilizador.getTipoUtilizador(), utilizador.getEmail(), utilizador.getIdUtilizador());
			return utilizador.getIdUtilizador();
		} else {
			// insert
			final String INSERT_SQL = "INSERT INTO utilizador (nomeUtilizador, password, tipoUtilizador, email)"
					+ " VALUES (?, ?, ?, ?)";
			int newId = 0;
			final String nomeUtilizador = utilizador.getNomeUtilizador();
			final String password = utilizador.getPassword();
			final String tipoUtilizador = utilizador.getTipoUtilizador();
			final String email = utilizador.getEmail();
			System.out.println("variaveis definidas no insert " + nomeUtilizador + ", " + password + ", " + tipoUtilizador + ", " + email);

			PreparedStatement ps;
			try {
				ps = connection.prepareStatement(INSERT_SQL.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, nomeUtilizador);
				ps.setString(2, password);
				ps.setString(3, tipoUtilizador);
				ps.setString(4, email);
				ps.executeUpdate();
				
				System.out.println("executou a query");
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					System.out.println("tem resultados");
					newId = rs.getInt(1);
					System.out.println("new id utilizador: " + newId);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("novo id: utilizador" + newId);
			return newId;
		}
	}

	@Override
	public void delete(int utilizadorId) {
		String sql = "DELETE FROM utilizador WHERE idUtilizador=?";
		jdbcTemplate.update(sql, utilizadorId);

	}

	@Override
	public Utilizador get(int utilizadorId) {
		String sql = "SELECT * FROM utilizador WHERE idUtilizador=" + utilizadorId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Utilizador>() {

			@Override
			public Utilizador extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Utilizador utilizador = new Utilizador();
					utilizador.setIdUtilizador(rs.getInt("idUtilizador"));
					utilizador.setNomeUtilizador(rs.getString("nomeUtilizador"));
					utilizador.setPassword(rs.getString("password"));
					utilizador.setTipoUtilizador(rs.getString("tipoUtilizador"));
					utilizador.setEmail(rs.getString("email"));
					return utilizador;
				}

				return null;
			}

		});
	}

	// idUtilizador, String nome, Set<?> pacientes

	@Override
	public List<Utilizador> list() {
		String sql = "SELECT * FROM Utilizador";
		List<Utilizador> listUtilizador = jdbcTemplate.query(sql, new RowMapper<Utilizador>() {

			@Override
			public Utilizador mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilizador aUtilizador = new Utilizador();

				aUtilizador.setIdUtilizador(rs.getInt("idUtilizador"));
				aUtilizador.setNomeUtilizador(rs.getString("nomeUtilizador"));
				aUtilizador.setPassword(rs.getString("password"));
				aUtilizador.setTipoUtilizador(rs.getString("tipoUtilizador"));
				aUtilizador.setEmail(rs.getString("email"));
				return aUtilizador;
			}

		});

		return listUtilizador;
	}

	@Override
	public Utilizador getFromEmail(String email) {
		String sql = "SELECT * FROM Utilizador WHERE email = '" + email  + "';";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Utilizador>() {

			@Override
			public Utilizador extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Utilizador utilizador = new Utilizador();
					utilizador.setIdUtilizador(rs.getInt("idUtilizador"));
					utilizador.setNomeUtilizador(rs.getString("nomeUtilizador"));
					utilizador.setPassword(rs.getString("password"));
					utilizador.setTipoUtilizador(rs.getString("tipoUtilizador"));
					utilizador.setEmail(rs.getString("email"));

					return utilizador;
				}

				return null;
			}

		});
	}

	@Override
	public Utilizador getFromUserName(String user) {
		String sql = "SELECT * FROM Utilizador u WHERE nomeUtilizador = '" + user + "';";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Utilizador>() {

			@Override
			public Utilizador extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Utilizador utilizador = new Utilizador();
					utilizador.setIdUtilizador(rs.getInt("idUtilizador"));
					utilizador.setNomeUtilizador(rs.getString("nomeUtilizador"));
					utilizador.setPassword(rs.getString("password"));
					utilizador.setTipoUtilizador(rs.getString("tipoUtilizador"));
					utilizador.setEmail(rs.getString("email"));

					return utilizador;
				}

				return null;
			}

		});
	}

	@Override
	public Utilizador matchUser(String username, String password) {
		String sql = "SELECT * FROM Utilizador u WHERE nomeUtilizador = '" + username + "' and password = '" + password + "';";
		System.out.println("SQL de match: " + sql);
		return jdbcTemplate.query(sql, new ResultSetExtractor<Utilizador>() {

			@Override
			public Utilizador extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Utilizador utilizador = new Utilizador();
					utilizador.setIdUtilizador(rs.getInt("idUtilizador"));
					utilizador.setNomeUtilizador(rs.getString("nomeUtilizador"));
					utilizador.setPassword(rs.getString("password"));
					utilizador.setTipoUtilizador(rs.getString("tipoUtilizador"));
					utilizador.setEmail(rs.getString("email"));

					return utilizador;
				}

				return null;
			}

		});
	}

}
