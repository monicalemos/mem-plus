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
					+ "SET nome_completo=?, "
					+ "nome_proprio=?, "
					+ "apelido=?,"
					+ "data_de_nascimento=?, "
					+ "LocalNascimento_idMorada=?, "
					+ "Morada_idMorada=?, "
					+ "genero=?, "
					+ "profissao=?, "
					+ "escolaridade=?, "
					+ "estado_civil=?, "
					+ "nivel_de_doenca=?, "
					+ "nome_medico=?, "
					+ "especialidade_medico=?, "
					+ "nivel_sessao=?, "
					+ "Tecnico_idTecnico=? "
					+ "WHERE idPaciente=?";

			jdbcTemplate.update(sql, 
					paciente.getNomeCompleto(),
					paciente.getNomeProprio(),
					paciente.getApelido(),
					paciente.getDataDeNascimento(),
					paciente.getMoradaByLocalNascimentoIdMorada().getIdMorada(),
					paciente.getMoradaByMoradaIdMorada().getIdMorada(),
					paciente.getGenero(),
					paciente.getProfissao(),
					paciente.getEscolaridade(),
					paciente.getEstadoCivil(),
					paciente.getNivelDeDoenca(),
					paciente.getNomeMedico(),
					paciente.getEspecialidadeMedico(),
					paciente.getNivelSessao(),
					paciente.getTecnico().getIdTecnico(),
					paciente.getIdPaciente());
		} else {
			// insert
			String sql = "INSERT INTO paciente "
					+ "(idPaciente, "
					+ "nome_completo, "
					+ "nome_proprio, "
					+ "apelido,"
					+ "data_de_nascimento, "
					+ "LocalNascimento_idMorada, "
					+ "Morada_idMorada, "
					+ "genero, "
					+ "profissao, "
					+ "escolaridade, "
					+ "estado_civil, "
					+ "nivel_de_doenca, "
					+ "nome_medico, "
					+ "especialidade_medico, "
					+ "nivel_sessao "
					+ "Tecnico_idTecnico)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			jdbcTemplate.update(sql, 
					paciente.getIdPaciente() ,
					paciente.getNomeCompleto(),
					paciente.getNomeProprio(),
					paciente.getApelido(),
					paciente.getDataDeNascimento(),
					paciente.getMoradaByLocalNascimentoIdMorada().getIdMorada(),
					paciente.getMoradaByMoradaIdMorada().getIdMorada(),
					paciente.getGenero(),
					paciente.getProfissao(),
					paciente.getEscolaridade(),
					paciente.getEstadoCivil(),
					paciente.getNivelDeDoenca(),
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
					Morada morada = moradaImpl.get(rs.getInt("Morada_idMorada"));
					Morada local_nascimento = moradaImpl.get(rs.getInt("LocalNascimento_idMorada"));
					Tecnico tecnico = tecnicoImpl.get(rs.getInt("Tecnico_idTecnico"));
					
					Paciente paciente = new Paciente();
					paciente.setIdPaciente(rs.getInt("idPaciente"));
					paciente.setNomeCompleto(rs.getString("nome_completo"));
					paciente.setNomeProprio(rs.getString("nome_proprio"));
					paciente.setApelido(rs.getString("apelido"));
					paciente.setDataDeNascimento(rs.getDate("data_de_nascimento"));
					paciente.setMoradaByLocalNascimentoIdMorada(local_nascimento);
					paciente.setMoradaByMoradaIdMorada(morada);
					paciente.setGenero(rs.getString("genero"));
					paciente.setProfissao(rs.getString("profissao"));
					paciente.setEscolaridade(rs.getString("escolaridade"));
					paciente.setEstadoCivil(rs.getString("estado_civil"));
					paciente.setNivelDeDoenca(rs.getInt("nivel_de_doenca"));
					paciente.setNomeMedico(rs.getString("nome_medico"));
					paciente.setEspecialidadeMedico(rs.getString("especialidade_medico"));
					paciente.setNivelSessao(rs.getInt("nivel_sessao"));
					paciente.setTecnico(tecnico);
					return paciente;
				}

				return null;
			}

		});
	}

	
	@Override
	public List<Paciente> list(int idTecnico) {
		String sql = "SELECT * FROM paciente, morada "
				+ "WHERE Paciente.Morada_idMorada = Morada.idMorada"
				+ "and paciente.Tecnico_idTecnico=" + idTecnico;
		
		List<Paciente> listPaciente = jdbcTemplate.query(sql, new RowMapper<Paciente>() {

			@Override
			public Paciente mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Morada morada = moradaImpl.get(rs.getInt("Morada_idMorada"));
				Morada local_nascimento = moradaImpl.get(rs.getInt("LocalNascimento_idMorada"));
				Tecnico tecnico = tecnicoImpl.get(rs.getInt("Tecnico_idTecnico"));
				
				Paciente paciente = new Paciente();
				paciente.setIdPaciente(rs.getInt("idPaciente"));
				paciente.setNomeCompleto(rs.getString("nome_completo"));
				paciente.setNomeProprio(rs.getString("nome_proprio"));
				paciente.setApelido(rs.getString("apelido"));
				paciente.setDataDeNascimento(rs.getDate("data_de_nascimento"));
				paciente.setMoradaByLocalNascimentoIdMorada(local_nascimento);
				paciente.setMoradaByMoradaIdMorada(morada);
				paciente.setGenero(rs.getString("genero"));
				paciente.setProfissao(rs.getString("profissao"));
				paciente.setEscolaridade(rs.getString("escolaridade"));
				paciente.setEstadoCivil(rs.getString("estado_civil"));
				paciente.setNivelDeDoenca(rs.getInt("nivel_de_doenca"));
				paciente.setNomeMedico(rs.getString("nome_medico"));
				paciente.setEspecialidadeMedico(rs.getString("especialidade_medico"));
				paciente.setNivelSessao(rs.getInt("nivel_sessao"));
				paciente.setTecnico(tecnico);
				
				return paciente;
			}

		});

		return listPaciente;
	}

}
