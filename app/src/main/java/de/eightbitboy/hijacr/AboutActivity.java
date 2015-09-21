package de.eightbitboy.hijacr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AboutActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_about);

		HtmlTextView view;

		view = (HtmlTextView) findViewById(R.id.about_text);
		view.setHtmlFromString(readFromFile("about.html"),
				new HtmlTextView.LocalImageGetter());

		view = (HtmlTextView) findViewById(R.id.changelog_text);
		view.setHtmlFromString(readFromFile("changelog.html"),
				new HtmlTextView.LocalImageGetter());
	}

	private String readFromFile(String fileName) {
		try {
			InputStream in = getAssets().open(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line;
			StringBuilder builder = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}

			reader.close();
			in.close();

			return builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
