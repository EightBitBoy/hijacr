package de.eightbitboy.hijacr.data;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;

import de.eightbitboy.hijacr.MainActivity;

public class ComicFetcher extends AsyncTask<Void, Void, Void> {
    private MainActivity activity;
    private ImageView imageView;
    private int comicNumber;

    //TODO remove the view from this class
    public ComicFetcher(MainActivity activity, ImageView imageView, int comicNumber) {
        this.activity = activity;
        this.imageView = imageView;
        this.comicNumber = comicNumber;
    }

    @Override
    protected Void doInBackground(Void... params) {
        final String URL = "http://xkcd.com/";
        Bitmap bitmap;


        try {
            Document page = Jsoup.connect(URL + comicNumber).get();
            Elements img = page.select("div[id=comic] img[src]");
            String imgSrc = img.attr("src");
            imgSrc = "http:" + imgSrc;
            InputStream input = new java.net.URL(imgSrc).openStream();
            bitmap = BitmapFactory.decodeStream(input);

            Log.wtf("foo", page.toString());
            Log.wtf("foo", img.toString());
            Log.wtf("foo", imgSrc);

            final Bitmap finalBitmap = bitmap;

            if (bitmap != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(finalBitmap);
                    }
                });
            }
        } catch (IOException e) {
            new AlertDialog.Builder(activity)
                    .setTitle("Oooops!")
                    .setMessage(e.toString())
                    .create()
                    .show();

            e.printStackTrace();
        }

        return null;
    }
}
