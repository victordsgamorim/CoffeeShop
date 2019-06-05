package com.dorset.coffeeshop.retrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dorset.coffeeshop.model.Coffee;
import com.dorset.coffeeshop.recyclerview.CoffeeShopRecyclerAdapter;
import com.dorset.coffeeshop.recyclerview.OnCoffeeClickListener;
import com.dorset.coffeeshop.retrofit.service.CoffeeService;
import com.dorset.coffeeshop.ui.CoffeeDetailActivity;
import com.dorset.coffeeshop.ui.constants.CoffeeDataConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoffeeRetrofit {

    /**
     * service of using htttp methods
     */
    private CoffeeService coffeeService;
    private CoffeeShopRecyclerAdapter recyclerViewAdapter;

    private final Context context;
    private final RecyclerView recyclerView;


    public CoffeeRetrofit(Context context, RecyclerView recylerView) {

        this.context = context;
        this.recyclerView = recylerView;

        Retrofit retrofit = accessDataRetrofit();
        loadDataRetrofit(retrofit);

    }

    private void loadDataRetrofit(Retrofit retrofit) {
        coffeeService = retrofit.create(CoffeeService.class);
        Call<List<Coffee>> call = coffeeService.getPosts();
        call.enqueue(coffeServiceCallBack);
    }

    private Retrofit accessDataRetrofit() {
        return new Retrofit.Builder().baseUrl("http://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    Callback<List<Coffee>> coffeServiceCallBack = new Callback<List<Coffee>>() {

        /** gets data if has any*/
        @Override
        public void onResponse(Call<List<Coffee>> call, Response<List<Coffee>> response) {
            if (!response.isSuccessful()) {
                Toast.makeText(context, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                return;

            }
            List<Coffee> posts = response.body();

            /** adding data to reyclerview*/
            addLoadedDataRecyclerView(posts);
        }

        /** failure message while loading data*/
        @Override
        public void onFailure(Call<List<Coffee>> call, Throwable t) {
            Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void addLoadedDataRecyclerView(List<Coffee> posts) {
        recyclerViewAdapter = new CoffeeShopRecyclerAdapter(context, posts);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewListener();
    }

    private void recyclerViewListener() {
        recyclerViewAdapter.setOnCoffeeClickListener(new OnCoffeeClickListener() {
            @Override
            public void onItemClick(Coffee coffee, int position) {
                Intent intent = new Intent(context, CoffeeDetailActivity.class);
                intent.putExtra(CoffeeDataConstant.INTENT_DATA, coffee);
                context.startActivity(intent);
            }
        });
    }


    public CoffeeService getCoffeeService() {
        return coffeeService;
    }
}
