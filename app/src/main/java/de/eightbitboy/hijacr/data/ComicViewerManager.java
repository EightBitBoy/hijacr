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
import com.orhanobut.logger.Logger;

import de.eightbitboy.hijacr.data.dao.Comic;
import de.eightbitboy.hijacr.events.ComicViewUpdateEvent;
import de.eightbitboy.hijacr.fragments.ComicViewerFragment;
import de.greenrobot.event.EventBus;

/**
 * Handles the comic viewing state and updates the comic view.
 */
public class ComicViewerManager {

	private Database db;
	private Comic comic;
	ComicViewerFragment viewer;
	private String currentComicUrl;
	private String previousComicUrl;
	private String randomComicUrl;
	private String nextComicUrl;
	private SimpleImageLoadingListener loadListener;
	private ImageLoadingProgressListener progressListener;

	/**
	 * Creates a new ComicViewerManager intended for starting a new comic.
	 */
	public ComicViewerManager(Context context, Comic comic, ComicViewerFragment viewer) {
		this.db = Database.getInstance(context);
		this.viewer = viewer;
		this.comic = comic;

		if (comic.getRecentUrl() != null) {
			this.currentComicUrl = comic.getRecentUrl();
		} else {
			this.currentComicUrl = comic.getFirstUrl();
		}

		setUpImageListeners();
		setBackButtonState();
	}

	private void setUpImageListeners() {
		loadListener = new SimpleImageLoadingListener() {
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				EventBus.getDefault().post(new ComicViewUpdateEvent());
				viewer.hideProgressBar();
			}
		};

		progressListener = new ImageLoadingProgressListener() {
			@Override
			public void onProgressUpdate(String imageUri, View view, int current, int total) {
				viewer.showProgressBar();
			}
		};
	}

	public void clearComic() {
		viewer.getImageView().setImageBitmap(null);
	}

	public void loadCurrentComic() {
		Crashlytics.setString("currentComicUrl", currentComicUrl);
		fetchComicUrl(currentComicUrl);
		saveProgress();
		setBackButtonState();
	}

	public void loadPreviousComic() {
		Crashlytics.setString("previousComicUrl", previousComicUrl);

		fetchComicUrl(previousComicUrl);

		if (previousComicUrl != null) {
			currentComicUrl = previousComicUrl;
			saveProgress();
			setBackButtonState();
		}
	}

	public void loadRandomComic() {
		Crashlytics.setString("randomComicUrl", randomComicUrl);

		//fetchComicUrl(comic.getRandomUrl());
	}

	public void loadNextComic() {
		Crashlytics.setString("nextComicUrl", nextComicUrl);

		fetchComicUrl(nextComicUrl);

		if (nextComicUrl != null) {
			currentComicUrl = nextComicUrl;
			saveProgress();
			setBackButtonState();
		}
	}

	private void fetchComicUrl(String url) {
		new ComicFetchTask(url, comic.getImageQuery(), comic.getPreviousQuery(),
				comic.getNextQuery(), comic.getRandomQuery(), this).execute();
	}

	public void onGetImageSource(String source, String previousComicUrl, String nextComicUrl) {
		this.previousComicUrl = previousComicUrl;
		this.nextComicUrl = nextComicUrl;
		ImageLoader.getInstance().displayImage(source, viewer.getImageView(), null, loadListener,
				progressListener);
	}

	private void saveProgress() {
		comic.setRecentUrl(currentComicUrl);
		db.updateComic(comic);
	}

	private void setBackButtonState() {
		if (currentComicUrl.equals(comic.getFirstUrl())) {
			viewer.disableBackButton();
		} else {
			viewer.enbaleBackButton();
		}
	}
}
