package de.eightbitboy.hijacr.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.eightbitboy.hijacr.R;
import de.eightbitboy.hijacr.Statistics;

public class DebugActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Statistics.logView("Debug");

		setContentView(R.layout.activity_debug);
	}
}
