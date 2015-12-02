package de.eightbitboy.hijacr.net;

import android.os.AsyncTask;

public class AbstractFetchTask extends AsyncTask<Void, Void, String> {
	protected FetchTaskListener listener;

	@Override
	protected String doInBackground(Void... params) {
		return null;
	}

	public interface FetchTaskListener {
		void onFetchTaskFinished(String imageUrl, String requestedUrl, String previousUrl,
				String nextUrl);
	}
}
