package baseDados;

public class Queries {

    //FOTOS
    public static final String insert_Imagem = "insert into `memdb`.`Imagem`"
            + " values(?,?,?);";

    public static final String ultimoId_Imagem = "select `idImagem` from "
            + "`memdb`.`Imagem` order by `idImagem` desc limit 1;";

    // PACIENTE
    public static final String insert_Paciente = "insert into "
            + "`memdb`.`paciente` values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    //public static final String insert_Paciente_2Morada = "insert into 
    //+ `memdb`.`paciente` (`Morada_idMorada`) values (?) where 
    //`paciente`.`idPaciente`= ?;";
    public static final String ultimoId_Paciente = "select `idPaciente` from "
            + "`memdb`.`paciente` order by `idPaciente` desc limit 1;";

    public static final String selectAll_PacientesComMorada = "select * from "
            + "`memdb`.`paciente`, `memdb`.`morada` where "
            + "`paciente`.`LocalNascimento_idMorada` = `morada`.`idMorada` and "
            + "`paciente`.`Tecnico_idTecnico` = ?;";

    public static final String select_PacienteId = "select * from "
            + "`memdb`.`paciente` where `paciente`.`idPaciente`=?;";

    public static final String delete_PacienteNome = "delete from "
            + "`memdb`.`paciente` where `idPaciente` = ?;";

    public static final String delete_Paciente = "delete from "
            + "`memdb`.`paciente` where nomeCompleto = ?;";

    public static final String selectAll_Pacientes = "select * from "
            + "`memdb`.`paciente`;";

    public static final String select_PacienteNome = "select * from "
            + "`memdb`.`paciente` WHERE nomeCompleto = ?;";

    public static final String update_Paciente = "update `memdb`.`paciente` "
            + "set nome_completo = ?, nome_proprio = ?, apelido = ?, "
            + "data_de_nascimento = ?, LocalNascimento_idMorada = ?, "
            + "Morada_idMorada = ?, genero = ?, profissao = ?, "
            + "escolaridade = ?,estado_civil = ?, nivel_de_doenca = ?, "
            + "nome_medico = ?, especialidade_medico = ?, nivel_sessao = ?  "
            + "WHERE idPaciente = ?;";

    //MORADA
    public static final String insert_Morada = "insert into `memdb`.`morada` "
            + "values(?,?,?,?);";

    public static final String ultimoId_Morada = "select `idMorada` from "
            + "`memdb`.`morada` order by `idMorada` desc limit 1;";

    public static final String select_MoradaId = "select * "
            + "from `memdb`.`morada` where `morada`.`idMorada`=?;";

    public static final String selectAll_Moradas = "select * "
            + "from `memdb`.`morada`;";

    public static final String delete_Morada = "delete from `memdb`.`morada` "
            + "where `idMorada` = ?;";

    public static final String selectAll_IdsMorada = "select `Morada_idMorada`"
            + "from `memdb`.`paciente`;";

    public static final String update_Morada = "update `memdb`.`morada` set "
            + "pais = ?, regiao = ?, cidade = ?  WHERE idMorada = ?;";

    //Familiar:
    public static final String insert_Familiar = "insert into "
            + "`memdb`.`familiar` values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    public static final String ultimoId_Familiar = "select `idFamiliar` from "
            + "`memdb`.`familiar` order by `idFamiliar` desc limit 1;";

    public static final String selectAll_Familiares = "select * from "
            + "`memdb`.`familiar`, `memdb`.`relacao_paciente_familiar` where "
            + "`relacao_paciente_familiar`.`Familiar_idFamiliar` = "
            + "`familiar`.`idFamiliar`and "
            + "`relacao_paciente_familiar`.`Paciente_idPaciente` = ?;";

    public static final String select_FamiliarId = "select * from "
            + "`memdb`.`familiar` where `familiar`.`idFamiliar`=?;";

    public static final String select_FamiliarNomeUtilizador = "select * from "
            + "`memdb`.`familiar` where `familiar`.`nome_utilizador`=?;";

    public static final String delete_Familiar = "delete from "
            + "`memdb`.`familiar` where `idFamiliar` = ?;";

    public static final String update_Familiar = "update `memdb`.`familiar` set"
            + " nome_completo = ?, nome_proprio = ?, apelido = ?, "
            + "data_de_nascimento = ?, LocalNascimento_idMorada = ?, "
            + "Morada_idMorada = ?, genero = ?, estado_civil = ?, "
            + "profissao = ?, e_cuidador = ?, nome_utilizador = ?, "
            + "telefone = ?, password = ? data_de_obito = ?  "
            + "WHERE idFamiliar = ?;";

    //TECNICO
    public static final String insert_Tecnico = "insert into `memdb`.`tecnico` "
            + "values(?,?,?,?,?,?,?);";

    public static final String ultimoId_Tecnico = "select `idTecnico` from "
            + "`memdb`.`tecnico` order by `idTecnico` desc limit 1;";

    public static final String select_TecnicoEmail = "select * from "
            + "`memdb`.`tecnico` where `tecnico`.`email`=?;";

    public static final String select_TecnicoNome_Utilizador = "select * from "
            + "`memdb`.`tecnico` where `tecnico`.`nome_utilizador`=?;";

    public static final String select_TecnicoId = "select * from "
            + "`memdb`.`tecnico` where `tecnico`.`idTecnico`=?;";

