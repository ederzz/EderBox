package com.eder.coolweather.util;

import android.text.TextUtils;
import android.util.Log;

import com.eder.coolweather.db.City;
import com.eder.coolweather.db.Country;
import com.eder.coolweather.db.Province;
import com.eder.coolweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provicneObj = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provicneObj.getString("name"));
                    province.setProvinceCode(provicneObj.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObj = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObj.getString("name"));
                    city.setCityCode(cityObj.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountryResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCountry = new JSONArray(response);
                for (int i = 0; i < allCountry.length(); i++) {
                    JSONObject countryObj = allCountry.getJSONObject(i);
                    Country country = new Country();
                    country.setCityId(cityId);
                    country.setCountryName(countryObj.getString("name"));
                    country.setWeatherId(countryObj.getString("weather_id"));
                    country.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 返回 Weather 实体类
    public static Weather handleWeatherResponse(String resp) {
        try {
            JSONObject jsonObject = new JSONObject((resp));
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
