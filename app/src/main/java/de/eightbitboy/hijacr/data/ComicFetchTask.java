package de.eightbitboy.hijacr.data;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ComicFetchTask extends AsyncTask<Void, Void, String> {

	private String targetUrl;
	private String imageQuery;

	public ComicFetchTask(String baseUrl, int number, String imageQuery) {
		this.targetUrl = baseUrl + number;
		this.imageQuery = imageQuery;
	}

	@Override
	protected String doInBackground(Void... voids) {
		try {
			Document page = Jsoup.connect(targetUrl).get();
			Elements image = page.select(imageQuery);
			return image.attr("src");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String s) {
		//TODO
		super.onPostExecute(s);
	}
}
