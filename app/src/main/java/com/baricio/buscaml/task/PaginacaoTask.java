package com.baricio.buscaml.task;

import android.os.AsyncTask;

import com.baricio.buscaml.fw.Constant;
import com.baricio.buscaml.fw.Util;
import com.baricio.buscaml.to.TOPaginacao;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Fabricio on 21/06/2015.
 */
public class PaginacaoTask extends AsyncTask<String, Void, TOPaginacao> {

    private String nomeProduto;

    public PaginacaoTask(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    @Override
    protected TOPaginacao doInBackground(String... params) {
        TOPaginacao t = null;
        JSONObject j;

        try {

            URL url = new URL(Constant.API.OBTER_PRODUTOS + this.nomeProduto);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type", "text/plain; charset=utf-8");

            InputStream i = new BufferedInputStream(http.getInputStream());
            String result = Util.readString(i);


            j = new JSONObject(result);

            t = TOPaginacao.createByJson(result, TOPaginacao.class);

        } catch (Exception e) {
            t = null;
        }

        return t;
    }

}
