package de.eightbitboy.hijacr.net;

public class RandomComicFetchTask extends AbstractFetchTask {

	private FetchTaskListener listener;

	public RandomComicFetchTask(String url, String randomQuery) {

	}

	@Override
	protected String doInBackground(Void... voids) {
		return null;
	}

	@Override
	protected void onPostExecute(String s) {
		//listener.onFetchTaskFinished(s, targetUrl, previousUrl, nextUrl);
	}
}
