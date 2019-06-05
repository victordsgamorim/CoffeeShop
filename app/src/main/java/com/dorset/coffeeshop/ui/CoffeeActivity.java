package com.dorset.coffeeshop.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dorset.coffeeshop.R;
import com.dorset.coffeeshop.retrofit.CoffeeRetrofit;

public class CoffeeActivity extends Activity {

    private RecyclerView recylerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_shop);
        setTitle("Coffee Shop List");

        recylerView = findViewById(R.id.recycler_view_coffee_list);
        recylerView.setLayoutManager(new LinearLayoutManager(this));

        /** responsible to load and add internet data into recycler view*/
        new CoffeeRetrofit(CoffeeActivity.this, recylerView);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
