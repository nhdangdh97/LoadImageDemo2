package com.example.nhdangdh.loadimagedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by nhdangdh on 7/10/2017.
 */

public class DownloadTask extends AsyncTask<String, Integer, Bitmap> {

    Bitmap bitmap = null;

    private Context context;

    public interface CallBack {
        void displayImage(Bitmap bitmap);
    }

    CallBack callBack;

    public void registerCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    private void doSomething(Bitmap bitmap) {
        callBack.displayImage(bitmap);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            bitmap = loadUrl(params[0]);
        }catch (Exception e){
            Log.d("Background Task", e.toString());
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        doSomething(bitmap);
    }

    private Bitmap loadUrl(String strUrl) throws IOException{
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            URL url = new URL(strUrl);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.connect();
            in = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
        }catch (Exception e){
            Log.d("Check", e.toString());
        }finally {
            in.close();
        }
        return bitmap;
    }
}