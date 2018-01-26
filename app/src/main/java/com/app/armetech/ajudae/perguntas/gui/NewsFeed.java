package com.app.armetech.ajudae.perguntas.gui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.perguntas.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class NewsFeed extends AppCompatActivity {

    private List<Question> questions;
    private RecyclerView recyclerViewFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        recyclerViewFeed = (RecyclerView) findViewById(R.id.recyclerViewFeed);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_feed:
                        Toast.makeText(NewsFeed.this, "Clicou na aba Feed", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.tab_faltas:
                        Toast.makeText(NewsFeed.this, "Clicou na aba Faltas", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.tab_search:
                        //ação a ser tomada ao clicar na aba buscar
                        //#teste
                        Toast.makeText(NewsFeed.this, "Clicou na aba Buscar", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.tab_notifications:
                        //ação a ser tomada ao clicar na aba notificacoes
                        //#teste
                        Toast.makeText(NewsFeed.this, "Clicou na aba Notificações", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.tab_profile:
                        //ação a ser tomada ao clicar na aba perfil
                        //#teste
                        Toast.makeText(NewsFeed.this, "Clicou na aba Perfil", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerViewFeed.setLayoutManager(llm);
        initializeData();
        initializeUpdateAdapter();
    }

    private void initializeData() {
        questions = new ArrayList<>();
        questions.add(new Question("Rodrigo Xavier", "BSI", "Onde eu baixo placa de vídeo?", "Vi que pra jogar GTA preciso de uma placa de vídeo nova, onde eu baixo?", "#PLACA DE VÍDEO"));
        questions.add(new Question("André Arruda", "BSI", "O Godzilla Giroflex é um bom navegador?", "Veio junto com meu computador, posso entrar no Facebook com ele? Tenho que instalar o Facebook de novo?", "#NAVEGADOR"));
        questions.add(new Question("Emerson Lira", "BSI", "Quem são os inimigos da HP, empresa concorrente?", "Meu tio sempre fala dos inimigos da HP. Minha impresora é HP, é uma empresa concorrente?", "#EMPRESAS"));
        questions.add(new Question("Ericka Pricila", "BSI", "Qual a diferença de um sistema operacional e o Windows?", "Comprei um computador que veio com o sistema operacional Linux, qual diferença de um sistema operacional e do Windows?", "#SISTEMAS OPERACIONAIS"));
        questions.add(new Question("Misael Tomaz", "BSI", "Posso lavar minha placa mãe com detergente?", "Minha placa mãe está suja, será que eu posso lavar ela usando detergente ou faz mal?", "#LIMPEZA DE HARDWARE"));
    }

    private void initializeUpdateAdapter() {
        RVFeed adapter = new RVFeed(questions);
        recyclerViewFeed.setAdapter(adapter);
    }

}
