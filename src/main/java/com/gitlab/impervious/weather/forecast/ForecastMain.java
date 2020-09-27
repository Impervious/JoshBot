
package com.gitlab.impervious.weather.forecast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ForecastMain {

    public ForecastMain() throws Exception {
    }

    private static String readURL(String urlString) throws Exception {
        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder builder = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                builder.append(chars, 0, read);
            }
            return builder.toString();
        } finally {
            if(reader != null) {
                reader.close();
            }
        }
    }

    public String jsonURL = readURL("https://api.openweathermap.org/data/2.5/onecall?lat=43.900417&lon=-78.796750&exclude=minutely,hourly&appid=0aa063ceb85171fa3a9b1e0882758357&units=metric");

    @SerializedName("current")
    private Current mCurrent;
    @SerializedName("daily")
    private List<Daily> mDaily;
    @SerializedName("lat")
    private Double mLat;
    @SerializedName("lon")
    private Double mLon;
    @SerializedName("timezone")
    private String mTimezone;
    @SerializedName("timezone_offset")
    private Long mTimezoneOffset;

    public Current getCurrent() {
        return mCurrent;
    }

    public void setCurrent(Current current) {
        mCurrent = current;
    }

    public List<Daily> getDaily() {
        return mDaily;
    }

    public void setDaily(List<Daily> daily) {
        mDaily = daily;
    }

    public Double getLat() {
        return mLat;
    }

    public void setLat(Double lat) {
        mLat = lat;
    }

    public Double getLon() {
        return mLon;
    }

    public void setLon(Double lon) {
        mLon = lon;
    }

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }

    public Long getTimezoneOffset() {
        return mTimezoneOffset;
    }

    public void setTimezoneOffset(Long timezoneOffset) {
        mTimezoneOffset = timezoneOffset;
    }

}
