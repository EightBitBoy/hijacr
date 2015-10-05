package de.eightbitboy.hijacr.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.eightbitboy.hijacr.R;
import de.eightbitboy.hijacr.data.ComicManager;
import de.eightbitboy.hijacr.data.ComicRepository;
import de.eightbitboy.hijacr.data.SettingsManager;
import de.eightbitboy.hijacr.data.comic.ComicData;
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

	private ComicData comicData;
	private ComicManager comicManager;
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

		comicManager = new ComicManager(getActivity(), comicView, ComicRepository.getComicData
				(settings.getLastComicId()));

		setUpButtonActions();
	}

	@SuppressWarnings("unused")
	public void onEvent(ComicSelectedEvent event) {
		if (comicManager == null) {
			comicManager = new ComicManager(getActivity(), comicView, event.comicData);
		} else {
			if (comicData != null && !comicData.equals(event.comicData)) {
				comicManager.clearComic();
				comicManager = new ComicManager(getActivity(), comicView, event.comicData);
			}
		}
		
		Crashlytics.log("current comic: " + event.comicData.getTitle());

		comicData = event.comicData;
		settings.setLastComicId(comicData.getId());
		comicManager.loadCurrentComic();
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
