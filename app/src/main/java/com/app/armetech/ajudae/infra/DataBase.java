package com.app.armetech.ajudae.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.armetech.ajudae.aulas.domain.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataBase extends SQLiteOpenHelper {
    private static final String DB_NAME = "dbajudae";
    private static final int VERSION = 1;
    

    //PESSOA
    private static final String PERSON_TABLE = "pessoa";
    private static final String PERSON_ID = "pessoa_id";
    private static final String PERSON_USER_ID = "usuario_id";
    private static final String PERSON_NAME = "nome";
    private static final String PERSON_GENDER = "genero";
    private static final String PERSON_BIRTHDATE = "data_nascimento";
    private static final String PERSON_CEP = "cep";

    public static String getPersonTable() {
        return PERSON_TABLE;
    }
    
    public static String getPersonId() {
        return PERSON_ID;
    }

    public static String getPersonUserId() {
        return PERSON_USER_ID;
    }

    public static String getPersonName() {
        return PERSON_NAME;
    }

    public static String getPersonGender() {
        return PERSON_GENDER;
    }

    public static String getPersonBirthdate() {
        return PERSON_BIRTHDATE;
    }

    public static String getPersonCep() {
        return PERSON_CEP;
    }

    //USUÁRIO
    private static final String USER_TABLE = "usuario";
    private static final String USER_ID = "user_id";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "senha";
    private static final String USER_TOKEN = "token";
    private static final String USER_COURSE = "curso";
    private static final String USER_SUBJECTS = "subjects";

    public static String getUserTable() {
        return USER_TABLE;
    }

    public static String getUserId() {
        return USER_ID;
    }

    public static String getUserEmail() {
        return USER_EMAIL;
    }

    public static String getUserPassword() {
        return USER_PASSWORD;
    }

    public static String getUserToken() {
        return USER_TOKEN;
    }

    public static String getUserCourse() {
        return USER_COURSE;
    }

    public static String getUserSubjects() { return USER_SUBJECTS; }

    //SESSAO DO USUARIO
    private static final String SESSION_TABLE = "sessao";
    private static final String LOGGED_ID = "logado_id";

    public static String getSessionTable() {
        return SESSION_TABLE;
    }

    public static String getLoggedId() {
        return LOGGED_ID;
    }

    //CURSO
    private static final String COURSE_TABLE = "curso";
    private static final String COURSE_ID = "curso_id";
    private static final String COURSE_NAME = "nome";

    //CADEIRAS
    private static final String SUBJECT_TABLE = "cadeira";
    private static final String SUBJECT_ID = "cadeira_id";
    private static final String SUBJECT_NAME = "nome";
    private static final String SUBJECT_DEPT = "dept";

    public static String getSubjectTable(){
        return SUBJECT_TABLE;
    }
    public static String getSubjectName(){
        return SUBJECT_NAME;
    }
    public static String getSubjectId(){
        return SUBJECT_ID;
    }
    public static String getSubjectDept(){
        return SUBJECT_DEPT;
    }

    //PRÉDIOS
    private static final String BUILDING_TABLE = "predio";
    private static final String BUILDING_ID = "predio_id";
    private static final String BUILDING_NAME = "nome";
    private static final String BUILDING_LAT = "lat";
    private static final String BUILDING_LONG = "long";

    //TURMAS
    private static final String COURSE_CLASS_TABLE = "turma";
    private static final String COURSE_CLASS_ID = "turma_id";
    private static final String COURSE_CLASS_NAME = "nome";
    private static final String COURSE_CLASS_LOCAL = "local";
    private static final String COURSE_CLASS_COURSE = "curso";
    private static final String COURSE_CLASS_SUBJECT = "cadeira";

    //HORARIOS
    private static final String TIME_TABLE = "horario";
    private static final String TIME_ID = "horario_id";
    private static final String TIME_DAY = "dia";
    private static final String TIME_START = "inicio";
    private static final String TIME_END = "fim";

    // AULA
    private static final String CLASS_TABLE = "aula";
    private static final String CLASS_ID = "aula_id";
    private static final String CLASS_COURSE_CLASS = "turma";
    private static final String CLASS_TIME = "horario";


    public DataBase(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLite){
        sqLite.execSQL("CREATE TABLE " + USER_TABLE + " ("+
                USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                USER_EMAIL + " TEXT NOT NULL, " +
                USER_PASSWORD + " TEXT NOT NULL, " +
                USER_SUBJECTS + " TEXT, " +
                USER_COURSE + " TEXT, " +
                USER_TOKEN + " TEXT);");

        sqLite.execSQL("CREATE TABLE " + PERSON_TABLE + " ("+
                PERSON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                PERSON_USER_ID + " INTEGER, " +
                PERSON_NAME + " TEXT NOT NULL, " +
                PERSON_GENDER + " TEXT NOT NULL, " +
                PERSON_CEP + " TEXT NOT NULL, " +
                PERSON_BIRTHDATE + " TEXT NOT NULL);");

        sqLite.execSQL("CREATE TABLE " + SESSION_TABLE + " (" +
                LOGGED_ID + " INTEGER);");

        sqLite.execSQL("CREATE TABLE " + COURSE_TABLE + " (" +
                COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COURSE_NAME + " TEXT NOT NULL);");

        sqLite.execSQL("CREATE TABLE " + SUBJECT_TABLE + " (" +
                SUBJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SUBJECT_NAME + " TEXT NOT NULL, " +
                SUBJECT_DEPT + " TEXT NOT NULL);");

        sqLite.execSQL("CREATE TABLE " + BUILDING_TABLE + " (" +
                BUILDING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                BUILDING_NAME + " TEXT NOT NULL, " +
                BUILDING_LAT + " TEXT NOT NULL, " +
                BUILDING_LONG + " TEXT NOT NULL);");

        sqLite.execSQL("CREATE TABLE " + COURSE_CLASS_TABLE + " (" +
                COURSE_CLASS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COURSE_CLASS_NAME + " TEXT NOT NULL, " +
                COURSE_CLASS_COURSE + " INTEGER, " +
                COURSE_CLASS_LOCAL + " INTEGER, " +
                COURSE_CLASS_SUBJECT + " INTEGER);");

        sqLite.execSQL("CREATE TABLE " + TIME_TABLE + " (" +
                TIME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TIME_DAY + " TEXT NOT NULL, " +
                TIME_START + " TEXT NOT NULL, " +
                TIME_END + " TEXT NOT NULL);");

        sqLite.execSQL("CREATE TABLE " + CLASS_TABLE + " (" +
                CLASS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CLASS_COURSE_CLASS + " INTEGER, " +
                CLASS_TIME + " INTEGER);");



        String addcurso = "INSERT INTO " + COURSE_TABLE + " (" + COURSE_NAME + ") values";
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


        String addcadeira = "INSERT INTO " + SUBJECT_TABLE + " (" + SUBJECT_NAME + ", " + SUBJECT_DEPT + ") values";
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


        String addpredio = "INSERT INTO " + BUILDING_TABLE + " (" + BUILDING_NAME +", "+ BUILDING_LAT + ", " + BUILDING_LONG + ") values";
        sqLite.execSQL(addpredio+" ('CEAGRI I', '-8.017773', '-34.944100');");
        sqLite.execSQL(addpredio+" ('CEAGRI II', '-8.017497', '-34.944366');");
        sqLite.execSQL(addpredio+" ('CEGOE', '-8.017213', '-34.950011');");
        sqLite.execSQL(addpredio+" ('CEGEN', '-8.015877', '-34.951278');");
        sqLite.execSQL(addpredio+" ('DEINFO', '-8.015877', '-34.951278');");
        sqLite.execSQL(addpredio+" ('DEFIS', '-8.018217', '-34.949120');");
        sqLite.execSQL(addpredio+" ('DZ', '-8.020185', '-34.954006');");
        sqLite.execSQL(addpredio+" ('CB', '-8.013700', '-34.950616');");
        sqLite.execSQL(addpredio+" ('DEPAq', '-8.019440', '-34.943907');");

        String addturma = "INSERT INTO " + COURSE_CLASS_TABLE + " (" + COURSE_CLASS_NAME +", "+ COURSE_CLASS_SUBJECT + ", " + COURSE_CLASS_COURSE + ", " + COURSE_CLASS_LOCAL + ") values";
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

        String addhorario = "INSERT INTO " + TIME_TABLE + " (" + TIME_DAY + ", " + TIME_START + ", " + TIME_END + ") values";
        sqLite.execSQL(addhorario+" ('SEGUNDA', '8', '10');") ;
        sqLite.execSQL(addhorario+" ('SEGUNDA', '10', '12');");
        sqLite.execSQL(addhorario+" ('TERÇA', '8', '10');");
        sqLite.execSQL(addhorario+" ('TERÇA', '10', '12');");
        sqLite.execSQL(addhorario+" ('QUARTA', '8', '10');");
        sqLite.execSQL(addhorario+" ('QUARTA', '10', '12');");
        sqLite.execSQL(addhorario+" ('QUINTA', '8', '10');");
        sqLite.execSQL(addhorario+" ('QUINTA', '10', '12');");
        sqLite.execSQL(addhorario+" ('SEXTA', '8', '10');");
        sqLite.execSQL(addhorario+" ('SEXTA', '10', '12');");

        String addaula = "INSERT INTO " + CLASS_TABLE + " (" + CLASS_COURSE_CLASS + ", " + CLASS_TIME + ") values";
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
