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


public class FamiliarDAOImpl implements FamiliarDAO {

	private JdbcTemplate jdbcTemplate;
	private MoradaDAOImpl moradaImpl;

	public FamiliarDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		moradaImpl = new MoradaDAOImpl(dataSource);
	}

	@Override
	public void saveOrUpdate(Familiar familiar) {
		if (familiar.getIdFamiliar() > 0) {
			// update
			String sql = "UPDATE familiar "
					+ "SET nome_completo=?, "
					+ "nome_proprio=?, "
					+ "apelido=?,"
					+ "data_de_nascimento=?, "
					+ "Local_Nascimento_idMorada=?, "
					+ "Morada_idMorada=?, "
					+ "genero=?, "
					+ "estado_civil=?, "
					+ "profissao=?, "
					+ "telefone=?, "
					+ "e_cuidador=?, "
					+ "nome_utilizador=?, "
					+ "password=?, "
					+ "data_de_obtio=?"
					+ "WHERE idFamiliar=?";

			jdbcTemplate.update(sql, 
					familiar.getNomeCompleto(),
					familiar.getNomeProprio(),
					familiar.getApelido(),
					familiar.getDataDeNascimento(),
					familiar.getMoradaByLocalNascimentoIdMorada().getIdMorada(),
					familiar.getMoradaByMoradaIdMorada().getIdMorada(),
					familiar.getGenero(),
					familiar.getEstadoCivil(),
					familiar.getProfissao(),
					familiar.getTelefone(),
					familiar.getECuidador(),
					familiar.getNomeUtilizador(),
					familiar.getPassword(),
					familiar.getDataDeObito(),
					familiar.getIdFamiliar() );
		} else {
			// insert
			String sql = "INSERT INTO familiar "
					+ "( idFamiliar, "
					+ "nome_completo, "
					+ "nome_proprio, "
					+ "apelido,"
					+ "data_de_nascimento, "
					+ "Local_Nascimento_idMorada, "
					+ "Morada_idMorada, "
					+ "genero, "
					+ "estado_civil, "
					+ "profissao, "
					+ "telefone, "
					+ "e_cuidador, "
					+ "nome_utilizador, "
					+ "password, "
					+ "data_de_obtio)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			jdbcTemplate.update(sql, 
					familiar.getIdFamiliar(), 
					familiar.getNomeCompleto(),
					familiar.getNomeProprio(),
					familiar.getApelido(),
					familiar.getDataDeNascimento(),
					familiar.getMoradaByLocalNascimentoIdMorada().getIdMorada(),
					familiar.getMoradaByMoradaIdMorada().getIdMorada(),
					familiar.getGenero(),
					familiar.getEstadoCivil(),
					familiar.getProfissao(),
					familiar.getTelefone(),
					familiar.getECuidador(),
					familiar.getNomeUtilizador(),
					familiar.getPassword(),
					familiar.getDataDeObito());
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
					Morada morada = moradaImpl.get(rs.getInt("Morada_idMorada"));
					Morada local_nascimento = moradaImpl.get(rs.getInt("LocalNascimento_idMorada"));
					
					Familiar familiar = new Familiar();
					familiar.setIdFamiliar(rs.getInt("idFamiliar"));
					familiar.setNomeCompleto(rs.getString("nome_completo"));
					familiar.setNomeProprio(rs.getString("nome_proprio"));
					familiar.setApelido(rs.getString("apelido"));
					familiar.setDataDeNascimento(rs.getDate("data_de_nascimento"));
					familiar.setMoradaByLocalNascimentoIdMorada(local_nascimento);
					familiar.setMoradaByMoradaIdMorada(morada);
					familiar.setGenero(rs.getString("genero"));
					familiar.setEstadoCivil(rs.getString("estado_civil"));
					familiar.setProfissao(rs.getString("profissao"));
					familiar.setTelefone(rs.getInt("telefone"));
					familiar.setECuidador(rs.getBoolean("e_cuidador"));
					familiar.setNomeUtilizador(rs.getString("nome_uilizador"));
					familiar.setPassword(rs.getString("password"));
					familiar.setDataDeObito(rs.getDate("data_de_obito"));
					return familiar;
				}

				return null;
			}

		});
	}

	@Override
	public Familiar getFromUserName(String user) {

		String sql = "SELECT * FROM familiar WHERE user_name=" + user;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Familiar>() {

			@Override
			public Familiar extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					
					Morada morada = moradaImpl.get(rs.getInt("Morada_idMorada"));
					Morada local_nascimento = moradaImpl.get(rs.getInt("LocalNascimento_idMorada"));
					
					Familiar familiar = new Familiar();
					familiar.setIdFamiliar(rs.getInt("idFamiliar"));
					familiar.setNomeCompleto(rs.getString("nome_completo"));
					familiar.setNomeProprio(rs.getString("nome_proprio"));
					familiar.setApelido(rs.getString("apelido"));
					familiar.setDataDeNascimento(rs.getDate("data_de_nascimento"));
					familiar.setMoradaByLocalNascimentoIdMorada(local_nascimento);
					familiar.setMoradaByMoradaIdMorada(morada);
					familiar.setGenero(rs.getString("genero"));
					familiar.setEstadoCivil(rs.getString("estado_civil"));
					familiar.setProfissao(rs.getString("profissao"));
					familiar.setTelefone(rs.getInt("telefone"));
					familiar.setECuidador(rs.getBoolean("e_cuidador"));
					familiar.setNomeUtilizador(rs.getString("nome_uilizador"));
					familiar.setPassword(rs.getString("password"));
					familiar.setDataDeObito(rs.getDate("data_de_obito"));
					return familiar;
				}

				return null;
			}

		});

	}

	@Override
	public List<Familiar> list(int idPaciente) {
		String sql = "SELECT * FROM familiar, relacao_paciente_familiar "
				+ "WHERE relacao_paciente_familiar.Familiar_idFamiliar = familiar.idFamiliar "
				+ "and relacao_paciente_familiar.Paciente_idPaciente = " + idPaciente;
		
		List<Familiar> listFamiliar = jdbcTemplate.query(sql, new RowMapper<Familiar>() {

			@Override
			public Familiar mapRow(ResultSet rs, int rowNum) throws SQLException {
				Morada morada = moradaImpl.get(rs.getInt("Morada_idMorada"));
				Morada local_nascimento = moradaImpl.get(rs.getInt("LocalNascimento_idMorada"));
				
				Familiar familiar = new Familiar();
				familiar.setIdFamiliar(rs.getInt("idFamiliar"));
				familiar.setNomeCompleto(rs.getString("nome_completo"));
				familiar.setNomeProprio(rs.getString("nome_proprio"));
				familiar.setApelido(rs.getString("apelido"));
				familiar.setDataDeNascimento(rs.getDate("data_de_nascimento"));
				familiar.setMoradaByLocalNascimentoIdMorada(local_nascimento);
				familiar.setMoradaByMoradaIdMorada(morada);
				familiar.setGenero(rs.getString("genero"));
				familiar.setEstadoCivil(rs.getString("estado_civil"));
				familiar.setProfissao(rs.getString("profissao"));
				familiar.setTelefone(rs.getInt("telefone"));
				familiar.setECuidador(rs.getBoolean("e_cuidador"));
				familiar.setNomeUtilizador(rs.getString("nome_uilizador"));
				familiar.setPassword(rs.getString("password"));
				familiar.setDataDeObito(rs.getDate("data_de_obito"));
				return familiar;
			}

		});

		return listFamiliar;
	}
}
