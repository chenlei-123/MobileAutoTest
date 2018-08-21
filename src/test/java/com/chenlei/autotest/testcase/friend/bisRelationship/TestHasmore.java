package com.chenlei.autotest.testcase.friend.bisRelationship;

import com.chenlei.autotest.component.HelperClassException;
import com.chenlei.autotest.component.TestHelper;
import com.chenlei.autotest.component.UIAutomatorHelper;
import com.chenlei.autotest.testcase.AbstractTestCase;
import com.chenlei.autotest.utils.GetMockDataPath;
import com.chenlei.autotest.utils.JDBLog;
import com.chenlei.autotest.utils.ProxyServerForMac;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by chenlei on 2017/6/23.
 */
public class TestHasmore extends AbstractTestCase {
    public static final String TAG = TestHasmore.class.getSimpleName();

    @DataProvider(name = "moduleTest")
    public static Object[][] getCases() {
        return new Object[][]{
                {"hasMore/hasmore0.json"},//只加载一条
                {"hasMore/hasmore1.json"}, //重复加载多条
        };
    }

    @Test(dataProvider = "moduleTest")
    public void testCase(String dataPath) throws InterruptedException, HelperClassException {
        JDBLog.info(TAG, dataPath);
        //传递本类名进去，返回mockdata路径地址，例如返回"\\proxymock\\phptradeui\\index\\productList\\"
        String path = GetMockDataPath.getPath(this.getClass());
        new ProxyServerForMac(path, dataPath).start();
        Thread.sleep(3000);
        driver.launchApp();
        TestHelper.waitForAppStart(driver, TAG);
        Thread.sleep(1000);
        JDBLog.info(TAG, "点击进入好友页");
        JDBLog.info(TAG, "点击进入添加好友页");
        String xpath = "//android.widget.ListView/android.widget.LinearLayout[@index=1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.LinearLayout[@index=2]";
        driver.findElementByXPath(xpath).click();

        waitViewDisplay("关注");
        WebElement addFriend = UIAutomatorHelper.findByUIAText(driver, "添加好友");
        Assert.assertTrue(addFriend.isDisplayed(), "页面显示异常");
        List<WebElement> TextViews = driver.findElementsByClassName("android.widget.TextView");
        int index = 0;
        for (int i = 0; i < TextViews.size(); i++) {
            if (TextViews.get(i).getText().equals("美丽")) {
                index++;
            }
        }
        if (dataPath.equals("hasMore/hasmore0.json")) {
            Assert.assertTrue(index == 1, "hasmore为0的时候展示错误");
        } else if (dataPath.equals("hasMore/hasmore1.json")) {
            Assert.assertTrue(index != 1, "hasmore为1的时候展示错误");
        }

    }


    private void waitViewDisplay(String text){
        try {
            final WebDriverWait wait = new WebDriverWait(driver, 30);
            Assert.assertNotNull(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[contains(@text,'" + text + "')]"))));
            System.out.println("找到了toast");
        } catch (Exception e) {
            throw new AssertionError("找不到");
        }
    }
}
