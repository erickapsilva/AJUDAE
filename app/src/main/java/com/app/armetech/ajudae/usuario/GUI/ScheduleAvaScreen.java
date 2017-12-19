package com.app.armetech.ajudae.usuario.GUI;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.usuario.dominio.Cadeira;
import com.app.armetech.ajudae.usuario.infra.DataHolder;
import com.app.armetech.ajudae.usuario.infra.InfoEstudante;
import com.app.armetech.ajudae.usuario.infra.RequestHttp;
import com.app.armetech.ajudae.usuario.infra.ReturnRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ScheduleAvaScreen extends Activity {

    //singleton
    private DataHolder dataHolder;
    private RequestHttp requestHttp;
    private InfoEstudante infoEstudante;
    private static final String TAG = ScheduleAvaScreen.class.getName();

    //Data
    private List<String> disciplinas;
    private List<String> turmas;

    //Variáveis da classe
    private List<Cadeira> cadeiras;
    private RecyclerView recyclerView;

    //UI variáveis
    private TextView txtViewNome;
    private TextView txtViewCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_ava_screen);
        txtViewNome = (TextView)findViewById(R.id.txtViewNome);
        txtViewCurso = (TextView)findViewById(R.id.txtViewCurso);

        //Inicia o RecyclerView na tela
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        //Busca dados
        dataHolder = DataHolder.getInstance();
        Log.i(TAG, "retrieved token: " + dataHolder.getData());
        //Chama o RequestHttp
        requestHttp = RequestHttp.getInstance();
        //Define o tipo de layout GridLayoutManager(activity, colunas) ou LinerLayoutManager(activity)
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        //Seta o layout definido pra o RecyclerView (é necessário que o RecyclerView tenha um layout definido
        //caso contrário pode ocorrer erro!!!)
        recyclerView.setLayoutManager(glm);

        //Inicia os dados (função que pode ser substituída pela função de busca de dados no JSON)
        initializeData();
        initializeAdapter();
    }

    public void printSomething() { Log.i(TAG, "printou"); }


    public void initializeData() {
        disciplinas = (ArrayList)dataHolder.getData().get("disciplinas");
        turmas = (ArrayList)dataHolder.getData().get("turmas");
        cadeiras = new ArrayList<>();
        for(int i = 0; i < disciplinas.size(); i++)
            cadeiras.add(new Cadeira(turmas.get(i), disciplinas.get(i)));
        txtViewNome.append(dataHolder.getData().get("nomecompleto").toString());
        txtViewCurso.append(dataHolder.getData().get("curso").toString());
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
