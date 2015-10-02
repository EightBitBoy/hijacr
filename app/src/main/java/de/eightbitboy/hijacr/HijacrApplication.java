package de.eightbitboy.hijacr;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import io.fabric.sdk.android.Fabric;

public class HijacrApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		Fabric.with(this, new Crashlytics());

		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true)
				.cacheOnDisk(true)
						//TODO experiment with bitmap config
						//.bitmapConfig(Bitmap.Config.RGB_565)
				.build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
				.memoryCacheExtraOptions(2048, 2048)
						//TODO use getFilesDir?
				.diskCache(new UnlimitedDiskCache(getCacheDir()))
				.diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
				.defaultDisplayImageOptions(options)
				.writeDebugLogs()
				.build();

		ImageLoader.getInstance().init(config);
	}
}
