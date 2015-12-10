package de.eightbitboy.hijacr.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.eightbitboy.hijacr.Statistics;
import de.eightbitboy.hijacr.fragments.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Statistics.logView("Settings");

		getFragmentManager().beginTransaction().replace(android.R.id.content, new
				SettingsFragment()).commit();
	}
}
