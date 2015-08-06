package com.mem.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mem.app.dao.JogoDAO;
import com.mem.app.model.Categoria;
import com.mem.app.model.Interacao;
import com.mem.app.model.Jogo;

public class JogoDAOImpl implements JogoDAO {

	private JdbcTemplate jdbcTemplate;
	private InteracaoDAOImpl interacaoImpl;
	private CategoriaDAOImpl categoriaImpl;

	public JogoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		interacaoImpl = new InteracaoDAOImpl(dataSource);
		categoriaImpl = new CategoriaDAOImpl(dataSource);
	}

	@Override
	public void saveOrUpdate(Jogo jogo) {
		if (jogo.getIdJogo() > 0) {
			// update
			String sql = "UPDATE jogo SET "
					+ "nome=?, "
					+ "nivel=?, "
					+ "Interacao_idInteracao=?, "
					+ "Categoria_idCategoria=? "
					+ "WHERE idJogo=?";

			jdbcTemplate.update(sql, 
					jogo.getNome(), 
					jogo.getNivel(), 
					jogo.getInteracao().getIdInteracao(),
					jogo.getCategoria().getIdCategoria(),
					jogo.getIdJogo() );
		} else {
			// insert
			String sql = "INSERT INTO jogo "
					+ "(idJogo, "
					+ "nome, "
					+ "nivel, "
					+ "Interacao_idInteracao, "
					+ "Categoria_idCategoria) "
					+ " VALUES (?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, 
					jogo.getIdJogo(),
					jogo.getNome(), 
					jogo.getNivel(), 
					jogo.getInteracao().getIdInteracao(),
					jogo.getCategoria().getIdCategoria());
		}
	}

	@Override
	public void delete(int jogoId) {
		String sql = "DELETE FROM jogo WHERE idJogo=?";
		jdbcTemplate.update(sql, jogoId);	}

	@Override
	public Jogo get(int jogoId) {
		String sql = "SELECT * FROM jogo WHERE idJogo=" + jogoId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Jogo>() {

			@Override
			public Jogo extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Interacao interacao = interacaoImpl.get(rs.getInt("Interacao_idInteracao"));
					Categoria categoria = categoriaImpl.get(rs.getInt("Categoria_idCategoria"));
					
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
				Interacao interacao = interacaoImpl.get(rs.getInt("Interacao_idInteracao"));
				Categoria categoria = categoriaImpl.get(rs.getInt("Categoria_idCategoria"));
				
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
