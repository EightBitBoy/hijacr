package de.eightbitboy.hijacr.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

import org.apache.http.impl.conn.LoggingSessionOutputBuffer;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.eightbitboy.hijacr.R;
import de.eightbitboy.hijacr.data.ComicManager;
import de.eightbitboy.hijacr.events.ComicSelectedEvent;
import de.eightbitboy.hijacr.events.ComicViewUpdateEvent;
import de.greenrobot.event.EventBus;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ComicViewerFragment extends Fragment {

	@Bind(R.id.comic_view)
	ImageView comicView;
	@Bind(R.id.older_button)
	Button olderButton;
	@Bind(R.id.newer_button)
	Button newerButton;

	private ComicManager comicManager;
	private PhotoViewAttacher attacher;

	@Override
	public void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);
	}

	@Override
	public void onStop() {
		EventBus.getDefault().unregister(this);
		super.onStop();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.comic_viewer, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		attacher = new PhotoViewAttacher(comicView);
		setUpButtonActions();
	}

	@SuppressWarnings("unused")
	public void onEvent(ComicSelectedEvent event) {
		comicManager = new ComicManager(comicView, event.comicData);
	}

	@SuppressWarnings("unused")
	public void onEvent(ComicViewUpdateEvent event) {
		attacher.update();
	}

	private void setUpButtonActions() {
		newerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				comicManager.loadNextComic();
			}
		});

		olderButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				comicManager.loadPreviousComic();
			}
		});
	}
}
