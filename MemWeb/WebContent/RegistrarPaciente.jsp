<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="Styles/Registo.css">
        <title>MEM+</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>

        <header>
            <img id="logo" src="images/mem+.png"
                 onclick="window.location.href = 'LoggedPage.jsp'" />

            <div id="containerLogout">
                <h2>Olá : <%= session.getAttribute("nome_utilizador")%></h2>
                <form action="./Inicial?accao=logout" method="post">
                    <div id="lower">
                        <input type="submit" name="logout" value="Logout" />
                    </div>
                </form>
            </div>

        </header>
        <div id="page">
            <div id="menu">
                <div class="line"></div>
                <ul>
                    <li><a href="./LoggedPage.jsp">Home</a></li>
                    <li><a href="./ServletInicial?accao=verPacientes">Ver Pacientes</a></li>
                    <li><a class="selected" href="./RegistrarPaciente.jsp">Registrar Novo Paciente</a></li>
                    <li><a href="./ServletInicial?accao=evolucaoPacientes">Evolução dos Pacientes</a></li>
                </ul>
            </div>
            <div id="container">
                <form action="Inicial">
                    <div id="lower">
                        <input type="submit" name="voltarInicio" value="voltar ao Inicio">
                    </div>
                </form>

                <form id="RegistrarPaciente" action="RegistrarPaciente">
                    <header id="header" class="info">
                        <h2>Dados do Paciente</h2>
                    </header>

                    <label for="nome">Nome Completo:</label> 
                    <input type="text" id="nome" name="nome" size="40px"> 
                    <br> 

                    <label for="data_nascimento">Data de Nascimento: (aaaa-mm-dd)</label> 
                    <input type="text" id="data_nascimento" name="data_nascimento" size="40px">
                    <br> 

                    <label for="local_nascimento">Local de Nascimento:</label> 
                    <label for="pais">País:</label> 
                    <input type="text" id="pais_nascimento" name="pais_nascimento" size="40px"> 
                    <br>
                    <label for="regiao">Região: </label> 
                    <input type="text" id="regiao_nascimento" name="regiao_nascimento" size="40px">
                    <br> 
                    <label for="cidade">Cidade: </label> 
                    <input type="text" id="cidade_nascimento" name="cidade_nascimento" size="40px">
                    <br> 

                    <label for="morada_atual">Morada Atual: </label> 
                    <label  for="pais">País:</label> 
                    <input type="text" id="pais_atual" name="pais_atual" size="40px"> 
                    <br> 
                    <label for="regiao">Região:</label> 
                    <input type="text" id="regiao_atual" name="regiao_atual" size="40px">
                    <br> 
                    <label for="cidade">Cidade:</label> 
                    <input type="text" id="cidade_atual" name="cidade_atual" size="40px">
                    <br> 

                    <label for="genero">Género</label> 
                    <select name="genero">
                        <option value="Masculino">Masculino</option>
                        <option value="Feminino">Feminino</option>
                    </select> 
                    <br> 

                    <label for="profissao">Profissão:</label> 
                    <input type="text" id="profissao" name="profissao" size="40px"> 
                    <br>

                    <label for="escolaridade">Escolaridade:</label> 
                    <select name="escolaridade">
                        <option value="Ensino_Basico">Ensino Básico</option>
                        <option value="Ensino_Secundario">Ensino Secundário</option>
                        <option value="Licenciatura">Licenciatura</option>
                        <option value="Pos_Graduacao">Pós-Graduação</option>
                        <option value="Mestrado">Mestrado</option>
                        <option value="Douturamento">Douturamento</option>
                    </select> 
                    <br> 

                    <label for="estado_civil">Estado Civil:</label> 
                    <select name="estado_civil">
                        <option value="Solteiro">Solteiro</option>
                        <option value="Casado">Casado</option>
                        <option value="Divorciado">Divorciado</option>
                        <option value="Viuvo">Viúvo</option>
                    </select> 
                    <br> 
                    
                    <label for="interesses">Interesses:</label>
                    <input type="text" id="interesse" name="interesse" size="40px"> 
                    <br>
                    
                    <label for="atividades">Atividades</label>
                    <input type="text" id="atividade" name="atividade" size="40px"> 
                    <br>
                    
                    <label for="nivel_doenca">Nível de Doença:</label> 
                    <select name="nivel_doenca">
                        <option value="leve">Leve</option>
                        <option value="moderado">Moderado</option>
                        <option value="avancado">Avançado</option>
                       
                    </select> 
                    <br>

                    <label for="fotografia">Insira uma fotografia:</label>
                    <input type="file" name="pic" accept="image/*">
                    <br>

                    <label for="nivel_sessao">Nível de Sessão Inicial Esperado:</label> 
                    <select name="nivel_sessao">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select> 
                    <br>

                    <label for="nome_medico">Nome do Médico:</label> 
                    <input type="text" id="nome_medico" name="nome_medico" size="40px">
                    <br> 

                    <label for="especialidade_medico">Especialidade do Médico:</label> 
                    <select name="especialidade_medico">
                        <option value="Neurologista">Neurologista</option>
                        <option value="Psiquiatra">Psiquiatra</option>
                    </select> 
                    <br> 

                    <div id="lower">
                        <input type="submit" value="Registar">
                    </div>
                </form>
                <!-- <form action="${pageContext.request.contextPath}/Home.jsp"> -->

            </div>
        </div>

        <footer>
            <p>Copyright 2014 - MEM +</p>
        </footer>
    </body>
</html>