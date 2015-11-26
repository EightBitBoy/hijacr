package de.eightbitboy.hijacr.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.core.IsEqual.equalTo;

import de.eightbitboy.hijacr.BuildConfig;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ComicFetchTaskTest {
	@Test
	public void doSomething() {
		String foo = "x";
		String bar = "x";
		Assert.assertThat(foo, equalTo(bar));
	}
}
