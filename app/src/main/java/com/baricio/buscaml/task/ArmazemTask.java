package com.baricio.buscaml.task;

import android.os.AsyncTask;

import com.baricio.buscaml.fw.Constant;
import com.baricio.buscaml.fw.Util;
import com.baricio.buscaml.to.TOArmazem;
import com.baricio.buscaml.to.TOBase;
import com.baricio.buscaml.to.TOPaginacao;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabricio on 21/06/2015.
 */
public class ArmazemTask extends AsyncTask<String, Void, TOArmazem> {

    private String nomeProduto;

    public ArmazemTask(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    @Override
    protected TOArmazem doInBackground(String... params) {

            TOArmazem t = null;

            try {

                URL url = new URL(Constant.API.OBTER_PRODUTOS + this.nomeProduto);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("GET");
                http.setRequestProperty("Content-Type", "text/plain; charset=utf-8");

                InputStream i = new BufferedInputStream(http.getInputStream());
                String result = Util.readString(i);
                t = TOArmazem.createByJson(result, TOArmazem.class);

            } catch (Exception e) {
                t = null;
            }

            return t;
    }
}


