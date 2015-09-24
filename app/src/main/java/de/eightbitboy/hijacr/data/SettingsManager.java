package de.eightbitboy.hijacr.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import de.eightbitboy.hijacr.R;

public class SettingsManager {

	private static SettingsManager instance;

	private SharedPreferences preferences;
	private Resources resources;


	private SettingsManager(Context context) {
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
		resources = context.getResources();
	}

	public static SettingsManager getInstance(Context context) {
		if (instance == null) {
			instance = new SettingsManager(context);
		}

		return instance;
	}

	public boolean isDebugMode() {
		return preferences.getBoolean(resources.getString(R.string.pref_debug), false);
	}
}
