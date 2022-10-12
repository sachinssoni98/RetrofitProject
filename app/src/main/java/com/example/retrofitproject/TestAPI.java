package com.example.retrofitproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TestAPI {

    @GET("products/{id}")
    Call<PojoClass> getData(@Path("id") int id);

}
