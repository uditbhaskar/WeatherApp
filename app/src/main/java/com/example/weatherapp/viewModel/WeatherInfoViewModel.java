package com.example.weatherapp.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.services.model.DataModel;
import com.example.weatherapp.services.repository.API;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherInfoViewModel extends ViewModel {
    private MutableLiveData<DataModel> weatherInfo;
    private MutableLiveData<Boolean> success;

    public void setSuccess(Boolean result) {
        success = new MutableLiveData<>();
        this.success.setValue(result);
    }

    public LiveData<Boolean> getSuccess() {
        return success;
    }

    public LiveData<DataModel> getWeatherInfo(String cityName) {
        weatherInfo = new MutableLiveData<>();
        loadWeatherInfo(cityName);

        return weatherInfo;
    }

    private void loadWeatherInfo(String city) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<DataModel> call = api.getWeatherInfo(city);

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response != null) {
                    setSuccess(true);
                   // Log.d("DATA REQUIRED", new Gson().toJson(response));
                    weatherInfo.setValue(response.body());
                }
                setSuccess(false);
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                setSuccess(false);
            }
        });

    }

}