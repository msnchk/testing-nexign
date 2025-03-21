package com.github.msnchk.api.spellchecker;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SpellCheckerService {
    private static final String API_URL = "https://speller.yandex.net/services/spellservice.json/checkText";
    private static final Logger logger = LoggerFactory.getLogger(SpellCheckerService.class);
    private static final int MAX_RETRIES = 3;
    private static final int BASE_DELAY = 5000;

    public List<Map<String, Object>> checkSpelling(String text) {
        int attempt = 0;

        while (attempt < MAX_RETRIES) {
            try {
                Response response = RestAssured.given()
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .formParam("text", text)
                        .formParam("options", 6)
                        .post(API_URL)
                        .then()
                        .extract()
                        .response();

                if (response.getStatusCode() == 200) {
                    return response.as(new TypeRef<>() {});
                } else if (response.getStatusCode() == 503) {
                    attempt++;
                    int delay = BASE_DELAY * (int) Math.pow(2, attempt - 1);
                    logger.warn("Сервер временно недоступен (503). Попытка {} из {}. Ожидание {} мс...", attempt, MAX_RETRIES, delay);
                    TimeUnit.MILLISECONDS.sleep(delay);
                } else {
                    logger.error("Ошибка: код ответа {}. Запрос не будет повторяться.", response.getStatusCode());
                    return Collections.emptyList();
                }
            } catch (InterruptedException ignored) {
                logger.warn("Прерывание ожидания перед повторной попыткой");
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                logger.error("Ошибка при отправке запроса: {}", e.getMessage());
                return Collections.emptyList();
            }
        }

        logger.error("Не удалось выполнить проверку после {} попыток", MAX_RETRIES);
        return Collections.emptyList();
    }
}