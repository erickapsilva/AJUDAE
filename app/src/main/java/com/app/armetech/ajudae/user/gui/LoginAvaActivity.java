package com.app.armetech.ajudae.user.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.infra.DataHolder;
import com.app.armetech.ajudae.user.domain.Session;
import com.app.armetech.ajudae.infra.StudentExternalData;
import com.app.armetech.ajudae.infra.RequestHttp;
import com.app.armetech.ajudae.infra.ReturnRequest;
import com.app.armetech.ajudae.user.business.UserBusiness;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginAvaActivity extends AppCompatActivity {
    private RequestHttp requestHttp;
    private DataHolder dataHolder;
    private StudentExternalData studentExternalData;
    private Button btnContinue;
    private EditText edtTextAvaLogin, edtTextAvaPass;
    private TextView txtViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ava_screen);
        createFields();
        requestHttp = RequestHttp.getInstance();
        requestHttp.initializeClient();
        dataHolder = DataHolder.getInstance();
    }



    public void loginAVA(View view){
        String avaLogin = edtTextAvaLogin.getText().toString();
        String avaPass = edtTextAvaPass.getText().toString();

        requestHttp.getToken(avaLogin, avaPass, new ReturnRequest() {
            @Override
            public void retrieveData(Object value) {

                if (value == null) {
                    getLoginInfo();
                } else {
                    String token = value.toString();
                    requestUserId(token);
                }
            }
        });
    }

    public void requestUserId(final String token) {
        requestHttp.getUsrId(token, new ReturnRequest() {
            @Override
            public void retrieveData(Object value) {
                if (value == null) {
                    getLoginInfo();
                } else {
                    JSONObject jsonUser = (JSONObject) value;
                    String userId, fullName;
                    dataHolder.setData("token", token);
                    try {
                        userId = jsonUser.get("userid").toString();
                        fullName = jsonUser.get("fullname").toString();
                        dataHolder.setData("fullname", fullName);
                        requestInfo(token, userId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void requestInfo(String token, String userId) {
        requestHttp.getInfo(token, userId, new ReturnRequest() {
            @Override
            public void retrieveData(Object value) {
                if (value == null) {
                    getLoginInfo();
                } else {
                    JSONObject dataAva = (JSONObject) value;
                    studentExternalData = new StudentExternalData();
                    try {
                        studentExternalData.searchData(dataAva, new ReturnRequest() {
                            @Override
                            public void retrieveData(Object value) {
                                boolean v = (boolean) value;
                                if (v)
                                    goToSchedule();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void createFields(){
        btnContinue = (Button) findViewById(R.id.btnContinue);
        edtTextAvaLogin = (EditText) findViewById(R.id.edtTextAvaLogin);
        edtTextAvaPass = (EditText) findViewById(R.id.edtTextAvaPass);
        txtViewName = (TextView) findViewById(R.id.textViewName);
        txtViewName.setText("Olá " + Session.getLoggedPerson().getName());
    }

    public void getLoginInfo() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginAvaActivity.this, R.string.login_incorreto , Toast.LENGTH_LONG).show();
            }
        });
    }

    public void goToSchedule(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent scheduleAvaScreen = new Intent(LoginAvaActivity.this, ScheduleAvaActivity.class);
                startActivity(scheduleAvaScreen);
                finish();
            }
        });
    }

    public void logout(){
        UserBusiness userBusiness = new UserBusiness(this);
        userBusiness.logout();
        Intent login = new Intent(LoginAvaActivity.this, LoginActivity.class);
        startActivity(login);
        finish();
    }

    @Override
    public void onBackPressed() {
        logout();
    }

}
