package com.app.armetech.ajudae.user.gui;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.armetech.ajudae.infra.Mask;
import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.infra.Validation;
import com.app.armetech.ajudae.user.domain.Person;
import com.app.armetech.ajudae.user.domain.User;
import com.app.armetech.ajudae.user.negocio.UserBussiness;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtNameText, edtBirthDateText, edtCepText, edtEmailText, edtPasswordText, edtConfirmPassword;
    private RadioGroup radioGroupGender;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        createFields();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void register(View view){
        validateFields();
    }

    public void createFields(){
        edtNameText = findViewById(R.id.edtNameText);
        edtBirthDateText = findViewById(R.id.edtBirthDateText);
        edtBirthDateText.addTextChangedListener(Mask.insert("##/##/####", edtBirthDateText));
        edtCepText = findViewById(R.id.edtCepText);
        edtCepText.addTextChangedListener(Mask.insert("#####-###", edtCepText));
        edtEmailText = findViewById(R.id.edtEmailText);
        edtPasswordText = findViewById(R.id.edtPasswordText);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        radioGroupGender = findViewById(R.id.Gender);
    }

    public String getGender(){
        RadioButton tickedGender = radioGroupGender.findViewById(radioGroupGender.getCheckedRadioButtonId());
        if (tickedGender == null){
            Toast.makeText(this, R.string.sem_genero, Toast.LENGTH_SHORT).show();
            return null;
        }
        if(tickedGender.getId() == R.id.rdBtnFemale){
            return "feminino";
        } else if (tickedGender.getId() == R.id.rdBtnMale){
            return "masculino";
        }  else {
            return "indefinido";
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void validateFields(){
        String name = edtNameText.getText().toString();
        String birthDate = edtBirthDateText.getText().toString();
        String cep = edtCepText.getText().toString();
        String email = edtEmailText.getText().toString();
        String password = edtPasswordText.getText().toString();
        String confirmPassword = edtConfirmPassword.getText().toString();
        String gender = getGender();

        boolean isValid = true;
        if (!Validation.validateCharacters(name)){
            edtNameText.setError(getString(R.string.nome_invalido));
            isValid = false;
        }
        if (!Validation.validateCep(cep)){
            edtCepText.setError(getString(R.string.cep_invalido));
            isValid = false;
        }
        if (!Validation.validateEmail(email)){
            edtEmailText.setError(getString(R.string.email_invalido));
            isValid = false;
        }
        if (!Validation.validateDate(birthDate)){
            edtBirthDateText.setError(getString(R.string.nascimento_invalido));
            isValid = false;
        }
        if (!password.equals(confirmPassword)){
            edtPasswordText.setError(getString(R.string.senhas_diferentes));
            edtConfirmPassword.setError(getString(R.string.senhas_diferentes));
            isValid = false;
        }
        if (!Validation.validatePassword(password)){
            edtPasswordText.setError(getString(R.string.senha_curta));
            isValid = false;
        }
        if (isValid){
            register(name, gender, birthDate, cep, email, password);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void register(String name, String gender, String birthDate, String cep, String email, String password){

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        Person person = new Person();
        person.setName(name);
        person.setCep(cep);
        person.setGender(gender);
        person.setBirthDate(birthDate);

        UserBussiness userBussiness = new UserBussiness(getApplicationContext());
        boolean register = userBussiness.register(user,person);

        if(register){
            Toast.makeText(this, R.string.cadastrou , Toast.LENGTH_LONG).show();
            Intent Login = new Intent(this, LoginActivity.class);
            startActivity(Login);
        }else{
            Toast.makeText(this, R.string.email_ja_cadastrado, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(login);
        finish();
    }
}
