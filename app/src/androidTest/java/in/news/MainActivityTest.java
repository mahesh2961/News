package in.news;

import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import in.news.testutils.ActivityRule;
import in.news.ui.MainActivity;

public class MainActivityTest {
    @Rule public final ActivityRule<MainActivity> main= new ActivityRule<>(MainActivity.class);


    //Testing basic flow
    @Test
    public void testRegularFlow() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(),ViewMatchers.withId(R.id.list)))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(5, ViewActions.click())
                );


        Espresso.onView(ViewMatchers.withId(R.id.news_web_view)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));


    }

}
