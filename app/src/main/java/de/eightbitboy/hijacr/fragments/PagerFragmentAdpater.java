package de.eightbitboy.hijacr.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerFragmentAdpater extends FragmentPagerAdapter{
    private static final int NUMBER_ITEMS = 2;

    public PagerFragmentAdpater(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new ComicListFragment();
            case 1:
                return new ComicViewerFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUMBER_ITEMS;
    }
}