    public static final String selectAll_Tecnicos = "select * from "
            + "`memdb`.`tecnico`;";

    public static final String delete_Tecnico = "delete from `memdb`.`tecnico` "
            + "where `idTecnico` = ?;";

    public static final String update_Tecnico = "update `memdb`.`tecnico` set"
            + " nome_completo = ?, nome_proprio = ?, apelido = ?,"
            + " nome_utilizador = ?, password = ? email = ? "
            + " WHERE idTecnico = ?;";

    //RELACAO_PACIENTE_FAMILIAR
    public static final String insert_Relacao_Paciente_Familiar = "insert into"
            + " `memdb`.`relacao_paciente_familiar` values(?,?,?,?);";

    public static final String ultimoId_Relacao_Paciente_Familiar = "select"
            + " `idRelacao_Paciente_Familiar` from "
            + "`memdb`.`relacao_paciente_familiar` order by "
            + "`idRelacao_Paciente_Familiar` desc limit 1;";

    public static final String select_Relacao_Paciente_FamiliarId = "select * "
            + "from `memdb`.`relacao_paciente_familiar` where "
            + "`relacao_paciente_familiar`.`idRelacao_Paciente_Familiar` = ? ;";

    public static final String select_Relacao_Paciente_Familiar = "select * "
            + "from `memdb`.`relacao_paciente_familiar` where "
            + " `relacao_paciente_familiar`.`Paciente_idPaciente` = ? and"
            + " `relacao_paciente_familiar`.`Familiar_idFamiliar` = ? ;";

    public static final String selectAll_Relacao_Paciente_Familiar = "select *"
            + " from `memdb`.`relacao_paciente_familiar`"
            + " where `relacao_paciente_familiar`.`Paciente_idPaciente`"
            + " = ? ;";

    public static final String delete_Relacao_Paciente_Familiar = "delete from "
            + "`memdb`.`relacao_paciente_familiar` where `idFamiliar` = ?;";

    public static final String update_Relacao_Paciente_Familiar = "update "
            + "`memdb`.`relacao_paciente_familiar` set Familiar_idFamiliar = ?,"
            + " Paciente_idPaciente = ?, tipo_relacao = ? "
            + "WHERE idRelacao_Paciente_Familiar = ?;";

    //RELACAO_FAMILIAR_FAMILIAR
    public static final String insert_Relacao_Familiar_Familiar = "insert into"
            + " `memdb`.`Relacao_Familiar_Familiar` values(?,?,?,?,?);";

    public static final String ultimoId_Relacao_Familiar_Familiar = "select"
            + " `idRelacao_Familiar_Familiar` from "
            + "`memdb`.`Relacao_Familiar_Familiar` order "
            + "by `idRelacao_Familiar_Familiar` desc limit 1;";

    public static final String select_Relacao_Familiar_FamiliarId = "select *"
            + " from `memdb`.`Relacao_Familiar_Familiar` where"
            + " `Relacao_Familiar_Familiar`.`idRelacaoFamiliar_Familiar` = ? ;";

    public static final String selectAll_Relacao_Familiar_Familiar = "select *"
            + " from `memdb`.`Relacao_Familiar_Familiar`;";

    public static final String selectAll_Relacao_Familiar_Familiar_Do_Familiar
            = "select * from `memdb`.`relacao_familiar_familiar` where"
            + " `Familiar_idFamiliar`=?;";

    public static final String delete_Relacao_Familiar_Familiar = "delete from"
            + " `memdb`.`Relacao_Familiar_Familiar` where "
            + "`Familiar_idFamiliar` = ? or `Familiar_idFamiliar1 = ?;";

    public static final String update_Relacao_Familiar_Familiar = "update"
            + " `memdb`.`Relacao_Familiar_Familiar` set "
            + "Familiar_idFamiliar = ?, Familiar_idFamiliar1 = ?,"
            + " Paciente_idPaciente = ?, tipo_relacao = ? "
            + "WHERE idRelacao_Familiar_Familiar = ?;";

    //EVENTO:
    public static final String insert_Evento_Sem_Familiar = "insert into"
            + " `memdb`.`Evento` values(?,?,?,?,?,?);";

    public static final String insert_Evento_Com_Familiar = "insert into"
            + " `memdb`.`Evento` values(?,?,?,?,?,?,?);";

    public static final String ultimoId_Evento = "select `idEvento` from"
            + " `memdb`.`Evento` order by `idEvento` desc limit 1;";

    public static final String select_EventoId = "select * from"
            + " `memdb`.`Evento` where `Evento`.`idEvento` = ?;";

    public static final String selectAll_Evento = "select * from "
            + "`memdb`.`Evento` where `Evento`.`Paciente_idPaciente`=?;";

    public static final String delete_Evento = "delete from `memdb`.`Evento` "
            + "where `Evento`.`idEvento`= ?;";

    public static final String update_Evento_Com_Familiar = "update"
            + " `memdb`.`Evento` set Data = ?, tipo_de_evento = ?, "
            + "Local_Evento_idMorada = ?, descricao = ?, "
            + "Familiar_idFamiliar = ? WHERE idEvento = ?;";

    public static final String update_Evento_Sem_Familiar = "update"
            + " `memdb`.`Evento` set Data = ?, tipo_de_evento = ?,"
            + " Local_Evento_idMorada = ?, descricao = ?,"
            + " WHERE idEvento = ?;";
}
