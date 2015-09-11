package de.eightbitboy.hijacr.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import de.eightbitboy.hijacr.R;
import de.eightbitboy.hijacr.data.comic.SimpleComicData;

public class ComicListAdapter extends ArrayAdapter<SimpleComicData> {
	private Context context;
	private List<SimpleComicData> comics;

	public ComicListAdapter(Context context, int resource, List<SimpleComicData> comics) {
		//TODO find out what resource does, fix -1 below
		super(context, -1, comics);
		this.context = context;
		this.comics = comics;
	}

	//TODO implement view holder pattern
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
				.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.comic_list_item, parent, false);

		TextView title = (TextView) view.findViewById(R.id.comic_title);
		title.setText(comics.get(position).title);

		TextView url = (TextView) view.findViewById(R.id.comic_url);
		url.setText(comics.get(position).url);

		return view;
	}
}
