package de.eightbitboy.hijacr.data.database;

import android.provider.BaseColumns;

public class ComicDatabaseContract {

	public ComicDatabaseContract() {
	}

	public static abstract class ComicDataEntry implements BaseColumns {
		public static final String TABLE_NAME = "comicdata";
		public static final String COLUMN_KEY = "title";
		public static final String COLUMN_PROGRESS_URL = "progressurl";
		public static final String COLUMN_PROGRESS_NUMBER = "progressnumber";

		//TODO add some statements for creating and deleting table
		public static final String CREATE_TABLE = "";
	}
}
