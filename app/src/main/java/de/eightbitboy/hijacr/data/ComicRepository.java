package de.eightbitboy.hijacr.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.eightbitboy.hijacr.data.comic.ComicData;
import de.eightbitboy.hijacr.data.comic.SimpleComicData;

public class ComicRepository {
	private static ComicRepository instance;

	private Map<String, SimpleComicData> comics = new HashMap<String, SimpleComicData>();

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
		comics.put("smbc", new SimpleComicData("SMBC", "http://www.smbc-comics.com/", "http://www" +
				".smbc-comics.com/index.php?id=", "#comic"));
		//TODO vgcats
		comics.put("xkcd", new SimpleComicData("XKCD", "http://xkcd.com/", "http://xkcd.com/",
				"div[id=comic] img[src]"));
	}

	public static SimpleComicData getComicData(String key) {
		return getInstance().comics.get(key);
	}

	public static Map<String, SimpleComicData> getAllComicData() {
		return getInstance().comics;
	}

	public static List<SimpleComicData> getComicList() {
		return new ArrayList<SimpleComicData>(getInstance().comics.values());
	}
}
