package de.eightbitboy.hijacr.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class SettingsActivityTest {
	@Rule
	public ActivityTestRule<SettingsActivity> activityRule = new ActivityTestRule<>(
			SettingsActivity.class);

	@Test
	public void expertCategoryExists() {
		onView(withText("Expert")).check(matches(isDisplayed()));
	}
}
