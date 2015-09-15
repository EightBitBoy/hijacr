package de.eightbitboy.hijacr.data.comic;

//TODO use getters and stuff

/**
 * Contains data for a comic whose page URLs can be counted easily.
 */
public class ComicData {
	/**
	 * The comic's title.
	 */
	private String title;

	/**
	 * The comic's website URL.
	 */
	private String url;

	/**
	 * The URL to the first page of the comic.
	 */
	private String firstUrl;

	/**
	 * The URL for a simple comic page, without the page number.
	 * http://www.examplecomic.com/comicId=
	 */
	private String baseUrl;

	/**
	 * The number of the first comic. It is only used when baseUrl is given.
	 */
	private int firstNumber;

	/**
	 * The jsoup query for getting the comic img element.
	 */
	private String imageQuery;

	/**
	 * The jsoup query for getting the anchor with the href to the previous page.
	 */
	private String previousQuery;

	/**
	 * The jsoup query for getting the anchor with the href to the next page.
	 */
	private String nextQuery;

	private boolean simple = false;

	/**
	 * Define "simple" a comic which uses an URL which ends with a
	 * number that can be counted and incremented for accessing comic pages.
	 *
	 * @param title
	 * @param url
	 * @param baseUrl
	 * @param firstNumber
	 * @param imageQuery
	 */
	public ComicData(String title, String url, String baseUrl, int firstNumber, String
			imageQuery) {
		this.title = title;
		this.url = url;
		this.baseUrl = baseUrl;
		this.firstNumber = firstNumber;
		this.imageQuery = imageQuery;
		this.simple = true;
	}

	/**
	 * Define a comic. The URLs for accessing comic pages must be parsed from a page.
	 *
	 * @param title
	 * @param url
	 * @param firstUrl
	 * @param imageQuery
	 * @param previousQuery
	 * @param nextQuery
	 */
	public ComicData(String title, String url, String firstUrl, String imageQuery, String
			previousQuery, String nextQuery) {
		this.title = title;
		this.url = url;
		this.firstUrl = firstUrl;
		this.imageQuery = imageQuery;
		this.previousQuery = previousQuery;
		this.nextQuery = nextQuery;
		this.simple = false;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public String getFirstUrl() {
		return firstUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public int getFirstNumber() {
		return firstNumber;
	}

	public String getImageQuery() {
		return imageQuery;
	}

	public String getPreviousQuery() {
		return previousQuery;
	}

	public String getNextQuery() {
		return nextQuery;
	}

	public boolean isSimple() {
		return simple;
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

	@Override
	public boolean equals(Object o) {
		return o instanceof ComicData && title.equals(((ComicData) o).getTitle());
	}
}
