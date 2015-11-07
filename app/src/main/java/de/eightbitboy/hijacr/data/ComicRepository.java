package de.eightbitboy.hijacr.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.eightbitboy.hijacr.data.comic.ComicData;

public class ComicRepository {
	private static ComicRepository instance;

	private Map<String, ComicData> comics = new HashMap<String, ComicData>();

	private ComicRepository() {
		initializeComics();
	}

	public static synchronized ComicRepository getInstance() {
		if (instance == null) {
			instance = new ComicRepository();
		}

		return instance;
	}

	//TODO a comic might start at position 0 like vgcats!
	private void initializeComics() {
		/*
		comics.put("explosm", new ComicData(5, "explosm", "Explosm", "http://explosm.net/",
				"http://explosm.net/comics/15", "#main-comic", ".previous-comic",
				".next-comic"));
		comics.put("smbc", new ComicData(2, "smbc", "SMBC", "http://www.smbc-comics.com/",
				"http://www.smbc-comics.com/index.php?id=", 1, "#comic"));
		comics.put("vgcats", new ComicData(3, "vgcats", "VgCats", "http://www.vgcats.com/",
				"http://www.vgcats.com/comics/?strip_id=", 0, "tbody div img[width]"));
		comics.put("xkcd", new ComicData(1, "xkcd", "XKCD", "http://xkcd.com/",
				"http://xkcd.com/", 1, "#comic img[src]"));
		*/
	}

	public static ComicData getComicData(String key) {
		return getInstance().comics.get(key);
	}

	public static ComicData getComicData(int id) {
		for (Map.Entry<String, ComicData> entry : getInstance().comics.entrySet()) {
			if (entry.getValue().getId() == id) {
				return entry.getValue();
			}
		}

		return null;
	}

	public static Map<String, ComicData> getAllComicData() {
		return getInstance().comics;
	}

	public static List<ComicData> getComicList() {
		return new ArrayList<ComicData>(getInstance().comics.values());
	}
}
