package de.eightbitboy.hijacr.data.comic;

//TODO use getters and stuff

/**
 * Contains data for a comic whose page URLs can be counted easily.
 */
public class SimpleComicData {
	public String title;

	/**
	 * The comic's website URL.
	 */
	public String url;

	public String baseUrl;

	public String imageQuery;

	public SimpleComicData(String url, String baseUrl, String imageQuery) {
		this.url = url;
		this.baseUrl = baseUrl;
		this.imageQuery = imageQuery;
	}
}
