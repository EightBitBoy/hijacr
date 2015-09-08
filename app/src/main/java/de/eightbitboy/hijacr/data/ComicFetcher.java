package de.eightbitboy.hijacr.data;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;

import de.eightbitboy.hijacr.MainActivity;

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
		Bitmap bitmap;

		try {
			Document page = Jsoup.connect(comicData.getBaseUrl() + comicNumber).get();
			Elements img = page.select(comicData.getImageQuery());
			String imgSrc = img.attr("src");
			imgSrc = "http:" + imgSrc;
			InputStream input = new java.net.URL(imgSrc).openStream();
			bitmap = BitmapFactory.decodeStream(input);

			Logger.d("Image url: " + imgSrc.toString());

			final Bitmap finalBitmap = bitmap;

			if (bitmap != null) {
				activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						imageView.setImageBitmap(finalBitmap);
					}
				});
			}
		} catch (IOException e) {
			Logger.e("Fetching the image failed!");
			e.printStackTrace();
		}

		return null;
	}
}
