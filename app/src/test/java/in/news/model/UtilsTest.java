package in.news.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import in.news.utils.Util;

@RunWith(MockitoJUnitRunner.class)
public class UtilsTest {



    @Test
    public void testGetSourceAndTime() {

        TimeUnit.MINUTES.toMillis(120);

        String result = Util.Companion.getSourceAndElapsedTime("BBC", new Timestamp(System.currentTimeMillis()));

        String result2 = Util.Companion.getSourceAndElapsedTime("BBC", new Timestamp(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(60 * 2)));

        String result3 = Util.Companion.getSourceAndElapsedTime("BBC", new Timestamp(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(60 * 48)));


        Assert.assertTrue((result.equals("BBC • less than 1min ago")));
        Assert.assertTrue((result2.equals("BBC • 2hrs ago")));
        Assert.assertTrue((result3.equals("BBC • 2days ago")));


    }


}
