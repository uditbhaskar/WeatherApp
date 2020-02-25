package com.example.weatherapp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class LocationAPI {
    private static final String key = "d4hIf4RGdLozMwjG2TOklyD3beuFOD7s";
    private static final String url = "http://dataservice.accuweather.com/locations/v1/cities/geoposition/search";
    private static String latitude = SplashActivity.latitude;
    private static String longitude = SplashActivity.longitude;
    private static final String lat= latitude;
    private static final String longit = longitude;

    public static LocationService locationService = null;

    public static LocationService getService() {
        if (locationService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create()) //check manifest to use java compileOptions in gradel
                    .build();

            locationService = retrofit.create(LocationService.class);
        }
        return locationService;
    }

    public interface LocationService {
        @GET("?apikey=" + key )
        Call<LocationInfo> getLocationKey();



    }
}