package com.mem.app.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mem.app.dao.PacienteDAO;
import com.mem.app.model.Morada;
import com.mem.app.model.Paciente;
import com.mem.app.model.Tecnico;
@Repository
public class PacienteDAOImpl implements PacienteDAO {

	private JdbcTemplate jdbcTemplate;
	private MoradaDAOImpl moradaImpl;
	private TecnicoDAOImpl tecnicoImpl;
	
	private Connection connection;
	
	@Autowired
	public PacienteDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		moradaImpl = new MoradaDAOImpl(dataSource);
		tecnicoImpl = new TecnicoDAOImpl(dataSource);
		
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int saveOrUpdate(Paciente paciente) {
		if (paciente.getIdPaciente() > 0) {
			// update
			String sql = "UPDATE paciente "
					+ "SET nomeCompleto=?, "
					+ "nomeProprio=?, "
					+ "apelido=?,"
					+ "dataNascimento=?, "
					+ "idLocalNascimento=?, "
					+ "idMorada=?, "
					+ "genero=?, "
					+ "profissao=?, "
					+ "escolaridade=?, "
					+ "estadoCivil=?, "
					+ "nivelDoenca=?, "
					+ "nomeMedico=?, "
					+ "especialidadeMedico=?, "
					+ "nivelSessao=?, "
					+ "idTecnico=? "
					+ "WHERE idPaciente=?";

			jdbcTemplate.update(sql, 
					paciente.getNomeCompleto(),
					paciente.getNomeProprio(),
					paciente.getApelido(),
					paciente.getDataNascimento(),
					paciente.getMoradaByIdLocalNascimento().getIdMorada(),
					paciente.getMoradaByIdMorada().getIdMorada(),
					paciente.getGenero(),
					paciente.getProfissao(),
					paciente.getEscolaridade(),
					paciente.getEstadoCivil(),
					paciente.getNivelDoenca(),
					paciente.getNomeMedico(),
					paciente.getEspecialidadeMedico(),
					paciente.getNivelSessao(),
					paciente.getTecnico().getIdTecnico(),
					paciente.getIdPaciente());
			
			return paciente.getIdPaciente();
		} else {
			// insert
			final String INSERT_SQL = "INSERT INTO paciente "
					+ "(nomeCompleto, "
					+ "nomeProprio, "
					+ "apelido,"
					+ "dataNascimento, "
					+ "idLocalNascimento, "
					+ "idMorada, "
					+ "genero, "
					+ "profissao, "
					+ "escolaridade, "
					+ "estadoCivil, "
					+ "nivelDoenca, "
					+ "nomeMedico, "
					+ "especialidadeMedico, "
					+ "nivelSessao, "
					+ "idTecnico)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			int newId = 0;
			final String nomeProprio = paciente.getNomeProprio();
			final String apelido = paciente.getApelido();
			final String nomeCompleto = apelido + "," + nomeProprio;
			final Date dataNascimento = (Date) paciente.getDataNascimento();
			final int idLocalNascimento = paciente.getMoradaByIdLocalNascimento().getIdMorada();
			final int idMorada = paciente.getMoradaByIdMorada().getIdMorada();
			final String genero = paciente.getGenero();
			final String profissao = paciente.getProfissao();
			final String escolaridade = paciente.getEscolaridade();
			final String estadoCivil = paciente.getEstadoCivil();
			final int nivelDoenca = paciente.getNivelDoenca();
			final String nomeMedico = paciente.getNomeMedico();
			final String especialidadeMedico = paciente.getEspecialidadeMedico();
			final int nivelSessao = paciente.getNivelSessao();
			final int idTecnico = paciente.getTecnico().getIdTecnico();		
			
			
			System.out.println("variaveis definidas no insert idTecnico " + nomeCompleto + ", " + nomeProprio + ", " + apelido+
					", "+ dataNascimento + ", "+ idLocalNascimento + ", "+ idMorada + ", " + genero + ", " + profissao + ", " + 
			escolaridade + ", " + estadoCivil + ", " + nivelDoenca + ", "+ nomeMedico+ ", "+ especialidadeMedico + 
			", " + nivelSessao);

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
				ps.setString(8, profissao);
				ps.setString(9, escolaridade);
				ps.setString(10, estadoCivil);
				ps.setInt(11, nivelDoenca);
				ps.setString(12, nomeMedico);
				ps.setString(13, especialidadeMedico);
				ps.setInt(14, nivelSessao);
				ps.setInt(15, idTecnico);
				
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

			System.out.println("novo id do paciente: " + newId);
			return newId;
		}
	}

