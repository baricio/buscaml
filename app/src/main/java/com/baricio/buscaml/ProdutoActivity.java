package com.baricio.buscaml;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.ClientCertRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ProdutoActivity extends Activity {

    private WebView webProduto;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        String link = getIntent().getStringExtra("link");
        webProduto = (WebView) findViewById(R.id.webProduto);
        dialog = new ProgressDialog(ProdutoActivity.this);

        openWebSite(link);
    }

    private void openWebSite(String link){
        webProduto.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        dialog.setMessage("Carregando Produto...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        webProduto.loadUrl(link);

        WebSettings webSettings = webProduto.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }



}
