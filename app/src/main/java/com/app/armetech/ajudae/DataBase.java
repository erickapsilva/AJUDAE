package com.app.armetech.ajudae;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 07/12/2017.
 */

public class DataBase extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "dbajudae";

    //PESSOA
    public static final String TABELA_PESSOA = "pessoa";
    public static final String PESSOA_ID = "pessoa_id";
    public static final String PESSOA_USER_ID = "usuario_id";
    public static final String PESSOA_NOME = "nome";
    public static final String PESSOA_GENERO = "genero";
    public static final String PESSOA_DATANASC = "data_nasc";
    public static final String PESSOA_CEP = "cep";

    //USUÁRIO
    public static final String TABELA_USUARIO = "usuario";
    public static final String USUARIO_ID = "user_id";
    public static final String USUARIO_EMAIL = "email";
    public static final String USUARIO_SENHA = "senha";
    public static final String USUARIO_TOKEN = "token";
    public static final String USUARIO_CURSO = "curso";

    //SESSAO DO USUARIO
    public static final String TABELA_SESSAO = "sessao";
    public static final String ID_LOGADO= "usuario_id";

    //CURSO
    public static final String TABELA_CURSO = "curso";
    public static final String CURSO_ID = "curso_id";
    public static final String CURSO_NOME = "nome";

    //CADEIRAS
    public static final String TABELA_CADEIRA = "cadeira";
    public static final String CADEIRA_ID = "cadeira_id";
    public static final String CADEIRA_NOME = "nome";
    public static final String CADEIRA_DEPT = "dept";

    //PRÉDIOS
    public static final String TABELA_PREDIO = "predio";
    public static final String PREDIO_ID = "predio_id";
    public static final String PREDIO_NOME = "nome";
    public static final String PREDIO_LAT = "lat";
    public static final String PREDIO_LONG = "long";

    //TURMAS
    public static final String TABELA_TURMA = "turma";
    public static final String TURMA_ID = "turma_id";
    public static final String TURMA_NOME = "nome";
    public static final String TURMA_LOCAL = "local";
    public static final String TURMA_CURSO = "curso";
    public static final String TURMA_CADEIRA = "cadeira";

    //HORARIOS
    public static final String TABELA_HORARIO = "horario";
    public static final String HORARIO_ID = "horario_id";
    public static final String HORARIO_DIA = "dia";
    public static final String HORARIO_INICIO = "inicio";
    public static final String HORARIO_FIM = "fim";

    // AULA
    public static final String TABELA_AULA = "aula";
    public static final String AULA_ID = "aula_id";
    public static final String AULA_TURMA = "turma";
    public static final String AULA_HORARIO = "horario";


    public DataBase(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLite){
        sqLite.execSQL("CREATE TABLE " + TABELA_USUARIO + " ("+
                USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                USUARIO_EMAIL + " TEXT NOT NULL, " +
                USUARIO_SENHA + " TEXT NOT NULL, " +
                USUARIO_TOKEN + " TEXT NOT NULL, " +
                USUARIO_CURSO + " TEXT NOT NULL);");

        sqLite.execSQL("CREATE TABLE " + TABELA_PESSOA + " ("+
                PESSOA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                PESSOA_USER_ID + " INTEGER, " +
                PESSOA_NOME + " TEXT NOT NULL, " +
                PESSOA_GENERO + " TEXT NOT NULL, " +
                PESSOA_CEP + " TEXT NOT NULL, " +
                PESSOA_DATANASC + " TEXT NOT NULL);");

        sqLite.execSQL("CREATE TABLE " + TABELA_SESSAO + " (" +
                ID_LOGADO + " INTEGER);");

        sqLite.execSQL("CREATE TABLE " + TABELA_CURSO + " (" +
                CURSO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CURSO_NOME + " TEXT NOT NULL);");

        sqLite.execSQL("CREATE TABLE " + TABELA_CADEIRA + " (" +
                CADEIRA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CADEIRA_NOME + " TEXT NOT NULL, " +
                CADEIRA_DEPT + " TEXT NOT NULL);");

        sqLite.execSQL("CREATE TABLE " + TABELA_PREDIO + " (" +
                PREDIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                PREDIO_NOME + " TEXT NOT NULL, " +
                PREDIO_LAT + " TEXT NOT NULL, " +
                PREDIO_LONG + " TEXT NOT NULL);");

        sqLite.execSQL("CREATE TABLE " + TABELA_TURMA + " (" +
                TURMA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TURMA_NOME + " TEXT NOT NULL, " +
                TURMA_CURSO + " INTEGER, " +
                TURMA_LOCAL + " INTEGER, " +
                TURMA_CADEIRA + " INTEGER);");

        sqLite.execSQL("CREATE TABLE " + TABELA_HORARIO + " (" +
                HORARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HORARIO_DIA + " TEXT NOT NULL, " +
                HORARIO_INICIO + " TEXT NOT NULL, " +
                HORARIO_FIM + " TEXT NOT NULL);");

        sqLite.execSQL("CREATE TABLE " + TABELA_AULA + " (" +
                AULA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AULA_TURMA + " INTEGER, " +
                AULA_HORARIO + " INTEGER);");

        String addpessoa = "INSERT INTO " + TABELA_PESSOA + " (" + PESSOA_USER_ID + ", " + PESSOA_NOME + ", " +
                PESSOA_GENERO + ", " + PESSOA_DATANASC + ", " + PESSOA_CEP + ") values";
        //sqLite.execSQL(addpessoa + " ('1', 'antonio', 'M', '13/06/1996', '54090-470');");
        //sqLite.execSQL(addpessoa+ " ('2', 'soares', 'M', '08/02/1996', '54110-112');");

        String addusuario = "INSERT INTO " + TABELA_USUARIO + " (" + USUARIO_EMAIL + ", " + USUARIO_SENHA + ", " +
                USUARIO_TOKEN + ", " + USUARIO_CURSO + ") values";
        //sqLite.execSQL(addusuario+" ('soares@gmail.com', '123456', '123456', 'BSI');");
        //sqLite.execSQL(addusuario+" ('antonio@gmail.com', '112233', '123455', 'BSI');");




        String addcurso = "INSERT INTO " + TABELA_CURSO + " (" + CURSO_NOME+ ") values";
        sqLite.execSQL(addcurso+" ('Bacharelado em Sistemas da Informação');");
        sqLite.execSQL(addcurso+" ('Administração');");
        sqLite.execSQL(addcurso+" ('Agronomia');");
        sqLite.execSQL(addcurso+" ('Bacharelado em Ciência da Computação');");
        sqLite.execSQL(addcurso+" ('Bacharelado em Ciências Biológicas');");
        sqLite.execSQL(addcurso+" ('Bacharelado em Ciências do Consumo');");
        sqLite.execSQL(addcurso+" ('Bacharelado em Ciências Econômicas');");
        sqLite.execSQL(addcurso+" ('Bacharelado em Ciências Sociais');");
        sqLite.execSQL(addcurso+" ('Bacharelado em Gastronomia');");
        sqLite.execSQL(addcurso+" ('Economia Doméstica');");
        sqLite.execSQL(addcurso+" ('Engenharia Agrícola e Ambiental');");
        sqLite.execSQL(addcurso+" ('Engenharia Civil');");
        sqLite.execSQL(addcurso+" ('Engenharia de Alimentos');");
        sqLite.execSQL(addcurso+" ('Engenharia de Materiais');");
        sqLite.execSQL(addcurso+" ('Engenharia de Pesca');");
        sqLite.execSQL(addcurso+" ('Engenharia Elétrica');");
        sqLite.execSQL(addcurso+" ('Engenharia Eletrônica');");
        sqLite.execSQL(addcurso+" ('Engenharia Florestal');");
        sqLite.execSQL(addcurso+" ('Engenharia Mecânica');");
        sqLite.execSQL(addcurso+" ('Licenciatura em Ciências Agrícolas');");
        sqLite.execSQL(addcurso+" ('Licenciatura em Computação');");
        sqLite.execSQL(addcurso+" ('Licenciatura em Educação Física');");
        sqLite.execSQL(addcurso+" ('Licenciatura em Física');");
        sqLite.execSQL(addcurso+" ('Licenciatura em História');");
        sqLite.execSQL(addcurso+" ('Licenciatura em Letras (Espanhol)');");
        sqLite.execSQL(addcurso+" ('Licenciatura em Letras (Inglês)');");
        sqLite.execSQL(addcurso+" ('Licenciatura em Matématica');");
        sqLite.execSQL(addcurso+" ('Licenciatura em Pedagogia');");
        sqLite.execSQL(addcurso+" ('Licenciatura em Química');");
        sqLite.execSQL(addcurso+" ('Licenciatura em Ciências Biológicas');");
        sqLite.execSQL(addcurso+" ('Medicina Veterinária');");
        sqLite.execSQL(addcurso+" ('Zootecnia');");


        String addcadeira = "INSERT INTO " + TABELA_CADEIRA + " (" +  CADEIRA_NOME+ ", " + CADEIRA_DEPT + ") values";
        sqLite.execSQL(addcadeira+" ('Cálculo a Uma Variável','DM');");
        sqLite.execSQL(addcadeira+" ('Matemática Discreta', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Introdução a Programação','DEINFO');");
        sqLite.execSQL(addcadeira+" ('Teoria Geral da Administração', 'DADM');");
        sqLite.execSQL(addcadeira+" ('Laboratório de Informática', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Introdução a Teoria da Computação', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Cálculo a Várias Variáveis', 'DM');");
        sqLite.execSQL(addcadeira+" ('Algoritmo e Estrutura de Dados', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Fundamentos de Sistemas de Informação', 'DADM');");
        sqLite.execSQL(addcadeira+" ('Laboratório de Programação', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Física para Computação', 'DF');");
        sqLite.execSQL(addcadeira+" ('Álgebra Vetorial e Linear Para Computação', 'DM');");
        sqLite.execSQL(addcadeira+" ('Fundamentos de Engenharia de Software', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Modelagem e Programação Orientada a Objetos', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Introdução a Economia', 'DECON');");
        sqLite.execSQL(addcadeira+" ('Psicologia Aplicada às Organizações', 'DED');");
        sqLite.execSQL(addcadeira+" ('Estatística e Exploratória I', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Processos de Desenvolvimento de Software', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Fundamentos de Banco de Dados', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Admnistração Financeira', 'DADM');");

        /*sqLite.execSQL(addcadeira+" ('Infraestrutura de Hardware', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Metodologia de Expressão Técnica e Científica', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Gerência de Projetos de Software', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Projeto de Banco de Dados', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Análise e Projeto de Sistemas de Informação', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Redes e Sistemas de Internet', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Projeto de Sistemas Distribuídos', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Empreendedorismo e Legislação', 'DADM');");
        sqLite.execSQL(addcadeira+" ('Interface Homem Máquina', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Sistemas de Apoio a Decisão', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Paradigmas de Programação', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Infraestrutura de Software', 'DEINFO');");
        sqLite.execSQL(addcadeira+" ('Análise Organizacional de Processos', 'DADM');");
        sqLite.execSQL(addcadeira+" ('Aspectos Filosóficos e Sociológicos de Informática','DEINFO');");
        sqLite.execSQL(addcadeira+" ('Fundamentos da Estratégia Competitiva', 'DADM');");
        sqLite.execSQL(addcadeira+" ('Segurança e Auditoria de Sistemas de Informação', 'DEINFO');");

        sqLite.execSQL(addcadeira+" ('Tendências Tecnológicas em TIC');");
        sqLite.execSQL(addcadeira+" ('Data Warehousing & Business Intelligence');");
        sqLite.execSQL(addcadeira+" ('Tópicos Avançados em IA (Computação Evolucionária)');");
        sqLite.execSQL(addcadeira+" ('Introdução à Inteligência Artificial');");
        sqLite.execSQL(addcadeira+" ('Sistemas Colaborativos');");
        sqLite.execSQL(addcadeira+" ('Desenvolvimento de Aplicações para Web');");
        sqLite.execSQL(addcadeira+" ('Tópicos em Engenharia de Software');");
        sqLite.execSQL(addcadeira+" ('Desenvolvimento Distribuído de Software');");
        sqLite.execSQL(addcadeira+" ('Modelagem Computacional');");
        sqLite.execSQL(addcadeira+" ('Intodução à Computação Quântica');");
        sqLite.execSQL(addcadeira+" ('Teoria da Computabilidade');");
        sqLite.execSQL(addcadeira+" ('Algoritmos Numéricos');");
        sqLite.execSQL(addcadeira+" ('Análise de Desempenho');");
        sqLite.execSQL(addcadeira+" ('Análise e Projeto de Algoritmos de Simulação');");
        sqLite.execSQL(addcadeira+" ('Análise e Projetos de Sistemas');"); //nota
        sqLite.execSQL(addcadeira+" ('Arquitetura de Software');");
        sqLite.execSQL(addcadeira+" ('Bioinformática Aplicada');");
        sqLite.execSQL(addcadeira+" ('Biologia Computacional');");
        sqLite.execSQL(addcadeira+" ('Circuitos Digitais');");
        sqLite.execSQL(addcadeira+" ('Computação em Nuvem');");
        sqLite.execSQL(addcadeira+" ('Desenvolvimento de Aplicações Móveis');");
        sqLite.execSQL(addcadeira+" ('Desenvolvimento Ágil de Software');");
        sqLite.execSQL(addcadeira+" ('Educação das Relações Étnico-Raciais');");
        sqLite.execSQL(addcadeira+" ('Elementos de Epidemiologia Computacional');");
        sqLite.execSQL(addcadeira+" ('Engenharia de Software Orientada a Modelos');");
        sqLite.execSQL(addcadeira+" ('Equações Diferenciais Ordinárias');");
        sqLite.execSQL(addcadeira+" ('Equações Diferenciais Parciais');");
        sqLite.execSQL(addcadeira+" ('Fundamentação Matemática para Teoria da Computabilidade');");
        sqLite.execSQL(addcadeira+" ('Fundamentos de Autômatos Celulares');");
        sqLite.execSQL(addcadeira+" ('Fundamentos de Computação Quântica');"); //nota
        sqLite.execSQL(addcadeira+" ('Fundamentos de Criptografia');");
        sqLite.execSQL(addcadeira+" ('Fábricas de Software');");
        sqLite.execSQL(addcadeira+" ('Heurísticas para Solução de Algoritmos NP-Completos');");
        sqLite.execSQL(addcadeira+" ('Informática na Educação');");
        sqLite.execSQL(addcadeira+" ('Inovação em Projetos de Software');");
        sqLite.execSQL(addcadeira+" ('Inovação em TIC');"); //nota
        sqLite.execSQL(addcadeira+" ('Inteligência Artificial Aplicada');");
        sqLite.execSQL(addcadeira+" ('Jogos Digitais');");
        sqLite.execSQL(addcadeira+" ('Laboratório em Modelagem');");
        sqLite.execSQL(addcadeira+" ('Língua Brasileira de Sinais - LIBRAS');");
        sqLite.execSQL(addcadeira+" ('Metodologia Ágeis de Desenvolvimento de Software');");
        sqLite.execSQL(addcadeira+" ('Modelagem em Inteligência Artificial');");
        sqLite.execSQL(addcadeira+" ('Modelagem Matemático-Computacional Aplicada à Epidemiologia');");
        sqLite.execSQL(addcadeira+" ('Modelos de Qualidade');");
        sqLite.execSQL(addcadeira+" ('Modelos em Redes');");
        sqLite.execSQL(addcadeira+" ('Modelos Não Convencionais de Computação');");
        sqLite.execSQL(addcadeira+" ('Processamento de Imagens');");
        sqLite.execSQL(addcadeira+" ('Programação de Sistemas Interativos');");
        sqLite.execSQL(addcadeira+" ('Programação Linear');");
        sqLite.execSQL(addcadeira+" ('Programação Paralela e Distribuída');");
        sqLite.execSQL(addcadeira+" ('Projeto de Sistemas de Informação');");
        sqLite.execSQL(addcadeira+" ('Projeto de Sistemas Educacionais');");
        sqLite.execSQL(addcadeira+" ('Projeto de Sistemas Web');");
        sqLite.execSQL(addcadeira+" ('Reconhecimento de Padrões');");
        sqLite.execSQL(addcadeira+" ('Redes de Computadores Sem Fio');");
        sqLite.execSQL(addcadeira+" ('Redes Neurais');");
        sqLite.execSQL(addcadeira+" ('Semântica de Linguagens de Programação');");
        sqLite.execSQL(addcadeira+" ('Sistemas Colaborativos');");
        sqLite.execSQL(addcadeira+" ('Sisteas Inteligentes');");
        sqLite.execSQL(addcadeira+" ('Teoria dos Grafos e Matróide');");
        sqLite.execSQL(addcadeira+" ('Teste de Software');");
        sqLite.execSQL(addcadeira+" ('Tópicos Avançados em Redes de Computadores I');");
        sqLite.execSQL(addcadeira+" ('Tópicos Avançados em Sistemas Distribuídos I');");
        sqLite.execSQL(addcadeira+" ('Tópicos em Ambientes Computacionais de Alto Desempenho');");
        sqLite.execSQL(addcadeira+" ('Tópicos Avançados em Modelagem Computacional');");
        sqLite.execSQL(addcadeira+" ('Tópicos em Otimização');");
        sqLite.execSQL(addcadeira+" ('Tópicos em Modelagem Computacional');");
        sqLite.execSQL(addcadeira+" ('Tópicos Especiais em Fundamentos Computacionais');");
        sqLite.execSQL(addcadeira+" ('Web Services');");*/


        String addpredio = "INSERT INTO " + TABELA_PREDIO + " (" +  PREDIO_NOME+", "+ PREDIO_LAT + ", " + PREDIO_LONG+ ") values";
        sqLite.execSQL(addpredio+" ('CEAGRI I', '-8.017773', '-34.944100');");
        sqLite.execSQL(addpredio+" ('CEAGRI II', '-8.017497', '-34.944366');");
        sqLite.execSQL(addpredio+" ('CEGOE', '-8.017213', '-34.950011');");
        sqLite.execSQL(addpredio+" ('CEGEN', '-8.015877', '-34.951278');");
        sqLite.execSQL(addpredio+" ('DEINFO', '-8.015877', '-34.951278');");
        sqLite.execSQL(addpredio+" ('DEFIS', '-8.018217', '-34.949120');");
        sqLite.execSQL(addpredio+" ('DZ', '-8.020185', '-34.954006');");
        sqLite.execSQL(addpredio+" ('CB', '-8.013700', '-34.950616');");
        sqLite.execSQL(addpredio+" ('DEPAq', '-8.019440', '-34.943907');");

        String addturma = "INSERT INTO " + TABELA_TURMA + " (" +  TURMA_NOME+", "+ TURMA_CADEIRA + ", " + TURMA_CURSO + ", " + TURMA_LOCAL + ") values";
        sqLite.execSQL(addturma+ " ('SI1', '1', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '2', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '3', '1', '1');");
        sqLite.execSQL(addturma+ " ('SI1', '4', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '5', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '6', '1', '1');");
        sqLite.execSQL(addturma+ " ('SI1', '7', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '8', '1', '1');");
        sqLite.execSQL(addturma+ " ('SI1', '9', '1', '1');");
        sqLite.execSQL(addturma+ " ('SI1', '10', '1', '1');");
        sqLite.execSQL(addturma+ " ('SI1', '11', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '12', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '13', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '14', '1', '1');");
        sqLite.execSQL(addturma+ " ('SI1', '15', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '16', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '17', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '18', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '19', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '20', '1', '2');");
        /*sqLite.execSQL(addturma+ " ('SI1', '21', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '22', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '23', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '24', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '25', '1', '2');");
        sqLite.execSQL(addturma+ " ('SI1', '26', '1', '2');");*/

        String addhorario = "INSERT INTO " + TABELA_HORARIO + " (" + HORARIO_DIA + ", " + HORARIO_INICIO + ", " + HORARIO_FIM + ") values";
        sqLite.execSQL(addhorario+" ('SEGUNDA', '8', '10');") ; //1
        sqLite.execSQL(addhorario+" ('SEGUNDA', '10', '12');"); //2
        sqLite.execSQL(addhorario+" ('TERÇA', '8', '10');");    //3
        sqLite.execSQL(addhorario+" ('TERÇA', '10', '12');");   //4
        sqLite.execSQL(addhorario+" ('QUARTA', '8', '10');");   //5
        sqLite.execSQL(addhorario+" ('QUARTA', '10', '12');");  //6
        sqLite.execSQL(addhorario+" ('QUINTA', '8', '10');");   //7
        sqLite.execSQL(addhorario+" ('QUINTA', '10', '12');");  //8
        sqLite.execSQL(addhorario+" ('SEXTA', '8', '10');");    //9
        sqLite.execSQL(addhorario+" ('SEXTA', '10', '12');");   //10

        String addaula = "INSERT INTO " + TABELA_AULA + " (" + AULA_TURMA + ", " + AULA_HORARIO + ") values";
        //1o PERIODO
        sqLite.execSQL(addaula + " ('1', '2');");
        sqLite.execSQL(addaula + " ('1', '6');");
        sqLite.execSQL(addaula + " ('2', '1');");
        sqLite.execSQL(addaula + " ('2', '5');");
        sqLite.execSQL(addaula + " ('3', '4');");
        sqLite.execSQL(addaula + " ('3', '7');");
        sqLite.execSQL(addaula + " ('3', '9');");
        sqLite.execSQL(addaula + " ('4', '3');");
        sqLite.execSQL(addaula + " ('4', '10');");
        sqLite.execSQL(addaula + " ('5', '8');");
        //2o PERIODO
        sqLite.execSQL(addaula + " ('6', '1');");
        sqLite.execSQL(addaula + " ('6', '5');");
        sqLite.execSQL(addaula + " ('7', '2');");
        sqLite.execSQL(addaula + " ('7', '6');");
        sqLite.execSQL(addaula + " ('8', '4');");
        sqLite.execSQL(addaula + " ('8', '8');");
        sqLite.execSQL(addaula + " ('9', '3');");
        sqLite.execSQL(addaula + " ('9', '10');");
        sqLite.execSQL(addaula + " ('10', '7');");
        sqLite.execSQL(addaula + " ('10', '9');");
        //3o PERIODO
        sqLite.execSQL(addaula + " ('11', '1');");
        sqLite.execSQL(addaula + " ('11', '5');");
        sqLite.execSQL(addaula + " ('12', '2');");
        sqLite.execSQL(addaula + " ('12', '6');");
        sqLite.execSQL(addaula + " ('13', '3');");
        sqLite.execSQL(addaula + " ('13', '4');");
        sqLite.execSQL(addaula + " ('14', '7');");
        sqLite.execSQL(addaula + " ('14', '9');");
        sqLite.execSQL(addaula + " ('15', '8');");
        sqLite.execSQL(addaula + " ('15', '10');");
        //4o PERIODO
        sqLite.execSQL(addaula + " ('16', '1');");
        sqLite.execSQL(addaula + " ('16', '5');");
        sqLite.execSQL(addaula + " ('17', '2');");
        sqLite.execSQL(addaula + " ('17', '6');");
        sqLite.execSQL(addaula + " ('18', '3');");
        sqLite.execSQL(addaula + " ('18', '4');");
        sqLite.execSQL(addaula + " ('19', '7');");
        sqLite.execSQL(addaula + " ('19', '9');");
        sqLite.execSQL(addaula + " ('20', '8');");
        sqLite.execSQL(addaula + " ('20', '10');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




}
