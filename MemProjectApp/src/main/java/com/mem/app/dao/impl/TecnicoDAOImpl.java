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

import com.mem.app.dao.TecnicoDAO;
import com.mem.app.model.Tecnico;
import com.mem.app.model.Utilizador;

@Repository
public class TecnicoDAOImpl implements TecnicoDAO {
	private JdbcTemplate jdbcTemplate;
	private UtilizadorDAOImpl utilizadorImpl;
	private Connection connection;

	@Autowired
	public TecnicoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		utilizadorImpl = new UtilizadorDAOImpl(dataSource);

		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int saveOrUpdate(Tecnico tecnico) {
		System.out.println("Id do tecnico no saver or update: " + tecnico.getIdTecnico());
		if (tecnico.getIdTecnico() > 0) {
			// update
			String sql = "UPDATE tecnico SET nomeProprio=?, apelido=?, "
					+ "telefone=?, idUtilizador=? WHERE idTecnico=?";

			jdbcTemplate.update(sql, tecnico.getNomeProprio(), tecnico.getApelido(), tecnico.getTelefone(),
					tecnico.getUtilizador().getIdUtilizador(), tecnico.getIdTecnico());

			return tecnico.getIdTecnico();
		} else {
			// insert
			final String INSERT_SQL = "INSERT INTO tecnico (nomeProprio, apelido, telefone, idUtilizador)"
					+ " VALUES (?, ?, ?, ?)";
			int newId = 0;
			final String nomeProprio = tecnico.getNomeProprio();
			final String apelido = tecnico.getApelido();
			final int telefone = tecnico.getTelefone();
			final int idUtilizador = tecnico.getUtilizador().getIdUtilizador();
			System.out.println("variaveis definidas no insert");

			PreparedStatement ps;
			try {
				ps = connection.prepareStatement(INSERT_SQL.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, nomeProprio);
				ps.setString(2, apelido);
				ps.setInt(3, telefone);
				ps.setInt(4, idUtilizador);
				ps.executeUpdate();

				System.out.println("executou a query");
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					System.out.println("tem resultados");
					newId = rs.getInt(1);
					System.out.println("new id tecnico " + newId);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("novo id do tecnico: " + newId);
			return newId;

		}
	}

	@Override
	public void delete(int tecnicoId) {
		String sql = "DELETE FROM tecnico WHERE idTecnico=?";
		jdbcTemplate.update(sql, tecnicoId);
	}

	@Override
	public List<Tecnico> list() {

		String sql = "SELECT * FROM tecnico";
		List<Tecnico> listTecnicos = jdbcTemplate.query(sql, new RowMapper<Tecnico>() {

			@Override
			public Tecnico mapRow(ResultSet rs, int rowNum) throws SQLException {
				Tecnico aTecnico = new Tecnico();
				Utilizador utilizador = utilizadorImpl.get(rs.getInt("idUtilizador"));

				aTecnico.setIdTecnico(rs.getInt("idTecnico"));
				aTecnico.setNomeProprio(rs.getString("nomeProprio"));
				aTecnico.setApelido(rs.getString("apelido"));
				aTecnico.setTelefone(rs.getInt("telefone"));
				aTecnico.setUtilizador(utilizador);

				return aTecnico;
			}

		});

		return listTecnicos;
	}

	@Override
	public Tecnico get(int tecnicoId) {
		String sql = "SELECT * FROM tecnico WHERE idTecnico=" + tecnicoId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Tecnico>() {

			@Override
			public Tecnico extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Utilizador utilizador = utilizadorImpl.get(rs.getInt("idUtilizador"));

					Tecnico tecnico = new Tecnico();
					tecnico.setIdTecnico(rs.getInt("idTecnico"));
					tecnico.setNomeProprio(rs.getString("nomeProprio"));
					tecnico.setApelido(rs.getString("apelido"));
					tecnico.setTelefone(rs.getInt("telefone"));
					tecnico.setUtilizador(utilizador);

					return tecnico;
				}

				return null;
			}

		});
	}

	@Override
	public Tecnico getByUtilizador(int utilizadorId) {
		String sql = "SELECT * FROM tecnico WHERE idUtilizador = " + utilizadorId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Tecnico>() {

			@Override
			public Tecnico extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Utilizador utilizador = utilizadorImpl.get(rs.getInt("idUtilizador"));

					Tecnico tecnico = new Tecnico();
					tecnico.setIdTecnico(rs.getInt("idTecnico"));
					tecnico.setNomeProprio(rs.getString("nomeProprio"));
					tecnico.setApelido(rs.getString("apelido"));
					tecnico.setTelefone(rs.getInt("telefone"));
					tecnico.setUtilizador(utilizador);

					return tecnico;
				}

				return null;
			}

		});
	}
}
