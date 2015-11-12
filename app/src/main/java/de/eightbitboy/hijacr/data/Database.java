package de.eightbitboy.hijacr.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Collections;
import java.util.Comparator;
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
					"http://c.xkcd.com/random/comic/",
					"https://xkcd.com/1/",
					null,
					null,
					"#comic img[src]",
					".comicNav a[rel=prev]",
					".comicNav a[rel=next]",
					null,
					false,
					false);
			dao.insert(comic);

			comic = new Comic(
					2L,
					"smbc",
					"SMBC",
					"http://www.smbc-comics.com/",
					null,
					"http://www.smbc-comics.com/index.php?id=1",
					null,
					null,
					"#comic",
					".prev",
					".next",
					".random",
					false,
					false);
			dao.insert(comic);

			comic = new Comic(
					3L,
					"vgcats",
					"VgCats",
					"http://www.vgcats.com/",
					null,
					"http://www.vgcats.com/comics/?strip_id=0",
					null,
					null,
					"tbody div img[width]",
					"a:has(img[src=back.gif])",
					"a:has(img[src=next.gif])",
					null,
					false,
					false);
			dao.insert(comic);

			comic = new Comic(
					4L,
					"extralife",
					"ExtraLife",
					"http://www.myextralife.com/",
					null,
					"http://www.myextralife.com/comic/06172001/",
					null,
					null,
					".comic",
					".prev_comic_link",
					".next_comic_link:containsOwn(previous)",
					".prev_comic_link:containsOwn(random)",
					false,
					false);
			dao.insert(comic);

			comic = new Comic(
					5L,
					"explosm",
					"Explosm",
					"http://explosm.net/",
					null, //TODO
					"http://explosm.net/comics/15",
					null,
					null,
					"#main-comic",
					".previous-comic",
					".next-comic",
					null, //TODO
					false,
					false);
			dao.insert(comic);

			comic = new Comic(
					6L,
					"dilbert",
					"Dilbert",
					"http://dilbert.com/",
					null, //TODO
					"http://dilbert.com/strip/1989-04-16",
					null,
					null,
					".img-comic",
					".nav-left a",
					".nav-right a",
					null, //TODO
					false,
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

	public List<Comic> getAllComicsSortedAlphabetically() {
		List<Comic> comics = createSessionDao().loadAll();

		Collections.sort(comics, new Comparator<Comic>() {
			@Override
			public int compare(Comic lhs, Comic rhs) {
				return lhs.getTitle().compareToIgnoreCase(rhs.getTitle());
			}
		});

		return comics;
	}

	public Comic getComicById(long id) {
		return createSessionDao().load(id);
	}

	public void updateComic(Comic comic) {
		createSessionDao().update(comic);
	}
}
