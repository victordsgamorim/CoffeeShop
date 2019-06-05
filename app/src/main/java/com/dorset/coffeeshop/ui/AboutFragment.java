package com.dorset.coffeeshop.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dorset.coffeeshop.R;
import com.dorset.coffeeshop.model.Coffee;
import com.dorset.coffeeshop.recyclerview.CoffeeOfferRecyclerAdapter;
import com.dorset.coffeeshop.recyclerview.CoffeePhotosRecyclerAdapter;
import com.dorset.coffeeshop.ui.asynctask.CoffeeImageAsyncTask;
import com.dorset.coffeeshop.ui.constants.CoffeeDataConstant;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;


public class AboutFragment extends Fragment{


    private Coffee coffee;
    private TextView shopName, weekdayOne, weekdayTwo, weekdayThree, informationText,
            contactTelephone, contactMobile, contactEmail, contactWebsite, coffeeRate;
    private FloatingActionButton fab;
    private RecyclerView menuRecyclerView, photoRecyclerView;
    private ImageView shopImage, facebookIcon, instagramIcon;
    private ExpandableRelativeLayout expandableLayout;
    private Button expandableButton;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about, container, false);

        findViewsById();

        /** get data informatin from bundle sent by the activity*/
        getLoadedInformation();

        /** add information to the respective view*/
        addLoadedInformationToView();

        return view;
    }

    private void setCoffeeRateData() {
        coffeeRate.setText(String.valueOf(coffee.getRate()));
    }

    private void buttonWebiteListener() {
        contactWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(coffee.getContact().get(3).getMethod()));
                startActivity(intent);
            }
        });
    }

    private void addingContactInformation() {
        expandableButtonListener();
        contactTelephone.setText(coffee.getContact().get(0).getMethod());
        contactMobile.setText(coffee.getContact().get(1).getMethod());
        contactEmail.setText(coffee.getContact().get(2).getMethod());
        contactWebsite.setText(coffee.getContact().get(3).getMethod());
    }

    private void expandableButtonListener() {
        isLayoutExpanded();
        expandableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout.toggle();
            }
        });
    }

    private void isLayoutExpanded() {
        if (expandableLayout.isExpanded()) {
            expandableLayout.collapse();
        }
    }

    private void socialMediaClickListener() {
        /**set coffee stars grade!*/
        setCoffeeRateData();

        facebookIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebook(coffee);
            }
        });
        instagramIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstagram(coffee);
            }
        });

        buttonWebiteListener();


    }

    private void openInstagram(Coffee coffee) {
        String instagram = coffee.getSocialMedia().get(1).getUrl();
        Uri uri = Uri.parse("http://instagram.com/_u" + instagram);
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com" + instagram)));

        }
    }

    public void openFacebook(Coffee coffee) {
        String url = coffee.getSocialMedia().get(0).getUrl();
        WebView webview = new WebView(getActivity());
        webview.loadUrl(url);

    }

    private void photoRecyclerView() {
        LinearLayoutManager linearLayoutPhotos =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        photoRecyclerView.setLayoutManager(linearLayoutPhotos);
        CoffeePhotosRecyclerAdapter coffeePhotosRecyclerAdapter =
                new CoffeePhotosRecyclerAdapter(getActivity(), coffee.getImage());
        photoRecyclerView.setAdapter(coffeePhotosRecyclerAdapter);
    }

    private void menuRecyclerView() {
        LinearLayoutManager linearLayoutMenu =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        menuRecyclerView.setLayoutManager(linearLayoutMenu);
        menuRecyclerView.setAdapter(new CoffeeOfferRecyclerAdapter(getActivity(), coffee.getMenu()));
    }

    private void getLoadedInformation() {
        if (getArguments() != null) {
            coffee = (Coffee) getArguments().getSerializable(CoffeeDataConstant.BUNDLE_DATA);
        }
    }

    private void addLoadedInformationToView() {
        setTopData();
        socialMediaClickListener();
        addingContactInformation();
        setInfoData(informationText, coffee.getInformation());
        menuRecyclerView();
        photoRecyclerView();

    }

    private void setInfoData(TextView informationText, String information) {
        informationText.setText(information);
    }

    private void setTopData() {
        shopName.setText(coffee.getName());
        weekdayOne.setText(coffee.getOpenHour().get(0).getHour()[0] + " :" + coffee.getOpenHour().get(0).getWeek()[0]);
        weekdayTwo.setText(coffee.getOpenHour().get(0).getHour()[1] + " :" + coffee.getOpenHour().get(0).getWeek()[1]);
        weekdayThree.setText(coffee.getOpenHour().get(0).getHour()[1] + " :" + coffee.getOpenHour().get(0).getWeek()[2]);
        String imageUrl = coffee.getImage().get(0).getUrl();
        new CoffeeImageAsyncTask(shopImage).execute(imageUrl);
        floatButtonNewsLetter();
    }

    private void floatButtonNewsLetter() {
        fab = view.findViewById(R.id.fab_newsletter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsletterFragment newsletterFragment = new NewsletterFragment();
                newsletterFragment.show(getActivity().getSupportFragmentManager(), "Newsletter");
            }
        });
    }

    private void findViewsById() {
        shopName = view.findViewById(R.id.coffee_shop_detail_name);
        shopImage = view.findViewById(R.id.coffee_shop_detail_image);
        weekdayOne = view.findViewById(R.id.coffe_shop_detail_opening_1);
        weekdayTwo = view.findViewById(R.id.coffe_shop_detail_opening_2);
        weekdayThree = view.findViewById(R.id.coffe_shop_detail_opening_3);
        informationText = view.findViewById(R.id.cs_information_text);
        menuRecyclerView = view.findViewById(R.id.coffee_shop_menu_recycler_view);
        photoRecyclerView = view.findViewById(R.id.coffee_shop_photos_recycler_view);
        facebookIcon = view.findViewById(R.id.social_media_facebook);
        instagramIcon = view.findViewById(R.id.social_media_instagram);
        expandableButton = view.findViewById(R.id.button_expandable);
        expandableLayout = view.findViewById(R.id.examplandle_content);
        contactTelephone = view.findViewById(R.id.contact_telephone);
        contactMobile = view.findViewById(R.id.contact_mobile);
        contactEmail = view.findViewById(R.id.contact_email);
        contactWebsite = view.findViewById(R.id.contact_url_website);
        coffeeRate = view.findViewById(R.id.coffee_shop_rate_information);
    }

}
