package bookrecommendation.book.controller.rest;

import bookrecommendation.book.controller.status.StatusCode;
import bookrecommendation.book.controller.status.WeatherResponse;
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
    public ResponseEntity<WeatherResponse> getWeatherCode(){
        WeatherResponse response = new WeatherResponse();
        
        try {
            WeatherCodeDto weatherCodeDto = WeatherService.getWeather();
            response.setCode(StatusCode.OK);
            response.setMessage("날씨 API 호출 성공");
            response.setData(weatherCodeDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
