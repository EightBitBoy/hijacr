package de.eightbitboy.hijacr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AboutActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_about);

		try {
			StringBuilder builder = new StringBuilder();
			InputStream inputStream = getApplicationContext().getAssets().open("about.html");
			//inputStream = getApplicationContext().getResources().openRawResource(R.raw.about);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			String s;
			while ((s = reader.readLine()) != null) {
				builder.append(s);
			}

			reader.close();
			inputStream.close();

			TextView textView = (TextView) findViewById(R.id.about_text);

			Logger.wtf(builder.toString());
			textView.setText(builder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
