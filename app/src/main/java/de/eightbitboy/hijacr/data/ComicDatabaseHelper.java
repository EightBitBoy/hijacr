package de.eightbitboy.hijacr.data;

import android.content.Context;

/**
 * A helper class for working with the ComicDatabse class.
 */
public class ComicDatabaseHelper {
	private ComicDatabase database;

	public ComicDatabaseHelper(Context context) {
		database = new ComicDatabase(context);
	}

	//TODO implement queries
}
