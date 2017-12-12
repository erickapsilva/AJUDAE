package com.app.armetech.ajudae;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.app.armetech.ajudae.usuario.GUI.LoginScreen;

/**
 * Created by user1 on 05/12/2017.
 */

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;

    //criando a lista de imagens

    public int[] listaImagens ={
        R.drawable.welcome1,
        R.drawable.welcome2
    };

    public SlideAdapter(Context context){
        this.context = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slider,container,false);
        ConstraintLayout layoutSlide = (ConstraintLayout) view.findViewById(R.id.constraintLayout);
        ImageView imgs  = (ImageView) view.findViewById(R.id.slideImg);
        Button btnEntendi = (Button) view.findViewById(R.id.btnEntendi);

        btnEntendi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent telaLogin = new Intent(context, LoginScreen.class);
                context.startActivity(telaLogin);
            }
        });

        imgs.setImageResource(listaImagens[position]);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return listaImagens.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view ==(ConstraintLayout)object);
    }
}
