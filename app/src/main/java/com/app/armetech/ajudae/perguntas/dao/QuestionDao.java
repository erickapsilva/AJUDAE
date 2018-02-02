package com.app.armetech.ajudae.perguntas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.armetech.ajudae.aulas.domain.Subject;
import com.app.armetech.ajudae.infra.DataBase;
import com.app.armetech.ajudae.perguntas.domain.Question;
import com.app.armetech.ajudae.user.domain.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roxac on 01/02/18.
 */

public class QuestionDao {
    private DataBase dbHelper;
    private SQLiteDatabase db;
    private String questionIdColumn = DataBase.getQuestionId();
    private String questionTitleColumn = DataBase.getQuestionTitle();
    private String questionBodyColumn = DataBase.getQuestionBody();
    private String questionTagsColumn = DataBase.getQuestionTags();
    private String questionOwnerColumn = DataBase.getQuestionOwner();
    private String questionTable = DataBase.getQuestionTable();


    public QuestionDao(Context context){
        dbHelper = new DataBase(context);
    }

    public List getQuestions(){
        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DataBase.getQuestionTable();
        Cursor cursor = db.rawQuery(query, null);
        List<Question> questions = new ArrayList<>();
        while(cursor.moveToNext()){
            questions.add(createQuestion(cursor));
        }

        cursor.close();
        db.close();

        return questions;
    }

    public Question createQuestion(Cursor cursor){
        String questionId = DataBase.getQuestionId();
        int indexQuestionId= cursor.getColumnIndex(questionId);
        int id = cursor.getInt(indexQuestionId);

        String questionOwner = DataBase.getQuestionOwner();
        int indexQuestionOwner = cursor.getColumnIndex(questionOwner);
        String owner = cursor.getString(indexQuestionOwner);

        String questionTitle = DataBase.getQuestionTitle();
        int indexQuestionTitle = cursor.getColumnIndex(questionTitle);
        String title = cursor.getString(indexQuestionTitle);

        String questionBody = DataBase.getQuestionBody();
        int indexQuestionBody = cursor.getColumnIndex(questionBody);
        String body = cursor.getString(indexQuestionBody);

        String questionTags = DataBase.getQuestionTags();
        int indexQuestionTags = cursor.getColumnIndex(questionTags);
        String tags = cursor.getString(indexQuestionTags);

        Question question = new Question(owner, "BSI", title, body, tags);
        question.setId(id);

        return question;

    }

    public long insertQuestion(Question question) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String owner = question.getName();
        values.put(questionOwnerColumn, owner);

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

        String owner = question.getName();
        values.put(questionOwnerColumn, owner);

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
