package de.eightbitboy.hijacr;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView comicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpButtonActions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //TODO async tasks?

    //TODO clean up all this logic
    private void setUpButtonActions() {
        comicView = (ImageView) findViewById(R.id.comic_view);
        Button olderButton = (Button) findViewById(R.id.older_button);
        Button newerButton = (Button) findViewById(R.id.newer_button);

        newerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new ComicFetcher().execute();
                    }
                });
            }
        });
    }

    private class ComicFetcher extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            final String URL = "http://xkcd.com/1/";
            Bitmap bitmap = null;

            try {
                Document page = Jsoup.connect(URL).get();
                //a[class=brand brand-image] img[src]
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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            comicView.setImageBitmap(finalBitmap);
                        }
                    });
                }
            } catch (IOException e) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Oooops!")
                        .setMessage(e.toString())
                        .create()
                        .show();

                e.printStackTrace();
            }

            return null;
        }
    }
}
