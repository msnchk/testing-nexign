package com.github.msnchk.tests.spelling;

import com.github.msnchk.api.spellchecker.SpellCheckerService;
import com.github.msnchk.pages.MainPage;
import com.github.msnchk.tests.BaseTest;
import com.github.msnchk.utils.SpellCheckHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class SpellingTest extends BaseTest {

    private MainPage mainPage;
    private SpellCheckerService spellCheckerService;
    private static final Logger logger = LoggerFactory.getLogger(SpellingTest.class);

    @BeforeEach
    public void setupTest() {
        getDriver().get("https://nexign.com/ru");
        mainPage = new MainPage(getDriver(), getActions());
        spellCheckerService = new SpellCheckerService();
    }

    @Test
    public void checkSpellingOnPages() {
        List<String> links = mainPage.getAllInternalLinks();

        for (String link : links) {
            getDriver().get(link);
            String text = mainPage.getPageText();

            if (text.isEmpty()) {
                logger.info("Не найден текст на странице: {}", link);
                continue;
            }

            List<Map<String, Object>> errors = SpellCheckHelper.checkSpellingInParts(text, spellCheckerService);

            if (!errors.isEmpty()) {
                System.out.println("Ошибки на странице: " + link);
                for (Map<String, Object> error : errors) {
                    System.out.println(error.get("word") +
                            ", исправления: " + error.get("s"));
                }
            }
        }
    }
}