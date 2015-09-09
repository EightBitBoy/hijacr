package de.eightbitboy.hijacr.data;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import de.eightbitboy.hijacr.data.comic.ComicData;

/**
 * Handles the comic viewing state and updates the comic view.
 */
public class ComicUpdater {
	private ImageView comicView;
	private ComicPathResolver pathResolver;

	private ComicData comicData;

	private Document comicPage;
	private String comicPageUrl;
	private String comicSource;

	/**
	 * Creates a new ComicUpdater intended for starting a new comic.
	 *
	 * @param comicView
	 * @param comicData
	 */
	public ComicUpdater(ImageView comicView, ComicData comicData) {
		this.comicView = comicView;
		this.comicData = comicData;
		this.comicPageUrl = comicData.getFirstPageUrl();
		this.pathResolver = new ComicPathResolver();
		loadPage();
		loadComic(comicSource);
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
		this.comicPageUrl = comicPage;
		this.pathResolver = new ComicPathResolver();
		loadPage();
		loadComic(comicSource);
	}

	public void loadPage() {
		//TODO do this asynchronous!
		try {
			comicPage = Jsoup.connect(comicPageUrl).get();
		} catch (IOException e) {

		}

		comicSource = pathResolver.getComicSource(comicPage, comicData.getImageQuery());
	}

	public void loadComic(String url) {
		ImageLoader.getInstance().displayImage(url, comicView);
	}
}
