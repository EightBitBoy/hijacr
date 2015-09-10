package de.eightbitboy.hijacr.data;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import de.eightbitboy.hijacr.data.comic.SimpleComicData;

/**
 * Handles the comic viewing state and updates the comic view.
 */
public class ComicManager {
	private ImageView comicView;
	private SimpleComicData comicData;
	private Document comicPage;
	private int comicCount = 1;

	/**
	 * Creates a new ComicManager intended for starting a new comic.
	 *
	 * @param comicView
	 * @param comicData
	 */
	public ComicManager(ImageView comicView, SimpleComicData comicData) {
		this.comicView = comicView;
		this.comicData = comicData;
		loadPage();
	}

	private void loadPage() {
		//TODO do this asynchronous!

		/*
		try {
			comicPage = Jsoup.connect(comicData.baseUrl + comicCount).get();
			Logger.d(comicPage.toString());
		} catch (IOException e) {

		}
		*/
	}

	public void loadNextComic() {

	}

	public void loadPreviousComic() {

	}

	/*
	public void loadComic(String url) {
		ImageLoader.getInstance().displayImage(url, comicView);
	}
	*/


}
