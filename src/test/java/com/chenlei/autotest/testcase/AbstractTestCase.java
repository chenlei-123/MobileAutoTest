package com.chenlei.autotest.testcase;

import com.chenlei.autotest.component.AppiumHelper;
import com.chenlei.autotest.component.HelperClassException;
import com.chenlei.autotest.component.TestHelper;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by chenlei on 2017/8/1.
 */
public class AbstractTestCase {
    public static volatile AndroidDriver driver = null;
    public static String firstActivity = ".modules.firstactivity.FirstActivity";
    public static String welComeActivity = ".modules.newuserguide.UserGuideActivity";
    public static String phoneNum = "13512937693";
    public static String passWord = "qqqqqq1";

    @BeforeSuite
    public void beforeSuit() throws MalformedURLException, HelperClassException {
        System.out.println("===========init driver ==========");
        driver = AppiumHelper.initAndroidDriver(driver);

    }

    @AfterSuite
    public void afterSuit() {
        System.out.println("==============quit driver=============");
        driver.quit();

    }

    @BeforeMethod
    public void setUp() throws IOException {
        driver.closeApp();
        TestHelper.killProxyServer();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.closeApp();
        TestHelper.killProxyServer();
    }
}
