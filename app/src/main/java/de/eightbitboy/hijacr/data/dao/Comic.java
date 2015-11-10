package de.eightbitboy.hijacr.data.dao;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "COMIC".
 */
public class Comic {

    private Long id;
    /** Not-null value. */
    private String key;
    /** Not-null value. */
    private String title;
    /** Not-null value. */
    private String url;
    /** Not-null value. */
    private String firstUrl;
    private String lastUrl;
    private String recentUrl;
    /** Not-null value. */
    private String imageQuery;
    /** Not-null value. */
    private String previousQuery;
    /** Not-null value. */
    private String nextQuery;
    private boolean hidden;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Comic() {
    }

    public Comic(Long id) {
        this.id = id;
    }

    public Comic(Long id, String key, String title, String url, String firstUrl, String lastUrl, String recentUrl, String imageQuery, String previousQuery, String nextQuery, boolean hidden) {
        this.id = id;
        this.key = key;
        this.title = title;
        this.url = url;
        this.firstUrl = firstUrl;
        this.lastUrl = lastUrl;
        this.recentUrl = recentUrl;
        this.imageQuery = imageQuery;
        this.previousQuery = previousQuery;
        this.nextQuery = nextQuery;
        this.hidden = hidden;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getKey() {
        return key;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setKey(String key) {
        this.key = key;
    }

    /** Not-null value. */
    public String getTitle() {
        return title;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Not-null value. */
    public String getUrl() {
        return url;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUrl(String url) {
        this.url = url;
    }

    /** Not-null value. */
    public String getFirstUrl() {
        return firstUrl;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setFirstUrl(String firstUrl) {
        this.firstUrl = firstUrl;
    }

    public String getLastUrl() {
        return lastUrl;
    }

    public void setLastUrl(String lastUrl) {
        this.lastUrl = lastUrl;
    }

    public String getRecentUrl() {
        return recentUrl;
    }

    public void setRecentUrl(String recentUrl) {
        this.recentUrl = recentUrl;
    }

    /** Not-null value. */
    public String getImageQuery() {
        return imageQuery;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setImageQuery(String imageQuery) {
        this.imageQuery = imageQuery;
    }

    /** Not-null value. */
    public String getPreviousQuery() {
        return previousQuery;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setPreviousQuery(String previousQuery) {
        this.previousQuery = previousQuery;
    }

    /** Not-null value. */
    public String getNextQuery() {
        return nextQuery;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNextQuery(String nextQuery) {
        this.nextQuery = nextQuery;
    }

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    // KEEP METHODS - put your custom methods here

	public String getSimpleUrl() {
		String simpleUrl;
		simpleUrl = url.replaceAll("/", "");
		simpleUrl = simpleUrl.replaceAll("http", "");
		simpleUrl = simpleUrl.replaceAll(":", "");
		simpleUrl = simpleUrl.replaceAll("www.", "");
		return simpleUrl;
	}
    // KEEP METHODS END

}
