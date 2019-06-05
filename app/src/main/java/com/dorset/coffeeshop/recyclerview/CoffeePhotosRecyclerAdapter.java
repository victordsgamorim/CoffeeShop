package com.dorset.coffeeshop.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dorset.coffeeshop.R;
import com.dorset.coffeeshop.model.object.CoffeeImage;
import com.dorset.coffeeshop.ui.asynctask.CoffeeImageAsyncTask;


import java.util.List;

public class CoffeePhotosRecyclerAdapter extends RecyclerView.Adapter<CoffeePhotosRecyclerAdapter.CoffeePhotoViewHolder> {

    private final Context context;
    private final List<CoffeeImage> imageList;

    public CoffeePhotosRecyclerAdapter(Context context, List<CoffeeImage> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public CoffeePhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.coffee_photo_item_card_view, viewGroup, false);
        return new CoffeePhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeePhotoViewHolder coffeePhotoViewHolder, int i) {
        CoffeeImage image = imageList.get(i);
        coffeePhotoViewHolder.bindInformation(image);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }


    class CoffeePhotoViewHolder extends RecyclerView.ViewHolder {

        private ImageView photoImage;

        public CoffeePhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            photoImage = itemView.findViewById(R.id.photo_item_image);

        }

        void bindInformation(CoffeeImage image) {
            new CoffeeImageAsyncTask(photoImage).execute(image.getUrl());
        }
    }
}
