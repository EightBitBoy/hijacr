package de.eightbitboy.hijacr.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import de.eightbitboy.hijacr.R;
import de.eightbitboy.hijacr.data.comic.ComicData;

public class ComicListAdapter extends ArrayAdapter<ComicData> {
	private Context context;
	private List<ComicData> comics;

	public ComicListAdapter(Context context, int resource, List<ComicData> comics) {
		super(context, -1, comics);
		this.context = context;
		this.comics = comics;
	}

	//TODO implement view holder pattern
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ComicData data = comics.get(position);

		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.comic_list_item, parent, false);

			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView.findViewById(R.id.comic_title);
			viewHolder.url = (TextView) convertView.findViewById(R.id.comic_url);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.title.setText(data.getTitle());
		viewHolder.url.setText(data.getCleanUrl());

		return convertView;
	}

	private class ViewHolder {
		public TextView title;
		public TextView url;
	}
}
