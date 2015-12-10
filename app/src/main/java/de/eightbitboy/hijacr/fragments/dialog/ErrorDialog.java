package de.eightbitboy.hijacr.fragments.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.eightbitboy.hijacr.R;

public class ErrorDialog extends DialogFragment {

	private static final String MESSAGE_KEY = "message";

	public static ErrorDialog newInstance(String message) {
		ErrorDialog dialog = new ErrorDialog();

		Bundle args = new Bundle();
		args.putString(MESSAGE_KEY, message);
		dialog.setArguments(args);

		return dialog;
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle("Error")
				.setMessage(getArguments().getString(MESSAGE_KEY))
				.setNeutralButton("ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dismiss();
					}
				});

		return builder.create();
	}
}
