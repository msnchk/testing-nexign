package com.github.msnchk.utils;

import com.github.msnchk.api.spellchecker.SpellCheckerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SpellCheckHelper {
    private static final Logger logger = LoggerFactory.getLogger(SpellCheckHelper.class);
    private static final Set<String> IGNORED_WORDS = Set.of("Nexign");
    private static final int PART_SIZE = 10_000;

    public static List<Map<String, Object>> checkSpellingInParts(String text, SpellCheckerService spellChecker) {
        List<Map<String, Object>> allErrors = new ArrayList<>();

        for (int i = 0; i < text.length(); i += PART_SIZE) {
            int end = Math.min(i + PART_SIZE, text.length());
            String part = text.substring(i, end).trim();
            List<Map<String, Object>> errors = spellChecker.checkSpelling(part);

            errors.removeIf(error -> {
                String word = String.valueOf(error.get("word"));
                return IGNORED_WORDS.contains(word) || isAbbreviation(word);
            });

            allErrors.addAll(errors);
        }
        return allErrors;
    }

    private static boolean isAbbreviation(String word) {
        return word.matches("[A-ZА-ЯЁ]+");
    }
}
