package com.baricio.buscaml.to;

import java.util.List;

/**
 * Created by Fabricio on 21/06/2015.
 */
public class TOArmazem extends TOBase {

    private List<TOProduto> results;

    public List<TOProduto> getResults() {
        return results;
    }

    public void setResults(List<TOProduto> results) {
        this.results = results;
    }
}
