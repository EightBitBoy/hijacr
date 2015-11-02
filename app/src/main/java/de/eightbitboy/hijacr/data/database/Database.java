package de.eightbitboy.hijacr.data.database;

import android.content.Context;

import de.eightbitboy.hijacr.data.dao.DaoMaster;

public class Database {
	//TODO rename as soon as old database code is removed
	public static String DB_NAME = "comics.db.new";

	private Context context;

	public void init() {
		new DaoMaster.DevOpenHelper(context, DB_NAME, null);
	}
}
