package com.mem.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mem.app.dao.FamiliarDAO;
import com.mem.app.model.Familiar;
import com.mem.app.model.Morada;
import com.mem.app.model.Utilizador;

@Repository
public class FamiliarDAOImpl implements FamiliarDAO {

	private JdbcTemplate jdbcTemplate;
	private MoradaDAOImpl moradaImpl;
	private UtilizadorDAOImpl utilizadorImpl;

	private Connection connection;

	@Autowired
	public FamiliarDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		moradaImpl = new MoradaDAOImpl(dataSource);
		utilizadorImpl = new UtilizadorDAOImpl(dataSource);

		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int saveOrUpdate(Familiar familiar) {
		if (familiar.getIdFamiliar() > 0) {
			// update
			System.out.println("UPDATE");
			final String UPDATE_SQL = "UPDATE familiar " + "SET "
					+ "nomeCompleto=?, " + 
					"nomeProprio=?, " + 
					"apelido=?,"
					+ "dataNascimento=?, " + 
					"idLocalNascimento=?, " + 
					"idMorada=?, " + 
					"genero=?, " + 
					"estadoCivil=?, "
					+ "profissao=?, " + 
					"telefone=?, " + 
					"eCuidador=?, " + 
					"dataObito=?, " + 
					"idUtilizador=? "
					+ "WHERE idFamiliar=?";
			final String nomeProprio = familiar.getNomeProprio();
			final String apelido = familiar.getApelido();
			final String nomeCompleto = apelido + ", " + nomeProprio;
			final Date dataNascimento = (Date) familiar.getDataNascimento();
			final int idLocalNascimento = familiar.getMoradaByIdLocalNascimento().getIdMorada();
			final int idMorada = familiar.getMoradaByIdMorada().getIdMorada();
			final String genero = familiar.getGenero();
			final String estadoCivil = familiar.getEstadoCivil();
			final String profissao = familiar.getProfissao();
			final int telefone = familiar.getTelefone();
			final boolean eCuidador = familiar.getEcuidador();
			final Date dataObito =  familiar.getDataObito();
			final int idFamiliar = familiar.getIdFamiliar();
			Integer idUtilizador = null; 
			if(familiar.getUtilizador()!= null){
				idUtilizador = familiar.getUtilizador().getIdUtilizador();
			}
			
			PreparedStatement ps;
			try {
				ps = connection.prepareStatement(UPDATE_SQL.toString(), 
						Statement.SUCCESS_NO_INFO);
				ps.setString(1, nomeCompleto);
				ps.setString(2, nomeProprio);
				ps.setString(3, apelido);
				ps.setDate(4, new java.sql.Date(dataNascimento.getTime()));
				ps.setInt(5, idLocalNascimento);
				ps.setInt(6, idMorada);
				ps.setString(7, genero);
				ps.setString(8, estadoCivil);
				ps.setString(9, profissao);
				ps.setInt(10, telefone);
				ps.setBoolean(11, eCuidador);
				
				if(dataObito == null){
					ps.setDate(12, null);
				}
				else{
					ps.setDate(12, new java.sql.Date(dataObito.getTime()));
				}
				
				if(idUtilizador != null)
					ps.setInt(13, idUtilizador);
				else{
					ps.setNull(13, java.sql.Types.INTEGER);
				}
				ps.setInt(14,  idFamiliar);
			
				ps.executeUpdate();

				System.out.println("executou a query");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return familiar.getIdFamiliar();
		} else {
			// insert
			System.out.println("INSERT");
			final String INSERT_SQL = "INSERT INTO familiar " + 
					"(nomeCompleto, " + 
					"nomeProprio, " + 
					"apelido," + 
					"dataNascimento, " + 
					"idLocalNascimento, " + 
					"idMorada, " + 
					"genero, " + 
					"estadoCivil, " + 
					"profissao, " + 
					"telefone, " + 
					"eCuidador, " + 
					"dataObito," + 
					"idUtilizador)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			int newId = 0;
			final String nomeProprio = familiar.getNomeProprio();
			final String apelido = familiar.getApelido();
			final String nomeCompleto = apelido + ", " + nomeProprio;
			final Date dataNascimento = (Date) familiar.getDataNascimento();
			final int idLocalNascimento = familiar.getMoradaByIdLocalNascimento().getIdMorada();
			final int idMorada = familiar.getMoradaByIdMorada().getIdMorada();
			final String genero = familiar.getGenero();
			final String estadoCivil = familiar.getEstadoCivil();
			final String profissao = familiar.getProfissao();
			final int telefone = familiar.getTelefone();
			final boolean eCuidador = familiar.getEcuidador();
			final Date dataObito =  familiar.getDataObito();
			Integer idUtilizador = null; 
			if(familiar.getUtilizador()!= null){
				idUtilizador = familiar.getUtilizador().getIdUtilizador();
			}
		

			System.out.println("variaveis definidas no insert idFamiliar " + nomeCompleto + ", " + nomeProprio + ", "
					+ apelido + ", " + dataNascimento + ", " + idLocalNascimento + ", " + idMorada + ", " + genero
					+ ", " + profissao + ", " + estadoCivil + ", " + telefone + ", " + eCuidador + ", " + dataObito
					+ ", " + idUtilizador);

			PreparedStatement ps;
			try {
				ps = connection.prepareStatement(INSERT_SQL.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, nomeCompleto);
				ps.setString(2, nomeProprio);
				ps.setString(3, apelido);
				ps.setDate(4, new java.sql.Date(dataNascimento.getTime()));
				ps.setInt(5, idLocalNascimento);
				ps.setInt(6, idMorada);
				ps.setString(7, genero);
				ps.setString(8, estadoCivil);
				ps.setString(9, profissao);
				ps.setInt(10, telefone);
				ps.setBoolean(11, eCuidador);
				
				if(dataObito == null){
					ps.setDate(12, null);
				}
				else{
					ps.setDate(12, new java.sql.Date(dataObito.getTime()));
				}
				
				if(idUtilizador != null)
					ps.setInt(13, idUtilizador);
				else{
					ps.setNull(13, java.sql.Types.INTEGER);
				}
				ps.executeUpdate();

				System.out.println("executou a query");
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					System.out.println("tem resultados");
					newId = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("novo id do familir: " + newId);
			return newId;
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
			public Familiar extractData(ResultSet rs) throws SQLException, DataAccessException {
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

		String sql = "SELECT f.* FROM familiar f, Utilizador u WHERE f.idUtilizador = u.idUtilizador and nomeUtilizador="
				+ user;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Familiar>() {

			@Override
			public Familiar extractData(ResultSet rs) throws SQLException, DataAccessException {
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
