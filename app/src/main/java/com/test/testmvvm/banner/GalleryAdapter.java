//package com.test.testmvvm.banner;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.test.testmvvm.R;
//import com.test.testmvvm.beans.Activities;
//import com.test.testmvvm.utils.FileUtil;
//import com.youth.banner.adapter.BannerAdapter;
//
//import java.util.List;
//
//public class GalleryAdapter extends BannerAdapter {
//    List<Activities> list;
//    Context context;
//    OnItemClick onItemClick;
//    private String TAG = getClass().getSimpleName();
//
//    public GalleryAdapter(List<Activities> list,Context context,OnItemClick onItemClick){
//        super(list);
//        this.list = list;
//        this.context = context;
//        this.onItemClick = onItemClick;
//    }
//
//
//    @Override
//    public Object onCreateHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(context).inflate(R.layout.gallery_item,parent,false);
//        return new BannerViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindView(Object holder, Object data, final int position, int size) {
//        final BannerViewHolder holder1 = (BannerViewHolder) holder;
//        Activities activities1 = list.get(position);
//        holder1.bg.setImageBitmap(FileUtil.imgs.get(FileUtil.images.get(position)));
//        holder1.name.setText(activities1.getName());
//        holder1.intro.setText(activities1.getIntro());
//        holder1.rootView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "onClick: ");
//                holder1.checkView.setVisibility(View.VISIBLE);
//                if(onItemClick != null){
//                    onItemClick.onItemClick(position);
//                }
//            }
//        });
//    }
//
//    class BannerViewHolder extends RecyclerView.ViewHolder{
//        View rootView;
//        RelativeLayout checkView;
//        ImageView bg;
//        TextView name,intro;
//
//        public BannerViewHolder(View rootView){
//            super(rootView);
//            this.rootView = rootView;
//            bg = rootView.findViewById(R.id.poster);
//            checkView = rootView.findViewById(R.id.lav_check);
//            name = rootView.findViewById(R.id.name);
//            intro = rootView.findViewById(R.id.intro);
//        }
//
//    }
//
//    public interface  OnItemClick{
//        void onItemClick(int position);
//    }
//}
