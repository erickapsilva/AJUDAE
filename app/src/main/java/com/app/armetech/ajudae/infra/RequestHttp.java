package com.app.armetech.ajudae.infra;

//Classe de requisição de informações do AVA
//É um singleton

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestHttp {
    //Declara a classe como estática
    private static final RequestHttp ourInstance = new RequestHttp();
    //Cria o construtor do singleton
    public static RequestHttp getInstance() { return ourInstance; }
    private RequestHttp() { }

    //OkHttp:
    private OkHttpClient okHttpClient;
    private Request request;
    //Variáveis:
    private static final String TAG;

    static {
        TAG = RequestHttp.class.getName();
    }

    //Função de inicialização de cliente
    public void initializeClient() {
        okHttpClient = new OkHttpClient();
    }

    //Faz a requisição pra obter o token do AVA, necessita de ter login e senha
    public void getToken(String usrAVA, String passwdAVA, final ReturnRequest returnRequest) {

        //Constrói o URL com os parâmetros necessários
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://ava.ufrpe.br/login/token.php").newBuilder();
        urlBuilder.addQueryParameter("username", usrAVA);
        urlBuilder.addQueryParameter("password", passwdAVA);
        urlBuilder.addQueryParameter("service","moodle_mobile_app");
        String url = urlBuilder.build().toString();

        request = new Request.Builder().url(url).build();

        //Faz a requisição passando o URL + parâmetros
        //Retorna os dados (erro ou acerto) de maneira assíncrona
        okHttpClient.newCall(request).enqueue(new Callback() {

            //Caso falhe
            @Override
            public void onFailure(Call call, IOException e) {
                //Chama a interface e passa nulo pra mostrar que houve alguma falha
                returnRequest.retrieveData(null);
                Log.i(TAG, e.getMessage());
            }

            //Caso funcione
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Pega as informações do body()
                //O body() pode ser usado APENAS UMA VEZ, após transformar em String, não poderá
                //ser usado novamente, caso se tente, ocorrerá em erro.
                //É necessário armazenar a informação localmente
                String result = response.body().string();
                try {
                    //Cria um JSONObject e joga dentro dele as informações recebidas
                    JSONObject jsonObj = new JSONObject(result);
                    String token = jsonObj.getString("token");
                    //Chama a interface e passa a informação (assim que ela for recebida)
                    returnRequest.retrieveData(token);
                } catch (Exception e) {
                    Log.i(TAG, e.getMessage());
                    Log.i(TAG, "LOGIN INVÁLIDO!" );
                    //Passa nulo caso essa informação esteja com algum erro (ou não exista o login)
                    returnRequest.retrieveData(null);
                }
            }
        });

    }

    //Faz a requisição do ID de usuário
    //É necessário que o token tenha sido recebido previamente, caso contrário ele não poderá
    //fazer a requisição
    public void getUsrId(String usrToken, final ReturnRequest returnRequest) {

        //usa a mesma lógica do getToken()
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
                    //Log.i(TAG, usrId);
                    returnRequest.retrieveData(jsonObj);
                } catch (Exception e) {
                    Log.i(TAG, e.getMessage());
                }
            }
        });

    }

    //Faz a requisição final, que traz os cursos como resultado
    //É necessário que se passe o token e o ID de usuário, logo para que essa requisição seja chamada
    //é preciso que as outras duas tenham sido feitas
    public void getInfo(String usrToken, String usrId, final ReturnRequest returnRequest) {

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
                    JSONArray jsonArr = new JSONArray(result);
                    JSONObject jsonObj = jsonArr.getJSONObject(0);
                    returnRequest.retrieveData(jsonObj);
                } catch (Exception e) {
                    Log.i(TAG, e.getMessage());
                }

            }
        });
    }

}

