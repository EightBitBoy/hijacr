package de.eightbitboy.hijacr.net;

public class RandomComicFetchTask extends AbstractFetchTask {

	private FetchTaskListener listener;

	private String targetUrl;
	private String imageQuery;
	private String randomQuery;

	public RandomComicFetchTask(String targetUrl, String imageQuery, String randomQuery) {
		this.targetUrl = targetUrl;
		this.imageQuery = imageQuery;
		this.randomQuery = randomQuery;
	}

	@Override
	protected String doInBackground(Void... voids) {
		if (targetUrl != null) {

		} else {
			
		}

		return null;
	}

	@Override
	protected void onPostExecute(String s) {
		//listener.onFetchTaskFinished(s, targetUrl, previousUrl, nextUrl);
	}
}
