package com.dorset.coffeeshop.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dorset.coffeeshop.R;
import com.dorset.coffeeshop.model.Coffee;
import com.dorset.coffeeshop.ui.constants.CoffeeDataConstant;


public class CoffeeDetailActivity extends AppCompatActivity implements NewsletterFragment.NewsletterDialogListener {

    private Coffee coffee;
    private Bundle bundle;
    private AboutFragment homeFragment;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_detail);


        /** load selected coffee page*/
        homeFragment = new AboutFragment();

        loadIntentInformation();
        setAppBarNameAccordingToCoffeeShop();
        findViewsByIds();

        /**set bundle in order to send data information between the fragments*/
        setFragmentBundle();

        /**start first fragment as default*/
        startFirstFragment(homeFragment);

    }

    private void setAppBarNameAccordingToCoffeeShop() {
        setTitle(coffee.getName());
    }

    private void startFirstFragment(Fragment homeFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, homeFragment)
                .commit();
    }

    private void setFragmentBundle() {
        bundle = new Bundle();
        bundle.putSerializable(CoffeeDataConstant.BUNDLE_DATA, coffee);
        homeFragment.setArguments(bundle);
    }

    private void findViewsByIds() {
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private void loadIntentInformation() {
        Intent data = getIntent();
        coffee = null;
        if (data.hasExtra(CoffeeDataConstant.INTENT_DATA)) {
            coffee = (Coffee) data.getSerializableExtra(CoffeeDataConstant.INTENT_DATA);
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    selectedFragment = getFragmentSelected(menuItem, selectedFragment);
                    startFragmentSelected(selectedFragment);

                    return true;
                }
            };

    private Fragment getFragmentSelected(@NonNull MenuItem menuItem, Fragment selectedFragment) {
        if (menuItem.getItemId() == R.id.nav_home) {
            selectedFragment = homeFragment;
        } else if (menuItem.getItemId() == R.id.nav_location) {
            selectedFragment = new MapFragment();
        }
        return selectedFragment;
    }

    private void startFragmentSelected(Fragment selectedFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit();
    }

    @Override
    public void sendMessage(String name, String email) {

        if (name.length() <= 0 || email.length() <= 0) {
            Toast.makeText(CoffeeDetailActivity.this, "Please insert your Name/E-mail", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(CoffeeDetailActivity.this, "Thank you " + name
                + "\nE-mail sent to " + email, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.back_menu_list) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
