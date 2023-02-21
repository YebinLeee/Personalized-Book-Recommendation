package bookrecommendation.book.openapi.params;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class WeatherQueryParamsTest {

    @Test
    void setTime() {
        WeatherQueryParams params = new WeatherQueryParams();
        System.out.println(WeatherQueryParams.getRecentHour());
    }
}