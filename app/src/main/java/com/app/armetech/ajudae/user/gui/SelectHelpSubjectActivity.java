package com.app.armetech.ajudae.user.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.annotation.SuppressLint;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.classes.business.SubjectBusiness;
import com.app.armetech.ajudae.classes.domain.Subject;
import com.app.armetech.ajudae.navigation.BottomTabActivity;
import com.app.armetech.ajudae.user.dao.UserDao;
import com.app.armetech.ajudae.user.domain.Session;
import com.app.armetech.ajudae.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class SelectHelpSubjectActivity extends AppCompatActivity {

    private List<Subject> takenSubjects;
    private RecyclerView recyclerView;
    private EditText editGetSubject;
    private UserDao userDao;
    private User user;
    private ArrayList<Subject> listSubjects;
    private SubjectBusiness subjectBusiness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_help_subject);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewHelp);
        editGetSubject = (EditText)findViewById(R.id.editText001);


        userDao = new UserDao(getApplicationContext());
        subjectBusiness = new SubjectBusiness(getApplicationContext());

        user = Session.getLoggedUser();

        listSubjects = new ArrayList<>();
        listSubjects = subjectBusiness.getSubjects();

        GridLayoutManager glm = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(glm);
        takenSubjects = new ArrayList<>();
        addAutocompleteData();
    }

    public void pressButton(View view) {
        addNewSubject();
        editGetSubject.setText("");
    }

    public void addNewSubject() {
        Subject newSubject = searchSubject(editGetSubject.getText().toString());
        takenSubjects.add(newSubject);
        user.addSubjectHelper(newSubject);
        initializeUpdateAdapter();
    }

    @SuppressLint("ResourceType")
    public void deleteSubject(View view){
        android.widget.LinearLayout layout = (LinearLayout) view.getParent();
        TextView lView = layout.findViewById(R.id.subjectName);
        String subjectName = lView.getText().toString();
        Subject subject1 = null;
        for(Subject subject: takenSubjects){
            if(subject.getSubjectName().equals(subjectName)){
                subject1 = subject;
                break;
            }
        }
        takenSubjects.remove(subject1);
        user.delSubjectHelper(subject1);
        initializeUpdateAdapter();
    }

    public void goToNewsFeedScreen(View view){
        userDao.insertUserSubjects();
        Intent intent = new Intent(this, BottomTabActivity.class);
        startActivity(intent);
        finish();
    }

    private void initializeUpdateAdapter() {
        RVSubjects adapter = new RVSubjects(takenSubjects, true);
        recyclerView.setAdapter(adapter);
    }

    private void addAutocompleteData(){
        ArrayAdapter<String> subjectAdapter = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, subjectBusiness.getAutocompleteData());
        AutoCompleteTextView actv =
                (AutoCompleteTextView) findViewById(R.id.editText001);

        actv.setThreshold(2);
        actv.setAdapter(subjectAdapter);
    }

    private Subject searchSubject(String nameSubject){
        Subject subjectReturn = null;
        for(Subject subject: listSubjects){
            if(subject.getSubjectName().equals(nameSubject)){
                subjectReturn = subject;
                subjectReturn.setSubjectName(subject.getSubjectName().toUpperCase());
                break;
            }
        }
        return subjectReturn;
    }

}
