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
	private int currentComicCount = 0;

	/**
	 * Creates a new ComicManager intended for starting a new comic.
	 *
	 * @param comicView
	 * @param comicData
	 */
	public ComicManager(ImageView comicView, SimpleComicData comicData) {
		this.comicView = comicView;
		this.comicData = comicData;
	}

	public void loadNextComic() {
		currentComicCount++;
		new ComicFetchTask(comicData.baseUrl, currentComicCount, comicData.imageQuery,
				this).execute();
	}

	public void loadPreviousComic() {
		currentComicCount--;
		new ComicFetchTask(comicData.baseUrl, currentComicCount, comicData.imageQuery,
				this).execute();
	}

	public void onGetImageSource(String source) {
		ImageLoader.getInstance().displayImage(source, comicView);
		//TODO add some listener for loading complete event, update image view attacher
	}
}
