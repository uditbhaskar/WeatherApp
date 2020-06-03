package com.example.weatherapp.services.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.services.model.DataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRepository {

    private final API api;
    public MutableLiveData<DataModel> weatherInfoMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> successResponse = new MutableLiveData<>();

    public APIRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);
    }

    public MutableLiveData<DataModel> loadWeatherInfo(String cityName) {
        api.getWeatherInfo(cityName).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {

                successResponse.setValue(true);
                // Log.d("DATA REQUIRED", new Gson().toJson(response));
                weatherInfoMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                weatherInfoMutableLiveData.setValue(null);
                successResponse.setValue(false);
            }
        });
        return weatherInfoMutableLiveData;
    }


}
