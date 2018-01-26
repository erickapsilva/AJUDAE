package com.app.armetech.ajudae.user.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.aulas.dao.SubjectDao;
import com.app.armetech.ajudae.aulas.domain.Subject;
import com.app.armetech.ajudae.perguntas.domain.Question;
import com.app.armetech.ajudae.perguntas.gui.NewsFeed;

import java.util.ArrayList;
import java.util.List;

public class SelectHelpSubjectActivity extends AppCompatActivity {

    private List<Subject> takenSubjects;
    private RecyclerView recyclerView;
    private EditText editGetSubject;

    //int flags[] = {R.drawable.ic_academic_cap, R.drawable.ic_academic_cap,
     //       R.drawable.ic_academic_cap, R.drawable.ic_academic_cap, R.drawable.ic_academic_cap};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_help_subject);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewHelp);
        editGetSubject = (EditText)findViewById(R.id.editText001);


        GridLayoutManager glm = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(glm);
        takenSubjects = new ArrayList<>();
    }

    //Performing action onItemSelected and onNothing selected

    public void pressButton(View view) {
        addNewSubject();
    }

    public void addNewSubject() {
        takenSubjects.add(new Subject("SI1", editGetSubject.getText().toString()));
        initializeUpdateAdapter();
    }

    //Função para testar o NewsFeed ButtomNavigation
    public void goToNewsFeedScreen(View view){
        Intent intent = new Intent(this, NewsFeed.class);
        startActivity(intent);
        finish();
    }

    private void initializeUpdateAdapter() {
        RVSubjects adapter = new RVSubjects(takenSubjects);
        recyclerView.setAdapter(adapter);
    }
}
