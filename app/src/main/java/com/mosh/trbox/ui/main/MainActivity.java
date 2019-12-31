package com.mosh.trbox.ui.main;

import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.mosh.trbox.R;
import com.mosh.trbox.databinding.ActivityMainBinding;
import com.mosh.trbox.ui.main.booking.BookingFragment;
import com.mosh.trbox.ui.main.library.LibFragment;
import com.mosh.trbox.ui.main.music.MusicFragment;
import com.mosh.trbox.ui.musicPlaying.MediaControllerFragment;
import com.mosh.trbox.util.AppCoordinator;
import com.mosh.trbox.util.Constants;
import com.mosh.trbox.util.HelperRegistry;
import com.mosh.trbox.util.Pref;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {


    @Inject
    HelperRegistry helperRegistry;

    @Inject
    AppCoordinator coordinator;

    @Inject
    Pref pref;

    private Map<String, Runnable> navigationMap = new HashMap<String, Runnable>();
    ActivityMainBinding binding;
    private Handler navDrawerRunnable = new Handler();
    private Runnable runnable;
    private String action;
    private Runnable navigateLibrary = new Runnable() {
        public void run() {
           // navigationView.getMenu().findItem(R.id.nav_music).setChecked(true);
            Fragment fragment = new MainFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment).commitAllowingStateLoss();

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        action = getIntent().getAction();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        navigationMap.put(Constants.NAVIGATE_LIBRARY, navigateLibrary);
//        setSupportActionBar(binding.toolbar);
//        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("");
//
//        setupViewPager(binding.viewpager);
//
//        binding.slidingTabs.setupWithViewPager(binding.viewpager);
//        createTabIcons();
        navDrawerRunnable.postDelayed(new Runnable() {
            @Override
            public void run() {
                setupDrawerContent(binding.navView);
                setupNavigationIcons(binding.navView);
            }
        }, 700);


        loadEverything();


        addBackstackListener();

        if (Intent.ACTION_VIEW.equals(action)) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                  //  navigateNowplaying.run();
                }
            }, 350);
        }

    }

    private void loadEverything() {
        Runnable navigation = navigationMap.get(action);
        if (navigation != null) {
            navigation.run();
        } else {
            navigateLibrary.run();
        }
        navigateToRadioQuickControl();

    }

    public void navigateToRadioQuickControl() {
        Fragment fragment = new MediaControllerFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.bottom_media_controller, fragment).commit();

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(final MenuItem menuItem) {
                        updatePosition(menuItem);
                        return true;

                    }
                });
    }

    private void setupNavigationIcons(NavigationView navigationView) {



    }

    private void updatePosition(final MenuItem menuItem) {
        runnable = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_profile:


                break;
            case R.id.nav_logout:
                pref.saveUser(this,null);
                coordinator.launchLoginActivity(this);
                break;


        }

        if (runnable != null) {
            menuItem.setChecked(true);
            binding.drawerLayout.closeDrawers();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    runnable.run();
                }
            }, 350);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);

        viewPagerAdapter.addFragment(new MusicFragment(), "Music");
        viewPagerAdapter.addFragment(new BookingFragment(), "Booking");
        viewPagerAdapter.addFragment(new LibFragment(), "Library");

        viewPager.setAdapter(viewPagerAdapter);

    }

//    private void createTabIcons() {
//
//        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.nav_tab, null);
//        tabOne.setText("Music");
//        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_music, 0, 0);
//        binding.slidingTabs.getTabAt(0).setCustomView(tabOne);
//
//        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.nav_tab, null);
//        tabTwo.setText("Booking");
//        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_booking, 0, 0);
//        binding.slidingTabs.getTabAt(1).setCustomView(tabTwo);
//
//        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.nav_tab, null);
//        tabThree.setText("Library");
//        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_library, 0, 0);
//        binding.slidingTabs.getTabAt(2).setCustomView(tabThree);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                if (isNavigatingMain()) {
                    binding.drawerLayout.openDrawer(GravityCompat.START);
                } else super.onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isNavigatingMain() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        return (currentFragment instanceof MainFragment);
    }

    private void addBackstackListener() {
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                getSupportFragmentManager().findFragmentById(R.id.fragment_container).onResume();
            }
        });
    }
}
