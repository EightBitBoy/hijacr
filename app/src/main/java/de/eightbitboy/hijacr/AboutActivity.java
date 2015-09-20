package de.eightbitboy.hijacr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

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

		//TODO use custom tag handler to support lists?
		//http://stackoverflow.com/questions/3150400/html-list-tag-not-working-in-android-textview-what-can-i-do
		//https://github.com/NightWhistler/HtmlSpanner
		TextView textView;

		textView = (TextView) findViewById(R.id.about_text);
		textView.setText(Html.fromHtml(readFromFile("about.html")));

		textView = (TextView) findViewById(R.id.changelog_text);
		textView.setText(Html.fromHtml(readFromFile("changelog.html")));

		HtmlTextView htmlTextView = (HtmlTextView) findViewById(R.id.html_text);
		htmlTextView.setHtmlFromString(readFromFile("about.html"),
				new HtmlTextView.LocalImageGetter());
	}

	private String readFromFile(String fileName) {
		//TODO use guava for all this stuff?
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
