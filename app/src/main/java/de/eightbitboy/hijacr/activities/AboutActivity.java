package de.eightbitboy.hijacr.activities;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.eightbitboy.hijacr.R;
import de.eightbitboy.hijacr.Statistics;

public class AboutActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Statistics.logView("About");

		setContentView(R.layout.activity_about);

		String version = "";
		try {
			version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		HtmlTextView view;
		String content;

		view = (HtmlTextView) findViewById(R.id.about_text);
		content = readFromFile("about.html");
		assert content != null;
		content = content.replace("VERSION_NUMBER", version);
		view.setHtmlFromString(content, new HtmlTextView.LocalImageGetter());

		view = (HtmlTextView) findViewById(R.id.changelog_text);
		content = readFromFile("changelog.html");
		view.setHtmlFromString(content, new HtmlTextView.LocalImageGetter());

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
