package com.app.armetech.ajudae.usuario.GUI;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.ScheduleAvaScreen;
import com.app.armetech.ajudae.usuario.infra.RequestHttp;
import com.app.armetech.ajudae.usuario.infra.ReturnRequest;

public class LoginAvaScreen extends AppCompatActivity {

    private RequestHttp requestHttp;
    private Button btnContinuar;
    private EditText edtTextLoginAva;
    private EditText edtTextSenhaAva;
    private static final String TAG = LoginAvaScreen.class.getName();
    private Handler mHandler;

    public LoginAvaScreen() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ava_screen);

        btnContinuar = (Button) findViewById(R.id.btnContinuar);
        edtTextLoginAva = (EditText) findViewById(R.id.edtTextLoginAva);
        edtTextSenhaAva = (EditText) findViewById(R.id.edtTextSenhaAva);

        requestHttp = RequestHttp.getInstance();
        requestHttp.initializeClient();

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginAva = edtTextLoginAva.getText().toString();
                String senhaAva = edtTextSenhaAva.getText().toString();
                requestHttp.getToken(loginAva, senhaAva, new ReturnRequest() {
                    @Override
                    public void retrieveData(Object value) {

                        if(value == null) {
                            getLoginInfo();
                        } else {
                            String cursos = value.toString();
                            Log.i(TAG, cursos);
                            goToSchedule();
                        }
                    }
                });
            }
        });

    }

    public void getLoginInfo() {
        Log.i(TAG, "Login ou senha inválido");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginAvaScreen.this, "Login ou senha inválidos" , Toast.LENGTH_LONG).show();
            }
        });
    }


    public void goToSchedule(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent scheduleAvaScreen = new Intent(LoginAvaScreen.this, ScheduleAvaScreen.class);
                startActivity(scheduleAvaScreen);
            }
        });

    }
}
