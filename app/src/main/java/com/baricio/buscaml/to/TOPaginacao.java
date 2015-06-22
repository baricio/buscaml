package com.baricio.buscaml.to;

import android.util.Log;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Fabricio on 21/06/2015.
 */
public class TOPaginacao extends TOBase{

    private JSONObject result;
    private JSONObject paging;
    private int total;
    private int offset;
    private int limit;


    public TOPaginacao(){

    }

    public TOPaginacao(JSONObject result){
        this.result = result;
        getData();
    }

    private void getData(){
        try {
            JSONObject paging = this.result.getJSONObject("paging");
            setLimit(paging.getInt("limit"));
            setTotal(paging.getInt("total"));
            setOffset(paging.getInt("offset"));
        }catch (Exception e){
            Log.e("TOPaginacao",e.getMessage());
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
