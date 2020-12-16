package arlauskas.lukas.quayzy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://www.coderhymes.com/loginforquayzy/";
    public static Retrofit retrofit = null;
    public static Gson gson;

    public static Retrofit getApiClient()
    {
        if(retrofit==null)
        {
            gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }
}
