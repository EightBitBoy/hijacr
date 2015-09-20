package de.eightbitboy.hijacr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

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
			InputStream inputStream = getApplicationContext().getAssets().open("about.html");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			StringBuilder builder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}

			reader.close();
			inputStream.close();

			TextView textView = (TextView) findViewById(R.id.about_text);
			//TODO use custom tag handler?
			textView.setText(Html.fromHtml(builder.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
