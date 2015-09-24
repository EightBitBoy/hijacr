package de.eightbitboy.hijacr.data;

import android.content.Context;

public class SettingsManager {

	private static SettingsManager instance;

	private Context context;

	private SettingsManager(Context context) {
		this.context = context;
	}

	public static SettingsManager getInstance(Context context) {
		if (instance == null) {
			instance = new SettingsManager(context);
		}

		return instance;
	}
}
