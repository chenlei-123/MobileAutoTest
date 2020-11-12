package com.chenlei.autotest.testcase;

import com.chenlei.autotest.basic.BasicAction;
import com.chenlei.autotest.component.HelperClassException;
import com.chenlei.autotest.component.TestHelper;
import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;

/**
 * Created by chenlei on 2017/8/11.
 */
public class Commons extends AbstractTestCase{
    public static void antiAnomaly(AndroidDriver driver,String TAG) throws InterruptedException, IOException, HelperClassException {
        //考虑到频繁杀进程启动刷开机接口会被踢出账号，所以在此判断时候为登录状态，如果不是，重新登录
        TestHelper.waitForAppStart(driver, TAG);
        if (welComeActivity.equals(driver.currentActivity())) {
            BasicAction.scrollPassWelcomePage(driver,TAG);
            BasicAction.loginJDB(driver,phoneNum,passWord,TAG);

            BasicAction.waitLoginPass(driver,TAG);
        }else if(firstActivity.equals(driver.currentActivity())){
            BasicAction.loginJDB(driver,phoneNum,passWord,TAG);

            BasicAction.waitLoginPass(driver,TAG);
        }
        Thread.sleep(2000);
        BasicAction.antiAnomaly(driver);
        //抗异常代码结束
    }
}
