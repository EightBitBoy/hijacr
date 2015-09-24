package de.eightbitboy.hijacr.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import de.eightbitboy.hijacr.R;

public class SettingsFragment extends PreferenceFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}
