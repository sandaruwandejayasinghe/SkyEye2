package com.example.skyeye2;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FusedLocationProviderClient fusedLocationClient;
    private TextView weatherConditionText, tempText, feelsLikeText, tempMaxText, tempMinText, humidityText, windSpeedText, addressText, rainfallText, timeText;
    private ImageView weatherImage;
    private EditText cityInput;
    private Button fetchButton, refreshButton;
    private WeatherFetcher weatherFetcher;
    LocationHandler locationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationHandler = new LocationHandler(this);
        weatherFetcher = new WeatherFetcher(this);
        fetchButton.setOnClickListener(v -> {
            String city = cityInput.getText().toString().trim();
            if (!city.isEmpty()) {
                fetchButton.setEnabled(false);
                fetchButton.setText("Fetching data...");
                weatherFetcher.fetchWeatherData(city);
            } else {
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show();
            }
        });

        refreshButton.setOnClickListener(v -> {
            refreshButton.setEnabled(false);
            refreshButton.setText("Refreshing data...");
            String city = cityInput.getText().toString().trim();
            if (!city.isEmpty()) {
                weatherFetcher.fetchWeatherData(city);
            } else {
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show();
                refreshButton.setEnabled(true);
                refreshButton.setText("Refresh");
            }
        });
    }

    private void initViews() {
        weatherConditionText = findViewById(R.id.weatherConditionText);
        tempText = findViewById(R.id.tempText);
        feelsLikeText = findViewById(R.id.feelsLikeText);
        tempMaxText = findViewById(R.id.tempMaxText);
        tempMinText = findViewById(R.id.tempMinText);
        humidityText = findViewById(R.id.humidityText);
        windSpeedText = findViewById(R.id.windSpeedText);
        addressText = findViewById(R.id.addressText);
        weatherImage = findViewById(R.id.weatherImage);
        rainfallText = findViewById(R.id.rainfallText);
        timeText = findViewById(R.id.timeText);
        cityInput = findViewById(R.id.cityEditText);
        fetchButton = findViewById(R.id.fetchWeatherButton);
        refreshButton = findViewById(R.id.refreshButton);
    }

    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        timeText.setText(currentTime);
    }

    public void updateWeatherUI(String temp, String feelsLike, String tempMax, String tempMin, String pressure, String humidity, String windSpeed, String weatherCondition, String rainfall) {
        weatherConditionText.setText(weatherCondition);
        tempText.setText(temp + "°");
        feelsLikeText.setText(feelsLike + "°C");
        tempMaxText.setText(tempMax + "°C");
        tempMinText.setText(tempMin + "°C");
        humidityText.setText(humidity + "%");
        windSpeedText.setText(windSpeed + " km/h");
        rainfallText.setText(rainfall + " mm");

        setWeatherImage(weatherCondition);
        updateTime();

        fetchButton.setEnabled(true);
        fetchButton.setText("Fetch Weather");
        refreshButton.setEnabled(true);
        refreshButton.setText("Refresh");
    }

    public void updateAddressUI(String address) {
        addressText.setText(address);
    }

    private void setWeatherImage(String weatherCondition) {
        switch (weatherCondition) {
            case "Clear":
                weatherImage.setImageResource(R.drawable.sunny);
                break;
            case "Clouds":
                weatherImage.setImageResource(R.drawable.cloudy);
                break;
            case "Rain":
            case "Drizzle":
                weatherImage.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                weatherImage.setImageResource(R.drawable.snowy);
                break;
            case "Thunderstorm":
                weatherImage.setImageResource(R.drawable.stormy);
                break;
            case "Atmosphere":
                weatherImage.setImageResource(R.drawable.windy);
                break;
            default:
                weatherImage.setImageResource(R.drawable.sunny);
                break;
        }
    }
}
