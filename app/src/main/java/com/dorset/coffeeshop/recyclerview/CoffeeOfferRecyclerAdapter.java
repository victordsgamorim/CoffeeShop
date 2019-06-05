package com.dorset.coffeeshop.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dorset.coffeeshop.R;
import com.dorset.coffeeshop.model.object.CoffeeOffer;
import com.dorset.coffeeshop.ui.asynctask.CoffeeImageAsyncTask;
import com.dorset.coffeeshop.util.PriceUtil;

import java.util.List;

public class CoffeeOfferRecyclerAdapter extends RecyclerView.Adapter<CoffeeOfferRecyclerAdapter.OfferItemViewHolder> {

    private final Context context;
    private final List<CoffeeOffer> offer;

    public CoffeeOfferRecyclerAdapter(Context context, List<CoffeeOffer> offer) {
        this.context = context;
        this.offer = offer;
    }

    @NonNull
    @Override
    public OfferItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.coffee_offer_item_card_view, viewGroup, false);
        return new OfferItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OfferItemViewHolder menuItemViewHolder, int i) {
        CoffeeOffer offerItem = offer.get(i);
        menuItemViewHolder.bindInformation(offerItem);
    }

    @Override
    public int getItemCount() {
        return offer.size();
    }

    class OfferItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;
        private TextView itemName;
        private TextView itemPrice;


        public OfferItemViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.menu_item_name);
            itemPrice = itemView.findViewById(R.id.menu_item_price);
            itemImage = itemView.findViewById(R.id.menu_item_image);
        }

        public void bindInformation(CoffeeOffer offer) {
            itemName.setText(offer.getName());
            itemPrice.setText(PriceUtil.refectorEuroCurrency(offer.getPrice()));
            new CoffeeImageAsyncTask(itemImage).execute(offer.getImage());
        }
    }
}
