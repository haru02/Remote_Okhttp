package com.example.froze.remote_okhttp;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String key = "685554457566726f3736674a746c45";
        String serviceName = "PublicParkingAvaliable";
        int begin = 1;
        int end = 5;

        String url = "http://openapi.seoul.go.kr:8088/"+key+"/json/"+serviceName+"/"+begin+"/"+end+"/";
        callHttp(url);
    }

    public void callHttp(String url){
        new AsyncTask<String, Void, String>(){

            @Override
            protected String doInBackground(String... params) {
                String result = "";
                try {
                    result = getData(params[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Log.e("Result", result);
            }
        }.execute(url);
    }

    public String getData(String url) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