	@Override
	public void delete(int pacienteId) {
		String sql = "DELETE FROM paciente WHERE idPaciente=?";
		jdbcTemplate.update(sql, pacienteId);
	}

	@Override
	public Paciente get(int pacienteId) {
		String sql = "SELECT * FROM paciente WHERE idPaciente=" + pacienteId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Paciente>() {

			@Override
			public Paciente extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Morada morada = moradaImpl.get(rs.getInt("idMorada"));
					Morada local_nascimento = moradaImpl.get(rs.getInt("idLocalNascimento"));
					Tecnico tecnico = tecnicoImpl.get(rs.getInt("idTecnico"));
					
					Paciente paciente = new Paciente();
					paciente.setIdPaciente(rs.getInt("idPaciente"));
					paciente.setNomeCompleto(rs.getString("nomeCompleto"));
					paciente.setNomeProprio(rs.getString("nomeProprio"));
					paciente.setApelido(rs.getString("apelido"));
					paciente.setDataNascimento(rs.getDate("dataNascimento"));
					paciente.setMoradaByIdLocalNascimento(local_nascimento);
					paciente.setMoradaByIdMorada(morada);
					paciente.setGenero(rs.getString("genero"));
					paciente.setProfissao(rs.getString("profissao"));
					paciente.setEscolaridade(rs.getString("escolaridade"));
					paciente.setEstadoCivil(rs.getString("estadoCivil"));
					paciente.setNivelDoenca(rs.getInt("nivelDoenca"));
					paciente.setNomeMedico(rs.getString("nomeMedico"));
					paciente.setEspecialidadeMedico(rs.getString("especialidadeMedico"));
					paciente.setNivelSessao(rs.getInt("nivelSessao"));
					paciente.setTecnico(tecnico);
					return paciente;
				}

				return null;
			}

		});
	}

	
	@Override
	public List<Paciente> list(int idTecnico) {
		String sql = "SELECT * FROM paciente WHERE idTecnico=" + idTecnico;
		
		List<Paciente> listPaciente = jdbcTemplate.query(sql, new RowMapper<Paciente>() {

			@Override
			public Paciente mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Morada morada = moradaImpl.get(rs.getInt("idMorada"));
				Morada local_nascimento = moradaImpl.get(rs.getInt("idLocalNascimento"));
				Tecnico tecnico = tecnicoImpl.get(rs.getInt("idTecnico"));
				
				Paciente paciente = new Paciente();
				paciente.setIdPaciente(rs.getInt("idPaciente"));
				paciente.setNomeCompleto(rs.getString("nomeCompleto"));
				paciente.setNomeProprio(rs.getString("nomeProprio"));
				paciente.setApelido(rs.getString("apelido"));
				paciente.setDataNascimento(rs.getDate("dataNascimento"));
				paciente.setMoradaByIdLocalNascimento(local_nascimento);
				paciente.setMoradaByIdMorada(morada);
				paciente.setGenero(rs.getString("genero"));
				paciente.setProfissao(rs.getString("profissao"));
				paciente.setEscolaridade(rs.getString("escolaridade"));
				paciente.setEstadoCivil(rs.getString("estadoCivil"));
				paciente.setNivelDoenca(rs.getInt("nivelDoenca"));
				paciente.setNomeMedico(rs.getString("nomeMedico"));
				paciente.setEspecialidadeMedico(rs.getString("especialidadeMedico"));
				paciente.setNivelSessao(rs.getInt("nivelSessao"));
				paciente.setTecnico(tecnico);
				
				return paciente;
			}

		});

		return listPaciente;
	}

}
