package com.example.weatherapp.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Size;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.services.model.DataModel;
import com.example.weatherapp.viewModel.WeatherInfoViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout_currentDayWeather;
    ImageButton btn_search;
    EditText et_location;
    TextView tv_temperature_current;
    ImageView iv_WeatherIcon;
    TextView tv_weatherDescription;
    TextView tv_sunrise;
    TextView tv_sunset;
    LottieAnimationView lottieAnimationView;

    String cityName;

    LinearLayout linearLayout_forecastWeather;
    TextView tv_firstDayName;
    TextView tv_maxTempFirstDay;

    TextView tv_secondDayName;
    TextView tv_maxTempSecondDay;

    TextView tv_thirdDayName;
    TextView tv_maxTempThirdDay;

    TextView tv_fourthDayName;
    TextView tv_maxTempFourthDay;

    TextView tv_fifthDayName;
    TextView tv_maxTempFifthDay;


    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        callViewModel("Bengaluru");

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityName = et_location.getText().toString();
                callViewModel(cityName);
            }
        });

    }

    private void callViewModel(String cityName) {
        WeatherInfoViewModel viewModel = ViewModelProviders.of(this).get(WeatherInfoViewModel.class);
        viewModel.getWeatherInfo(cityName).observe(this, new Observer<DataModel>() {
            @Override
            public void onChanged(DataModel dataModel) {
                Boolean result = viewModel.getSuccess().getValue();

                if (dataModel != null && result) {
                    frameLayout.setVisibility(View.GONE);
                    String location = dataModel.getCity().getName();
                    Double temperature_current_string = dataModel.getList().get(0).getMain().getTemp();
                    String temperature_current = temperature_current(temperature_current_string);

                    String iconURL = dataModel.getList().get(0).getWeather().get(0).getIcon();
                    String imageUrl = "https://openweathermap.org/img/w/" + iconURL + ".png";
                    String description = dataModel.getList().get(0).getWeather().get(0).getDescription();
                    String sunRise = timeStamp(dataModel.getCity().getSunrise());
                    String sunSet = timeStamp(dataModel.getCity().getSunset());

                    String firstDayName = dayName(dataModel.getList().get(0).getDt());
                    Double firstDayMaxTemp = dataModel.getList().get(0).getMain().getTemp_max();
                    Double firstDayMinTemp = dataModel.getList().get(2).getMain().getTemp_min();
                    String firstDayTemp = temperature(firstDayMinTemp,firstDayMaxTemp);

                    String secondDayName = dayName(dataModel.getList().get(9).getDt());
                    Double secondDayMaxTemp = dataModel.getList().get(8).getMain().getTemp_max();
                    Double secondDayMinTemp = dataModel.getList().get(10).getMain().getTemp_min();
                    String secondDayTemp = temperature(secondDayMinTemp,secondDayMaxTemp);

                    String thirdDayName = dayName(dataModel.getList().get(16).getDt());
                    Double thirdDayMaxTemp = dataModel.getList().get(15).getMain().getTemp_max();
                    Double thirdDayMinTemp = dataModel.getList().get(18).getMain().getTemp_min();
                    String thirdDayTemp = temperature(thirdDayMinTemp,thirdDayMaxTemp);

                    String fourthDayName = dayName(dataModel.getList().get(23).getDt());
                    Double fourthDayMaxTemp = dataModel.getList().get(24).getMain().getTemp_max();
                    Double fourthDayMinTemp = dataModel.getList().get(20).getMain().getTemp_min();
                    String fourthDayTemp = temperature(fourthDayMinTemp,fourthDayMaxTemp);

                    String fifthDayName = dayName(dataModel.getList().get(dataModel.getList().size()-7).getDt());
                    Double fifthDayMaxTemp = dataModel.getList().get(dataModel.getList().size()-1).getMain().getTemp_max();
                    Double fifthDayMinTemp = dataModel.getList().get(dataModel.getList().size()-4).getMain().getTemp_min();
                    String fifthDayTemp = temperature(fifthDayMinTemp,fifthDayMaxTemp);

                    if ((temperature_current != null && !temperature_current.isEmpty()) && (location != null && !location.isEmpty()) && (sunRise != null && !sunRise.isEmpty()) &&
                            (sunSet != null && !sunSet.isEmpty()) &&
                            (description != null && !description.isEmpty()) &&
                            (iconURL != null && !iconURL.isEmpty()) &&
                            (imageUrl != null && !imageUrl.isEmpty()) &&
                            (firstDayName != null && !firstDayName.isEmpty()) &&
                            (fifthDayName != null && !fifthDayName.isEmpty())) {

                        et_location.setText(location);
                        tv_temperature_current.setText(temperature_current);
                        Glide.with(MainActivity.this).load(imageUrl).into(iv_WeatherIcon);
                        tv_weatherDescription.setText(description);
                        tv_sunrise.setText(sunRise);
                        tv_sunset.setText(sunSet);

                        tv_firstDayName.setText(firstDayName);
                        tv_maxTempFirstDay.setText(firstDayTemp);


                        tv_secondDayName.setText(secondDayName);
                        tv_maxTempSecondDay.setText(secondDayTemp);


                        tv_thirdDayName.setText(thirdDayName);
                        tv_maxTempThirdDay.setText(thirdDayTemp);


                        tv_fourthDayName.setText(fourthDayName);
                        tv_maxTempFourthDay.setText(fourthDayTemp);


                        tv_fifthDayName.setText(fifthDayName);
                        tv_maxTempFifthDay.setText(fifthDayTemp);


                    }else {
                        Toast.makeText(MainActivity.this, "Enter valid city name!", Toast.LENGTH_LONG).show();



                    }
                }else {
                    Toast.makeText(MainActivity.this, "Enter valid city name!", Toast.LENGTH_LONG).show();
                    frameLayout.setVisibility(View.VISIBLE);


                }

            }
        });

    }

    public String temperature(Double temp_max,Double temp_min) {
        int temperature_max = (int) Math.round(temp_max - 273);
        int temperature_min = (int) Math.round(temp_min - 273);
        int temperature = (temperature_max+temperature_min)/2;
        return String.valueOf(temperature)+ "\u00B0";
    }
    public String temperature_current(Double temp) {
        int temperature = (int) Math.round(temp - 273);
        return String.valueOf(temperature)+ "\u00B0";
    }

    public String timeStamp(int timeStamp) {

        Date date = new Date(timeStamp * 1000L);
        SimpleDateFormat jdf = new SimpleDateFormat("HH:mm a");
        jdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String java_date = jdf.format(date);
        return java_date;

    }

    public String dayName(int timeStamp) {
        Date date = new Date(timeStamp * 1000L);
        SimpleDateFormat jdf = new SimpleDateFormat("EEE");
        jdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String java_date = jdf.format(date);
        return java_date;
    }


    private void init() {
        linearLayout_currentDayWeather = findViewById(R.id.linear_current_day_weather);
        btn_search = findViewById(R.id.btn_search);
        et_location = findViewById(R.id.et_location_current);
        tv_temperature_current = findViewById(R.id.tv_temperature_current);
        iv_WeatherIcon = findViewById(R.id.iv_weather_current_icon);
        tv_weatherDescription = findViewById(R.id.tv_temp_description_current);
        tv_sunrise = findViewById(R.id.tv_sunrise_current);
        tv_sunset = findViewById(R.id.tv_sunset_current);


        linearLayout_forecastWeather = findViewById(R.id.linear_forecast_weather);
        tv_firstDayName = findViewById(R.id.tv_first_day);
        tv_maxTempFirstDay = findViewById(R.id.tv_first_day_max_temp);

        tv_secondDayName = findViewById(R.id.tv_second_day);
        tv_maxTempSecondDay = findViewById(R.id.tv_second_day_max_temp);

        tv_thirdDayName = findViewById(R.id.tv_third_day);
        tv_maxTempThirdDay = findViewById(R.id.tv_third_day_max_temp);

        tv_fourthDayName = findViewById(R.id.tv_fourth_day);
        tv_maxTempFourthDay = findViewById(R.id.tv_fourth_day_max_temp);

        tv_fifthDayName = findViewById(R.id.tv_fifth_day);
        tv_maxTempFifthDay = findViewById(R.id.tv_fifth_day_max_temp);

        frameLayout = findViewById(R.id.frameLayout);

    }
}
