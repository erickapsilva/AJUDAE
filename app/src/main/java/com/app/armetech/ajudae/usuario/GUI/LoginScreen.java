package com.app.armetech.ajudae.usuario.GUI;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.Validacao;
import com.app.armetech.ajudae.usuario.negocio.LoginNegocio;

public class LoginScreen extends AppCompatActivity {
    EditText edtemail, edtsenha;
    Button btnEntrar;
    private boolean podeLogar = false;

    public void setPodeLogar(boolean b){
        this.podeLogar = b;
    }
    public boolean getPodeLogar(){
        return this.podeLogar;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        criaComponentes();
    }

    public void cadastrar(View view){
        Intent telaCadastro = new Intent(this, RegisterScreen.class);
        startActivity(telaCadastro);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void entrar(View view) {
        validaCampos();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void validaCampos(){
        String email = edtemail.getText().toString();
        String senha = edtsenha.getText().toString();
        boolean valido = true;
        if (!Validacao.validaEmail(email)){
            edtemail.setError("Email inválido!");
            valido = false;
        } if(!Validacao.validaSenha(senha)){
            edtsenha.setError("Senha inválida!");
            valido = false;
        }
        if (valido){
            LoginNegocio loginN = chamarLoginNegocio(email,senha);
            if(loginN.validarBanco()){
                Intent telaInicial = new Intent(this, LoginAvaScreen.class);
                startActivity(telaInicial);
            }else{
                Toast.makeText(this, "E-mail ou Senha Incorretas", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void criaComponentes(){
        edtemail = findViewById(R.id.email);
        edtsenha = findViewById(R.id.senha);
        btnEntrar = findViewById(R.id.btnEntrar);
    }
    public LoginNegocio chamarLoginNegocio(String emailObjeto, String senhaObjeto){
        LoginNegocio loginNegocio = new LoginNegocio(getApplicationContext());
        loginNegocio.getUsuario().setEmail(emailObjeto);
        loginNegocio.getUsuario().setSenha(senhaObjeto);
        return loginNegocio;
    }




}
