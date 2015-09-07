package de.eightbitboy.hijacr.data;

/**
 * Information about a comic.
 */
public class ComicData {
    /**
     * The comic's title.
     */
    private String title;

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

    public ComicData(String title, String imageQuery, String nextQuery, String previousQuery){
        this.title = title;
        this.imageQuery = imageQuery;
        this.nextQuery = nextQuery;
        this.previousQuery = previousQuery;
    }

    public String getTitle(){
        return this.title;
    }

    public String getImageQuery(){
        return this.imageQuery;
    }

    public String getNextQuery(){
        return this.nextQuery;
    }

    public String getPreviousQuery(){
        return this.previousQuery;
    }
}
