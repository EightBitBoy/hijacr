package de.eightbitboy.hijacr;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;

import de.eightbitboy.hijacr.data.dao.Comic;

public class Statistics {
	public static void logView(String name) {
		logCustom(new CustomEvent("ShowView")
				.putCustomAttribute("Name", name));
	}

	public static void logFetch(Comic comic) {
		logCustom(new CustomEvent("ComicLoad")
				.putCustomAttribute("Comic", comic.getKey()));
	}

	public static void logCustom(CustomEvent event) {
		Answers.getInstance().logCustom(event);
	}
}
