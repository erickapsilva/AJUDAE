package com.app.armetech.ajudae.user.gui;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.aulas.domain.Subject;

import java.util.List;

//Adaptador que monta o RecyclerView e dá override pra adicionar itens customizados
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CadeiraViewHolder> {

    //Inicia o ViewHolder
    public static class CadeiraViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView courseClass;
        TextView name;

        //Define as informações de inicialização da View. Funciona semalhante ao onCreate()
        CadeiraViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            courseClass = (TextView)itemView.findViewById(R.id.courseClass);
            name = (TextView)itemView.findViewById(R.id.subjectName);
        }
    }

    List<Subject> subjects;

    RecyclerViewAdapter(List<Subject> subjects){
        this.subjects = subjects;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    //Procura as configurações do CardView no XML e partir dai incorpora essas configurações
    //dentro de cada novo CardView que vai ser colocado na tela
    @Override
    public CadeiraViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item_card, viewGroup, false);
        CadeiraViewHolder pvh = new CadeiraViewHolder(v);
        return pvh;
    }

    //Busca quais campos devem ser preenchidos e seta o texto a partir das informações resgatadas
    @Override
    public void onBindViewHolder(CadeiraViewHolder personViewHolder, int i) {
        personViewHolder.courseClass.setText(subjects.get(i).getCourseClass());
        personViewHolder.name.setText(subjects.get(i).getSubjectName());
    }

    //Pega a quantidade de itens na lista local
    @Override
    public int getItemCount() {
        return subjects.size();
    }
}