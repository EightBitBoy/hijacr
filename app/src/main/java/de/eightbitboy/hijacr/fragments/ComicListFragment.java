package de.eightbitboy.hijacr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.eightbitboy.hijacr.R;
import de.eightbitboy.hijacr.data.dao.Comic;
import de.eightbitboy.hijacr.data.database.Database;
import de.eightbitboy.hijacr.events.ComicSelectedEvent;
import de.greenrobot.event.EventBus;

public class ComicListFragment extends Fragment {

	@Bind(R.id.comic_list_view)
	ListView comicList;
	private Database db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.comic_list, container, false);
		ButterKnife.bind(this, view);
		db = Database.getInstance(this.getContext());
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setUpComicList();
		//setUpComicListClickAction();
	}

	private void setUpComicList() {
		comicList.setAdapter(
				new ComicListAdapter(getActivity().getApplicationContext(), 0,
						db.getAllComics()));
	}

	private void setUpComicListClickAction() {
		comicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				EventBus.getDefault().post(new ComicSelectedEvent((Comic) comicList
						.getItemAtPosition(i)));
			}
		});
	}
}
