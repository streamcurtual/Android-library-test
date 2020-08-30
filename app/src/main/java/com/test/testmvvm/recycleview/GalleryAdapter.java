package com.test.testmvvm.recycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = getClass().getSimpleName();
    private Context context;
    private OnItemClick onItemClick;
    private List<Activities> activities;
    private float mPagePadding = 50;
    private float mShowLeftCardWidth = 680;

    public GalleryAdapter(Context context,List<Activities> activities,OnItemClick onItemClick){
        this.context = context;
        this.onItemClick = onItemClick;
        this.activities = activities;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.gallery_item,parent,false);
//        onCreateViewHolder(parent,itemView);
        return new VHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final VHolder holder1 = (VHolder)holder;
        Log.i(TAG, "onBindViewHolder: ");
        Activities activities1 = activities.get(position);
        holder1.bg.setImageBitmap(FileUtil.imgs.get(FileUtil.images.get(position)));
        holder1.name.setText(activities1.getName());
        holder1.intro.setText(activities1.getIntro());
        holder1.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClick != null){
                    onItemClick.onItemClick(holder1.checkView,position);
                }
            }
        });
//        onBindViewHolder(holder1.rootView,position,activities.size());
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    class VHolder extends RecyclerView.ViewHolder{
        View rootView;
        RelativeLayout checkView;
        ImageView bg;
        TextView name,intro;

        private VHolder(View rootView){
            super(rootView);
            this.rootView = rootView;
            bg = rootView.findViewById(R.id.poster);
            checkView = rootView.findViewById(R.id.lav_check);
            name = rootView.findViewById(R.id.name);
            intro = rootView.findViewById(R.id.intro);
        }
    }

   public interface  OnItemClick{
        void onItemClick(View view,int position);
    }

//    public void onCreateViewHolder(ViewGroup parent, View itemView) {
//        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) itemView.getLayoutParams();
//        lp.width = parent.getWidth() - ScreenUtils.dip2px(itemView.getContext(), 2 * (mPagePadding + mShowLeftCardWidth));
//        itemView.setLayoutParams(lp);
//    }
//
//    public void onBindViewHolder(View itemView, final int position, int itemCount) {
//        int padding = ScreenUtils.dip2px(itemView.getContext(), mPagePadding);
//        itemView.setPadding(padding, 0, padding, 0);
//        int leftMarin = position == 0 ? padding + ScreenUtils.dip2px(itemView.getContext(), mShowLeftCardWidth) : 0;
//        int rightMarin = position == itemCount - 1 ? padding + ScreenUtils.dip2px(itemView.getContext(), mShowLeftCardWidth) : 0;
//        setViewMargin(itemView, leftMarin, 0, rightMarin, 0);
//    }
//
//    private void setViewMargin(View view, int left, int top, int right, int bottom) {
//        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
//        if (lp.leftMargin != left || lp.topMargin != top || lp.rightMargin != right || lp.bottomMargin != bottom) {
//            lp.setMargins(left, top, right, bottom);
//            view.setLayoutParams(lp);
//        }
//    }



}
