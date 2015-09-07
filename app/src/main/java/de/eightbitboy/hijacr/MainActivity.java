package de.eightbitboy.hijacr;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.eightbitboy.hijacr.data.ComicFetcher;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.comic_view) ImageView comicView;
    @Bind(R.id.older_button) Button olderButton;
    @Bind(R.id.newer_button) Button newerButton;

    private int comicCounter = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO move this to application class
        Logger.init("foo");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
        olderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new ComicFetcher(MainActivity.this, comicView, comicCounter).execute();
                        comicCounter--;
                    }
                });
            }
        });

        newerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new ComicFetcher(MainActivity.this, comicView, comicCounter).execute();
                        comicCounter++;
                    }
                });
            }
        });
    }
}
