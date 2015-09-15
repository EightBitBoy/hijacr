package de.eightbitboy.hijacr.data;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

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
		try {
			Document page = Jsoup.connect(targetUrl).get();
			Elements image = page.select(imageQuery);
			//TODO handle missing "http:"
			return image.attr("abs:src");
			//return "http:" + image.attr("src");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String s) {
		manager.onGetImageSource(s);
	}
}
