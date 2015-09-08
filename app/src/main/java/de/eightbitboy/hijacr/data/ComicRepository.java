package de.eightbitboy.hijacr.data;

import java.util.HashMap;
import java.util.Map;

public class ComicRepository {
	private Map<String, ComicData> comics = new HashMap<String, ComicData>();

	public ComicRepository() {
		initializeComics();
	}

	private void initializeComics() {
		comics.put("xkcd", new ComicData("XKCD", "http://xkcd.com/", "http://xkcd.com/1/",
				"div[id=comic] img[src]", "ul[class=comicNav] a[rel=next]",
				"ul[class=comicNav] a[rel=prev]"));
		comics.put("extralife", new ComicData("ExtraLife", "http://www.myextralife.com/",
				"http://www.myextralife.com/comic/06172001/", "div[class=comic_container] img[src]",
				"a[class=next_comic_link]", "a[class=prev_comic_link]"));
	}

	public ComicData getComicData(String key) {
		return comics.get(key);
	}
}
