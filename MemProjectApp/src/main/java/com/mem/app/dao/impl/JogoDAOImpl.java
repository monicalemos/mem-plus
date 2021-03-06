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

import com.mem.app.dao.JogoDAO;
import com.mem.app.model.Categoria;
import com.mem.app.model.Interacao;
import com.mem.app.model.Jogo;
import com.mysql.jdbc.Statement;

@Repository
public class JogoDAOImpl implements JogoDAO {

	private JdbcTemplate jdbcTemplate;
	private InteracaoDAOImpl interacaoImpl;
	private CategoriaDAOImpl categoriaImpl;

	private Connection connection;

	@Autowired
	public JogoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		interacaoImpl = new InteracaoDAOImpl(dataSource);
		categoriaImpl = new CategoriaDAOImpl(dataSource);

		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int saveOrUpdate(Jogo jogo) {
		if (jogo.getIdJogo() > 0) {
			// update
			String sql = "UPDATE jogo SET " + "nome=?, " + "nivel=?, " + "idInteracao=?, " + "idCategoria=? "
					+ "WHERE idJogo=?";

			jdbcTemplate.update(sql, jogo.getNome(), jogo.getNivel(), jogo.getInteracao().getIdInteracao(),
					jogo.getCategoria().getIdCategoria(), jogo.getIdJogo());

			return jogo.getIdJogo();
		} else {
			// insert
			final String INSERT_SQL = "INSERT INTO jogo " + "(nome, " + "nivel, " + "idInteracao, " + "idCategoria) "
					+ " VALUES (?, ?, ?, ?)";
			
			int newId = 0;
			final String nome = jogo.getNome();
			final String nivel = jogo.getNivel();
			final int idInteracao = jogo.getInteracao().getIdInteracao();
			final int idCategoria = jogo.getCategoria().getIdCategoria();
			
			System.out.println("Variaveis definidas no insert do jogo " + nome + ", " + nivel + ", " + idInteracao + ", " + idCategoria );
			
			PreparedStatement ps;
			try{
				ps = connection.prepareStatement(INSERT_SQL.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,  nome);
				ps.setString(2,  nivel);
				ps.setInt(3,  idInteracao);
				ps.setInt(4, idCategoria);
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

			System.out.println("novo id do jogo: " + newId);
			return newId;

		}
	}

	@Override
	public void delete(int jogoId) {
		String sql = "DELETE FROM jogo WHERE idJogo=?";
		jdbcTemplate.update(sql, jogoId);
	}

	@Override
	public Jogo get(int jogoId) {
		String sql = "SELECT * FROM jogo WHERE idJogo=" + jogoId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Jogo>() {

			@Override
			public Jogo extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Interacao interacao = interacaoImpl.get(rs.getInt("idInteracao"));
					Categoria categoria = categoriaImpl.get(rs.getInt("idCategoria"));

					Jogo jogo = new Jogo();
					jogo.setIdJogo(rs.getInt("idJogo"));
					jogo.setNome(rs.getString("nome"));
					jogo.setNivel(rs.getString("nivel"));
					jogo.setInteracao(interacao);
					jogo.setCategoria(categoria);
					return jogo;
				}

				return null;
			}

		});
	}

	@Override
	public List<Jogo> list() {
		String sql = "SELECT * FROM jogo";
		List<Jogo> listJogo = jdbcTemplate.query(sql, new RowMapper<Jogo>() {

			@Override
			public Jogo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Interacao interacao = interacaoImpl.get(rs.getInt("idInteracao"));
				Categoria categoria = categoriaImpl.get(rs.getInt("idCategoria"));

				Jogo jogo = new Jogo();
				jogo.setIdJogo(rs.getInt("idJogo"));
				jogo.setNome(rs.getString("nome"));
				jogo.setNivel(rs.getString("nivel"));
				jogo.setInteracao(interacao);
				jogo.setCategoria(categoria);
				return jogo;
			}

		});

		return listJogo;
	}

	@Override
	public List<Jogo> listByCategoria(int idCategoria) {
		String sql = "SELECT * FROM jogo WHERE jogo.idCategoria=" + idCategoria;
		List<Jogo> listJogo = jdbcTemplate.query(sql, new RowMapper<Jogo>() {

			@Override
			public Jogo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Interacao interacao = interacaoImpl.get(rs.getInt("idInteracao"));
				Categoria categoria = categoriaImpl.get(rs.getInt("idCategoria"));

				Jogo jogo = new Jogo();
				jogo.setIdJogo(rs.getInt("idJogo"));
				jogo.setNome(rs.getString("nome"));
				jogo.setNivel(rs.getString("nivel"));
				jogo.setInteracao(interacao);
				jogo.setCategoria(categoria);
				return jogo;
			}

		});

		return listJogo;
	}

	@Override
	public List<Jogo> listByInteracao(int idInteracao) {
		String sql = "SELECT * FROM jogo WHERE jogo.idInteracao=" + idInteracao;
		List<Jogo> listJogo = jdbcTemplate.query(sql, new RowMapper<Jogo>() {

			@Override
			public Jogo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Interacao interacao = interacaoImpl.get(rs.getInt("idInteracao"));
				Categoria categoria = categoriaImpl.get(rs.getInt("idCategoria"));

				Jogo jogo = new Jogo();
				jogo.setIdJogo(rs.getInt("idJogo"));
				jogo.setNome(rs.getString("nome"));
				jogo.setNivel(rs.getString("nivel"));
				jogo.setInteracao(interacao);
				jogo.setCategoria(categoria);
				return jogo;
			}

		});

		return listJogo;
	}
}
