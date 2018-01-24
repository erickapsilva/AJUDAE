package com.app.armetech.ajudae.perguntas.gui;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.perguntas.domain.Question;

import java.util.List;

//Adaptador que monta o RecyclerView e dá override pra adicionar itens customizados
public class RVFeed extends RecyclerView.Adapter<RVFeed.QuestionViewHolder> {

    //Inicia o ViewHolder
    public static class QuestionViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView name;
        TextView course;
        TextView title;
        TextView question;
        Button tag;
        ImageView userPicture;

        //Define as informações de inicialização da View. Funciona semalhante ao onCreate()
        QuestionViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardViewFeed);
            name = (TextView)itemView.findViewById(R.id.textView14);
            course = (TextView)itemView.findViewById(R.id.textView15);
            title = (TextView)itemView.findViewById(R.id.textView18);
            question = (TextView)itemView.findViewById(R.id.textView20);
            tag = (Button)itemView.findViewById(R.id.button2);
            userPicture = (ImageView)itemView.findViewById(R.id.imageView13);

        }
    }

    List<Question> questions;

    RVFeed(List<Question> questions){
        this.questions = questions;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    //Procura as configurações do CardView no XML e partir dai incorpora essas configurações
    //dentro de cada novo CardView que vai ser colocado na tela
    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_question_card, viewGroup, false);
        QuestionViewHolder pvh = new QuestionViewHolder(v);
        return pvh;
    }

    //Busca quais campos devem ser preenchidos e seta o texto a partir das informações resgatadas
    @Override
    public void onBindViewHolder(QuestionViewHolder questionViewHolder, int i) {
        questionViewHolder.name.setText(questions.get(i).getName());
        questionViewHolder.course.setText(questions.get(i).getCourse());
        questionViewHolder.title.setText(questions.get(i).getTitle());
        questionViewHolder.question.setText(questions.get(i).getQuestion());
        questionViewHolder.tag.setText(questions.get(i).getTag());
    }

    //Pega a quantidade de itens na lista local
    @Override
    public int getItemCount() { return questions.size(); }
}