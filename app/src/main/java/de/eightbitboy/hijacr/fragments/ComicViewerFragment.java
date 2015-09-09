package de.eightbitboy.hijacr.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.eightbitboy.hijacr.R;
import de.eightbitboy.hijacr.data.ComicFetcher;
import de.eightbitboy.hijacr.data.ComicRepository;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ComicViewerFragment extends Fragment {

	@Bind(R.id.comic_view)
	ImageView comicView;
	@Bind(R.id.older_button)
	Button olderButton;
	@Bind(R.id.newer_button)
	Button newerButton;

	private int comicCounter = 1;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.comic_viewer, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		PhotoViewAttacher attacher = new PhotoViewAttacher(comicView);
		setUpButtonActions();
	}

	private void setUpButtonActions() {
		/*
		olderButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						new ComicFetcher(getActivity(), comicView, comicCounter,
								ComicRepository.getComicData("xkcd")).execute();
						comicCounter--;
					}
				});
			}
		});

		newerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						new ComicFetcher(getActivity(), comicView, comicCounter,
								ComicRepository.getComicData("xkcd")).execute();
						comicCounter++;
					}
				});
			}
		});
		*/
	}
}
