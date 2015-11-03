package com.flashapps.humorfinder.Managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.flashapps.humorfinder.Models.Joke;

import retrofit.Callback;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;

/**
 * Created by dietervaesen on 7/09/15.
 */
public class RestClient {

    private static ApiManagerService apiService;
    public static Context mContext;
    static SharedPreferences sharedPreferences;
    static String ENDPOINT = "https://sbb.appwise-dev.com";

    public interface ApiManagerService {

        @GET("/jokes/random")
        public void getRandomJoke(Callback<Joke> jokeCallback);




    }




    public static ApiManagerService getApiService(Context context) {
        mContext = context;


        apiService = getAdapter().create(ApiManagerService.class);
        return apiService;


    }


    private static RestAdapter getAdapter() {

        //solution for allocspace object error with retrofit parse realmobject
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Endpoints.newFixedEndpoint(ConstantManager.ENDPOINT_URL))
                .setConverter(new GsonConverter(ConstantManager.buildGson()));
        return builder.build();
    }








}



