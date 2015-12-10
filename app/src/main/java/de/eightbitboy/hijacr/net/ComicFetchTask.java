package de.eightbitboy.hijacr.net;

import com.crashlytics.android.Crashlytics;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ComicFetchTask extends AbstractFetchTask {


	private String targetUrl;
	private String imageQuery;
	private String previousQuery;
	private String nextQuery;
	private String randomQuery;

	private String previousUrl;
	private String nextUrl;
	private String randomUrl;


	public ComicFetchTask(String targetUrl, String imageQuery, String previousQuery,
			String nextQuery, String randomQuery, FetchTaskListener listener) {
		this.targetUrl = targetUrl;
		this.imageQuery = imageQuery;
		this.previousQuery = previousQuery;
		this.nextQuery = nextQuery;
		this.randomQuery = randomQuery;
		this.listener = listener;
	}

	@Override
	protected String doInBackground(Void... voids) {
		try {
			Crashlytics.setString("targetUrl", targetUrl);
			Document page = Jsoup.connect(targetUrl).get();

			if (randomQuery != null) {
				Elements random = page.select(randomQuery);
				randomUrl = random.attr("abs:href");
				targetUrl = randomUrl;
			}

			Elements previous = page.select(previousQuery);
			previousUrl = previous.attr("abs:href");

			Elements next = page.select(nextQuery);
			nextUrl = next.attr("abs:href");

			Elements image = page.select(imageQuery);
			return image.attr("abs:src");
		} catch (IOException e) {
			Crashlytics.logException(e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String s) {
		listener.onFetchTaskFinished(s, targetUrl, previousUrl, nextUrl);
	}
}
