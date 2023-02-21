package bookrecommendation.book.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class WeatherServiceTest {

    private final WeatherService weatherService;

    @Autowired
    WeatherServiceTest(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Test
    @DisplayName("Weather Service를 이용해 날씨 코드 받아오기")
    void getWeather() {
        System.out.println("weatherService = " + WeatherService.getWeather().getCode());
    }
}