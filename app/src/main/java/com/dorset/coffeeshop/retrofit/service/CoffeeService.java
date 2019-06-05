package com.dorset.coffeeshop.retrofit.service;

import com.dorset.coffeeshop.model.Coffee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoffeeService {

    @GET("bins/pq0pq")
    Call<List<Coffee>> getPosts();
}
