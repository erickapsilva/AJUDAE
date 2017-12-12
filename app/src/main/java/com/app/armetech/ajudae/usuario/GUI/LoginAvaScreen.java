package com.app.armetech.ajudae.usuario.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.ScheduleAvaScreen;

public class LoginAvaScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ava_screen);
    }

    public void goToSchedule(View view){
        Intent scheduleAvaScreen = new Intent(this, ScheduleAvaScreen.class);
        startActivity(scheduleAvaScreen);
    }
}
