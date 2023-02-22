package bookrecommendation.book.service;

import bookrecommendation.book.dto.WeatherCodeDto;
import bookrecommendation.book.openapi.WeatherOpenDataParamSetter;
import bookrecommendation.book.openapi.params.WeatherQueryParams;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static bookrecommendation.book.openapi.constant.ApiUrlConstant.OPEN_DATA_WEATHER_API_URL;
import static bookrecommendation.book.openapi.constant.WeatherCodeConstant.*;

@Service
public class WeatherService {
    public static WeatherCodeDto getResult(JSONObject jsonObject){
        WeatherCodeDto weatherCodeDto = new WeatherCodeDto();

        JSONObject response = jsonObject.getJSONObject("response");
        JSONObject body = response.getJSONObject("body");
        JSONObject items = body.getJSONObject("items");
        JSONArray item = items.getJSONArray("item");

        String ptyResult = "0";
        String skyResult = "0";

        for (int i = 0; i < item.length(); i++) {
            JSONObject value = item.getJSONObject(i);
            String category = value.getString("category");
            if (category.equals("PTY")){
                ptyResult = value.getString("fcstValue");
            }
            if (category.equals("SKY")){
                skyResult = value.getString("fcstValue");
            }
        }

        switch(skyResult){
            case "3","4"-> weatherCodeDto.setCode(CLOUDY);
            default -> weatherCodeDto.setCode(CLEAR);
        }

        switch (ptyResult){
            case "1", "4" -> weatherCodeDto.setCode(RAINY);
            case "2","3" -> weatherCodeDto.setCode(SNOWY);
            default -> weatherCodeDto.setCode(CLEAR);
        }
        return weatherCodeDto;
    }

    public static WeatherCodeDto getWeather(){
        WeatherQueryParams params = new WeatherQueryParams();
        WeatherOpenDataParamSetter paramSetter = new WeatherOpenDataParamSetter();


        paramSetter.setRequestUrl(OPEN_DATA_WEATHER_API_URL);

        String requestUrl = paramSetter.setParams(params);
        String encodedUrl = requestUrl.replace("%25", "%");
        Connection connection = Jsoup.connect(encodedUrl);

        String response;
        try {
            response = connection.ignoreContentType(true).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getResult(new JSONObject(response));
    }
}
