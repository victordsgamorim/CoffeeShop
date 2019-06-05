package com.dorset.coffeeshop.ui.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class CoffeeImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    ImageView image;
    Bitmap bitmap;

    public CoffeeImageAsyncTask(ImageView image) {

        this.image = image;
    }


    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];

        bitmap = null;

        try {
            InputStream urlConnection = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(urlConnection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        image.setImageBitmap(bitmap);
    }
}
