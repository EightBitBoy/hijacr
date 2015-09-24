package de.eightbitboy.hijacr;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import io.fabric.sdk.android.Fabric;

public class HijacrApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		Fabric.with(this, new Crashlytics());

		//TODO enable disk caching and configure other stuff
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
		ImageLoader.getInstance().init(config);
	}
}
