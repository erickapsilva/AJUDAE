package com.app.armetech.ajudae;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
    }
    public void continuar(View view){
        Intent loginAvaScreen = new Intent(this, LoginAvaScreen.class);
        startActivity(loginAvaScreen);
    }
}
