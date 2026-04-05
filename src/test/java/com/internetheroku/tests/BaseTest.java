package com.internetheroku.tests;
import com.microsoft.playwright.*;
import org.testng.annotations.*;


public class BaseTest {

    private static final ThreadLocal<Playwright> playwright= new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();


    @BeforeMethod
    public void setup() {
        Playwright pwright = Playwright.create();
        Browser b = pwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setChannel("chrome")
                            .setHeadless(false)
            );


        BrowserContext bc = b.newContext();
        Page p = bc.newPage();
        p.navigate("https://the-internet.herokuapp.com");


        browser.set(b);
        playwright.set(pwright);
        context.set(bc);
        page.set(p);
    }

    @AfterMethod
    public void teardown() {
        page.get().close();
        context.get().close();
        browser.get().close();
        playwright.get().close();

        page.remove();
        context.remove();
        browser.remove();
        playwright.remove();

    }

    public Page getPage(){
        return page.get();
    }
    public Browser getBrowser(){
        return browser.get();
    }
    public Playwright getPlaywright(){
        return playwright.get();
    }
    public BrowserContext getContext(){
        return context.get();
    }


}