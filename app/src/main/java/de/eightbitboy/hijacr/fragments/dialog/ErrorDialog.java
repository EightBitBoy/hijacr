package de.eightbitboy.hijacr.fragments.dialog;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ErrorDialog extends DialogFragment {
	static ErrorDialog newInstance(String message) {
		ErrorDialog dialog = new ErrorDialog();

		Bundle args = new Bundle();
		args.putString("message", message);
		dialog.setArguments(args);

		return dialog;
	}
}
