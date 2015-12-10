package de.eightbitboy.hijacr.activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import de.eightbitboy.hijacr.R;
import de.eightbitboy.hijacr.Statistics;
import de.eightbitboy.hijacr.fragments.dialog.ErrorDialog;

public class DebugActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Statistics.logView("Debug");

		setContentView(R.layout.activity_debug);

		Button doSomethingButton = (Button) findViewById(R.id.do_something_button);
		doSomethingButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				doSomething();
			}
		});

	}

	private void doSomething() {
		DialogFragment dialog = ErrorDialog.newInstance("Doing something!");
		dialog.show(getSupportFragmentManager().beginTransaction(), "dialog");
	}
}
