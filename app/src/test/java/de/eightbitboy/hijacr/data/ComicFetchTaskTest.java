package de.eightbitboy.hijacr.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import de.eightbitboy.hijacr.BuildConfig;
import de.eightbitboy.hijacr.net.AbstractFetchTask;
import de.eightbitboy.hijacr.net.ComicFetchTask;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ComicFetchTaskTest {
	@Test
	public void doSomething() {
		String foo = "x";
		String bar = "x";
		assertThat(foo, equalTo(bar));
	}

	@Test
	public void testFetch() {
		AbstractFetchTask.FetchTaskListener listener = new AbstractFetchTask.FetchTaskListener() {
			@Override
			public void onFetchTaskFinished(String imageUrl, String requestedUrl, String previousUrl, String nextUrl) {
				assertThat(imageUrl,
						equalTo("http://imgs.xkcd.com/comics/tree_cropped_(1).jpg"));
				assertThat(requestedUrl,
						equalTo("http://xkcd.com/2/"));
				assertThat(previousUrl,
						equalTo("http://xkcd.com/1/"));
				assertThat(nextUrl,
						equalTo("http://xkcd.com/3/"));
			}
		};

		new ComicFetchTask(
				"https://xkcd.com/2/",
				"#comic img[src]",
				".comicNav a[rel=prev]",
				".comicNav a[rel=next]",
				null,
				listener)
				.execute();
	}
}
