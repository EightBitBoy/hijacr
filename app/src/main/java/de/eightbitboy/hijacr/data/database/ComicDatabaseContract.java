package de.eightbitboy.hijacr.data.database;

import android.provider.BaseColumns;

public class ComicDatabaseContract {

	public ComicDatabaseContract() {
	}

	public static abstract class ComicDataEntry implements BaseColumns {
		private static final String TEXT_TYPE = " TEXT";
		private static final String COMMA_SEP = ",";

		public static final String TABLE_NAME = "comicdata";
		public static final String COLUMN_KEY = "title";
		public static final String COLUMN_PROGRESS_URL = "progressurl";
		public static final String COLUMN_PROGRESS_NUMBER = "progressnumber";

		public static final String CREATE_TABLE =
				"CREATE TABLE " + TABLE_NAME + " (" +
						_ID + " INTEGER PRIMARY KEY," +
						COLUMN_KEY + TEXT_TYPE + COMMA_SEP +
						COLUMN_PROGRESS_URL + TEXT_TYPE + COMMA_SEP + " )";

		public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
	}
}
