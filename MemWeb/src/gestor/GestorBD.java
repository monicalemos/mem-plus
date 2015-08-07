package gestor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import baseDados.Queries;
import classesDados.Evento;
import classesDados.Familiar;
import classesDados.Imagem;
import classesDados.Morada;
import classesDados.Paciente;
import classesDados.Relacao;
import classesDados.Tecnico;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import enumerados.EspecialidadeMedico;
import enumerados.TipoEscolaridade;
import enumerados.TipoEstadoCivil;
import enumerados.TipoGenero;
import enumerados.TipoRelacao;

public class GestorBD {

    private PreparedStatement preparedStatement;
    //	private PreparedStatement preparedStatement2;
    private Connection connection;
    HttpSession session = null;

    public GestorBD(Connection connection) {
        this.connection = connection;
    }

    //IMAGEM
    public int insert_Imagem(Imagem foto) throws FileNotFoundException {
        int row = 0;
        FileInputStream fis = new FileInputStream(foto.getFoto());
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.insert_Imagem);
            preparedStatement.setInt(1, foto.getId());
            preparedStatement.setString(2, foto.getNome());
            preparedStatement.setBinaryStream(3, fis, (int) foto.getFoto().length());
            row = preparedStatement.executeUpdate();

            System.out.println("Fiz os inserts da imagem");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu inserir a foto " + foto.getNome());
        }
        return row;

    }

    public int verificaId_Imagem() throws SQLException {
        preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.ultimoId_Imagem);
        ResultSet resultSet = preparedStatement.executeQuery();
        int lastInsertedId = 0;

        if (resultSet != null) {
            if (resultSet.next()) {
                lastInsertedId = resultSet.getInt(1);
            }
            resultSet.close();
        }
        return lastInsertedId;
    }

    //PACIENTE
    public int insert_Paciente(Paciente u) {
        int row = 0;
        try {

            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.insert_Paciente);
            preparedStatement.setInt(1, u.getId());
            preparedStatement.setString(2, u.getNome_completo());
            preparedStatement.setString(3, u.getNomeProprio());
            preparedStatement.setString(4, u.getApelido());
            preparedStatement.setDate(5, new java.sql.Date(u.getData_de_nascimento().getTime()));
            preparedStatement.setInt(6, u.getLocal_nascimento().getId());
            preparedStatement.setInt(7, u.getMorada().getId());
            preparedStatement.setString(8, u.getGenero().toString());
            preparedStatement.setString(9, u.getProfissao());
            preparedStatement.setString(10, u.getEscolaridade().toString());
            preparedStatement.setString(11, u.getEstadoCivil().toString());
            preparedStatement.setString(12, u.getNivel_doenca());
            preparedStatement.setString(13, u.getNome_medico());
            preparedStatement.setString(14, u.getEspecialidade_medico().toString());
            preparedStatement.setInt(15, u.getNivel_sessao());
            preparedStatement.setInt(16, u.getTecnico().getId());
            //preparedStatement.setInt(17, u.getFoto().getId());
            row = preparedStatement.executeUpdate();

            System.out.println("Fiz os inserts do paciente");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu inserir o paciente " + u.getNome_completo());
        }
        return row;
    }

    public int verificaId_Paciente() throws SQLException {
        preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.ultimoId_Paciente);
        ResultSet resultSet = preparedStatement.executeQuery();
        int lastInsertedId = 0;

        if (resultSet != null) {
            if (resultSet.next()) {
                lastInsertedId = resultSet.getInt(1);
            }
            resultSet.close();
        }
        return lastInsertedId;
    }

    public ArrayList<Paciente> selectAll_Pacientes(int idTecnico) {
        ArrayList<Paciente> pacientes = new ArrayList<Paciente>();

        try {

            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.selectAll_PacientesComMorada);
            preparedStatement.setInt(1, idTecnico);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tecnico tecnico = select_TecnicoId(resultSet.getInt("Tecnico_idTecnico"));
                System.out.println("id do tecnico: " + tecnico.getId());
                pacientes.add(new Paciente(resultSet.getInt("idPaciente"),
                        resultSet.getString("nome_completo"),
                        resultSet.getDate("data_de_nascimento"),
                        new Morada(resultSet.getInt("idMorada"),
                                resultSet.getString("pais"),
                                resultSet.getString("cidade"),
                                resultSet.getString("regiao")),
                        new Morada(resultSet.getInt("idMorada"),
                                resultSet.getString("pais"),
                                resultSet.getString("cidade"),
                                resultSet.getString("regiao")),
                        TipoGenero.valueOf(resultSet.getString("genero")),
                        resultSet.getString("profissao"),
                        TipoEscolaridade.valueOf(resultSet.getString("escolaridade")),
                        TipoEstadoCivil.valueOf(resultSet.getString("estado_civil")),
                        resultSet.getString("nivel_de_doenca"),
                        resultSet.getString("nome_medico"),
                        EspecialidadeMedico.valueOf(resultSet.getString("especialidade_medico")),
                        resultSet.getInt("nivel_sessao"),
                        tecnico));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("A base de dados não tem pacientes");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pacientes;
    }

    public Paciente select_PacienteId(int id_paciente) {
        Paciente paciente = null;
        Morada local;
        Morada morada;
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.select_PacienteId);
            preparedStatement.setInt(1, id_paciente);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                local = select_MoradaId(resultSet.getInt("LocalNascimento_idMorada"));
                morada = select_MoradaId(resultSet.getInt("Morada_idMorada"));

                paciente = new Paciente(resultSet.getInt("idPaciente"),
                        resultSet.getString("nome_completo"),
                        resultSet.getDate("data_de_nascimento"),
                        local,
                        morada,
                        TipoGenero.valueOf(resultSet.getString("genero")),
                        resultSet.getString("profissao"),
                        TipoEscolaridade.valueOf(resultSet.getString("escolaridade")),
                        TipoEstadoCivil.valueOf(resultSet.getString("estado_civil")),
                        resultSet.getString("nivel_de_doenca"),
                        resultSet.getString("nome_medico"),
                        EspecialidadeMedico.valueOf(resultSet.getString("especialidade_medico")),
                        resultSet.getInt("nivel_sessao"),
                        select_TecnicoId(resultSet.getInt("Tecnico_idTecnico")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("O paciente com id " + id_paciente + " não existe na BD");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return paciente;

    }

    public int update_Paciente(Paciente u) {
        int row = 0;
        try {

            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.update_Paciente);
            preparedStatement.setString(1, u.getNome_completo());
            preparedStatement.setString(2, u.getNomeProprio());
            preparedStatement.setString(3, u.getApelido());
            preparedStatement.setDate(4, new java.sql.Date(u.getData_de_nascimento().getTime()));
            preparedStatement.setInt(5, u.getLocal_nascimento().getId());
            preparedStatement.setInt(6, u.getMorada().getId());
            preparedStatement.setString(7, u.getGenero().toString());
            preparedStatement.setString(8, u.getProfissao());
            preparedStatement.setString(9, u.getEscolaridade().toString());
            preparedStatement.setString(10, u.getEstadoCivil().toString());
            preparedStatement.setString(11, u.getNivel_doenca());
            preparedStatement.setString(12, u.getNome_medico());
            preparedStatement.setString(13, u.getEspecialidade_medico().toString());
            preparedStatement.setInt(14, u.getNivel_sessao());
            preparedStatement.setInt(15, u.getId());
            //preparedStatement.setInt(16, u.getFoto().getId());
            row = preparedStatement.executeUpdate();

            System.out.println("Fiz os updates do paciente");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu alterar o paciente " + u.getNome_completo());
        }
        return row;
    }

    public int delete_PacienteNome(String nomeCompleto) {
        int row = 0;
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.delete_PacienteNome);
            preparedStatement.setString(1, nomeCompleto);
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu apagar o paciete " + nomeCompleto);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    public int delete_Paciente(Paciente p) {
        int row = 0;
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.delete_Paciente);
            preparedStatement.setInt(1, p.getId());
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu apagar o paciete " + p.getNome_completo());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    //MORADA:
    public int insert_Morada(Morada m) {
        int row = 0;
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.insert_Morada);
            preparedStatement.setInt(1, m.getId());
            preparedStatement.setString(2, m.getPais());
            preparedStatement.setString(3, m.getRegiao());
            preparedStatement.setString(4, m.getCidade());
            //preparedStatement.setInt(5, m.getFoto().getId());
            row = preparedStatement.executeUpdate();
            System.out.println("Fiz os inserts da morada");
        } catch (SQLException e) {
            System.out.println("Não conseguiu inserir a morada " + m);
            e.printStackTrace();
        }
        return row;
    }

    public int verificaId_Morada() throws SQLException {

        preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.ultimoId_Morada);
        ResultSet resultSet = preparedStatement.executeQuery();
        int lastInsertedId = 0;

        if (resultSet != null) {
            if (resultSet.next()) {
                lastInsertedId = resultSet.getInt(1);
            }
            resultSet.close();
        }
        return lastInsertedId;
    }

    public Morada select_MoradaId(int id_morada) {
        Morada morada = null;
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.select_MoradaId);
            preparedStatement.setInt(1, id_morada);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                morada = new Morada(resultSet.getInt("idMorada"), resultSet.getString("pais"),
                        resultSet.getString("cidade"),
                        resultSet.getString("regiao"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("A morada com id " + id_morada + " não existe na BD");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return morada;
    }

    public ArrayList<Morada> selectAll_Moradas() {
        ArrayList<Morada> moradas = new ArrayList<Morada>();

        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.selectAll_Moradas);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                moradas.add(new Morada(resultSet.getInt("idMorada"),
                        resultSet.getString("pais"),
                        resultSet.getString("cidade"),
                        resultSet.getString("regiao")));
            }
        } catch (SQLException e) {
            System.out.println("A base de dados não tem moradas");
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return moradas;
    }

    public int update_Morada(Morada m) {
        int row = 0;
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.update_Morada);
            preparedStatement.setString(1, m.getPais());
            preparedStatement.setString(2, m.getRegiao());
            preparedStatement.setString(3, m.getCidade());
            preparedStatement.setInt(4, m.getId());
            //preparedStatement.setInt(5, m.getFoto().getId());
            row = preparedStatement.executeUpdate();
            System.out.println("Fiz os updates da morada");
        } catch (SQLException e) {
            System.out.println("Não conseguiu aterar a morada " + m);
            e.printStackTrace();
        }
        return row;
    }

    public int delete_Morada(Morada morada) {
        int row = 0;
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.delete_Tecnico);
            preparedStatement.setInt(1, morada.getId());
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Não conseguiu apagar a morada " + morada);
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    //FAMILIAR
    public int insert_Familiar(Familiar p) {
        int row = 0;
        try {

            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.insert_Familiar);
            preparedStatement.setInt(1, p.getId());
            preparedStatement.setString(2, p.getNome_completo());
            preparedStatement.setString(3, p.getNomeProprio());
            preparedStatement.setString(4, p.getApelido());
            preparedStatement.setDate(5, new java.sql.Date(p.getData_de_nascimento().getTime()));
            preparedStatement.setInt(6, p.getLocal_nascimento().getId());
            preparedStatement.setInt(7, p.getMorada().getId());
            preparedStatement.setString(8, p.getGenero().toString());
            preparedStatement.setString(9, p.getEstadoCivil().toString());
            preparedStatement.setString(10, p.getProfissao());
            preparedStatement.setInt(11, p.getTelefone());
            preparedStatement.setBoolean(12, p.eCuidador());
            if (p.getNome_utilizador() != null & p.getPassword() != null) {
                preparedStatement.setString(13, p.getNome_utilizador());
                preparedStatement.setString(14, p.getPassword());
            } else {
                preparedStatement.setString(13, null);
                preparedStatement.setString(14, null);
            }
            preparedStatement.setString(15, null);

            row = preparedStatement.executeUpdate();
            System.out.println("Fiz os inserts do familiar");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu inserir o familiar " + p.getNome_completo());
        }
        return row;
    }

    public int verificaId_Familiar() throws SQLException {
        preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.ultimoId_Familiar);
        ResultSet resultSet = preparedStatement.executeQuery();
        int lastInsertedId = 0;

        if (resultSet != null) {
            if (resultSet.next()) {
                lastInsertedId = resultSet.getInt(1);
            }
            resultSet.close();
        }
        return lastInsertedId;
    }

    public ArrayList<Familiar> selectAll_Familiares(int idPaciente) {
        ArrayList<Familiar> familiares = new ArrayList<Familiar>();
        Morada local;
        Morada morada;
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.selectAll_Familiares);
            preparedStatement.setInt(1, idPaciente);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                local = select_MoradaId(resultSet.getInt("Local_Nascimento_idMorada"));
                morada = select_MoradaId(resultSet.getInt("Morada_idMorada"));

                familiares.add(new Familiar(resultSet.getInt("idFamiliar"),
                        setNomeCompleto(resultSet.getString("nome_proprio"), resultSet.getString("apelido")),
                        resultSet.getDate("data_de_nascimento"),
                        local,
                        morada,
                        TipoGenero.valueOf(resultSet.getString("genero")),
                        TipoEstadoCivil.valueOf(resultSet.getString("estado_civil")),
                        resultSet.getString("profissao"),
                        resultSet.getInt("telefone"),
                        resultSet.getBoolean("e_cuidador")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("A base de dados não tem familiares");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("tamanho familiares gestor: " + familiares.size());
        return familiares;
    }

    private String setNomeCompleto(String nome, String apelido) {
        return nome + " " + apelido;
    }

    public Familiar select_FamiliarId(int id) {
        Familiar familiar = null;
        Morada local;
        Morada morada;
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.select_FamiliarId);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                local = select_MoradaId(resultSet.getInt("Local_Nascimento_idMorada"));
                morada = select_MoradaId(resultSet.getInt("Morada_idMorada"));
                boolean eCuidador = resultSet.getBoolean("e_cuidador");
                String user;
                String pass;

                if (eCuidador == true) {
                    user = resultSet.getString("nome_utilizador");
                    pass = resultSet.getString("password");

                    familiar = new Familiar(resultSet.getInt("idFamiliar"),
                            resultSet.getString("nome_completo"),
                            resultSet.getDate("data_de_nascimento"),
                            local,
                            morada,
                            TipoGenero.valueOf(resultSet.getString("genero")),
                            TipoEstadoCivil.valueOf(resultSet.getString("estado_civil")),
                            resultSet.getString("profissao"),
                            resultSet.getInt("telefone"),
                            resultSet.getBoolean("e_cuidador"), user, pass);
                } else {
                    System.out.println("nome todo do while " + resultSet.getString("nome_proprio") + " " + resultSet.getString("apelido"));
                    familiar = new Familiar(resultSet.getInt("idFamiliar"),
                            resultSet.getString("nome_proprio") + " " + resultSet.getString("apelido"),
                            resultSet.getDate("data_de_nascimento"),
                            local,
                            morada,
                            TipoGenero.valueOf(resultSet.getString("genero")),
                            TipoEstadoCivil.valueOf(resultSet.getString("estado_civil")),
                            resultSet.getString("profissao"),
                            resultSet.getInt("telefone"),
                            resultSet.getBoolean("e_cuidador"));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return familiar;
    }

    public Familiar select_FamiliarNomeUtilizador(String user) {
        Familiar familiar = null;
        Morada local;
        Morada morada;

        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.select_FamiliarNomeUtilizador);
            preparedStatement.setString(1, user);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                local = select_MoradaId(resultSet.getInt("Local_Nascimento_idMorada"));
                morada = select_MoradaId(resultSet.getInt("Morada_idMorada"));
                boolean eCuidador = resultSet.getBoolean("e_cuidador");
                String nome_user;
                String pass;

                if (eCuidador == true) {
                    nome_user = resultSet.getString("nome_utilizador");
                    pass = resultSet.getString("password");

                    familiar = new Familiar(resultSet.getInt("idFamiliar"),
                            resultSet.getString("nome_completo"),
                            resultSet.getDate("data_de_nascimento"),
                            local,
                            morada,
                            TipoGenero.valueOf(resultSet.getString("genero")),
                            TipoEstadoCivil.valueOf(resultSet.getString("estado_civil")),
                            resultSet.getString("profissao"),
                            resultSet.getInt("telefone"),
                            resultSet.getBoolean("e_cuidador"), nome_user, pass);
                } else {
                    familiar = new Familiar(resultSet.getInt("idFamiliar"),
                            resultSet.getString("nome_completo"),
                            resultSet.getDate("data_de_nascimento"),
                            local,
                            morada,
                            TipoGenero.valueOf(resultSet.getString("genero")),
                            TipoEstadoCivil.valueOf(resultSet.getString("estado_civil")),
                            resultSet.getString("profissao"),
                            resultSet.getInt("telefone"),
                            resultSet.getBoolean("e_cuidador"));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("A base de dados não tem  o paciente com o nome de utilizador " + user);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return familiar;
    }

    public int update_Familiar(Familiar p) {
        int row = 0;
        try {

            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.update_Familiar);
            preparedStatement.setString(1, p.getNome_completo());
            preparedStatement.setString(2, p.getNomeProprio());
            preparedStatement.setString(3, p.getApelido());
            preparedStatement.setDate(4, new java.sql.Date(p.getData_de_nascimento().getTime()));
            preparedStatement.setInt(5, p.getLocal_nascimento().getId());
            preparedStatement.setInt(6, p.getMorada().getId());
            preparedStatement.setString(7, p.getGenero().toString());
            preparedStatement.setString(8, p.getEstadoCivil().toString());
            preparedStatement.setString(9, p.getProfissao());
            preparedStatement.setInt(10, p.getTelefone());
            preparedStatement.setBoolean(11, p.eCuidador());
            if (p.getNome_utilizador() != null & p.getPassword() != null) {
                preparedStatement.setString(12, p.getNome_utilizador());
                preparedStatement.setString(13, p.getPassword());
            } else {
                preparedStatement.setString(12, null);
                preparedStatement.setString(13, null);
            }
            preparedStatement.setDate(14, new java.sql.Date(p.getData_obito().getTime()));
            preparedStatement.setInt(15, p.getId());

            row = preparedStatement.executeUpdate();
            System.out.println("Fiz os updates do familiar");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu altear o familiar " + p.getNome_completo());
        }
        return row;
    }

    public int delete_Familiar(Familiar familiar) {
        int row = 0;
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.delete_Familiar);
            preparedStatement.setInt(1, familiar.getId());
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu apagar a familiar " + familiar.getNome_completo());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    //TECNICO
    public int verificaId_Tecnico() throws SQLException {
        preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.ultimoId_Tecnico);
        ResultSet resultSet = preparedStatement.executeQuery();
        int lastInsertedId = 0;

        if (resultSet != null) {
            if (resultSet.next()) {
                lastInsertedId = resultSet.getInt(1);
            }
            resultSet.close();
        }
        return lastInsertedId;
    }

    public int insert_Tecnico(Tecnico rm) {

        int row;
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.insert_Tecnico);
            preparedStatement.setInt(1, rm.getId());
            preparedStatement.setString(2, rm.getNome_completo());
            preparedStatement.setString(3, rm.getNomeProprio());
            preparedStatement.setString(4, rm.getApelido());
            preparedStatement.setString(5, rm.getNome_utilizador());
            preparedStatement.setString(6, rm.getPassword());
            preparedStatement.setString(7, rm.getEmail());
            row = preparedStatement.executeUpdate();
            System.out.println("Fiz os inserts do tecnico");
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu inserir o tecnico " + rm.getNome_completo());
            row = 0;
        }
        return row;
    }

    public Tecnico select_TecnicoEmail(String email) {
        Tecnico tecnico = null;
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.select_TecnicoEmail);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tecnico = new Tecnico(resultSet.getInt("idTecnico"),
                        resultSet.getString("nome_completo"),
                        resultSet.getString("nome_utilizador"),
                        resultSet.getString("password"),
                        resultSet.getString("email"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("A base de dados não tem  o tecnico com o email " + email);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tecnico;
    }

    public Tecnico select_TecnicoNome_Utilizador(String nome_utilizador) {
        Tecnico tecnico = null;
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.select_TecnicoNome_Utilizador);
            preparedStatement.setString(1, nome_utilizador);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tecnico = new Tecnico(resultSet.getInt("idTecnico"),
                        resultSet.getString("nome_completo"),
                        resultSet.getString("nome_utilizador"),
                        resultSet.getString("password"),
                        resultSet.getString("email"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("A base de dados não tem  o tecnico com o nome de utilizador " + nome_utilizador);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tecnico;
    }

    public Tecnico select_TecnicoId(int idTecnico) {
        Tecnico tecnico = null;
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.select_TecnicoId);
            preparedStatement.setInt(1, idTecnico);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tecnico = new Tecnico(resultSet.getInt("idTecnico"),
                        resultSet.getString("nome_completo"),
                        resultSet.getString("nome_utilizador"),
                        resultSet.getString("password"),
                        resultSet.getString("email"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("O tecnico com id " + idTecnico + " não existe na BD");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tecnico;
    }

    public ArrayList<Tecnico> selectAll_Tecnicos() {
        ArrayList<Tecnico> tecnicos = new ArrayList<Tecnico>();

        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.selectAll_Tecnicos);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tecnicos.add(new Tecnico(resultSet.getInt("idTecnico"),
                        resultSet.getString("nome_completo"),
                        resultSet.getString("nome_utilizador"),
                        resultSet.getString("password"),
                        resultSet.getString("email")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("A base de dados não tem tecnicos");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tecnicos;
    }

    public int update_Tecnico(Tecnico rm) {

        int row;
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.update_Tecnico);
            preparedStatement.setString(1, rm.getNome_completo());
            preparedStatement.setString(2, rm.getNomeProprio());
            preparedStatement.setString(3, rm.getApelido());
            preparedStatement.setString(4, rm.getNome_utilizador());
            preparedStatement.setString(5, rm.getPassword());
            preparedStatement.setString(6, rm.getEmail());
            preparedStatement.setInt(7, rm.getId());
            row = preparedStatement.executeUpdate();
            System.out.println("Fiz os updates do tecnico");
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu alterar o tecnico " + rm.getNome_completo());
            row = 0;
        }
        return row;

    }

    public int delete_Tecnico(Tecnico tecnico) {
        int row = 0;
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.delete_Tecnico);
            preparedStatement.setInt(1, tecnico.getId());
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu apagar o tecnico " + tecnico.getNome_completo());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    //RELACAO_PACIENTE_FAMILIAR
    public int insert_Relacao_Paciente_Familiar(Relacao r) {
        int row = 0;
        try {

            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.insert_Relacao_Paciente_Familiar);
            preparedStatement.setInt(1, r.getId());
            preparedStatement.setInt(2, r.getFamiliar_nivel1().getId());
            preparedStatement.setInt(3, r.getPaciente().getId());
            preparedStatement.setString(4, r.getTipo_relacao().toString());

            row = preparedStatement.executeUpdate();
            System.out.println("Fiz os inserts do paciente");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu inserir a relação ");
        }
        return row;
    }

    public int verificaId_Relacao_Paciente_Familiar() throws SQLException {
        preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.ultimoId_Relacao_Paciente_Familiar);
        ResultSet resultSet = preparedStatement.executeQuery();
        int lastInsertedId = 0;

        if (resultSet != null) {
            if (resultSet.next()) {
                lastInsertedId = resultSet.getInt(1);
            }
            resultSet.close();
        }
        return lastInsertedId;
    }

    public Relacao select_Relacao_Paciente_Familiar(int id_paciente, int id_familiar) {
        Relacao relacao = null;
        Paciente paciente = null;
        Familiar familiar = null;
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.select_Relacao_Paciente_Familiar);
            preparedStatement.setInt(1, id_paciente);
            preparedStatement.setInt(2, id_familiar);

            ResultSet resultSet = preparedStatement.executeQuery();

            //paciente = select_PacienteId(resultSet.getInt("Paciente_idPaciente"), id_tecnico);
            paciente = select_PacienteId(id_paciente);

            //familiar = select_FamiliarId(resultSet.getInt("Familiar_idFamiliar"));
            familiar = select_FamiliarId(id_familiar);

            while (resultSet.next()) {
                relacao = new Relacao(resultSet.getInt("idRelacao_Paciente_Familiar"),
                        paciente, familiar,
                        TipoRelacao.valueOf(resultSet.getString("tipo_relacao")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("A relaçao com id " + relacao.getId() + " e paciente " + paciente + " e familiar: " + familiar + " não existe na BD");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return relacao;
    }

    public ArrayList<Relacao> selectAll_Relacao_Paciente_Familiar(int idPaciente) {
        ArrayList<Relacao> relacao_Paciente_Familiar = new ArrayList<Relacao>();

        try {

            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.selectAll_Relacao_Paciente_Familiar);
            preparedStatement.setInt(1, idPaciente);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Paciente paciente = select_PacienteId(resultSet.getInt("Paciente_idPaciente"));
                Familiar familiar = select_FamiliarId(resultSet.getInt("Familiar_idFamiliar"));
                relacao_Paciente_Familiar.add(new Relacao(resultSet.getInt("idRelacao_Paciente_Familiar"),
                        paciente, familiar,
                        TipoRelacao.valueOf(resultSet.getString("tipo_relacao"))));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("A base de dados não tem pacientes");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("tamanhos : " + relacao_Paciente_Familiar.size());
        return relacao_Paciente_Familiar;
    }

    public int delete_Relacao_Paciente_Familiar(Familiar familiar) {
        int row = 0;
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.delete_Relacao_Paciente_Familiar);
            preparedStatement.setInt(1, familiar.getId());
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu apagar a relação familiar ");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    //RELACAO_FAMILIAR_FAMILIAR
    public int insert_Relacao_Familiar_Familiar(Relacao r) {
        int row = 0;
        try {

            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.insert_Relacao_Familiar_Familiar);
            preparedStatement.setInt(1, r.getId());
            preparedStatement.setInt(2, r.getFamiliar_nivel1().getId());
            preparedStatement.setInt(3, r.getFamiliar_nivel2().getId());
            preparedStatement.setString(4, r.getTipo_relacao().toString());
            preparedStatement.setInt(5, r.getPaciente().getId());

            row = preparedStatement.executeUpdate();
            System.out.println("Fiz os inserts da relação");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu inserir a relação ");
        }
        return row;
    }

    public int verificaId_Relacao_Familiar_Familiar() throws SQLException {
        preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.ultimoId_Relacao_Familiar_Familiar);
        ResultSet resultSet = preparedStatement.executeQuery();
        int lastInsertedId = 0;

        if (resultSet != null) {
            if (resultSet.next()) {
                lastInsertedId = resultSet.getInt(1);
            }
            resultSet.close();
        }
        return lastInsertedId;
    }

    public Relacao select_Relacao_Familiar_FamiliarId(int id) {
        Relacao relacao = null;
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.select_Relacao_Familiar_FamiliarId);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Paciente paciente = select_PacienteId(resultSet.getInt("Paciente_idPaciente"));
                Familiar familiar = select_FamiliarId(resultSet.getInt("Familiar_idFamiliar"));
                Familiar familiar1 = select_FamiliarId(resultSet.getInt("Familiar_idFamiliar1"));

                relacao = new Relacao(resultSet.getInt("idRelacao_Familiar_Familiar"),
                        paciente, familiar, familiar1,
                        TipoRelacao.valueOf(resultSet.getString("tipo_relacao")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("A relação com id " + id + " não existe na BD");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return relacao;
    }

    public ArrayList<Relacao> selectAll_Relacao_Familiar_Familiar(Paciente p) {
        ArrayList<Relacao> relacao_Familiar_Familiar = new ArrayList<Relacao>();

        try {

            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.selectAll_Relacao_Familiar_Familiar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Familiar familiar1 = select_FamiliarId(resultSet.getInt("Familiar_idFamiliar"));
                Familiar familiar2 = select_FamiliarId(resultSet.getInt("Familiar_idFamiliar1"));

                relacao_Familiar_Familiar.add(new Relacao(resultSet.getInt("idRelacao_Familiar_Familiar"),
                        p, familiar1, familiar2,
                        TipoRelacao.valueOf(resultSet.getString("tipo_relacao"))));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("A base de dados não tem relações");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return relacao_Familiar_Familiar;
    }

    public ArrayList<Relacao> selectAll_Relacao_Familiar_Familiar_Do_Familiar(Paciente p, Familiar f) {

        ArrayList<Relacao> relacao_Paciente_Familiar = new ArrayList<Relacao>();

        try {

            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.selectAll_Relacao_Familiar_Familiar_Do_Familiar);
            preparedStatement.setInt(1, f.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            

            while (resultSet.next()) {
                Familiar familiar = select_FamiliarId(resultSet.getInt("Familiar_idFamiliar1"));
                
                relacao_Paciente_Familiar.add(new Relacao(resultSet.getInt("idRelacao_Familiar_Familiar"),
                        p, f, familiar,
                        TipoRelacao.valueOf(resultSet.getString("tipo_relacao"))));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("A base de dados não tem pacientes");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return relacao_Paciente_Familiar;
    }

    public int delete_Relacao_Familiar_Familiar(Familiar familiar) {
        int row = 0;
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.delete_Relacao_Paciente_Familiar);
            preparedStatement.setInt(1, familiar.getId());
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu apagar a relação familiar ");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    //EVENTO:
    public int insert_Evento(Evento e) {
        int row = 0;
        if (e.getFamiliar() != null) {
            try {
                preparedStatement = (PreparedStatement) connection
                        .prepareStatement(Queries.insert_Evento_Com_Familiar);
                preparedStatement.setInt(1, e.getId());
                preparedStatement.setDate(2, new java.sql.Date(e.getData().getTime()));
                preparedStatement.setString(3, e.getTipo_de_evento());
                preparedStatement.setInt(4, e.getLocal_evento().getId());
                preparedStatement.setString(5, e.getDescricao());
                preparedStatement.setInt(6, e.getPaciente().getId());
                preparedStatement.setInt(7, e.getFamiliar().getId());

                row = preparedStatement.executeUpdate();
                System.out.println("Fiz os inserts do evento com familiar");
            } catch (SQLException g) {
                g.printStackTrace();
                System.out.println("Não conseguiu inserir o evento com familiar");
            }
        } else {
            try {

                preparedStatement = (PreparedStatement) connection
                        .prepareStatement(Queries.insert_Evento_Sem_Familiar);
                preparedStatement.setInt(1, e.getId());
                preparedStatement.setDate(2, new java.sql.Date(e.getData().getTime()));
                preparedStatement.setString(3, e.getTipo_de_evento());
                preparedStatement.setInt(4, e.getLocal_evento().getId());
                preparedStatement.setString(5, e.getDescricao());
                preparedStatement.setInt(6, e.getPaciente().getId());

                row = preparedStatement.executeUpdate();
                System.out.println("Fiz os inserts do evento sem familiar");
            } catch (SQLException g) {
                g.printStackTrace();
                System.out.println("Não conseguiu inserir o evento sem familiar");
            }
        }
        return row;
    }

    public int verificaId_Evento() throws SQLException {
        preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.ultimoId_Evento);
        ResultSet resultSet = preparedStatement.executeQuery();
        int lastInsertedId = 0;

        if (resultSet != null) {
            if (resultSet.next()) {
                lastInsertedId = resultSet.getInt(1);
            }
            resultSet.close();
        }
        return lastInsertedId;
    }

    public Evento select_EventoId(int id) {
        Evento evento = null;
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.select_EventoId);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Paciente paciente = select_PacienteId(resultSet.getInt("Paciente_idPaciente"));
                Familiar familiar = select_FamiliarId(resultSet.getInt("Familiar_idFamiliar"));
                Morada morada = select_MoradaId(resultSet.getInt("Local_Evento_idMorada"));

                if (familiar != null) {
                    evento = new Evento(resultSet.getInt("idEvento"), resultSet.getDate("Data"), resultSet.getString("tipo_de_evento"),
                            morada, resultSet.getString("descricao"), paciente, familiar);
                } else {
                    evento = new Evento(resultSet.getInt("idEvento"), resultSet.getDate("Data"), resultSet.getString("tipo_de_evento"),
                            morada, resultSet.getString("descricao"), paciente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("O evento com id " + id + " não existe na BD");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return evento;
    }

    public ArrayList<Evento> selectAll_Evento(Paciente p) {
        ArrayList<Evento> eventos = new ArrayList<Evento>();
        int idPaciente = p.getId();
        try {

            preparedStatement = (PreparedStatement) connection
                    .prepareStatement(Queries.selectAll_Evento);
            preparedStatement.setInt(1, idPaciente);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Familiar familiar = select_FamiliarId(resultSet.getInt("Familiar_idPessoa"));
                System.out.println("encontrou familiar? " + familiar != null);
                Morada morada = select_MoradaId(resultSet.getInt("Local_Evento_idMorada"));

                if (familiar != null) {
                    eventos.add(new Evento(resultSet.getInt("idEvento"), resultSet.getDate("Data"),
                            resultSet.getString("tipo_de_evento"), morada, resultSet.getString("descricao"),
                            p, familiar));
                } else {
                    eventos.add(new Evento(resultSet.getInt("idEvento"), resultSet.getDate("Data"),
                            resultSet.getString("tipo_de_evento"), morada, resultSet.getString("descircao"),
                            p));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("A base de dados não tem relações");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return eventos;
    }

    public int update_Evento(Evento e) {
        int row = 0;
        if (e.getFamiliar() != null) {
            try {
                preparedStatement = (PreparedStatement) connection
                        .prepareStatement(Queries.update_Evento_Com_Familiar);
                preparedStatement.setDate(1, new java.sql.Date(e.getData().getTime()));
                preparedStatement.setString(2, e.getTipo_de_evento());
                preparedStatement.setInt(3, e.getLocal_evento().getId());
                preparedStatement.setString(4, e.getDescricao());
                preparedStatement.setInt(5, e.getFamiliar().getId());
                preparedStatement.setInt(6, e.getId());

                row = preparedStatement.executeUpdate();
                System.out.println("Fiz os updates do evento com familiar");
            } catch (SQLException g) {
                g.printStackTrace();
                System.out.println("Nao conseguiu alterar o evento com familiar");
            }
        } else {
            try {

                preparedStatement = (PreparedStatement) connection
                        .prepareStatement(Queries.update_Evento_Sem_Familiar);
                preparedStatement.setDate(1, new java.sql.Date(e.getData().getTime()));
                preparedStatement.setString(2, e.getTipo_de_evento());
                preparedStatement.setInt(3, e.getLocal_evento().getId());
                preparedStatement.setString(4, e.getDescricao());
                preparedStatement.setInt(5, e.getId());

                row = preparedStatement.executeUpdate();
                System.out.println("Fiz os updates do evento sem familiar");
            } catch (SQLException g) {
                g.printStackTrace();
                System.out.println("Nao conseguiu alterar o evento sem familiar");
            }
        }
        return row;
    }

    public int delete_Evento(Evento evento) {
        int row = 0;
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(Queries.delete_Evento);
            preparedStatement.setInt(1, evento.getId());
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Não conseguiu apagar o evento " + evento);
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }
}
