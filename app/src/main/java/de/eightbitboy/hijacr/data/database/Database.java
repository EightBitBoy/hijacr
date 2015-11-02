package de.eightbitboy.hijacr.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import de.eightbitboy.hijacr.data.dao.Comic;
import de.eightbitboy.hijacr.data.dao.ComicDao;
import de.eightbitboy.hijacr.data.dao.DaoMaster;
import de.eightbitboy.hijacr.data.dao.DaoSession;

public class Database {
	//TODO rename as soon as old database code is removed
	public static String DB_NAME = "comics.db.new";

	private Context context;

	private SQLiteDatabase db;

	private DaoMaster master;

	public Database(Context context) {
		this.context = context;
		initialize();
	}

	private void initialize() {
		db = new DaoMaster.DevOpenHelper(context, DB_NAME, null).getWritableDatabase();
		master = new DaoMaster(db);
	}

	public Comic getComicById(long id) {
		DaoSession session = master.newSession();
		ComicDao dao = session.getComicDao();
		return dao.load(id);
	}
}
