package de.eightbitboy.hijacr.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.crashlytics.android.Crashlytics;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import de.eightbitboy.hijacr.data.dao.Comic;
import de.eightbitboy.hijacr.events.ComicViewUpdateEvent;
import de.greenrobot.event.EventBus;

/**
 * Handles the comic viewing state and updates the comic view.
 */
public class ComicViewerManager {
	public static final int PROGRESS_MAX = 100;

	private Database db;
	private ImageView comicView;
	private ProgressBar progressBar;
	private Comic comic;
	private int currentComicCount = 0;
	private String currentComicUrl;
	private String previousComicUrl;
	private String nextComicUrl;
	private SimpleImageLoadingListener loadListener;
	private ImageLoadingProgressListener progressListener;

	/**
	 * Creates a new ComicViewerManager intended for starting a new comic.
	 *
	 * @param comicView
	 * @param comic
	 */
	public ComicViewerManager(Context context, ImageView comicView, ProgressBar progressBar,
			Comic comic) {
		this.db = Database.getInstance(context);
		this.comicView = comicView;
		this.progressBar = progressBar;
		this.comic = comic;

		progressBar.setProgress(PROGRESS_MAX);

		if (comic.getLastViewedUrl() != null) {
			this.currentComicUrl = comic.getLastViewedUrl();
		} else {
			this.currentComicUrl = comic.getFirstUrl();
		}

		setUpImageListeners();
	}

	private void setUpImageListeners() {
		loadListener = new SimpleImageLoadingListener() {
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				EventBus.getDefault().post(new ComicViewUpdateEvent());
				progressBar.setVisibility(View.INVISIBLE);
			}
		};

		progressListener = new ImageLoadingProgressListener() {
			@Override
			public void onProgressUpdate(String imageUri, View view, int current, int total) {
				progressBar.setVisibility(View.VISIBLE);
			}
		};
	}

	public void clearComic() {
		comicView.setImageBitmap(null);
	}

	public void loadCurrentComic() {
		Crashlytics.setString("currentComicUrl", currentComicUrl);
		fetchComicUrl(currentComicUrl);
	}

	public void loadNextComic() {
		Crashlytics.setString("nextComicUrl", nextComicUrl);
		currentComicCount++;
		/*
		database.setProgressNumber(comicData.getId(), currentComicCount);
		*/
		fetchComicUrl(nextComicUrl);

		if (nextComicUrl != null) {
			currentComicUrl = nextComicUrl;
			/*
			database.setProgressUrl(comicData.getId(), currentComicUrl);
			*/
		}
	}

	public void loadPreviousComic() {
		Crashlytics.setString("previousComicUrl", previousComicUrl);
		currentComicCount--;
		/*
		database.setProgressNumber(comicData.getId(), currentComicCount);
		*/
		fetchComicUrl(previousComicUrl);

		if (previousComicUrl != null) {
			currentComicUrl = previousComicUrl;
			/*
			database.setProgressUrl(comicData.getId(), currentComicUrl);
			*/
		}
	}

	private void fetchComicUrl(String url) {
		new ComicFetchTask(url, comic.getImageQuery(), comic.getPreviousQuery(),
				comic.getNextQuery(), this).execute();
	}

	public void onGetImageSource(String source, String previousComicUrl, String nextComicUrl) {
		this.previousComicUrl = previousComicUrl;
		this.nextComicUrl = nextComicUrl;
		ImageLoader.getInstance().displayImage(source, comicView, null, loadListener,
				progressListener);
	}

	/**
	 * This callback is executed when a SimpleComicFetchTask returns successfully.
	 * It loads the image from the given url into the comic ImageView.
	 *
	 * @param source The image URL.
	 */
	public void onGetSimpleImageSource(String source) {
		ImageLoader.getInstance().displayImage(source, comicView, null, loadListener,
				progressListener);
	}
}
