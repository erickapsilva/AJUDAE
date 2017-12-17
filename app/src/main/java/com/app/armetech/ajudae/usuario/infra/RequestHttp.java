package com.app.armetech.ajudae.usuario.infra;

/**
 * Created by erickapsilva on 16/12/2017.
 */

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestHttp {
    private static final RequestHttp ourInstance = new RequestHttp();

    public static RequestHttp getInstance() {
        return ourInstance;
    }

    private RequestHttp() {
    }

    //OkHttp plugin declarations:
    private OkHttpClient okHttpClient;
    private Request request;
    //Variable declarations:
    private static final String TAG;

    static {
        TAG = RequestHttp.class.getName();
    }

    public void initializeClient() {
        okHttpClient = new OkHttpClient();
    }

    public void getToken(String usrAVA, String passwdAVA, final ReturnRequest returnRequest) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://ava.ufrpe.br/login/token.php").newBuilder();
        urlBuilder.addQueryParameter("username", usrAVA);
        urlBuilder.addQueryParameter("password", passwdAVA);
        urlBuilder.addQueryParameter("service","moodle_mobile_app");
        String url = urlBuilder.build().toString();

        request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                returnRequest.retrieveData(null);
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    String token = jsonObj.getString("token");
                    returnRequest.retrieveData(token);
                } catch (Exception e) {
                    Log.i(TAG, e.getMessage());
                    Log.i(TAG, "LOGIN INVÁLIDO!" );
                    returnRequest.retrieveData(null);
                }
            }
        });

    }

    public void getUsrId(String usrToken, final ReturnRequest returnRequest) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://ava.ufrpe.br/webservice/rest/server.php?moodlewsrestformat=json").newBuilder();
        urlBuilder.addQueryParameter("wsfunction", "core_webservice_get_site_info");
        urlBuilder.addQueryParameter("wstoken", usrToken);
        String url = urlBuilder.build().toString();

        request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                returnRequest.retrieveData(null);
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    String usrId = jsonObj.getString("userid");
                    //Log.i(TAG, usrId);
                    returnRequest.retrieveData(usrId);
                } catch (Exception e) {
                    Log.i(TAG, e.getMessage());
                }
            }
        });

    }

    public void getCourseList(String usrToken, String usrId, final ReturnRequest returnRequest) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://ava.ufrpe.br/webservice/rest/server.php?moodlewsrestformat=json").newBuilder();
        urlBuilder.addQueryParameter("wsfunction", "core_user_get_users_by_id");
        urlBuilder.addQueryParameter("wstoken", usrToken);
        urlBuilder.addQueryParameter("userids[0]", usrId);
        String url = urlBuilder.build().toString();

        request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                returnRequest.retrieveData(null);
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();

                try {
                    JSONArray jsonObj = new JSONArray(result);

                    returnRequest.retrieveData(jsonObj);
                } catch (Exception e) {
                    Log.i(TAG, e.getMessage());
                }

            }
        });
    }

}

