package de.eightbitboy.hijacr.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import de.eightbitboy.hijacr.data.dao.Comic;
import de.eightbitboy.hijacr.data.dao.ComicDao;
import de.eightbitboy.hijacr.data.dao.DaoMaster;
import de.eightbitboy.hijacr.data.dao.DaoSession;

public class Database {
	public static String DB_NAME = "comics.dao.db";

	private static Database instance;

	private Context context;

	private SQLiteDatabase db;

	private DaoMaster master;

	private Database(Context context) {
		this.context = context;
		setUp();
		insertDefaultData();
	}

	public static synchronized Database getInstance(Context context) {
		if (instance == null) {
			instance = new Database(context);
		}

		return instance;
	}

	private void setUp() {
		db = new DaoMaster.DevOpenHelper(context, DB_NAME, null).getWritableDatabase();
		master = new DaoMaster(db);
	}

	/**
	 * Populate the empty database with comic data.
	 */
	private void insertDefaultData() {
		if (getAllComics().isEmpty()) {
			ComicDao dao = createSessionDao();
			Comic comic;

			comic = new Comic(
					4L,
					"extralife",
					"ExtraLife",
					"http://www.myextralife.com/",
					"http://www.myextralife.com/comic/06172001/",
					"",
					".comic",
					".prev_comic_link",
					".next_comic_link",
					false);
			dao.insert(comic);
		}
	}

	public List<Comic> getAllComics() {
		return createSessionDao().loadAll();
	}

	public Comic getComicById(long id) {
		return createSessionDao().load(id);
	}

	private ComicDao createSessionDao() {
		return master.newSession().getComicDao();
	}
}