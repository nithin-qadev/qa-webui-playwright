package com.internetheroku.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class CheckBoxTest extends BaseTest {

    @Test
    public void checkBothBoxes() {
        Page page = getPage();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Checkboxes")).click();
        if (!page.getByRole(AriaRole.CHECKBOX).first().isChecked()) {
            page.getByRole(AriaRole.CHECKBOX).first().check();
        }
        if (!page.getByRole(AriaRole.CHECKBOX).nth(1).isChecked()) {
            page.getByRole(AriaRole.CHECKBOX).nth(1).check();
        }

        assertTrue(page.getByRole(AriaRole.CHECKBOX).nth(1).isChecked(), "second box not checked");
        assertTrue(page.getByRole(AriaRole.CHECKBOX).first().isChecked(), "first box not checked");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")));

    }
}
