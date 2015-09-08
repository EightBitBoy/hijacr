package de.eightbitboy.hijacr.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private void initializeComics() {
		comics.put("xkcd", new ComicData("XKCD", "http://xkcd.com/", "http://xkcd.com/1/",
				"div[id=comic] img[src]", "ul[class=comicNav] a[rel=next]",
				"ul[class=comicNav] a[rel=prev]"));
		comics.put("extralife", new ComicData("ExtraLife", "http://www.myextralife.com/",
				"http://www.myextralife.com/comic/06172001/", "div[class=comic_container] img[src]",
				"a[class=next_comic_link]", "a[class=prev_comic_link]"));
	}

	public static ComicData getComicData(String key) {
		return getInstance().comics.get(key);
	}

	public static Map<String, ComicData> getAllComicData() {
		return getInstance().comics;
	}

	public static List<ComicData> getComicList() {
		return new ArrayList<ComicData>(getInstance().comics.values());
	}
}
