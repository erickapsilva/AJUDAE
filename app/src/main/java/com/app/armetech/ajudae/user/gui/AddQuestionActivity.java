package com.app.armetech.ajudae.user.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.questions.dao.QuestionDao;
import com.app.armetech.ajudae.questions.domain.Question;
import com.app.armetech.ajudae.user.domain.Session;
import com.app.armetech.ajudae.user.domain.User;

public class AddQuestionActivity extends AppCompatActivity {

    private EditText edtTextQuestion;
    private EditText edtTextQuestionResume;
    private EditText edtTextQuestionTag;
    private QuestionDao questionDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        edtTextQuestion = (EditText)findViewById(R.id.edtTextQuestion);
        edtTextQuestionResume = (EditText)findViewById(R.id.edtTextQuestionResume);
        edtTextQuestionTag = (EditText) findViewById(R.id.edtTextTag);

        questionDao = new QuestionDao(getApplicationContext());
    }

    public void sendQuestion(View view) {
        String title = edtTextQuestion.getText().toString();
        String resume = edtTextQuestionResume.getText().toString();
        String tags = edtTextQuestionTag.getText().toString();
        User owner = Session.getLoggedUser();

        Question question = new Question(title, resume);
        question.setOwnerId(owner.getId());
        question.setCourse(owner.getCourse());
        question.setTag(tags);
        question.setName("Exemplo");

        questionDao.insertQuestion(question);

        Intent intent = new Intent(this, BottomTabActivity.class);
        startActivity(intent);
        finish();
    }
}
