package bookrecommendation.book.controller.rest;

import bookrecommendation.book.dto.WeatherCodeDto;
import bookrecommendation.book.service.WeatherService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class WeatherRestController {
    private final WeatherService weatherService;

    @Autowired
    public WeatherRestController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/weather")
    public ResponseEntity<WeatherCodeDto> getWeatherCode(){
        return ResponseEntity.status(HttpStatus.OK).body(WeatherService.getWeather());
    }
}
