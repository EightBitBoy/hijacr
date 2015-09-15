package de.eightbitboy.hijacr.data.database;

import android.os.AsyncTask;

import com.orhanobut.logger.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import de.eightbitboy.hijacr.data.ComicManager;

public class ComicFetchTask extends AsyncTask<Void, Void, String> {

	private String targetUrl;
	private String imageQuery;
	private ComicManager manager;

	public ComicFetchTask(String baseUrl, int number, String imageQuery, ComicManager manager) {
		this.targetUrl = baseUrl + number;
		this.imageQuery = imageQuery;
		this.manager = manager;
	}

	@Override
	protected String doInBackground(Void... voids) {
		Logger.d("target url: " + targetUrl);

		try {
			Document page = Jsoup.connect(targetUrl).get();
			Elements image = page.select(imageQuery);
			return image.attr("abs:src");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String s) {
		manager.onGetSimpleImageSource(s);
	}
}
