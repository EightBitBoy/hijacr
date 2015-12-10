package de.eightbitboy.hijacr.net;

import com.crashlytics.android.Crashlytics;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class RandomComicFetchTask extends AbstractFetchTask {

	private String targetUrl;
	private String imageQuery;
	private String randomQuery;
	private String randomUrl;

	public RandomComicFetchTask(String targetUrl, String imageQuery, String randomQuery,
			FetchTaskListener listener) {
		this.targetUrl = targetUrl;
		this.imageQuery = imageQuery;
		this.randomQuery = randomQuery;
		this.listener = listener;
	}

	@Override
	protected String doInBackground(Void... voids) {
		try {
			Document page = Jsoup.connect(targetUrl).get();

			if (randomQuery != null) {
				Elements random = page.select(randomQuery);
				randomUrl = random.attr(randomQuery);
			}

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
		//listener.onFetchTaskFinished(s, targetUrl, previousUrl, nextUrl);
	}
}
