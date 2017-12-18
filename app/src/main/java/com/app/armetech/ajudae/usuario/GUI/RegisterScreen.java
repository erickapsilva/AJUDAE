package com.app.armetech.ajudae.usuario.GUI;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.app.armetech.ajudae.Mask;
import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.Validacao;
import com.app.armetech.ajudae.usuario.negocio.UsuarioNegocio;

public class RegisterScreen extends AppCompatActivity {
    private EditText nome, nascimento, cep, email,senha;
    private RadioButton masculino, feminino, indefinido;
    UsuarioNegocio usuarioNegocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        carregaBotoes();
        usuarioNegocio = new UsuarioNegocio(getApplicationContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void continuar(View view){
        validarCampos();
    }

    public void carregaBotoes(){
        nome = findViewById(R.id.edtTextNomeCompleto);
        nascimento = findViewById(R.id.edtTextDataNascimento);
        nascimento.addTextChangedListener(Mask.insert("##/##/####",nascimento));
        cep = findViewById(R.id.edtTextCep);
        cep.addTextChangedListener(Mask.insert("#####-###",cep));
        email = findViewById(R.id.edtTextEmail);
        senha = findViewById(R.id.edtTextSenha);
        masculino = findViewById(R.id.rdBtnMasc);
        feminino = findViewById(R.id.rdBtnFem);
        indefinido = findViewById(R.id.rdBtnNaoDefinir);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void validarCampos(){
        String strnome = nome.getText().toString();
        String strnascimento = nascimento.getText().toString();
        String strcep = cep.getText().toString();
        String stremail = email.getText().toString();
        String strsenha = senha.getText().toString();
        String strgenero;
        if (feminino.isChecked()){
            strgenero = "Feminino";
        } else if (masculino.isChecked()){
            strgenero = "Masculino";
        } else{
            strgenero = "Indefinido";
        }
        boolean valido = true;
        if (!Validacao.validaCaracteres(strnome)){
            nome.setError("Nome invalido");
            valido = false;
        }
        if (!Validacao.validaCep(strcep)){
            cep.setError("CEP invalido");
            valido = false;
        }
        if (!Validacao.validaEmail(stremail)){
            email.setError("E-mail invalido");
            valido = false;
        }
        if (!Validacao.validaData(strnascimento)){
            nascimento.setError("Data de nascimento invalida");
            valido = false;
        }
        if (!Validacao.validaSenha(strsenha)){
            senha.setError("A senha deve conter no minimo 6 caracteres entre letras e numeros");
            valido = false;
        }
        if (valido){
            Cadastrar(strnome, strgenero, strnascimento, strcep, stremail, strsenha);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void Cadastrar(String nome, String genero, String nascimento, String cep, String email, String senha){
        boolean cadastrar = usuarioNegocio.cadastrar(nome, genero, nascimento, cep, email, senha);
        if(cadastrar){
            Toast.makeText(this, "Usuario cadastrado" , Toast.LENGTH_LONG).show();
            Intent Login = new Intent(this, LoginScreen.class);
            startActivity(Login);
        }else{
            Toast.makeText(this, "E-mail já cadastrado", Toast.LENGTH_LONG).show();
        }
    }





}
