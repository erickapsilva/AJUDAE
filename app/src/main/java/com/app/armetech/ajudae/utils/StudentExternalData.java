package com.app.armetech.ajudae.utils;

import com.app.armetech.ajudae.infra.DataHolder;
import com.app.armetech.ajudae.infra.ReturnRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roxac on 18/12/17.
 */

public class StudentExternalData {
    private DataHolder dataHolder;

    public void searchData(JSONObject requestData, ReturnRequest returnRequest) throws JSONException {

        dataHolder = DataHolder.getInstance();

        dataHolder.setData("dpt", requestData.get("department").toString());
        dataHolder.setData("city", requestData.get("city").toString());

        JSONArray jsonArr = (JSONArray)requestData.get("enrolledcourses");
        List<String> subjects = new ArrayList<>();
        List<String> courseClass = new ArrayList<>();

        for(int i = 0; i < jsonArr.length(); i++) {
            JSONObject tempJson = (JSONObject)jsonArr.get(i);
            if(tempJson.get("shortname").toString().contains("2017.2")) {
                String subject = tempJson.get("fullname").toString().substring(9);
                subjects.add(subject.substring(0, subject.indexOf("-") - 1));
                courseClass.add(subject.substring(subject.indexOf("-") + 1));
            }
        }

        dataHolder.setData("subjects", subjects);
        dataHolder.setData("course_class", courseClass);
        returnRequest.retrieveData(true);
    }
}
