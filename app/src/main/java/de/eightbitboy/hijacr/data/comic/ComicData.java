package de.eightbitboy.hijacr.data.comic;

//TODO use getters and stuff

/**
 * Contains data for a comic whose page URLs can be counted easily.
 */
public class ComicData {
	/**
	 * The comic's title.
	 */
	public String title;

	/**
	 * The comic's website URL.
	 */
	public String url;

	/**
	 * The URL for a simple comic page, without the page number.
	 * http://www.examplecomic.com/comicId=
	 */
	public String baseUrl;

	/**
	 * The URL to the first page of the comic.
	 */
	public String firstUrl;

	/**
	 * The number of the first comic. It is only used when baseUrl is given.
	 */
	public int firstNumber;

	/**
	 * The jsoup query for getting the comic img element.
	 */
	public String imageQuery;

	/**
	 * The jsoup query for getting the anchor with the href to the previous page.
	 */
	public String previousQuery;

	/**
	 * The jsoup query for getting the anchor with the href to the next page.
	 */
	public String nextQuery;

	private boolean simple = false;

	//TODO for simple
	public ComicData(String title, String url, String baseUrl, int firstNumber, String
			imageQuery) {
		this.title = title;
		this.url = url;
		this.baseUrl = baseUrl;
		this.firstNumber = firstNumber;
		this.imageQuery = imageQuery;
		this.simple = true;
	}

	//TODO for full
	public ComicData() {
		this.simple = false;
	}

	//TODO improve cleanup
	public String getCleanUrl() {
		String cleanUrl;
		cleanUrl = url.replaceAll("/", "");
		cleanUrl = cleanUrl.replaceAll("http", "");
		cleanUrl = cleanUrl.replaceAll(":", "");
		cleanUrl = cleanUrl.replaceAll("www.", "");
		return cleanUrl;
	}
}
