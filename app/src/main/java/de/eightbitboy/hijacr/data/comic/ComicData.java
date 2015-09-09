package de.eightbitboy.hijacr.data.comic;

/**
 * Information about a comic.
 */
public class ComicData {
    /**
     * The comic's title.
     */
    private String title;

    /**
     * The comic's website URL.
     */
    private String baseUrl;

    /**
     * The comic's first page URL.
     */
    private String firstPageUrl;

    /**
     * The Jsoup query for the image URL.
     */
    private String imageQuery;

    /**
     * The Jsoup query for the URL to ne next comic site.
     */
    private String nextQuery;

    /**
     * The Jsoup query for the URL to the previous comic site.
     */
    private String previousQuery;

    /**
     * Should be true if the comic page URL ends with a countable integer.
     * Example: "http://xkcd.com/1", "http://xkcd.com/2", "http://xkcd.com/3" ...
     */
    private boolean countable = false;

    /**
     * The comic page base URL if the comic is countable. It does not contain the counter integer.
     * Example: for "http://xkcd.com/1" the countableUrl should be "http://xkcd.com/"
     */
    private String countableUrl = null;

    public ComicData(String title, String baseUrl, String firstPageUrl, String imageQuery, String nextQuery, String previousQuery) {
        this.title = title;
        this.baseUrl = baseUrl;
        this.firstPageUrl = firstPageUrl;
        this.imageQuery = imageQuery;
        this.nextQuery = nextQuery;
        this.previousQuery = previousQuery;
    }

    public ComicData(String title, String baseUrl, String firstPageUrl, String imageQuery, String nextQuery, String previousQuery, boolean countable, String countableUrl) {
        this(title, baseUrl, firstPageUrl, imageQuery, nextQuery, previousQuery);
        this.countable = countable;
        this.countableUrl = countableUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getFirstPageUrl() {
        return this.firstPageUrl;
    }

    public String getImageQuery() {
        return this.imageQuery;
    }

    public String getNextQuery() {
        return this.nextQuery;
    }

    public String getPreviousQuery() {
        return this.previousQuery;
    }
}
