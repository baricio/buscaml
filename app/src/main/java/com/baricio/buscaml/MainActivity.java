package com.baricio.buscaml;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baricio.buscaml.fw.CreateTO;
import com.baricio.buscaml.task.PaginacaoTask;
import com.baricio.buscaml.task.ResultTask;
import com.baricio.buscaml.to.TOPaginacao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editBusca;
    private TextView txtTotal;
    private Button btnBusca;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editBusca = (EditText) findViewById(R.id.editBusca);
        txtTotal = (TextView) findViewById(R.id.txtTotal);
        btnBusca = (Button) findViewById(R.id.btnBusca);
        btnBusca.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        obterArmazem();
    }

    public void obterArmazem() {

        pd = new ProgressDialog(this);
        pd.setMessage("Carregando...");
        pd.show();

        txtTotal.setText(String.valueOf(0));
        ResultTask pt = new ResultTask(editBusca.getText().toString()) {
            @Override
            protected void onPostExecute(String result) {

                pd.hide();

                if (result != null) {

                    TOPaginacao toPaginacao = CreateTO.getTOPaginacao(result);
                    txtTotal.setText(String.valueOf(toPaginacao.getTotal()));

                }

            }
        };

        pt.execute("");

    }



}
