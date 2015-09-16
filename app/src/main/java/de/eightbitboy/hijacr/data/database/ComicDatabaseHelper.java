package de.eightbitboy.hijacr.data.database;

import android.content.Context;

import de.eightbitboy.hijacr.data.database.ComicDatabase;

/**
 * A helper class for working with the ComicDatabse class.
 */
public class ComicDatabaseHelper {
	private ComicDatabase database;

	public ComicDatabaseHelper(Context context) {
		database = ComicDatabase.getInstance(context);
	}

	public String getProgressUrl(String key) {
		//TODO
		return null;
	}

	public int getProgressNumber(String key) {
		//TODO
		return 0;
	}

	public void setProgressUrl(String key, String progressUrl) {
		database.insert(key, progressUrl, 0);
	}

	public void setProgressNumber(String key, int progressNumber) {
		database.insert(key, "", progressNumber);
	}
}
