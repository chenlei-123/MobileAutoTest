package com.chenlei.autotest.utils;

import org.testng.Reporter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by chenlei on 2017/4/10.
 */
public class ProxyServerFor2Url extends Thread{
    private String rulePath;
    private String mockData1;
    private String mockData2;

    public ProxyServerFor2Url(String rulePath,String mockData1,String mockData2){
        this.rulePath = rulePath;
        this.mockData1 = mockData1;
        this.mockData2 = mockData2;
    }

    public void run() {
        String rule = rulePath+"/rule_mock_new.js";
        Reporter.log(rule);
        String line;
        String startProxyStr = null;
        try {
            Process exec = Runtime.getRuntime().exec("anyproxy -r"+" "+rule+" "+mockData1+" "+mockData2);
            InputStream inputStream = exec.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            startProxyStr = stringBuilder.toString();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            exec.destroy();
            System.out.println(startProxyStr);
        } catch (IOException e) {

        }
    }
}
