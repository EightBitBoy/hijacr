package de.eightbitboy.hijacr.data.database;

import android.provider.BaseColumns;

public class ComicDatabaseContract {

	public ComicDatabaseContract() {
	}

	public static abstract class ComicDataEntry implements BaseColumns {
		public static final String TABLE_NAME = "comicdata";
		public static final String COLUMN_TITLE = "title";
		public static final String COLUMN_URL = "url";
		public static final String COLUMN_FIRST_URL = "firsturl";
		public static final String COLUMN_BASE_URL = "baseurl";
		public static final String COLUMN_NEXT_QUERY = "nextquery";
		public static final String COLUMN_PREV_QUERY = "prevquery";
		public static final String COLUMN_IMAGE_QUERY = "imagequery";

		//TODO add some statements for creating and deleting table
	}
}
