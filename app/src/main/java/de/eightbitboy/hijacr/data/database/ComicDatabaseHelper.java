package de.eightbitboy.hijacr.data.database;

import android.content.Context;

/**
 * A helper class for working with the ComicDatabase class.
 */
@Deprecated
public class ComicDatabaseHelper {
	private ComicDatabase database;

	public ComicDatabaseHelper(Context context) {
		database = ComicDatabase.getInstance(context);
	}

	public String getProgressUrl(int id) {
		return database.getProgressUrl(id);
	}

	public int getProgressNumber(int id) {
		return database.getProgressNumber(id);
	}

	public void setProgressUrl(int id, String progressUrl) {
		database.insert(id, progressUrl, 0);
	}

	public void setProgressNumber(int id, int progressNumber) {
		//TODO use null instead of empty string?
		database.insert(id, "", progressNumber);
	}
}
