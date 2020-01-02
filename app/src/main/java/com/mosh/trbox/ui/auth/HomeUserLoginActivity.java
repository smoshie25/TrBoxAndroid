package com.mosh.trbox.ui.auth;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;


import com.mosh.trbox.R;
import com.mosh.trbox.databinding.ActivityHomeUserLoginBinding;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeUserLoginActivity extends AppCompatActivity {
    private ActivityHomeUserLoginBinding binding;
    private int currentPage;
    private SlidingImage_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_home_user_login);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_user_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        ArrayList<Integer> myImageList = new ArrayList<>();
        myImageList.add(R.drawable.music_intro1);
        myImageList.add(R.drawable.music_intro2);
        myImageList.add(R.drawable.music_intro3);
        myImageList.add(R.drawable.music_intro4);

        adapter = new SlidingImage_Adapter(HomeUserLoginActivity.this);
        binding.pager.setAdapter(adapter);
        adapter.addAll(myImageList);
        adapter.notifyDataSetChanged();
        binding.titles.setViewPager(binding.pager);
        autoSlide();
        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeUserLoginActivity.this , LoginActivity.class));
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeUserLoginActivity.this , SignUpActivity.class));
            }
        });
    }

    private void autoSlide() {
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == adapter.getCount()) {
                    currentPage = 0;
                }
                binding.pager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);


        binding.titles.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }
}
