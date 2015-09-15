package de.eightbitboy.hijacr.data;

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
	private String previousQuery;
	private String nextQuery;
	private ComicManager manager;

	private String previousUrl;
	private String nextUrl;

	public ComicFetchTask(String targetUrl, String imageQuery, String previousQuery,
			String nextQuery, ComicManager manager) {
		this.targetUrl = targetUrl;
		this.imageQuery = imageQuery;
		this.previousQuery = previousQuery;
		this.nextQuery = nextQuery;
		this.manager = manager;
	}

	@Override
	protected String doInBackground(Void... voids) {
		Logger.d("target url: " + targetUrl);

		try {
			Document page = Jsoup.connect(targetUrl).get();

			Elements previous = page.select(previousQuery);
			previousUrl = previous.attr("abs:href");

			Elements next = page.select(nextQuery);
			nextUrl = next.attr("abs:href");

			Elements image = page.select(imageQuery);
			return image.attr("abs:src");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String s) {
		manager.onGetImageSource(s, previousUrl, nextUrl);
	}
}
