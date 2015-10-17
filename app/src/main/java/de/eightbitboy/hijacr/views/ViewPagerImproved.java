package de.eightbitboy.hijacr.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.orhanobut.logger.Logger;

public class ViewPagerImproved extends ViewPager {

	public ViewPagerImproved(Context context) {
		super(context);
	}

	public ViewPagerImproved(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		try {
			return super.onInterceptTouchEvent(ev);
		} catch (IllegalArgumentException e) {
			Logger.w("Exception while zooming: " + e.toString());
			return false;
		}
	}
}
