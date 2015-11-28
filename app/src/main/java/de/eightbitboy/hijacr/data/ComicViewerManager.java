package de.eightbitboy.hijacr.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.orhanobut.logger.Logger;

import de.eightbitboy.hijacr.data.dao.Comic;
import de.eightbitboy.hijacr.data.db.Database;
import de.eightbitboy.hijacr.events.ComicViewUpdateEvent;
import de.eightbitboy.hijacr.fragments.ComicViewerFragment;
import de.eightbitboy.hijacr.net.ComicFetchTask;
import de.greenrobot.event.EventBus;

/**
 * Handles the comic viewing state and updates the comic view.
 */
public class ComicViewerManager {

	private Database db;
	private Comic comic;
	ComicViewerFragment viewer;
	private String currentImageUrl;
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

		if (comic.getRecentImageUrl() != null) {
			this.currentImageUrl = comic.getRecentImageUrl();
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

		boolean success;
		success = fetchComicUrl(currentComicUrl);
		if (success) {
			//TODO fetch currentImageUrl if not null

			saveProgress();
			setBackButtonState();
		}
	}

	public void loadPreviousComic() {
		Crashlytics.setString("previousComicUrl", previousComicUrl);

		boolean success;
		success = fetchComicUrl(previousComicUrl);

		if (success && previousComicUrl != null) {
			currentComicUrl = previousComicUrl;
			saveProgress();
			setBackButtonState();
		}
	}

	public void loadRandomComic() {
		Crashlytics.setString("randomComicUrl", randomComicUrl);

		fetchRandomComicUrl();

		saveProgress(randomComicUrl);
		setBackButtonState();
	}

	public void loadNextComic() {
		Crashlytics.setString("nextComicUrl", nextComicUrl);

		boolean success;
		success = fetchComicUrl(nextComicUrl);

		if (success && nextComicUrl != null) {
			currentComicUrl = nextComicUrl;
			saveProgress();
			setBackButtonState();
		}
	}

	private boolean fetchComicUrl(String url) {
		if (!isUrlValid("in method fetchComicUrl", url)) {
			viewer.showErrorMessage("Error while trying to access an invalid URL. The developer " +
					"is aware of this problem and working on it.");
			return false;
		}

		new ComicFetchTask(url, comic.getImageQuery(), comic.getPreviousQuery(),
				comic.getNextQuery(), this).execute();

		return true;
	}

	private void fetchRandomComicUrl() {

	}

	/**
	 * Called by a finished {@link ComicFetchTask}.
	 *
	 * @param imageUrl         The URL to the requested image.
	 * @param requestedUrl     The URL used by the ComicFetchTask. It is the URL for the current
	 *                         comic.
	 * @param previousComicUrl The URL to the previous comic page.
	 * @param nextComicUrl     The URL to the next comic page.
	 */
	public void onGetImageSource(String imageUrl, String requestedUrl, String previousComicUrl,
			String nextComicUrl) {
		this.previousComicUrl = previousComicUrl;
		this.nextComicUrl = nextComicUrl;
		this.randomComicUrl = requestedUrl;
		this.currentImageUrl = imageUrl;

		verifyUrls();

		ImageLoader.getInstance().displayImage(imageUrl, viewer.getImageView(), null, loadListener,
				progressListener);
	}

	private void saveProgress() {
		comic.setRecentUrl(currentComicUrl);
		comic.setRecentImageUrl(currentImageUrl);
		db.updateComic(comic);
	}

	private void saveProgress(String url) {
		comic.setRecentUrl(url);
		db.updateComic(comic);
	}

	private void setBackButtonState() {
		if (currentComicUrl.equals(comic.getFirstUrl())) {
			viewer.disableBackButton();
		} else {
			viewer.enbaleBackButton();
		}
	}

	private void verifyUrls() {
		boolean isValid = true;

		isValid = isValid && isUrlValid("currentImageUrl", this.currentImageUrl);
		isValid = isValid && isUrlValid("nextComicUrl", this.nextComicUrl);
		
		if (!this.currentComicUrl.equals(comic.getFirstUrl())) {
			isValid = isValid && isUrlValid("previousComicUrl", this.previousComicUrl);
		}

		//TODO do something with this information
	}

	private boolean isUrlValid(String urlName, String url) {
		if (url != null && !url.isEmpty()) {
			return true;
		}

		Logger.e("Comic: " + comic.getKey() + " Invalid url # " + urlName + " #" + url);
		Crashlytics.log(Log.ERROR, "HIJACR",
				"Comic: " + comic.getKey() + " Invalid url # " + urlName + " #" + url);
		return false;
	}
}
