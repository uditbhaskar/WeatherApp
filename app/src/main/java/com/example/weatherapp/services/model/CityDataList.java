package com.example.weatherapp.services.model;

import java.util.List;

public class CityDataList {


    private Integer dt;

    private Temp main;

    private List<Weather> weather = null;




    public CityDataList(Integer dt, Temp main, List<Weather> weather) {
        this.dt = dt;
        this.main = main;
        this.weather = weather;


    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Temp getMain() {
        return main;
    }

    public void setMain(Temp main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }




}
