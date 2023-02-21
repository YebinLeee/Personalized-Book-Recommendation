package bookrecommendation.book.service;

import jakarta.transaction.Transactional;
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
    void getWeather() {
        System.out.println("weatherService = " + weatherService.getWeather().getCode());
    }
}