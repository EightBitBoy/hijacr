package de.eightbitboy.hijacr.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import de.eightbitboy.hijacr.data.comic.ComicData;
import de.eightbitboy.hijacr.data.database.ComicDatabaseHelper;
import de.eightbitboy.hijacr.events.ComicViewUpdateEvent;
import de.greenrobot.event.EventBus;

/**
 * Handles the comic viewing state and updates the comic view.
 */
public class ComicManager {
	private ComicDatabaseHelper database;
	private ImageView comicView;
	private ComicData comicData;
	private int currentComicCount = 0;
	private String currentComicUrl;
	private String previousComicUrl;
	private String nextComicUrl;
	private SimpleImageLoadingListener loadListener;
	private ImageLoadingProgressListener progressListener;

	/**
	 * Creates a new ComicManager intended for starting a new comic.
	 *
	 * @param comicView
	 * @param comicData
	 */
	public ComicManager(Context context, ImageView comicView, ComicData comicData) {
		database = new ComicDatabaseHelper(context);

		this.comicView = comicView;
		this.comicData = comicData;

		if (this.comicData.isSimple()) {
			this.currentComicCount = this.comicData.getFirstNumber();
		} else {
			this.currentComicUrl = this.comicData.getFirstUrl();
		}

		int savedComicCount = database.getProgressNumber(this.comicData.getId());
		if (savedComicCount > -1) {
			this.currentComicCount = savedComicCount;
		}

		String savedComicUrl = database.getProgressUrl(this.comicData.getId());
		if (savedComicUrl != null) {
			this.currentComicUrl = savedComicUrl;
			updateUrls();
		}

		setUpImageListeners();
	}

	private void setUpImageListeners() {
		loadListener = new SimpleImageLoadingListener() {
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				EventBus.getDefault().post(new ComicViewUpdateEvent());
			}
		};

		progressListener = new ImageLoadingProgressListener() {
			@Override
			public void onProgressUpdate(String imageUri, View view, int current, int total) {
				//TODO show progress somewhere
			}
		};
	}

	private void updateUrls() {
		if (previousComicUrl == null) {

		}

		if (nextComicUrl == null) {

		}
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
		database.setProgressNumber(comicData.getId(), currentComicCount);
		fetchComicUrl(nextComicUrl);

		if (nextComicUrl != null) {
			currentComicUrl = nextComicUrl;
			database.setProgressUrl(comicData.getId(), currentComicUrl);
		}
	}

	public void loadPreviousComic() {
		Crashlytics.setString("previousComicUrl", previousComicUrl);
		currentComicCount--;
		database.setProgressNumber(comicData.getId(), currentComicCount);
		fetchComicUrl(previousComicUrl);

		if (previousComicUrl != null) {
			currentComicUrl = previousComicUrl;
			database.setProgressUrl(comicData.getId(), currentComicUrl);
		}
	}

	private void fetchComicUrl(String url) {
		if (comicData.isSimple()) {
			new SimpleComicFetchTask(comicData.getBaseUrl(), currentComicCount,
					comicData.getImageQuery(),
					this).execute();
		} else {
			new ComicFetchTask(url, comicData.getImageQuery(), comicData
					.getPreviousQuery(), comicData.getNextQuery(), this).execute();
		}
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
