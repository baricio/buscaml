package com.baricio.buscaml.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.baricio.buscaml.fw.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Realiza o Download, salva e retorna o bitmap
 * Created by Fabricio on 26/06/2015.
 */
public class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {



    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];
        Bitmap b = obterImagem(url);
        if (b == null) {

            try {
                InputStream in = new java.net.URL(url).openStream();
                b = BitmapFactory.decodeStream(in);

                salvarBitmap(b, url);

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

        }

        return b;
    }

    private void salvarBitmap(Bitmap b, String url) throws Exception {
        String id = Util.md5(url);

        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/imagens/";

        File dir = new File(file_path);
        dir.mkdir();

        Log.i("imagem", "Salvando imagem: " + file_path + id + ".png");

        FileOutputStream out = new FileOutputStream(file_path + id + ".png");
        b.compress(Bitmap.CompressFormat.JPEG, 100, out);
        out.flush();
        out.close();
    }

    private Bitmap obterImagem(String url) {

        String id = Util.md5(url);

        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/imagens/" + id + ".png";

        File f = new File(file_path);

        if (f.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(f.getPath());
            return bitmap;
        } else {
            return null;
        }

    }
}
