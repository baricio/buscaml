package com.baricio.buscaml;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.baricio.buscaml.adapter.ProdutoAdapter;
import com.baricio.buscaml.fw.CreateTO;
import com.baricio.buscaml.task.ArmazemTask;
import com.baricio.buscaml.task.PaginacaoTask;
import com.baricio.buscaml.task.ResultTask;
import com.baricio.buscaml.to.TOArmazem;
import com.baricio.buscaml.to.TOPaginacao;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    private EditText editBusca;
    private ListView listaProdutos;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaProdutos = (ListView) findViewById(R.id.listaProdutos);
        editBusca = (EditText) findViewById(R.id.editBusca);
        editBusca.setOnEditorActionListener(this);
        editBusca.setFocusableInTouchMode(true);
        editBusca.requestFocus();
    }

    public void obterArmazem() {

        pd = new ProgressDialog(this);
        pd.setMessage("Carregando...");
        pd.show();

        ArmazemTask pt = new ArmazemTask(editBusca.getText().toString()) {
            @Override
            protected void onPostExecute(TOArmazem toArmazem) {

                pd.hide();

                if (toArmazem != null) {

                    ProdutoAdapter produtoAdapter = new ProdutoAdapter(toArmazem,MainActivity.this);
                    listaProdutos.setAdapter(produtoAdapter);
                }

            }
        };

        pt.execute("");

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        boolean handled = false;
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            obterArmazem();
            handled = true;
        }

        return handled;
    }
}
