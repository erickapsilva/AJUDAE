package com.app.armetech.ajudae.user.gui;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.infra.Validation;
import com.app.armetech.ajudae.user.negocio.UserBussiness;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        createFields();
    }

    public void register(View view){
        Intent registerScreen = new Intent(this, RegisterActivity.class);
        startActivity(registerScreen);
        finish();
    }

    public void createFields(){
        edtEmail = findViewById(R.id.email);
        edtPassword = findViewById(R.id.password);
        btnEnter = findViewById(R.id.btnEnter);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void login(View view) {
        validateFields();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void validateFields(){
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        boolean valid = true;
        if (!Validation.validateEmail(email)){
            edtEmail.setError(getString(R.string.email_invalido));
            valid = false;
        } if(!Validation.validatePassword(password)){
            edtPassword.setError(getString(R.string.senha_curta));
            valid = false;
        }
        if (valid){
            login(email,password);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void login(String email, String password){
        UserBussiness userBussiness = new UserBussiness(getApplicationContext());
        boolean verifyLogin = userBussiness.validateLogin(email,password);
        if (verifyLogin){
            Intent startScreen = new Intent(this, LoginAvaActivity.class);
            startActivity(startScreen);
            finish();
        } else {
            Toast.makeText(this, R.string.login_incorreto, Toast.LENGTH_SHORT).show();
        }
    }

}
