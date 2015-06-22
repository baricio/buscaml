package com.baricio.buscaml.fw;

import com.baricio.buscaml.to.TOArmazem;
import com.baricio.buscaml.to.TOPaginacao;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fabricio on 22/06/2015.
 */
public class CreateTO {

    public static TOArmazem getTOArmazem(String result){
        return TOArmazem.createByJson(result, TOArmazem.class);
    }

    public static TOPaginacao getTOPaginacao(String result){
        try {
            return new TOPaginacao(new JSONObject(result));
        } catch (JSONException e) {
            return new TOPaginacao();
        }
    }

}
