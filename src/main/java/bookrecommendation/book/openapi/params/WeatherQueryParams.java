package bookrecommendation.book.openapi.params;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class WeatherQueryParams {
    private String serviceKey;
    private int nx;
    private int ny;
    private String baseDate;
    private String baseTime;
    private String dataType;

    public WeatherQueryParams(){
        setServiceKey("BKuHZJ8L9XAchad4FpWJRKdC7Se202%2B4Kz318LRfX0Vpdz3Kp3aH7nq1Hu49yXajvS%2BuWKADnJMcrpLW1a9tmA%3D%3D");
        setNx(61);
        setNy(127);
        setBaseDate(getTodayDate());
        setBaseTime(getRecentHour());
        setDataType("json");
    }
    public static String getTodayDate(){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return today.format(formatter);
    }
    public static String getRecentHour(){
        int hour = LocalTime.now().getHour();

        if(hour<6){
            return "0200";
        }
        else if(hour<9){
            return "0500";
        }
        else if(hour<12){
            return "0800";
        }
        else if(hour<15){
            return "1100";
        }
        else if(hour<18){
            return "1400";
        }
        else if(hour<19){
            return "1700";
        }
        else if(hour<22){
            return "2000";
        }
        else{
            return "2300";
        }
    }
}
