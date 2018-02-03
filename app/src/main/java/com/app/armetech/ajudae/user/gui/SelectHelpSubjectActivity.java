package com.app.armetech.ajudae.user.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.classes.dao.SubjectDao;
import com.app.armetech.ajudae.classes.domain.Subject;
import com.app.armetech.ajudae.user.dao.UserDao;
import com.app.armetech.ajudae.user.domain.Session;
import com.app.armetech.ajudae.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class SelectHelpSubjectActivity extends AppCompatActivity {

    private List<Subject> takenSubjects;
    private RecyclerView recyclerView;
    private EditText editGetSubject;
    private SubjectDao subjectDao;
    private UserDao userDao;
    private User user;

    //int flags[] = {R.drawable.ic_academic_cap, R.drawable.ic_academic_cap,
     //       R.drawable.ic_academic_cap, R.drawable.ic_academic_cap, R.drawable.ic_academic_cap};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_help_subject);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewHelp);
        editGetSubject = (EditText)findViewById(R.id.editText001);
        subjectDao = new SubjectDao(getApplicationContext());
        userDao = new UserDao(getApplicationContext());
        user = Session.getLoggedUser();


        GridLayoutManager glm = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(glm);
        takenSubjects = new ArrayList<>();
        addAutocompleteData();
    }

    //Performing action onItemSelected and onNothing selected

    public void pressButton(View view) {
        addNewSubject();
        editGetSubject.setText("");
    }

    public void addNewSubject() {
        Subject newSubject = new Subject("SI1", editGetSubject.getText().toString().toUpperCase());
        takenSubjects.add(newSubject);
        user.setSubjectHelper(newSubject);
        initializeUpdateAdapter();
    }

    //Função para testar o NewsFeed ButtomNavigation
    public void goToNewsFeedScreen(View view){
        userDao.insertUserSubjects();
        Intent intent = new Intent(this, BottomTabActivity.class);
        startActivity(intent);
        finish();
    }

    private void initializeUpdateAdapter() {
        RVSubjects adapter = new RVSubjects(takenSubjects);
        recyclerView.setAdapter(adapter);
    }

    private void addAutocompleteData(){
        ArrayList<Subject> listSubjects = subjectDao.getSubjects();
        ArrayList<String> listSubjectsName = new ArrayList<String>();
        for(Subject subject: listSubjects){
            listSubjectsName.add(subject.getSubjectName());
        }
        ArrayAdapter<String> subjectAdapter = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, listSubjectsName);

        AutoCompleteTextView actv =
                (AutoCompleteTextView) findViewById(R.id.editText001);


        actv.setThreshold(2);
        actv.setAdapter(subjectAdapter);

    }
}
