package de.eightbitboy.hijacr.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import de.eightbitboy.hijacr.data.dao.Comic;
import de.eightbitboy.hijacr.data.dao.ComicDao;
import de.eightbitboy.hijacr.data.dao.DaoMaster;

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
					1L,
					"xkcd",
					"XKCD",
					"http://xkcd.com/",
					"https://xkcd.com/1/",
					null,
					null,
					"#comic img[src]",
					".comicNav a[rel=prev]",
					".comicNav a[rel=next]",
					false);
			dao.insert(comic);

			comic = new Comic(
					2L,
					"smbc",
					"SMBC",
					"http://www.smbc-comics.com/",
					"http://www.smbc-comics.com/index.php?id=1",
					null,
					null,
					"#comic",
					".prev",
					".next",
					false);
			dao.insert(comic);

			comic = new Comic(
					3L,
					"vgcats",
					"VgCats",
					"http://www.vgcats.com/",
					"http://www.vgcats.com/comics/?strip_id=0",
					null,
					null,
					"tbody div img[width]",
					"a:has(img[src=back.gif])",
					"a:has(img[src=next.gif])",
					false);
			dao.insert(comic);

			comic = new Comic(
					4L,
					"extralife",
					"ExtraLife",
					"http://www.myextralife.com/",
					"http://www.myextralife.com/comic/06172001/",
					null,
					null,
					".comic",
					".prev_comic_link",
					".next_comic_link",
					false);
			dao.insert(comic);

			comic = new Comic(
					5L,
					"explosm",
					"Explosm",
					"http://explosm.net/",
					"http://explosm.net/comics/15",
					null,
					null,
					"#main-comic",
					".previous-comic",
					".next-comic",
					false);
			dao.insert(comic);
		}
	}

	private ComicDao createSessionDao() {
		return master.newSession().getComicDao();
	}

	public List<Comic> getAllComics() {
		return createSessionDao().loadAll();
	}

	public Comic getComicById(long id) {
		return createSessionDao().load(id);
	}

	public void updateComic(Comic comic) {
		createSessionDao().update(comic);
	}
}
