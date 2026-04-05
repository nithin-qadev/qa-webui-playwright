package com.internetheroku.tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class LoginTest {
    static Playwright pwRunner;
    static Browser browser;
    static BrowserContext browserContext;
    static Page page;

    @BeforeSuite
    static void launchBrowser(){
        pwRunner = Playwright.create();
        browser = pwRunner.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeTest
    static void setContextAndPage(){
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @AfterTest
    void closeContext(){
        page.close();
        browserContext.close();
    }

    @Test
    public void validLoginTest() throws InterruptedException {
        page.navigate("https://the-internet.herokuapp.com");
        page.click("a[href='/login']");
        page.fill("#username", "tomsmith");
        page.fill("#password", "SuperSecretPassword!");
        page.click("button[type='submit']");

        assertTrue(page.locator(".flash.success").isVisible());
    }

    @Test
    public void invalidLoginTest() throws InterruptedException {
        page.navigate("https://the-internet.herokuapp.com");
        page.click("a[href='/login']");
        page.fill("#username", "wronguser");
        page.fill("#password", "wrongpass");
        page.click("button[type='submit']");

        assertTrue(page.locator(".flash.error").isVisible());
    }
}