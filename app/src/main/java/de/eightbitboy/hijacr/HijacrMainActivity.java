package de.eightbitboy.hijacr;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import de.eightbitboy.hijacr.events.ComicSelectedEvent;
import de.eightbitboy.hijacr.fragments.PagerFragmentAdapter;
import de.greenrobot.event.EventBus;

public class HijacrMainActivity extends AppCompatActivity {
	private ViewPager pager;

	@Override
	protected void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);
	}

	@Override
	protected void onStop() {
		EventBus.getDefault().unregister(this);
		super.onStop();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.hijacr_main);
		ButterKnife.bind(this);

		pager = (ViewPager) findViewById(R.id.comic_pager);
		pager.setAdapter(new PagerFragmentAdapter(getSupportFragmentManager()));

		TabLayout tabLayout = (TabLayout) findViewById(R.id.comic_pager_tabs);
		tabLayout.setupWithViewPager(pager);

		//TODO do this in xml
		//noinspection ConstantConditions
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(33, 90, 109)));
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("unused")
	public void onEvent(ComicSelectedEvent event) {
		pager.setCurrentItem(PagerFragmentAdapter.Pages.COMIC_VIEWER);
	}
}
