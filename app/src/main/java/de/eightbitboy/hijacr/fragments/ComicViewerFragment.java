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

import butterknife.Bind;
import butterknife.ButterKnife;
import de.eightbitboy.hijacr.R;
import de.eightbitboy.hijacr.data.ComicViewerManager;
import de.eightbitboy.hijacr.data.SettingsManager;
import de.eightbitboy.hijacr.data.dao.Comic;
import de.eightbitboy.hijacr.data.db.Database;
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
	@Bind(R.id.random_button)
	Button randomButton;
	@Bind(R.id.newer_button)
	Button newerButton;

	private Comic currentComic;
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

		currentComic = Database.getInstance(this.getContext()).getComicById(settings
				.getLastComicId());

		setRandomButtonState();

		comicViewerManager = new ComicViewerManager(getActivity(), currentComic, this);
		comicViewerManager.loadCurrentComic();

		setUpButtonActions();
	}

	@SuppressWarnings("unused")
	public void onEvent(ComicSelectedEvent event) {

		if (comicViewerManager == null) {
			comicViewerManager = new ComicViewerManager(getActivity(), event.comic, this);
		} else {
			if (currentComic != null && !(currentComic.getId().equals(event.comic.getId()))) {
				comicViewerManager.clearComic();
				comicViewerManager = new ComicViewerManager(getActivity(), event.comic, this);
			}
		}

		Crashlytics.setString("currentComic", event.comic.getTitle());

		currentComic = event.comic;

		setRandomButtonState();

		settings.setLastComicId(currentComic.getId());
		comicViewerManager.loadCurrentComic();
	}

	@SuppressWarnings("unused")
	public void onEvent(ComicViewUpdateEvent event) {
		attacher.update();
	}

	private void setUpButtonActions() {
		olderButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				comicViewerManager.loadPreviousComic();
			}
		});

		randomButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				comicViewerManager.loadRandomComic();
			}
		});

		newerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				comicViewerManager.loadNextComic();
			}
		});
	}

	public ImageView getImageView() {
		return comicView;
	}

	public void showProgressBar() {
		//TODO use something different than setting visibility?
		progressBar.setVisibility(View.VISIBLE);
	}

	public void hideProgressBar() {
		progressBar.setVisibility(View.GONE);
	}

	public void enbaleBackButton() {
		olderButton.setEnabled(true);
	}

	public void disableBackButton() {
		olderButton.setEnabled(false);
	}

	private void setRandomButtonState() {
		if (currentComic.getRandomUrl() == null && currentComic.getRandomQuery() == null) {
			randomButton.setEnabled(false);
			randomButton.setVisibility(View.INVISIBLE);
		} else {
			randomButton.setEnabled(true);
			randomButton.setVisibility(View.VISIBLE);
		}
	}

	public void showErrorMessage(String message) {

	}
}
