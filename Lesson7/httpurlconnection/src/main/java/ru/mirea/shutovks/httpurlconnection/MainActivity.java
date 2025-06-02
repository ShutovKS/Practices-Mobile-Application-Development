package ru.mirea.shutovks.httpurlconnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView textCity, textRegion, textWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textCity = findViewById(R.id.textCity);
        textRegion = findViewById(R.id.textRegion);
        textWeather = findViewById(R.id.textWeather);

        Button button = findViewById(R.id.buttonGetWeather);
        button.setOnClickListener(v -> {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                new DownloadIpTask().execute("https://ipinfo.io/json");
            } else {
                Toast.makeText(this, "Нет интернета", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class DownloadIpTask extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... urls) {
            try {
                String json = downloadUrl(urls[0]);
                return new JSONObject(json);
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            if (json == null) return;
            try {
                String city = json.getString("city");
                String region = json.getString("region");
                String loc = json.getString("loc");
                textCity.setText("Город: " + city);
                textRegion.setText("Регион: " + region);

                String[] parts = loc.split(",");
                double lat = Double.parseDouble(parts[0]);
                double lon = Double.parseDouble(parts[1]);
                new DownloadWeatherTask().execute(lat, lon);
            } catch (Exception e) {
                textCity.setText("Ошибка получения города");
                textRegion.setText("Ошибка получения региона");
                textWeather.setText("Ошибка получения погоды");
            }
        }
    }

    private class DownloadWeatherTask extends AsyncTask<Double, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(Double... coords) {
            String weatherUrl = String.format("https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current_weather=true", coords[0], coords[1]);
            try {
                String json = downloadUrl(weatherUrl);
                return new JSONObject(json);
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            if (json == null) return;
            try {
                JSONObject currentWeather = json.getJSONObject("current_weather");
                String result = "Темп: " + currentWeather.getDouble("temperature") + "°C, " +
                        "Ветер: " + currentWeather.getDouble("windspeed") + " км/ч";
                textWeather.setText("Погода: " + result);
            } catch (Exception e) {
                textWeather.setText("Ошибка получения погоды");
                Log.e("MainActivity", "Error parsing weather data", e);
            }
        }
    }

    private String downloadUrl(String urlStr) throws IOException {
        InputStream inputStream = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }

            inputStream = conn.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int b;
            while ((b = inputStream.read()) != -1) out.write(b);
            return out.toString();
        } finally {
            if (inputStream != null) inputStream.close();
        }
    }
}
