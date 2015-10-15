package de.eightbitboy.hijacr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.eightbitboy.hijacr.R;
import de.eightbitboy.hijacr.data.ComicRepository;
import de.eightbitboy.hijacr.data.comic.ComicData;
import de.eightbitboy.hijacr.events.ComicSelectedEvent;
import de.greenrobot.event.EventBus;

public class ComicListFragment extends Fragment {

	@Bind(R.id.comic_list_view)
	ListView comicList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.comic_list, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setUpComicList();
		setUpComicListClickAction();
	}

	private void setUpComicList() {
		comicList.setAdapter(
				new ComicListAdapter(getActivity().getApplicationContext(), 0,
						ComicRepository.getComicList()));
	}

	private void setUpComicListClickAction() {
		comicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Logger.wtf("clicked: " + i);
				EventBus.getDefault().post(new ComicSelectedEvent((ComicData) comicList
						.getItemAtPosition(i)));
			}
		});
	}
}
