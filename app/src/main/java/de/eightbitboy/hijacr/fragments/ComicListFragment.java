package de.eightbitboy.hijacr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.eightbitboy.hijacr.R;
import de.eightbitboy.hijacr.data.dao.Comic;
import de.eightbitboy.hijacr.data.db.Database;
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
		setUpComicListClickAction();
	}

	private void setUpComicList() {
		comicList.setAdapter(
				new ComicListAdapter(getActivity().getApplicationContext(), 0,
						db.getAllComicsSortedAlphabetically()));
	}

	private void setUpComicListClickAction() {
		comicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Comic comic = (Comic) comicList.getItemAtPosition(i);

				Answers.getInstance().logContentView(new ContentViewEvent()
						.putContentType("Comic")
						.putContentName(comic.getKey()));

				EventBus.getDefault().post(new ComicSelectedEvent(comic));
			}
		});
	}
}
