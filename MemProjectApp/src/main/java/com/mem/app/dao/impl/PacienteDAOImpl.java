package com.mem.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mem.app.dao.PacienteDAO;
import com.mem.app.model.Morada;
import com.mem.app.model.Paciente;
import com.mem.app.model.Tecnico;

public class PacienteDAOImpl implements PacienteDAO {

	private JdbcTemplate jdbcTemplate;
	private MoradaDAOImpl moradaImpl;
	private TecnicoDAOImpl tecnicoImpl;

	public PacienteDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		moradaImpl = new MoradaDAOImpl(dataSource);
		tecnicoImpl = new TecnicoDAOImpl(dataSource);
	}

	@Override
	public void saveOrUpdate(Paciente paciente) {
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
		} else {
			// insert
			String sql = "INSERT INTO paciente "
					+ "(idPaciente, "
					+ "nomeCompleto, "
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
					+ "nivelSessao "
					+ "idTecnico)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			jdbcTemplate.update(sql, 
					paciente.getIdPaciente() ,
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
					paciente.getTecnico().getIdTecnico());					
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
		String sql = "SELECT * FROM paciente paciente.idTecnico=" + idTecnico;
		
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
