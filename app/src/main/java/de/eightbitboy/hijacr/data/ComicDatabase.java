package de.eightbitboy.hijacr.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Stores information about comics and reading progress in a SQLite database.
 */
public class ComicDatabase extends SQLiteOpenHelper {
	public static final int DATABSE_VERSION = 1;
	public static final String DATABASE_NAME = "comics.db";

	public ComicDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABSE_VERSION);
	}

	//TODO constructor is not used, read in doc about errorhandler and factory
	public ComicDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
