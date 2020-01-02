package com.mosh.trbox.ui.auth;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.mosh.trbox.R;

import java.util.ArrayList;
import java.util.List;


public class SlidingImage_Adapter extends PagerAdapter {


    private ArrayList<Integer> arrayList;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImage_Adapter(Context context) {
        this.context = context;
        this.arrayList = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    private void add(Integer item) {
        arrayList.add(item);
        //    notifyItemInserted(arrayList.size() - 1);
    }

    public void addAll(List<Integer> movieDatas) {
//        if (movieDatas.size() < 5) {
//            arrayList.addAll(movieDatas);
//        } else {
//            for (int i = 0; i < 5; i++) {
//                BaseMovie movie = movieDatas.get(i);
//                add(movie);
//            }
//        }
        for (Integer movieData : movieDatas) {
            add(movieData);
        }

        notifyDataSetChanged();
    }

    public void clear() {
        while (getCount() > 0) {
            arrayList.clear();
        }
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.item_login_slide, view, false);

        assert imageLayout != null;
        final ImageView imageView =  imageLayout
                .findViewById(R.id.image_login_slide);
        Integer imageItem = arrayList.get(position);
        imageView.setImageResource(imageItem);

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}