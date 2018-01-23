package com.app.armetech.ajudae.user.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.armetech.ajudae.R;

public class SelectHelpSubjectActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] countryNames={"MATEMÁTICA DISCRETA","ÁLGEBRA","FÍSICA","ECONOMIA","ENGENHARIA DE SOFTWARE"};
    int flags[] = {R.drawable.ic_academic_cap, R.drawable.ic_academic_cap,
            R.drawable.ic_academic_cap, R.drawable.ic_academic_cap, R.drawable.ic_academic_cap};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_help_subject);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.simpleSpinner);
        spin.setOnItemSelectedListener(this);

        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),flags,countryNames);
        spin.setAdapter(customAdapter);
    }
    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), countryNames[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    //Função para testar o NewsFeed ButtomNavigation
    public void goToNewsFeedScreen(View view){
        Intent intent = new Intent(this, NewsFeed.class);
        startActivity(intent);
        finish();
    }
}
