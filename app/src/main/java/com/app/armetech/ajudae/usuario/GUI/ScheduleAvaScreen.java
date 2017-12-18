package com.app.armetech.ajudae.usuario.GUI;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.usuario.dominio.Cadeira;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAvaScreen extends Activity {

    //Variáveis da classe
    private List<Cadeira> cadeiras;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_ava_screen);

        //Inicia o RecyclerView na tela
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        //Define o tipo de layout GridLayoutManager(activity, colunas) ou LinerLayoutManager(activity)
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        //Seta o layout definido pra o RecyclerView (é necessário que o RecyclerView tenha um layout definido
        //caso contrário pode ocorrer erro!!!)
        recyclerView.setLayoutManager(glm);

        //Inicia os dados (função que pode ser substituída pela função de busca de dados no JSON)
        initializeData();
        //Inicia o adaptador (precisa vir depois dos dados já estarem dispostos na lista)
        initializeAdapter();

    }

    //Inicia a lista com os dados
    private void initializeData() {
        cadeiras = new ArrayList<>();
        cadeiras.add(new Cadeira("SI 1", "Modelagem Computacional"));
        cadeiras.add(new Cadeira("SI 2", "Matemática Discreta"));
        cadeiras.add(new Cadeira("SI 1", "Cálculo N1"));
        cadeiras.add(new Cadeira("SI 1", "Cálculo N2"));
        cadeiras.add(new Cadeira("SI 1", "Fundamento de Sistemas de Informação"));
        cadeiras.add(new Cadeira("SI 1", "Álgebra Linear"));
        cadeiras.add(new Cadeira("SI 1", "Física Computacional"));
        cadeiras.add(new Cadeira("SI 2", "Teoria da Computação"));
        cadeiras.add(new Cadeira("SI 1", "Paradigmas de Programação"));
        cadeiras.add(new Cadeira("SI 2", "Modelagem e Programação Orientada a Objetos"));

    }


    //Inicia o adaptador
    private void initializeAdapter() {
        //Cria um novo adaptador, passando a lista dessa classe "cadeiras" onde se encontra os dados
        //inicializados em initializeData() como argumento
        //Por isso initializeData() necessita vir primeiro, pois o adaptador vai distribuir as cadeiras
        //a partir disso
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(cadeiras);
        //Seta o adaptador criado pro RecyclerView
        recyclerView.setAdapter(adapter);
    }
}
