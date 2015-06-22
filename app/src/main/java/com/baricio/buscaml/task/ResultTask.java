package com.baricio.buscaml.task;

import android.os.AsyncTask;

import com.baricio.buscaml.fw.Constant;
import com.baricio.buscaml.fw.Util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Fabricio on 22/06/2015.
 */
public class ResultTask extends AsyncTask<String, Void, String> {

    private String nomeProduto;

    public ResultTask(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    @Override
    protected String doInBackground(String... params) {

        String s = null;

        try {
            String query = URLEncoder.encode(this.nomeProduto, "utf-8");
            URL url = new URL(Constant.API.OBTER_PRODUTOS + query);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type", "text/plain; charset=utf-8");

            InputStream i = new BufferedInputStream(http.getInputStream());
            s = Util.readString(i);

        } catch (Exception e) {
            s = null;
        }

        return s;
    }
}
