package de.eightbitboy.hijacr.data;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.orhanobut.logger.Logger;

import de.eightbitboy.hijacr.data.comic.ComicData;
import de.eightbitboy.hijacr.events.ComicViewUpdateEvent;
import de.greenrobot.event.EventBus;

/**
 * Handles the comic viewing state and updates the comic view.
 */
public class ComicManager {
	private ImageView comicView;
	private ComicData comicData;
	private int currentComicCount = 0;
	private String currentComicUrl;
	private SimpleImageLoadingListener loadListener;
	private ImageLoadingProgressListener progressListener;

	/**
	 * Creates a new ComicManager intended for starting a new comic.
	 *
	 * @param comicView
	 * @param comicData
	 */
	public ComicManager(ImageView comicView, ComicData comicData) {
		this.comicView = comicView;
		this.comicData = comicData;

		if (this.comicData.isSimple()) {
			this.currentComicCount = this.comicData.getFirstNumber();
		} else {
			this.currentComicUrl = this.comicData.getFirstUrl();
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

	public void clearComic() {
		comicView.setImageBitmap(null);
	}

	public void loadCurrentComic() {
		fetchComicUrl();
	}

	public void loadNextComic() {
		currentComicCount++;
		fetchComicUrl();
	}

	public void loadPreviousComic() {
		currentComicCount--;
		fetchComicUrl();
	}

	private void fetchComicUrl() {
		if (comicData.isSimple()) {
			new ComicFetchTask(comicData.getBaseUrl(), currentComicCount, comicData.getImageQuery(),
					this).execute();
		} else {
			
		}
	}

	/**
	 * This callback is executed when a ComicFetchTask returns successfully.
	 * It loads the image from the given url into the comic ImageView.
	 *
	 * @param source The image URL.
	 */
	public void onGetImageSource(String source) {
		Logger.d("image source: " + source);
		ImageLoader.getInstance().displayImage(source, comicView, null, loadListener,
				progressListener);
	}
}
