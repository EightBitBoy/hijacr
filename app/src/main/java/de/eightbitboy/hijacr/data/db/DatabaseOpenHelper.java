package de.eightbitboy.hijacr.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import de.eightbitboy.hijacr.data.dao.DaoMaster;

public class DatabaseOpenHelper extends DaoMaster.OpenHelper {
	public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
		super(context, name, factory);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
