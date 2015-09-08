package de.eightbitboy.hijacr.data;

import android.widget.ImageView;

public class ComicUpdater {
	private ImageView imageView;
	private ComicData comicData;
	private String currentComicPage;

	/**
	 * @param imageView
	 * @param comicData
	 */
	public ComicUpdater(ImageView imageView, ComicData comicData) {
		this.imageView = imageView;
		this.comicData = comicData;
		this.currentComicPage = comicData.getFirstPageUrl();
	}

	/**
	 * @param imageView
	 * @param comicData
	 * @param comicPage
	 */
	public ComicUpdater(ImageView imageView, ComicData comicData, String comicPage) {
		this.imageView = imageView;
		this.comicData = comicData;
		this.currentComicPage = comicPage;
	}
}
