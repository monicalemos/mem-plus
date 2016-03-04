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

import com.mem.app.dao.CategoriaDAO;
import com.mem.app.model.Categoria;
import com.mysql.jdbc.Statement;
@Repository
public class CategoriaDAOImpl implements CategoriaDAO {

	private JdbcTemplate jdbcTemplate;
	private Connection connection;

	@Autowired
	public CategoriaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int saveOrUpdate(Categoria categoria) {
		if (categoria.getIdCategoria() > 0) {
			// update
			String sql = "UPDATE categoria SET name=? WHERE idCategoria=?";

			jdbcTemplate.update(sql, categoria.getNome(), categoria.getIdCategoria() );
			
			return categoria.getIdCategoria();
		} else {
			// insert
			final String INSERT_SQL = "INSERT INTO categoria (nome)"
					+ " VALUES (?)";
			int newId = 0;
			final String nome = categoria.getNome();
			
			System.out.println("Variaveis definidas no insert da categoria " + nome);
			
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

			System.out.println("novo id da categoria: " + newId);
			return newId;
		}
	}

	@Override
	public void delete(int categoriaId) {
		String sql = "DELETE FROM categoria WHERE idCategoria=?";
		jdbcTemplate.update(sql, categoriaId);

	}

	@Override
	public Categoria get(int categoriaId) {
		String sql = "SELECT * FROM categoria WHERE idCategoria=" + categoriaId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Categoria>() {

			@Override
			public Categoria extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Categoria categoria = new Categoria();
					categoria.setIdCategoria(rs.getInt("idCategoria"));
					categoria.setNome(rs.getString("nome"));
					return categoria;
				}

				return null;
			}

		});
	}

	@Override
	public List<Categoria> list() {
		String sql = "SELECT * FROM categoria";
		List<Categoria> listCategoria = jdbcTemplate.query(sql, new RowMapper<Categoria>() {

			@Override
			public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
				Categoria aCategoria = new Categoria();

				aCategoria.setIdCategoria(rs.getInt("idCategoria"));
				aCategoria.setNome(rs.getString("nome"));
				return aCategoria;
			}

		});

		return listCategoria;
	}
}
