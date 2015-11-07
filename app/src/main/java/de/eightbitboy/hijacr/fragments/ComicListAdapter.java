package de.eightbitboy.hijacr.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import de.eightbitboy.hijacr.R;
import de.eightbitboy.hijacr.data.dao.Comic;

public class ComicListAdapter extends ArrayAdapter<Comic> {
	private Context context;
	private List<Comic> comics;

	public ComicListAdapter(Context context, int resource, List<Comic> comics) {
		super(context, -1, comics);
		this.context = context;
		this.comics = comics;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Comic comic = comics.get(position);

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

		viewHolder.title.setText(comic.getTitle());
		viewHolder.url.setText(comic.getSimpleUrl());

		return convertView;
	}

	private class ViewHolder {
		public TextView title;
		public TextView url;
	}
}
