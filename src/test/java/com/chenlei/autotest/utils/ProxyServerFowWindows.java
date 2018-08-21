package com.chenlei.autotest.utils;

import java.io.*;

/**
 * Created by Administrator on 2017/2/20.
 */
public class ProxyServerFowWindows extends Thread{
    private String Case;
    private String rulePath;
    public ProxyServerFowWindows(String rulePath,String Case){
        this.Case = Case;
        this.rulePath = rulePath;
    }
    public void run() {
        String projectRootPath = System.getProperty("user.dir");
        String rule = rulePath+"rule_mock.js";
        System.out.println(getClass().getSimpleName()+rule);
        String line;
        File startServer = new File(projectRootPath+"\\bat\\StartServer.bat"+" "+rule+" "+Case);
        String startProxyStr = null;
        try {
            Process exec = Runtime.getRuntime().exec(startServer.toString());
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
