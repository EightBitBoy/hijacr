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
	private String currentComicUrl;
	private String previousComicUrl;
	private String nextComicUrl;
	private SimpleImageLoadingListener loadListener;
	private ImageLoadingProgressListener progressListener;

	/**
	 * Creates a new ComicViewerManager intended for starting a new comic.
	 */
	public ComicViewerManager(Context context, ImageView comicView, ProgressBar progressBar,
			Comic comic) {
		this.db = Database.getInstance(context);
		this.comicView = comicView;
		this.progressBar = progressBar;
		this.comic = comic;

		progressBar.setProgress(PROGRESS_MAX);

		if (comic.getRecentUrl() != null) {
			this.currentComicUrl = comic.getRecentUrl();
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
				//TODO use something different than setting visibility?
				progressBar.setVisibility(View.GONE);
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
		saveProgress();
	}

	public void loadNextComic() {
		Crashlytics.setString("nextComicUrl", nextComicUrl);

		fetchComicUrl(nextComicUrl);

		if (nextComicUrl != null) {
			currentComicUrl = nextComicUrl;
			saveProgress();
		}
	}

	public void loadPreviousComic() {
		Crashlytics.setString("previousComicUrl", previousComicUrl);

		fetchComicUrl(previousComicUrl);

		if (previousComicUrl != null) {
			currentComicUrl = previousComicUrl;
			saveProgress();
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

	private void saveProgress() {
		comic.setRecentUrl(currentComicUrl);
		db.updateComic(comic);
	}
}
