package com.example.practical10;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {
    @GET("/{number}")
    Call<String> getFact(@Path(value = "number", encoded = true) String number);
}