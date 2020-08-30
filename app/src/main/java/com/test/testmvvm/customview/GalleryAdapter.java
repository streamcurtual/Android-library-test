package com.test.testmvvm.customview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.testmvvm.R;
import com.test.testmvvm.beans.Activities;
import com.test.testmvvm.utils.FileUtil;

import java.util.ArrayList;

public class GalleryAdapter extends PagerAdapter {
    private ArrayList<String> resources;
    private ArrayList<Activities> list;
    private Context mContext;
    private View mCurrentView;
    private OnItemClick onItemClick;
    private String TAG = getClass().getSimpleName();

    public GalleryAdapter(ArrayList<String> resources, ArrayList<Activities> list, Context mContext) {
        this.resources = resources;
        this.mContext = mContext;
        this.list = list;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout
                .gallery_item, null);
        Log.i(TAG, "instantiateItem: " + position);
        ImageView poster = view.findViewById(R.id.poster);
        final RelativeLayout checkView = view.findViewById(R.id.lav_check);
        Activities activities = list.get(position);
        poster.setImageBitmap(FileUtil.imgs.get(FileUtil.images.get(position)));
        TextView name = view.findViewById(R.id.name);
        TextView intro = view.findViewById(R.id.intro);
        intro.setText(activities.getIntro());
        name.setText(String.format("%s%s", activities.getTime(), activities.getName()));
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkView.setVisibility(View.VISIBLE);

                if (onItemClick != null) {
                    onItemClick.onItemClick(checkView, position);
                }
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.i(TAG, "destroyItem: " + position);
        container.removeView((View) object);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        mCurrentView = (View) object;
    }

    public View getPrimaryItem() {
        return mCurrentView;
    }

    public interface OnItemClick {
        void onItemClick(View view, int position);
    }

}
