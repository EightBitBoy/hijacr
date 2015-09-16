package de.eightbitboy.hijacr.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orhanobut.logger.Logger;

import de.eightbitboy.hijacr.data.database.ComicDatabaseContract.ComicDataEntry;

/**
 * Stores information about comics and reading progress in a SQLite database.
 */
public class ComicDatabase extends SQLiteOpenHelper {
	private static ComicDatabase instance;

	public static final int DATABASE_VERSION = 3;
	public static final String DATABASE_NAME = "comics.db";

	private ComicDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
		db.execSQL(ComicDataEntry.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//TODO handle upgrade, do not just delete table
		db.execSQL(ComicDataEntry.DROP_TABLE);
		onCreate(db);
	}

	public void insert(String key, String progressUrl, int progressNumber) {
		ContentValues values = new ContentValues();
		values.put(ComicDataEntry.COLUMN_KEY, key);
		values.put(ComicDataEntry.COLUMN_PROGRESS_URL, progressUrl);
		values.put(ComicDataEntry.COLUMN_PROGRESS_NUMBER, progressNumber);

		long rowId = getWritableDatabase().insert(ComicDataEntry.TABLE_NAME, null, values);
	}

	public int getProgressNumber(String key) {
		String[] projection = {
				ComicDataEntry.COLUMN_KEY,
				ComicDataEntry.COLUMN_PROGRESS_URL,
				ComicDataEntry.COLUMN_PROGRESS_NUMBER
		};

		Cursor cursor = getReadableDatabase().
				rawQuery(
						"select * from " + ComicDataEntry.TABLE_NAME + " where "
								+ ComicDataEntry.COLUMN_KEY + " = ?", new String[]{key});

		Logger.wtf("count: " + cursor.getCount());
		if (cursor.getCount() > 0) {
			Logger.wtf("value: " + cursor.getColumnIndex(ComicDataEntry.COLUMN_PROGRESS_NUMBER));
			cursor.moveToFirst();
			Logger.wtf("value: " + cursor.getInt(3));
		}

		cursor.close();

		//TODO
		return 0;
	}
}
