package com.mosh.trbox.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.mosh.trbox.R;
import com.mosh.trbox.databinding.ActivityMainBinding;
import com.mosh.trbox.ui.auth.AuthViewModel;
import com.mosh.trbox.ui.main.booking.BookingFragment;
import com.mosh.trbox.ui.main.library.LibFragment;
import com.mosh.trbox.ui.main.music.MusicFragment;
import com.mosh.trbox.util.AppCoordinator;
import com.mosh.trbox.util.HelperRegistry;
import com.mosh.trbox.util.Pref;
import com.mosh.trbox.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {


    @Inject
    HelperRegistry helperRegistry;

    @Inject
    AppCoordinator coordinator;

    @Inject
    Pref pref;

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupViewPager(binding.viewpager);

        binding.slidingTabs.setupWithViewPager(binding.viewpager);
        createTabIcons();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);

        viewPagerAdapter.addFragment(new MusicFragment(), "Music");
        viewPagerAdapter.addFragment(new BookingFragment(), "Booking");
        viewPagerAdapter.addFragment(new LibFragment(), "Library");

        viewPager.setAdapter(viewPagerAdapter);

    }

    private void createTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.nav_tab, null);
        tabOne.setText("Music");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_music, 0, 0);
        binding.slidingTabs.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.nav_tab, null);
        tabTwo.setText("Booking");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_booking, 0, 0);
        binding.slidingTabs.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.nav_tab, null);
        tabThree.setText("Library");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_library, 0, 0);
        binding.slidingTabs.getTabAt(2).setCustomView(tabThree);
    }

}
