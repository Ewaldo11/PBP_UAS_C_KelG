package com.example.tubes_kelg_c.splash;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.tubes_kelg_c.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CheckActivityTest9839 {

    @Rule
    public ActivityTestRule<CheckActivity> mActivityTestRule = new ActivityTestRule<>(CheckActivity.class);

    @Test
    public void checkActivityTest9839() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_login), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.passwordlogin),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_passwordlogin),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText.perform(replaceText("password"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_loginpage), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.passwordlogin), withText("password"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_passwordlogin),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText2.perform(replaceText(""));

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.passwordlogin),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_passwordlogin),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.emaillogin),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_emaillogin),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("sebastianewaldo11@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btn_loginpage), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.emaillogin), withText("sebastianewaldo11@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_emaillogin),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText5.perform(replaceText(""));

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.emaillogin),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_emaillogin),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText6.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btn_loginpage), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.emaillogin),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_emaillogin),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText7.perform(replaceText("sebastianewaldo39@gmailcom"), closeSoftKeyboard());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.emaillogin), withText("sebastianewaldo39@gmailcom"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_emaillogin),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText8.perform(click());

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.emaillogin), withText("sebastianewaldo39@gmailcom"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_emaillogin),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText9.perform(replaceText("sebastianewaldo39@gmail.com"));

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.emaillogin), withText("sebastianewaldo39@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_emaillogin),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText10.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.passwordlogin),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_passwordlogin),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText11.perform(replaceText("password"), closeSoftKeyboard());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btn_loginpage), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction textInputEditText12 = onView(
                allOf(withId(R.id.emaillogin), withText("sebastianewaldo39@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_emaillogin),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText12.perform(replaceText("sebas@gmail.com"));

        ViewInteraction textInputEditText13 = onView(
                allOf(withId(R.id.emaillogin), withText("sebas@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_emaillogin),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText13.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.btn_loginpage), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction textInputEditText14 = onView(
                allOf(withId(R.id.emaillogin), withText("sebas@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_emaillogin),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText14.perform(replaceText("sebastianewaldo11@gmail.com"));

        ViewInteraction textInputEditText15 = onView(
                allOf(withId(R.id.emaillogin), withText("sebastianewaldo11@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_emaillogin),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText15.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText16 = onView(
                allOf(withId(R.id.passwordlogin), withText("password"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_passwordlogin),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText16.perform(replaceText("pass"));

        ViewInteraction textInputEditText17 = onView(
                allOf(withId(R.id.passwordlogin), withText("pass"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_passwordlogin),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText17.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.btn_loginpage), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton7.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
