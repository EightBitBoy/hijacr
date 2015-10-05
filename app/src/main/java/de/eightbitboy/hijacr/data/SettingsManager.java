package de.eightbitboy.hijacr.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import de.eightbitboy.hijacr.R;

//TODO use https://github.com/orhanobut/hawk for this? or https://github.com/grandcentrix/tray?
public class SettingsManager {

	public static final String PREF_LAST_COMIC = "pref_last_comic";

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

	public int getLastComicId() {
		return preferences.getInt(PREF_LAST_COMIC, 1);
	}

	public void setLastComicId(int value) {
		edit().putInt(PREF_LAST_COMIC, value).commit();
	}

	private SharedPreferences.Editor edit() {
		return preferences.edit();
	}
}
