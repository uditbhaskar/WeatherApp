package com.example.weatherapp.services.model;


public class Temp {

    private Double temp;

    private Double feelsLike;

    private Double temp_min;

    private Double temp_max;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

    public Temp(Double temp, Double feelsLike, Double temp_min, Double temp_max) {
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }






}
