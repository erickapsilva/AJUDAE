package com.app.armetech.ajudae;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    public void cadastrar(View view){
        Intent telaCadastro = new Intent(this, RegisterScreen.class);
        startActivity(telaCadastro);
    }


}
