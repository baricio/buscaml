package com.baricio.buscaml.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baricio.buscaml.R;
import com.baricio.buscaml.task.DownloadImageTask;
import com.baricio.buscaml.to.TOArmazem;
import com.baricio.buscaml.to.TOProduto;

/**
 * Infla o layout de cada item de produto encontrado
 * Created by Fabricio on 25/06/2015.
 */
public class ProdutoAdapter extends BaseAdapter {

    private TOArmazem armazem;
    private Context context;

    public ProdutoAdapter(TOArmazem armazem, Context context){
        this.armazem = armazem;
        this.context = context;
    }

    @Override
    public int getCount() {
        return armazem.getResults().size();
    }

    @Override
    public Object getItem(int position) {
        return armazem.getResults().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TOProduto produto = armazem.getResults().get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_produto, null);
        TextView titulo = (TextView) v.findViewById(R.id.titulo);
        TextView preco = (TextView) v.findViewById(R.id.preco);

        final ImageView i = (ImageView) v.findViewById(R.id.imagemProduto);

        DownloadImageTask d = new DownloadImageTask() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                i.setImageBitmap(bitmap);
            }
        };

        d.execute(produto.getThumbnail());

        titulo.setText(produto.getTitle());
        preco.setText(produto.getPrice());

        return v;
    }
}

