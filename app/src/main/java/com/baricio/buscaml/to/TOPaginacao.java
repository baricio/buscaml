package com.baricio.buscaml.to;

import java.util.List;

/**
 * Created by Fabricio on 21/06/2015.
 */
public class TOPaginacao extends TOBase{

    private int total;
    private int offset;
    private int limit;

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
