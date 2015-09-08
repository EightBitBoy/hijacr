package de.eightbitboy.hijacr.data;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ComicPathResolver {
	public String getComicSource(Document page, String imageQuery) {
		Elements img = page.select(imageQuery);
		return img.attr("src");
	}
}
