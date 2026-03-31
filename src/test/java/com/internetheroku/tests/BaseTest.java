package com.internetheroku.tests;
import com.microsoft.playwright.*;
import org.testng.annotations.*;


public class BaseTest {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chromium") String browserName) {
        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {
            case "chrome" -> browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setChannel("chrome")
                            .setHeadless(false)
            );
            case "webkit"   -> browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
            default         -> browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }

        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://the-internet.herokuapp.com");
    }

    @AfterMethod
    public void teardown() {
        context.close();
        browser.close();
        playwright.close();
    }
}