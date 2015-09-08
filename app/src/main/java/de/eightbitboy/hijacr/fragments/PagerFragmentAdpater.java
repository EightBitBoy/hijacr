package de.eightbitboy.hijacr.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerFragmentAdpater extends FragmentPagerAdapter {
	public class Pages {
		public static final int COMIC_LIST = 0;
		public static final int COMIC_VIEWER = 1;

		private static final int NUMBER_ITEMS = 2;
	}

	public PagerFragmentAdpater(FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case Pages.COMIC_LIST:
				return new ComicListFragment();
			case Pages.COMIC_VIEWER:
				return new ComicViewerFragment();
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return Pages.NUMBER_ITEMS;
	}
}
