package com.chenlei.autotest.basic;

import com.chenlei.autotest.component.AppiumHelper;
import com.chenlei.autotest.component.HelperClassException;
import com.chenlei.autotest.component.UIAutomatorHelper;
import com.chenlei.autotest.utils.JDBLog;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.io.IOException;

/**
 * Created by Administrator on 2016/9/30.
 */
public class BasicAction {
    /**
     * 滑动过首页欢迎页
     *
     * @param driver
     * @throws HelperClassException
     * @throws InterruptedException
     */
    public static void scrollPassWelcomePage(AndroidDriver driver, String TAG) throws InterruptedException, IOException, HelperClassException {
        String UserGuideActivity = ".modules.newuserguide.UserGuideActivity";
        int waitTimes = 0;
        while (true) {
            String activity = driver.currentActivity();
            if (UserGuideActivity.equals(activity)) {
                JDBLog.info(TAG, "应用打开成功");
                break;
            }
            Thread.sleep(1000);
            JDBLog.info(TAG, "等待应用打开");
            waitTimes++;
            if (waitTimes > 10) {
                break;
            }
        }
        int i = 0;
        WebElement enterJDB = null;
        while (true) {
            try {
                enterJDB = UIAutomatorHelper.findByUIAResId(driver, "com.rrh.jdb:id/easy_borrow");
                JDBLog.info(TAG, "点击进入借贷宝");
                enterJDB.click();
            } catch (NullPointerException e) {
                JDBLog.info(TAG, "滑屏：" + i + "次");
                AppiumHelper.swipeToLeft(driver);
            } catch (NoSuchElementException e) {
                JDBLog.info(TAG, "滑屏：" + i + "次");
                AppiumHelper.swipeToLeft(driver);
            }
            if (enterJDB != null) {
                break;
            }
            Thread.sleep(500);
            i++;
            if (i > 6) {
                break;
            }
        }
    }
    

    /**
     * 填写用户名密码
     *
     * @param driver
     * @param phoneNum
     * @param password
     * @return
     * @throws HelperClassException
     * @throws InterruptedException
     */
    public static void loginJDB(AndroidDriver driver, String phoneNum, String password, String TAG) throws HelperClassException, InterruptedException, IOException {
        JDBLog.info(TAG, "点击登录");
        UIAutomatorHelper.clickByUIAText(driver, "登录");
        Thread.sleep(1000);
        JDBLog.info(TAG, "输入电话号码");
        WebElement editPhone = AppiumHelper.findById(driver, "com.rrh.jdb:id/etMobile");
        editPhone.clear();
        editPhone.sendKeys(phoneNum);
        Thread.sleep(1000);
        WebElement editPassword = AppiumHelper.findById(driver, "com.rrh.jdb:id/etPassword");
        editPassword.sendKeys(password);
        JDBLog.info(TAG, "点击登录");
        UIAutomatorHelper.clickByUIAResId(driver, "com.rrh.jdb:id/btnLogin");
        Thread.sleep(2000);
    }

    /**
     * 等待验证登录成功
     *
     * @param driver
     * @return
     * @throws HelperClassException
     * @throws InterruptedException
     */
    public static boolean waitLoginPass(AndroidDriver driver, String TAG) throws HelperClassException, InterruptedException {
        boolean result = false;
        String currentActivity;
        String expectActivity = ".newmodule.tab.maintab.MainTabFragmentActivity";
        int loopCount = 0;
        while (loopCount < 30) {
            currentActivity = driver.currentActivity();
            if (expectActivity.equals(currentActivity)) {
                JDBLog.info(TAG, "登录成功");
                result = true;
                break;
            }
            JDBLog.info(TAG, "等待登录成功···");
            JDBLog.info(TAG, "当前Activity：" + currentActivity);
            Thread.sleep(1000);
            loopCount++;
        }
        return result;
    }

    /**
     * 首页抗异常
     *
     * @param driver
     * @throws HelperClassException
     */
    public static void antiAnomaly(AndroidDriver driver) throws HelperClassException, IOException {
        try {
            WebElement closeBtn = AppiumHelper.findById(driver, "com.rrh.jdb:id/imgClose");
            closeBtn.click();
        } catch (NoSuchElementException e) {
        } catch (NullPointerException e) {
        }
        try {
            WebElement iKnow = AppiumHelper.findById(driver, "com.rrh.jdb:id/firstBtn");
            iKnow.click();
        } catch (NoSuchElementException e) {
        } catch (NullPointerException e) {
        }
        try {
            WebElement byId = AppiumHelper.findById(driver, "com.rrh.jdb:id/secondBtn");
            byId.click();
        }catch (NullPointerException e){
        }catch (NoSuchElementException e){
        }
    }


}
