package com.github.msnchk.tests.navigation;

import com.github.msnchk.pages.MainPage;
import com.github.msnchk.tests.BaseTest;
import org.junit.jupiter.api.Test;

public class NavigationTest extends BaseTest {
    private MainPage mainPage;

    @Test
    public void testNavigation() {
        getDriver().get("https://nexign.com/ru ");
        mainPage = new MainPage(getDriver(), getActions());

        mainPage.openProductsAndSolutions();
        mainPage.openToolsForItTeams();
        mainPage.openNexignNord();
    }
}
