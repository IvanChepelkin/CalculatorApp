package com.example.user.calculatorapp.view;


import android.support.test.rule.ActivityTestRule;

import com.example.user.calculatorapp.R;
import org.junit.Rule;
import org.junit.Test;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void simbolButtonClickedOne() throws Exception {
        onView(withId(R.id.one)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("1")));

        onView(withId(R.id.two)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("12")));

        onView(withId(R.id.three)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("123")));

        onView(withId(R.id.four)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("1234")));

        onView(withId(R.id.five)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("12345")));

        onView(withId(R.id.six)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("123456")));

        onView(withId(R.id.seven)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("1234567")));

        onView(withId(R.id.eight)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("12345678")));

        onView(withId(R.id.nine)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("123456789")));

        onView(withId(R.id.addition)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("123456789+")));

        onView(withId(R.id.subtraction)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("123456789+-")));

        onView(withId(R.id.multiplication)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("123456789+-*")));

        onView(withId(R.id.division)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("123456789+-*/")));

        onView(withId(R.id.leftBrecket)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("123456789+-*/(")));

        onView(withId(R.id.rightBrecket)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("123456789+-*/()")));
    }

    @Test
    public void clearBurronClicked() {
        onView(withId(R.id.one)).perform(click());
        onView(withId(R.id.del)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("")));
    }

    @Test
    public void cumputeButtonClicked() {
        onView(withId(R.id.one)).perform(click());
        onView(withId(R.id.addition)).perform(click());
        onView(withId(R.id.two)).perform(click());
        onView(withId(R.id.compute)).perform(click());
        onView(withId(R.id.resultText)).check(matches(withText("1+2=3.0")));

    }
}