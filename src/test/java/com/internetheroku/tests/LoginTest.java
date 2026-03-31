package com.internetheroku.tests;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() throws InterruptedException {
        page.click("a[href='/login']");
        page.fill("#username", "tomsmith");
        page.fill("#password", "SuperSecretPassword!");
        page.click("button[type='submit']");

        assertTrue(page.locator(".flash.success").isVisible());
    }

    @Test
    public void invalidLoginTest() throws InterruptedException {
        page.click("a[href='/login']");
        page.fill("#username", "wronguser");
        page.fill("#password", "wrongpass");
        page.click("button[type='submit']");

        assertTrue(page.locator(".flash.error").isVisible());
    }
}