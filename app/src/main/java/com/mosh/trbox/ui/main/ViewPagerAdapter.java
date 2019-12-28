package com.mosh.trbox.ui.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mosh.trbox.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final Context context;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> fragmentTitles = new ArrayList<>();

    private int[] imageResId = {
            R.drawable.ic_music,
            R.drawable.ic_booking,
            R.drawable.ic_library };

    public ViewPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return fragmentTitles.get(position);
        // Generate title based on item position
        /*Drawable image = context.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        // Replace blank spaces with image icon
        SpannableString sb = new SpannableString("   " + fragmentTitles.get(position));
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;*/
    }

    public void addFragment(Fragment fragment, String name) {
        fragmentList.add(fragment);
        fragmentTitles.add(name);
    }
}
