package com.kaffka.pixabayapp.api_services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PixabayService {

    public static PixabayApi createPixabayService() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://pixabay.com/");

        return builder.build().create(PixabayApi.class);
    }
}
