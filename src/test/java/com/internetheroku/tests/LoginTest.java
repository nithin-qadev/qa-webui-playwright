package com.internetheroku.tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

import java.nio.file.Paths;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest{

    @Test
    public void validLoginTest() {
        Page page = getPage();
        page.click("a[href='/login']");
        page.fill("#username", "tomsmith");
        page.fill("#password", "SuperSecretPassword!");
        page.click("button[type='submit']");

        assertTrue(page.locator(".flash.success").isVisible());
    }

    @Test
    public void invalidLoginTest() throws InterruptedException {
        Page page = getPage();
//        BrowserContext context = getContext();
//        context.tracing().start(new Tracing.StartOptions()
//                .setScreenshots(true)
//                .setSnapshots(true)
//                .setSources(true));

        page.click("a[href='/login']");
        page.fill("#username", "wronguser");
        page.fill("#password", "wrongpass");
        page.click("button[type='submit']");

        assertTrue(page.locator(".flash.error").isVisible());

//        context.tracing().stop(new Tracing.StopOptions()
//                .setPath(Paths.get("trace.zip")));
    }
}