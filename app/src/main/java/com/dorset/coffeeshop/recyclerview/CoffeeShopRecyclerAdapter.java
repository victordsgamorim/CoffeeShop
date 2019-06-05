package com.dorset.coffeeshop.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dorset.coffeeshop.model.object.CoffeeImage;
import com.dorset.coffeeshop.model.Coffee;
import com.dorset.coffeeshop.ui.asynctask.CoffeeImageAsyncTask;
import com.dorset.coffeeshop.R;


import java.util.List;

public class CoffeeShopRecyclerAdapter extends RecyclerView.Adapter<CoffeeShopRecyclerAdapter.CoffeeViewHolder> {

    private Context context;
    private List<Coffee> coffeeList;
    private OnCoffeeClickListener onCoffeeClickListener;

    public CoffeeShopRecyclerAdapter(Context context, List<Coffee> coffeeList) {
        this.context = context;
        this.coffeeList = coffeeList;
    }

    @Override
    public CoffeeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewInflater = inflater.inflate(R.layout.coffee_shop_card_view, viewGroup, false);
        return new CoffeeViewHolder(viewInflater);
    }

    @Override
    public void onBindViewHolder(CoffeeViewHolder coffeeViewHolder, int i) {
        Coffee studio = coffeeList.get(i);
        coffeeViewHolder.bindInformation(studio);
    }

    public void setOnCoffeeClickListener(OnCoffeeClickListener onCoffeeClickListener) {
        this.onCoffeeClickListener = onCoffeeClickListener;
    }

    @Override
    public int getItemCount() {
        return coffeeList.size();
    }

    class CoffeeViewHolder extends RecyclerView.ViewHolder {

        private TextView coffeeName;
        private ImageView coffeeImage;
        private TextView coffeeRate;
        private Coffee cof;

        public CoffeeViewHolder(View itemView) {
            super(itemView);
            coffeeName = itemView.findViewById(R.id.coffee_shop_name);
            coffeeImage = itemView.findViewById(R.id.coffee_shop_image);
            coffeeRate = itemView.findViewById(R.id.coffee_shop_rate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCoffeeClickListener.onItemClick(cof, getAdapterPosition());
                }
            });
        }

        public void bindInformation(Coffee coffee) {
            this.cof = coffee;
            coffeeName.setText(coffee.getName());
            coffeeRate.setText(String.valueOf(coffee.getRate()));
            CoffeeImage image = coffee.getImage().get(0);
            String url = image.getUrl();
            new CoffeeImageAsyncTask(coffeeImage).execute(url);

        }
    }
}
