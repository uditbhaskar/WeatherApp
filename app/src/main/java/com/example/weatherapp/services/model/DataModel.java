
package com.example.weatherapp.services.model;


import java.util.List;

public class DataModel {


    private List<CityDataList> list;
    private City city;

    public DataModel(List<CityDataList> list, City city) {

        this.list = list;
        this.city = city;
    }


    public List<CityDataList> getList() {
        return list;
    }

    public void setList(List<CityDataList> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}


