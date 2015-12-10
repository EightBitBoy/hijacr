package de.eightbitboy.hijacr.fragments.dialog;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.eightbitboy.hijacr.R;

public class ErrorDialog extends DialogFragment {

	public static ErrorDialog newInstance(String message) {
		ErrorDialog dialog = new ErrorDialog();

		Bundle args = new Bundle();
		args.putString("message", message);
		dialog.setArguments(args);

		return dialog;
	}

	//TODO see http://developer.android.com/guide/topics/ui/dialogs.html

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);

		View view = inflater.inflate(R.layout.error_dialog, container);
		TextView textView = (TextView) view.findViewById(R.id.error_message);
		textView.setText(getArguments().getString("message"));

		return view;
	}
}
