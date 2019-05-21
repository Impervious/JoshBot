package com.gitlab.impervious.utils;

import com.github.dvdme.ForecastIOLib.FIOCurrently;
import com.github.dvdme.ForecastIOLib.ForecastIO;

public class WeatherManager {

    private ForecastIO fio = new ForecastIO("ce76b6d9ce1df4a84cfb8177a1647178");


    public WeatherManager() {
        fio.setUnits(ForecastIO.UNITS_CA);
        fio.setLang(ForecastIO.LANG_ENGLISH);
        fio.getForecast("43.9109", "78.7884");
    }

    public Double getTemperature() {
        FIOCurrently currently = new FIOCurrently(fio);
        return currently.get().temperature();
    }
}
