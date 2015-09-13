package de.eightbitboy.hijacr.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Stores information about comics and reading progress in a SQLite database.
 */
public class ComicDatabase extends SQLiteOpenHelper {
	private static ComicDatabase instance;

	public static final int DATABSE_VERSION = 1;
	public static final String DATABASE_NAME = "comics.db";

	private ComicDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABSE_VERSION);
	}

	public static synchronized ComicDatabase getInstance(Context context) {
		if (instance == null) {
			instance = new ComicDatabase(context);
		}
		return instance;
	}

	//TODO constructor is not used, read in doc about errorhandler and factory
	private ComicDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//TODO create table
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//TODO delete old db
		//TODO create table

		//TODO handle upgrade
	}
}
