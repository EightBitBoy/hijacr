package de.eightbitboy.hijacr.data;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ComicFetcher extends AsyncTask<Void, Void, Void> {
	private FragmentActivity activity;
	private ImageView imageView;
	private int comicNumber;
	private ComicData comicData;

	//TODO remove the view from this class
	public ComicFetcher(FragmentActivity activity, ImageView imageView, int comicNumber, ComicData comicData) {
		this.activity = activity;
		this.imageView = imageView;
		this.comicNumber = comicNumber;
		this.comicData = comicData;
	}

	@Override
	protected Void doInBackground(Void... params) {
		final String imageUrl = getImageUrl();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				fetchImage(imageUrl, imageView);
			}
		});

		return null;
	}

	private String getImageUrl() {
		try {
			Document page = Jsoup.connect(comicData.getBaseUrl() + comicNumber).get();
			Elements img = page.select(comicData.getImageQuery());
			String imgSrc = img.attr("src");
			imgSrc = "http:" + imgSrc;

			Logger.d("Image url: " + imgSrc);

			return imgSrc;
		} catch (IOException e) {
			Logger.e("Fetching the image failed!");
			e.printStackTrace();
		}

		return null;
	}

	private void fetchImage(String imageUrl, ImageView imageView) {
		ImageLoader.getInstance().displayImage(imageUrl, imageView);

		/*
		ImageLoader.getInstance().loadImage(imageUrl, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				// Do whatever you want with Bitmap
			}
		});
		*/
	}
}
