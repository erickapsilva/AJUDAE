package com.app.armetech.ajudae.user.gui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.perguntas.domain.Question;
import com.app.armetech.ajudae.perguntas.gui.RVFeed;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;


public class FeedFragment extends Fragment {

    private List<Question> questions;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton2, floatingActionButton3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed,container,false);

        materialDesignFAM = (FloatingActionMenu) rootView.findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton2 =  (FloatingActionButton) rootView.findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) rootView.findViewById(R.id.material_design_floating_action_menu_item3);

        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initializeData();
        recyclerView.setAdapter(new RVFeed(questions));


        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked

            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked

            }
        });

        return recyclerView;

    }

    public void initializeData() {
        questions = new ArrayList<>();
        questions.add(new Question("Rodrigo Xavier", "BSI", "Onde eu baixo placa de vídeo?", "Vi que pra jogar GTA preciso de uma placa de vídeo nova, onde eu baixo?", "#PLACA DE VÍDEO"));
        questions.add(new Question("André Arruda", "BSI", "O Godzilla Giroflex é um bom navegador?", "Veio junto com meu computador, posso entrar no Facebook com ele? Tenho que instalar o Facebook de novo?", "#NAVEGADOR"));
        questions.add(new Question("Emerson Lira", "BSI", "Quem são os inimigos da HP, empresa concorrente?", "Meu tio sempre fala dos inimigos da HP. Minha impresora é HP, é uma empresa concorrente?", "#EMPRESAS"));
        questions.add(new Question("Ericka Pricila", "BSI", "Qual a diferença de um sistema operacional e o Windows?", "Comprei um computador que veio com o sistema operacional Linux, qual diferença de um sistema operacional e do Windows?", "#SISTEMAS OPERACIONAIS"));
        questions.add(new Question("Misael Tomaz", "BSI", "Posso lavar minha placa mãe com detergente?", "Minha placa mãe está suja, será que eu posso lavar ela usando detergente ou faz mal?", "#LIMPEZA DE HARDWARE"));
    }

}
