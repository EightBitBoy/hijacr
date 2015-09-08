package de.eightbitboy.hijacr.data;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Handles the comic viewing state and updates the comic view.
 */
public class ComicUpdater {
	private ImageView comicView;
	private ComicData comicData;
	private String currentComicPage;
	private ComicPathResolver pathResolver;

	/**
	 * Creates a new ComicUpdater intended for starting a new comic.
	 *
	 * @param comicView
	 * @param comicData
	 */
	public ComicUpdater(ImageView comicView, ComicData comicData) {
		this.comicView = comicView;
		this.comicData = comicData;
		this.currentComicPage = comicData.getFirstPageUrl();
		this.pathResolver = new ComicPathResolver();
	}

	/**
	 * Creates a new ComicUpdater intended for continuing a comic.
	 *
	 * @param comicView
	 * @param comicData
	 * @param comicPage
	 */
	public ComicUpdater(ImageView comicView, ComicData comicData, String comicPage) {
		this.comicView = comicView;
		this.comicData = comicData;
		this.currentComicPage = comicPage;
		this.pathResolver = new ComicPathResolver();
	}

	public void loadComic() {

	}

	public void loadNextComic() {
		ImageLoader.getInstance().displayImage(pathResolver.getComicSource(), comicView);
	}

	public void loadPreviousComic() {

	}
}
