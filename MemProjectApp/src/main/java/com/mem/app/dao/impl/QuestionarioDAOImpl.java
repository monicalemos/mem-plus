package com.mem.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mem.app.dao.QuestionarioDAO;
import com.mem.app.model.Categoria;
import com.mem.app.model.Interacao;
import com.mem.app.model.Questionario;

public class QuestionarioDAOImpl implements QuestionarioDAO {

	private JdbcTemplate jdbcTemplate;
	private InteracaoDAOImpl interacaoImpl;
	private CategoriaDAOImpl categoriaImpl;

	public QuestionarioDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		interacaoImpl = new InteracaoDAOImpl(dataSource);
		categoriaImpl = new CategoriaDAOImpl(dataSource);
	}
	
	@Override
	public void saveOrUpdate(Questionario questionario) {
		if (questionario.getIdQuestionario() > 0) {
			// update
			String sql = "UPDATE questionario SET "
					+ "numPerguntas=?, "
					+ "nivel=?, "
					+ "idInteracao=?, "
					+ "idCategoria=? "
					+ "WHERE idQuestionario=?";

			jdbcTemplate.update(sql, 
					questionario.getNumPerguntas(), 
					questionario.getNivel(), 
					questionario.getInteracao().getIdInteracao(),
					questionario.getCategoria().getIdCategoria(),
					questionario.getIdQuestionario() );
		} else {
			// insert
			String sql = "INSERT INTO questionario "
					+ "(idQuestionario, "
					+ "numPerguntas, "
					+ "nivel, "
					+ "idInteracao, "
					+ "idCategoria) "
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, 
					questionario.getIdQuestionario(),
					questionario.getNumPerguntas(), 
					questionario.getInteracao().getIdInteracao(),
					questionario.getCategoria().getIdCategoria());
		}
	}

	@Override
	public void delete(int questionarioId) {
		String sql = "DELETE FROM questionario WHERE idQuestionario=?";
		jdbcTemplate.update(sql, questionarioId);
	}

	@Override
	public Questionario get(int questionarioId) {
		String sql = "SELECT * FROM questionario WHERE idQuestionario=" + questionarioId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Questionario>() {

			@Override
			public Questionario extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Interacao interacao = interacaoImpl.get(rs.getInt("idInteracao"));
					Categoria categoria = categoriaImpl.get(rs.getInt("idCategoria"));
					
					Questionario questionario = new Questionario();
					questionario.setIdQuestionario(rs.getInt("idQuestionario"));
					questionario.setNumPerguntas(rs.getInt("numPerguntas"));
					questionario.setNivel(rs.getString("nivel"));
					questionario.setInteracao(interacao);
					questionario.setCategoria(categoria);
					return questionario;
				}

				return null;
			}

		});
	}

	@Override
	public List<Questionario> list() {
		String sql = "SELECT * FROM questionario";
		List<Questionario> listQuestionario = jdbcTemplate.query(sql, new RowMapper<Questionario>() {

			@Override
			public Questionario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Interacao interacao = interacaoImpl.get(rs.getInt("idInteracao"));
				Categoria categoria = categoriaImpl.get(rs.getInt("idCategoria"));
				
				Questionario aQuestionario = new Questionario();

				aQuestionario.setIdQuestionario(rs.getInt("idQuestionario"));
				aQuestionario.setNumPerguntas(rs.getInt("numPerguntas"));
				aQuestionario.setNivel(rs.getString("nivel"));
				aQuestionario.setInteracao(interacao);
				aQuestionario.setCategoria(categoria);
				return aQuestionario;
			}

		});

		return listQuestionario;
	}
	
	@Override
	public List<Questionario> listByCategoria(int idCategoria) {
		String sql = "SELECT * FROM questionario WHERE questionario.idCategoria=" + idCategoria;
		List<Questionario> listQuestionario = jdbcTemplate.query(sql, new RowMapper<Questionario>() {

			@Override
			public Questionario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Interacao interacao = interacaoImpl.get(rs.getInt("idInteracao"));
				Categoria categoria = categoriaImpl.get(rs.getInt("idCategoria"));
				
				Questionario aQuestionario = new Questionario();

				aQuestionario.setIdQuestionario(rs.getInt("idQuestionario"));
				aQuestionario.setNumPerguntas(rs.getInt("numPerguntas"));
				aQuestionario.setNivel(rs.getString("nivel"));
				aQuestionario.setInteracao(interacao);
				aQuestionario.setCategoria(categoria);
				return aQuestionario;
			}

		});

		return listQuestionario;
	}
	
	@Override
	public List<Questionario> listByInteracao(int idInteracao) {
		String sql = "SELECT * FROM questionario WHERE questionario.idInteracao=" + idInteracao;
		List<Questionario> listQuestionario = jdbcTemplate.query(sql, new RowMapper<Questionario>() {

			@Override
			public Questionario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Interacao interacao = interacaoImpl.get(rs.getInt("idInteracao"));
				Categoria categoria = categoriaImpl.get(rs.getInt("idCategoria"));
				
				Questionario aQuestionario = new Questionario();

				aQuestionario.setIdQuestionario(rs.getInt("idQuestionario"));
				aQuestionario.setNumPerguntas(rs.getInt("numPerguntas"));
				aQuestionario.setNivel(rs.getString("nivel"));
				aQuestionario.setInteracao(interacao);
				aQuestionario.setCategoria(categoria);
				return aQuestionario;
			}

		});

		return listQuestionario;
	}

}
