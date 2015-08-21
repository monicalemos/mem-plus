package com.mem.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mem.app.dao.FamiliarDAO;
import com.mem.app.model.Familiar;
import com.mem.app.model.Morada;
import com.mem.app.model.Utilizador;


public class FamiliarDAOImpl implements FamiliarDAO {

	private JdbcTemplate jdbcTemplate;
	private MoradaDAOImpl moradaImpl;
	private UtilizadorDAOImpl utilizadorImpl;

	public FamiliarDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		moradaImpl = new MoradaDAOImpl(dataSource);
		utilizadorImpl = new UtilizadorDAOImpl(dataSource);
	}

	@Override
	public void saveOrUpdate(Familiar familiar) {
		if (familiar.getIdFamiliar() > 0) {
			// update
			String sql = "UPDATE familiar "
					+ "SET nomeCompleto=?, "
					+ "nomeProprio=?, "
					+ "apelido=?,"
					+ "dataNascimento=?, "
					+ "idLocalNascimento=?, "
					+ "idMorada=?, "
					+ "genero=?, "
					+ "estadoCivil=?, "
					+ "profissao=?, "
					+ "telefone=?, "
					+ "eCuidador=?, "
					+ "dataObito=?, "
					+ "idUtilizador=? "
					+ "WHERE idFamiliar=?";

			jdbcTemplate.update(sql, 		
					familiar.getNomeCompleto(),
					familiar.getNomeProprio(),
					familiar.getApelido(),
					familiar.getDataNascimento(),
					familiar.getMoradaByIdLocalNascimento().getIdMorada(),
					familiar.getMoradaByIdMorada().getIdMorada(),
					familiar.getGenero(),
					familiar.getEstadoCivil(),
					familiar.getProfissao(),
					familiar.getTelefone(),
					familiar.getEcuidador(),
					familiar.getDataObito(),
					familiar.getUtilizador().getIdUtilizador(),
					familiar.getIdFamiliar());
		} else {
			// insert
			String sql = "INSERT INTO familiar "
					+ "( idFamiliar, "
					+ "nomeCompleto, "
					+ "nomeProprio, "
					+ "apelido,"
					+ "dataNascimento, "
					+ "idLocalNascimento, "
					+ "idMorada, "
					+ "genero, "
					+ "estadoCivil, "
					+ "profissao, "
					+ "telefone, "
					+ "eCuidador, "
					+ "dataObito,"
					+ "idUtilizador)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			jdbcTemplate.update(sql, 
					familiar.getIdFamiliar(), 
					familiar.getNomeCompleto(),
					familiar.getNomeProprio(),
					familiar.getApelido(),
					familiar.getDataNascimento(),
					familiar.getMoradaByIdLocalNascimento().getIdMorada(),
					familiar.getMoradaByIdMorada().getIdMorada(),
					familiar.getGenero(),
					familiar.getEstadoCivil(),
					familiar.getProfissao(),
					familiar.getTelefone(),
					familiar.getEcuidador(),
					familiar.getDataObito(),
					familiar.getUtilizador().getIdUtilizador());
		}
	}

	@Override
	public void delete(int familiarId) {
		String sql = "DELETE FROM familiar WHERE idFamiliar=?";
		jdbcTemplate.update(sql, familiarId);
	}

	@Override
	public Familiar get(int familiarId) {
		String sql = "SELECT * FROM familiar WHERE idFamiliar=" + familiarId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Familiar>() {

			@Override
			public Familiar extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Morada morada = moradaImpl.get(rs.getInt("idMorada"));
					Morada local_nascimento = moradaImpl.get(rs.getInt("idLocalNascimento"));
					Utilizador utilizador = utilizadorImpl.get(rs.getInt("idUtilizador"));
					
					Familiar familiar = new Familiar();
					familiar.setIdFamiliar(rs.getInt("idFamiliar"));
					familiar.setNomeCompleto(rs.getString("nomeCompleto"));
					familiar.setNomeProprio(rs.getString("nomeProprio"));
					familiar.setApelido(rs.getString("apelido"));
					familiar.setDataNascimento(rs.getDate("dataNascimento"));
					familiar.setMoradaByIdLocalNascimento(local_nascimento);
					familiar.setMoradaByIdMorada(morada);
					familiar.setGenero(rs.getString("genero"));
					familiar.setEstadoCivil(rs.getString("estadoCivil"));
					familiar.setProfissao(rs.getString("profissao"));
					familiar.setTelefone(rs.getInt("telefone"));
					familiar.setEcuidador(rs.getBoolean("eCuidador"));
					familiar.setDataObito(rs.getDate("dataObito"));
					familiar.setUtilizador(utilizador);
					return familiar;
				}

				return null;
			}

		});
	}

	@Override
	public Familiar getFromUserName(String user) {

		String sql = "SELECT f.* FROM familiar f, Utilizador u WHERE f.idUtilizador = u.idUtilizador and nomeUtilizador="+ user;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Familiar>() {

			@Override
			public Familiar extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					
					Morada morada = moradaImpl.get(rs.getInt("idMorada"));
					Morada local_nascimento = moradaImpl.get(rs.getInt("idLocalNascimento"));
					Utilizador utilizador = utilizadorImpl.get(rs.getInt("idUtilizador"));
					
					Familiar familiar = new Familiar();
					familiar.setIdFamiliar(rs.getInt("idFamiliar"));
					familiar.setNomeCompleto(rs.getString("nomeCompleto"));
					familiar.setNomeProprio(rs.getString("nomeProprio"));
					familiar.setApelido(rs.getString("apelido"));
					familiar.setDataNascimento(rs.getDate("dataNascimento"));
					familiar.setMoradaByIdLocalNascimento(local_nascimento);
					familiar.setMoradaByIdMorada(morada);
					familiar.setGenero(rs.getString("genero"));
					familiar.setEstadoCivil(rs.getString("estadoCivil"));
					familiar.setProfissao(rs.getString("profissao"));
					familiar.setTelefone(rs.getInt("telefone"));
					familiar.setEcuidador(rs.getBoolean("eCuidador"));
					familiar.setDataObito(rs.getDate("dataObito"));
					familiar.setUtilizador(utilizador);
					return familiar;
				}

				return null;
			}

		});

	}

	@Override
	public List<Familiar> list(int idPaciente) {
		String sql = "SELECT * FROM familiar, relacao_paciente_familiar "
				+ "WHERE relacao_paciente_familiar.idFamiliar = familiar.idFamiliar "
				+ "and relacao_paciente_familiar.idPaciente = " + idPaciente;
		
		List<Familiar> listFamiliar = jdbcTemplate.query(sql, new RowMapper<Familiar>() {

			@Override
			public Familiar mapRow(ResultSet rs, int rowNum) throws SQLException {
				Morada morada = moradaImpl.get(rs.getInt("idMorada"));
				Morada local_nascimento = moradaImpl.get(rs.getInt("idLocalNascimento"));
				Utilizador utilizador = utilizadorImpl.get(rs.getInt("idUtilizador"));
				
				Familiar familiar = new Familiar();
				familiar.setIdFamiliar(rs.getInt("idFamiliar"));
				familiar.setNomeCompleto(rs.getString("nomeCompleto"));
				familiar.setNomeProprio(rs.getString("nomeProprio"));
				familiar.setApelido(rs.getString("apelido"));
				familiar.setDataNascimento(rs.getDate("dataNascimento"));
				familiar.setMoradaByIdLocalNascimento(local_nascimento);
				familiar.setMoradaByIdMorada(morada);
				familiar.setGenero(rs.getString("genero"));
				familiar.setEstadoCivil(rs.getString("estadoCivil"));
				familiar.setProfissao(rs.getString("profissao"));
				familiar.setTelefone(rs.getInt("telefone"));
				familiar.setEcuidador(rs.getBoolean("eCuidador"));
				familiar.setDataObito(rs.getDate("dataObito"));
				familiar.setUtilizador(utilizador);
				return familiar;
			}

		});

		return listFamiliar;
	}
}
