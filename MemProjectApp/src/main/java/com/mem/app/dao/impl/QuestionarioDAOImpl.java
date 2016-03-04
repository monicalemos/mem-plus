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

import com.mem.app.dao.QuestionarioDAO;
import com.mem.app.model.Categoria;
import com.mem.app.model.Interacao;
import com.mem.app.model.Questionario;
import com.mysql.jdbc.Statement;
@Repository
public class QuestionarioDAOImpl implements QuestionarioDAO {

	private JdbcTemplate jdbcTemplate;
	private InteracaoDAOImpl interacaoImpl;
	private CategoriaDAOImpl categoriaImpl;
	
	private Connection connection;
	
	@Autowired
	public QuestionarioDAOImpl(DataSource dataSource) {
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
	public int saveOrUpdate(Questionario questionario) {
		if (questionario.getIdQuestionario() > 0) {
			// update
			String sql = "UPDATE questionario SET "
					+ "numPerguntas=?, "
					+ "respostasCertas=?, "
					+ "nivel=?, "
					+ "idInteracao=?, "
					+ "idCategoria=? "
					+ "WHERE idQuestionario=?";

			jdbcTemplate.update(sql, 
					questionario.getNumPerguntas(), 
					questionario.getRespostasCertas(),
					questionario.getNivel(), 
					questionario.getInteracao().getIdInteracao(),
					questionario.getCategoria().getIdCategoria(),
					questionario.getIdQuestionario() );
			
			return questionario.getIdQuestionario();
		} else {
			// insert
			final String INSERT_SQL = "INSERT INTO questionario "
					+ "(numPerguntas, "
					+ "respostasCertas, "
					+ "nivel, "
					+ "idInteracao, "
					+ "idCategoria) "
					+ " VALUES (?, ?, ?, ?, ?)";
			
			int newId = 0;
			final Integer numPerguntas = questionario.getNumPerguntas();
			final Integer respostasCertas = questionario.getRespostasCertas();
			final Integer nivel = questionario.getNivel();
			final Integer idInteracao = questionario.getInteracao().getIdInteracao();
			final Integer idCategoria = questionario.getCategoria().getIdCategoria();
			
			PreparedStatement ps;
			try{
				ps = connection.prepareStatement(INSERT_SQL.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, numPerguntas);
				ps.setInt(3, respostasCertas);
				ps.setInt(3, nivel);
				ps.setInt(4, idInteracao);
				ps.setInt(5, idCategoria);
				
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

			System.out.println("novo id do questionario: " + newId);
			return newId;
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
					questionario.setRespostasCertas(rs.getInt("respostasCertas"));
					questionario.setNivel(rs.getInt("nivel"));
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
				aQuestionario.setRespostasCertas(rs.getInt("respostasCertas"));
				aQuestionario.setNivel(rs.getInt("nivel"));
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
				aQuestionario.setRespostasCertas(rs.getInt("respostasCertas"));
				aQuestionario.setNivel(rs.getInt("nivel"));
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
				aQuestionario.setRespostasCertas(rs.getInt("respostasCertas"));
				aQuestionario.setNivel(rs.getInt("nivel"));
				aQuestionario.setInteracao(interacao);
				aQuestionario.setCategoria(categoria);
				return aQuestionario;
			}

		});

		return listQuestionario;
	}

}
