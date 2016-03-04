package com.mem.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mem.app.dao.RespostaDAO;
import com.mem.app.model.Pergunta;
import com.mem.app.model.Resposta;
import com.mysql.jdbc.Statement;
@Repository
public class RespostaDAOImpl implements RespostaDAO {
	private JdbcTemplate jdbcTemplate;
	private Connection connection;

	private PerguntaDAOImpl perguntaImpl;
	@Autowired
	public RespostaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		perguntaImpl = new PerguntaDAOImpl(dataSource);
		
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int saveOrUpdate(Resposta resposta) {
		if (resposta.getIdResposta() > 0) {
			// update
			String sql = "UPDATE resposta SET "
					+ "data = ?, "
					+ "tipo = ?, "
					+ "resposta = ?, "
					+ "acertou = ?, "
					+ "idPergunta = ? "
					+ "WHERE idResposta=?";

			jdbcTemplate.update(sql, 
					resposta.getData(), 
					resposta.getTipo(),
					resposta.getResposta(),
					resposta.isAcertou(),
					resposta.getPergunta().getIdPergunta(),
					resposta.getIdResposta() );
			
			return resposta.getIdResposta();
		} else {
			// insert
			final String INSERT_SQL = "INSERT INTO resposta ("
					+ "data, "
					+ "tipo, "
					+ "respota, "
					+ "acertou, "
					+ "idPergunta)"
					+ " VALUES (?, ?, ?, ?, ?)";
			int newId = 0;
			final Date data = (Date) resposta.getData();
			final Integer tipo = resposta.getTipo();
			final String resp = resposta.getResposta();
			final boolean acertou = resposta.isAcertou();
			final Integer idPergunta = resposta.getPergunta().getIdPergunta();
			
			PreparedStatement ps;
			try{
				ps = connection.prepareStatement(INSERT_SQL.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setDate(1, new java.sql.Date(data.getTime()));
				ps.setInt(2, tipo);
				ps.setString(3, resp);
				ps.setBoolean(4, acertou);
				ps.setInt(5, idPergunta);
				
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

			System.out.println("novo id da resposta: " + newId);
			return newId;
		}
	}

	@Override
	public void delete(int respostaId) {
		String sql = "DELETE FROM resposta WHERE idResposta=?";
		jdbcTemplate.update(sql, respostaId);

	}

	@Override
	public Resposta get(int respostaId) {
		String sql = "SELECT * FROM resposta WHERE idResposta=" + respostaId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Resposta>() {

			@Override
			public Resposta extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Resposta resposta = new Resposta();
					
					Pergunta pergunta = perguntaImpl.get(rs.getInt("idPergunta"));
					
					resposta.setIdResposta(rs.getInt("idResposta"));
					resposta.setData(rs.getDate("data"));
					resposta.setTipo(rs.getInt("tipo"));
					resposta.setResposta(rs.getString("resposta"));
					resposta.setAcertou(rs.getBoolean("acertou"));
					resposta.setPergunta(pergunta);
					return resposta;
				}

				return null;
			}

		});
	}

	@Override
	public List<Resposta> list() {
		String sql = "SELECT * FROM resposta";
		List<Resposta> listResposta = jdbcTemplate.query(sql, new RowMapper<Resposta>() {

			@Override
			public Resposta mapRow(ResultSet rs, int rowNum) throws SQLException {
				Resposta resposta = new Resposta();
				
				Pergunta pergunta = perguntaImpl.get(rs.getInt("idPergunta"));
				
				resposta.setIdResposta(rs.getInt("idResposta"));
				resposta.setData(rs.getDate("data"));
				resposta.setTipo(rs.getInt("tipo"));
				resposta.setResposta(rs.getString("resposta"));
				resposta.setAcertou(rs.getBoolean("acertou"));
				resposta.setPergunta(pergunta);
				return resposta;
			}

		});

		return listResposta;
	}
	@Override
	public List<Resposta> listFromQuestion(int idPergunta) {
		String sql = "SELECT * FROM resposta WHERE idPergunta = " + idPergunta;
		List<Resposta> listResposta = jdbcTemplate.query(sql, new RowMapper<Resposta>() {

			@Override
			public Resposta mapRow(ResultSet rs, int rowNum) throws SQLException {
				Resposta resposta = new Resposta();
				
				Pergunta pergunta = perguntaImpl.get(idPergunta);
				
				resposta.setIdResposta(rs.getInt("idResposta"));
				resposta.setData(rs.getDate("data"));
				resposta.setTipo(rs.getInt("tipo"));
				resposta.setResposta(rs.getString("resposta"));
				resposta.setAcertou(rs.getBoolean("acertou"));
				resposta.setPergunta(pergunta);
				return resposta;
			}

		});

		return listResposta;
	}
}
