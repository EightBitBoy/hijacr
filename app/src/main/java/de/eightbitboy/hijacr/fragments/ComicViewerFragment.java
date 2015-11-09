package de.eightbitboy.hijacr.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.crashlytics.android.Crashlytics;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.eightbitboy.hijacr.R;
import de.eightbitboy.hijacr.data.ComicViewerManager;
import de.eightbitboy.hijacr.data.Database;
import de.eightbitboy.hijacr.data.SettingsManager;
import de.eightbitboy.hijacr.data.dao.Comic;
import de.eightbitboy.hijacr.events.ComicSelectedEvent;
import de.eightbitboy.hijacr.events.ComicViewUpdateEvent;
import de.greenrobot.event.EventBus;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ComicViewerFragment extends Fragment {

	@Bind(R.id.comic_view)
	ImageView comicView;
	@Bind(R.id.progress_bar)
	ProgressBar progressBar;
	@Bind(R.id.older_button)
	Button olderButton;
	@Bind(R.id.newer_button)
	Button newerButton;

	private Comic comic;
	private ComicViewerManager comicViewerManager;
	private SettingsManager settings;
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
		settings = SettingsManager.getInstance(getActivity());
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		attacher = new PhotoViewAttacher(comicView);

		//TODO get comic from settings.getLastComicId()
		comicViewerManager = new ComicViewerManager(getActivity(), comicView, progressBar,
				Database.getInstance(this.getContext()).getComicById(4L));
		//TODO
		//comicViewerManager.loadCurrentComic();

		setUpButtonActions();
	}

	@SuppressWarnings("unused")
	public void onEvent(ComicSelectedEvent event) {
		Logger.wtf("event: " + event.toString());
		if (comicViewerManager == null) {
			comicViewerManager = new ComicViewerManager(getActivity(), comicView, progressBar,
					event.comic);
		} else {
			if (comic != null && !comic.equals(event.comic)) {
				comicViewerManager.clearComic();
				comicViewerManager = new ComicViewerManager(getActivity(), comicView, progressBar,
						event.comic);
			}
		}

		Crashlytics.setString("comic", event.comic.getTitle());

		comic = event.comic;
		settings.setLastComicId(comic.getId());
		comicViewerManager.loadCurrentComic();
	}

	@SuppressWarnings("unused")
	public void onEvent(ComicViewUpdateEvent event) {
		attacher.update();
	}

	private void setUpButtonActions() {
		newerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				comicViewerManager.loadNextComic();
			}
		});

		olderButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				comicViewerManager.loadPreviousComic();
			}
		});
	}
}
