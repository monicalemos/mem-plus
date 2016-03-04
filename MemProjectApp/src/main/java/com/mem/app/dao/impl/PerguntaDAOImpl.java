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

import com.mem.app.dao.PerguntaDAO;
import com.mem.app.model.Pergunta;
import com.mem.app.model.Questionario;
import com.mysql.jdbc.Statement;
@Repository
public class PerguntaDAOImpl implements PerguntaDAO {


	private JdbcTemplate jdbcTemplate;
	private Connection connection;

	private QuestionarioDAOImpl questionarioImpl;
	@Autowired
	public PerguntaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		questionarioImpl = new QuestionarioDAOImpl(dataSource);
		
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int saveOrUpdate(Pergunta pergunta) {
		if (pergunta.getIdPergunta() > 0) {
			// update
			String sql = "UPDATE pergunta SET "
					+ "pergunta = ?, "
					+ "idQuestionario = ? "
					+ "WHERE idPergunta=?";

			jdbcTemplate.update(sql, 
					pergunta.getPergunta(),
					pergunta.getQuestionario().getIdQuestionario(),
					pergunta.getIdPergunta() );
			
			return pergunta.getIdPergunta();
		} else {
			// insert
			final String INSERT_SQL = "INSERT INTO pergunta ("
					+ "respota, "
					+ "idQuestionario)"
					+ " VALUES (?, ?)";
			int newId = 0;
			final String resp = pergunta.getPergunta();
			final Integer idQuestionario = pergunta.getQuestionario().getIdQuestionario();
			
			PreparedStatement ps;
			try{
				ps = connection.prepareStatement(INSERT_SQL.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(3, resp);
				ps.setInt(5, idQuestionario);
				
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

			System.out.println("novo id da pergunta: " + newId);
			return newId;
		}
	}

	@Override
	public void delete(int perguntaId) {
		String sql = "DELETE FROM pergunta WHERE idPergunta=?";
		jdbcTemplate.update(sql, perguntaId);

	}

	@Override
	public Pergunta get(int perguntaId) {
		String sql = "SELECT * FROM pergunta WHERE idPergunta=" + perguntaId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Pergunta>() {

			@Override
			public Pergunta extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Pergunta pergunta = new Pergunta();
					
					Questionario questionario = questionarioImpl.get(rs.getInt("idQuestionario"));
					
					pergunta.setIdPergunta(rs.getInt("idPergunta"));
					pergunta.setPergunta(rs.getString("pergunta"));
					pergunta.setQuestionario(questionario);
					return pergunta;
				}

				return null;
			}

		});
	}

	@Override
	public List<Pergunta> list() {
		String sql = "SELECT * FROM pergunta";
		List<Pergunta> listPergunta = jdbcTemplate.query(sql, new RowMapper<Pergunta>() {

			@Override
			public Pergunta mapRow(ResultSet rs, int rowNum) throws SQLException {
				Pergunta pergunta = new Pergunta();
				
				Questionario questionario = questionarioImpl.get(rs.getInt("idQuestionario"));
				
				pergunta.setIdPergunta(rs.getInt("idPergunta"));
				pergunta.setPergunta(rs.getString("pergunta"));
				pergunta.setQuestionario(questionario);
				return pergunta;
			}

		});

		return listPergunta;
	}
	@Override
	public List<Pergunta> listFromQuestionnaire(int idQuestionario) {
		String sql = "SELECT * FROM pergunta WHERE idQuestionario = " + idQuestionario;
		List<Pergunta> listPergunta = jdbcTemplate.query(sql, new RowMapper<Pergunta>() {

			@Override
			public Pergunta mapRow(ResultSet rs, int rowNum) throws SQLException {
				Pergunta pergunta = new Pergunta();
				
				Questionario questionario = questionarioImpl.get(rs.getInt("idQuestionario"));
				
				pergunta.setIdPergunta(rs.getInt("idPergunta"));
				pergunta.setPergunta(rs.getString("pergunta"));
				pergunta.setQuestionario(questionario);
				return pergunta;
			}

		});

		return listPergunta;
	}

}
