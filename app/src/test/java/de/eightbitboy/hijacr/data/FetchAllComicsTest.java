package de.eightbitboy.hijacr.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import de.eightbitboy.hijacr.BuildConfig;
import de.eightbitboy.hijacr.data.dao.Comic;
import de.eightbitboy.hijacr.data.db.Database;
import de.eightbitboy.hijacr.net.AbstractFetchTask;
import de.eightbitboy.hijacr.net.ComicFetchTask;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class FetchAllComicsTest {
	@Test
	public void fetchAllComics() {
		Database db = Database.getInstance(RuntimeEnvironment.application);
		List<Comic> comics = db.getAllComics();

		AbstractFetchTask.FetchTaskListener listener = new AbstractFetchTask.FetchTaskListener() {
			@Override
			public void onFetchTaskFinished(String imageUrl, String requestedUrl, String previousUrl, String nextUrl) {
				assertNotNull(imageUrl);
				assertNotNull(requestedUrl);
				assertNotNull(previousUrl);
				assertNotNull(nextUrl);
			}
		};

		for (Comic comic : comics) {
			new ComicFetchTask(comic.getFirstUrl(), comic.getImageQuery(), comic.getPreviousQuery(),
					comic.getNextQuery(), comic.getRandomQuery(), listener).execute();
		}
	}
}
