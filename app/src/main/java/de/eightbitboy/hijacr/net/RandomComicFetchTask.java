package de.eightbitboy.hijacr.net;

import com.crashlytics.android.Crashlytics;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class RandomComicFetchTask extends AbstractFetchTask {

	private String targetUrl;
	private String imageQuery;
	private String randomQuery;

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
			if (randomQuery == null) {
			}

			Document page = Jsoup.connect(targetUrl).get();
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
