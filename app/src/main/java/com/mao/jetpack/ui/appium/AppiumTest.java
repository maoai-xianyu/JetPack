package com.mao.jetpack.ui.appium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;

public class  AppiumTest {

    private static AndroidDriver<WebElement> androidDriver;

    public static void main(String[] args) throws MalformedURLException {

        // 1. 创建配置对象
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        // 2. 添加配置
        // deviceName 可以找到我们的测试设备
        desiredCapabilities.setCapability("deviceName",  "c890a5ce");
        // platformName 测试平台 Android or iOS
        desiredCapabilities.setCapability("platformName", "Android");
        // appPackage 找到要测试的app  驾考宝典
        desiredCapabilities.setCapability("appPackage", "com.handsgo.jiakao.android");
        // appActivity 测试启动app入口
        desiredCapabilities.setCapability("appActivity", "com.handsgo.jiakao.android.splash.Login");

        // 3. 创建驱动
        // 传入两个参数
        // 第一个参数：appium通讯地址
        // 第二个参数：配置对象
        androidDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
                desiredCapabilities);
        // 启动服务，运行代码  会拉起应用
    }


    public static void testJiaKao() throws InterruptedException {

        // 没有打开页面，先睡10秒
        Thread.sleep(10000);
        // 1. 找到定位城市文本，并且点击
        androidDriver.findElementById("com.handsgo.jiakao.android:id/cityTv").click();
        // 2. 找到搜索框 并且输入 北京
        androidDriver.findElementById("com.handsgo.jiakao.android:id/edt_search_q").sendKeys("北京");
        // 3. 找打搜索结果中的 北京 点击
        androidDriver.findElementById("com.handsgo.jiakao.android:id/edt_search_q").click();
        // 4. 找到小车，地啊你
        androidDriver.findElementById("com.handsgo.jiakao.android:id/itemCar").click();
        // 5. 找到下一步，点击
        androidDriver.findElementById("com.handsgo.jiakao.android:id/okBtn").click();

        Thread.sleep(5000);
        //
    }
}
