package com.example.memory_mate;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup() {
        // Initialize Intents
        Intents.init();
    }

    @After
    public void cleanup() {
        // Release Intents
        Intents.release();
    }

    @Test
    public void clickButton_opensLocation1Activity() {
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(Location1.class.getName()));
    }

    @Test
    public void clickButton_opensLocation2Activity() {
        Espresso.onView(ViewMatchers.withId(R.id.button2)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(Location2.class.getName()));
    }
}
