package de.eightbitboy.hijacr.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

	@Rule
	public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);


	@Test
	public void pagerIsVisible() {
		onView(withText(equalToIgnoringCase("LISTe"))).check(matches(isDisplayed()));
		onView(withText(equalToIgnoringCase("VIEWER"))).check(matches(isDisplayed()));
	}
}
