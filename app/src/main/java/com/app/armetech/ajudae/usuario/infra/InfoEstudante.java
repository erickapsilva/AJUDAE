package com.app.armetech.ajudae.usuario.infra;

import android.icu.text.IDNA;
import android.util.Log;

import com.app.armetech.ajudae.usuario.GUI.ScheduleAvaScreen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roxac on 18/12/17.
 */

public class InfoEstudante {
    private static final String TAG = InfoEstudante.class.getName();
    private DataHolder dataHolder;

    public void searchData(JSONObject requestData, ReturnRequest returnRequest) throws JSONException {

        dataHolder = DataHolder.getInstance();

        dataHolder.setData("curso", requestData.get("department").toString());
        dataHolder.setData("cidade", requestData.get("city").toString());

        JSONArray jsonArr = (JSONArray)requestData.get("enrolledcourses");
        List<String> disciplinas = new ArrayList<>();
        List<String> turmas = new ArrayList<>();

        for(int i = 0; i < jsonArr.length(); i++) {
            JSONObject tempJson = (JSONObject)jsonArr.get(i);
            if(tempJson.get("shortname").toString().contains("2017.2")) {
                String disciplina = tempJson.get("fullname").toString().substring(9);
                disciplinas.add(disciplina.substring(0, disciplina.indexOf("-") - 1));
                turmas.add(disciplina.substring(disciplina.indexOf("-") + 1));
            }
        }

        dataHolder.setData("disciplinas", disciplinas);
        dataHolder.setData("turmas", turmas);
        returnRequest.retrieveData(true);
    }
}
