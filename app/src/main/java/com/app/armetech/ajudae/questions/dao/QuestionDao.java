package com.app.armetech.ajudae.questions.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.armetech.ajudae.classes.domain.Subject;
import com.app.armetech.ajudae.infra.DataBaseHelper;
import com.app.armetech.ajudae.questions.domain.Question;
import com.app.armetech.ajudae.user.dao.UserDao;
import com.app.armetech.ajudae.user.domain.Session;
import com.app.armetech.ajudae.user.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roxac on 01/02/18.
 */

public class QuestionDao {
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;
    private UserDao userDao;
    private String questionIdColumn = DataBaseHelper.getQuestionId();
    private String questionTitleColumn = DataBaseHelper.getQuestionTitle();
    private String questionBodyColumn = DataBaseHelper.getQuestionBody();
    private String questionTagsColumn = DataBaseHelper.getQuestionTags();
    private String questionOwnerColumn = DataBaseHelper.getQuestionOwner();
    private String questionTable = DataBaseHelper.getQuestionTable();
    private String questionOwnerId = DataBaseHelper.getQuestionOwnerId();


    public QuestionDao(Context context){
        userDao = new UserDao(context);
        dbHelper = new DataBaseHelper(context);
    }

    //retorna todas as questões existentes no banco de dados
    public List getQuestions(){
        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DataBaseHelper.getQuestionTable();
        Cursor cursor = db.rawQuery(query, null);
        List<Question> questions = new ArrayList<>();
        while(cursor.moveToNext()){
            questions.add(createQuestion(cursor));
        }

        cursor.close();
        db.close();

        return questions;
    }

    //retorna todas as questões daquele determinado usuário
    public List<Question> getQuestionsByOwnerId(long userId) {
        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DataBaseHelper.getQuestionTable() + " WHERE " + DataBaseHelper.getQuestionOwnerId() + " LIKE ?";
        String uid = Long.toString(userId);
        String[] arguments = {uid};
        Cursor cursor = db.rawQuery(query, arguments);
        List<Question> questions = new ArrayList<>();
        while(cursor.moveToNext()){
            questions.add(createQuestion(cursor));
        }

        cursor.close();
        db.close();

        return questions;
    }

    //retorna o id do dono da questão para comparação
    public long getQuestionOwnerId(long questionId) {
        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DataBaseHelper.getQuestionTable() + " WHERE " + DataBaseHelper.getQuestionId() + " LIKE ?";
        String stringId = Long.toString(questionId);
        String[] arguments = {stringId};
        Cursor cursor = db.rawQuery(query, arguments);
        Question question = new Question();
        while(cursor.moveToNext()) {
            question = createQuestion(cursor);
        }

        cursor.close();
        db.close();

        return question.getOwnerId();
    }

    //retorna as questões a partir de uma determinada tag/subject
    public List<Question> getQuestionsBySubject(Subject subject) {
        List<Question> questions = new ArrayList<>();
        return questions;
    }

    public List<Question> getQuestionsByTag(String tag) {
        List<Question> questions = new ArrayList<>();
        return questions;
    }

    public Question createQuestion(Cursor cursor){
        String questionId = DataBaseHelper.getQuestionId();
        int indexQuestionId = cursor.getColumnIndex(questionId);
        int id = cursor.getInt(indexQuestionId);

        String questionOwnerId = DataBaseHelper.getQuestionOwnerId();
        int indexQuestionOwnerId = cursor.getColumnIndex(questionOwnerId);
        int ownerId = cursor.getInt(indexQuestionOwnerId);

        String questionTitle = DataBaseHelper.getQuestionTitle();
        int indexQuestionTitle = cursor.getColumnIndex(questionTitle);
        String title = cursor.getString(indexQuestionTitle);

        String questionBody = DataBaseHelper.getQuestionBody();
        int indexQuestionBody = cursor.getColumnIndex(questionBody);
        String body = cursor.getString(indexQuestionBody);

        String questionTags = DataBaseHelper.getQuestionTags();
        int indexQuestionTags = cursor.getColumnIndex(questionTags);
        String tags = cursor.getString(indexQuestionTags);

        User owner = userDao.getUserById(ownerId);

        Question question = new Question(title, body);
        question.setTag(tags);
        question.setCourse("BACHARELADO DE SISTEMAS DE INFORMAÇÃO");
        question.setOwnerId(ownerId);
        question.setId(id);
        question.setName("Exemplo");

        return question;

    }

    public long insertQuestion(Question question) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String ownerId = Long.toString(Session.getLoggedUser().getId());
        values.put(questionOwnerId, ownerId);

        String title = question.getTitle();
        values.put(questionTitleColumn, title);

        String body = question.getQuestion();
        values.put(questionBodyColumn, body);

        String tags = question.getTag();
        values.put(questionTagsColumn, tags);

        long id = db.insert(questionTable, null, values);

        db.close();

        return id;
    }

    public void updateQuestion(Question question) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String ownerId = Long.toString(Session.getLoggedUser().getId());
        values.put(questionOwnerId, ownerId);

        String title = question.getTitle();
        values.put(questionTitleColumn, title);

        String body = question.getQuestion();
        values.put(questionBodyColumn, body);

        String tags = question.getTag();
        values.put(questionTagsColumn, tags);

        db.update(questionTable, values, questionIdColumn + "=" + question.getId(), null);
    }

    public long deleteQuestion(Question question) {
        return db.delete(questionTable, questionIdColumn + "=" + question.getId(), null);
    }
}
