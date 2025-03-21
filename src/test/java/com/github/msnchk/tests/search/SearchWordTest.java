package com.github.msnchk.tests.search;

import com.github.msnchk.pages.MainPage;
import com.github.msnchk.tests.BaseTest;
import org.junit.jupiter.api.Test;

public class SearchWordTest extends BaseTest {
    private MainPage mainPage;

    @Test
    public void countWords() {
        getDriver().get("https://nexign.com/ru");
        mainPage = new MainPage(getDriver(), getActions());

        int count1 = mainPage.countWordNexignJava();
        System.out.println("Первый способ. Количество слов \"nexign\" на странице: " + count1);

        int count2 = mainPage.countWordNexignJavascript();
        System.out.println("Второй способ. Количество слов \"nexign\" на странице: " + count2);

    }
}
