package com.test.testmvvm.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.test.testmvvm.R;
import com.test.testmvvm.recycleview.GalleryAdapter;
import com.test.testmvvm.utils.FileUtil;

public class ActiveActivity extends AppCompatActivity implements View.OnClickListener {
    Button button2;
    String TAG = getClass().getSimpleName();
//    ViewPager pager;
    //    private ImageView animationView;
    RecyclerView recyclerView;

    @Override
    protected void onStop() {
        super.onStop();
    }

    public Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();
        initPager();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
//                startActivity(new Intent(ActiveActivity.this, DiningActivity.class));
                break;
            case R.id.view_pager:
                break;
        }

    }

    private void sendHandleMessage(int what, Object obj, long time) {
        android.os.Message message = mHandler.obtainMessage();
        message.what = what;
        message.obj = obj;
        if (time < 0) {
            message.sendToTarget();
        } else {
            mHandler.sendMessageDelayed(message, time);
        }

//        pager.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        switch (keyCode) {
            case 22://右
                break;
            case 21://左
                break;
            case 19://上
                if (getCurrentFocus().getId() == button2.getId()) {
                    recyclerView.setFocusable(true);
                    button2.setFocusable(false);
                }
                break;
            case 20://下
                if (getCurrentFocus().getId() == button2.getId()) {
                    recyclerView.setFocusable(false);
                    button2.setFocusable(true);
                }
                break;
            case 66://中间
                break;
            case 82://三条
                break;
            case 25://-
                break;
            case 24://+
                break;
        }
        return true;
    }

    private GalleryAdapter adapter;
    private GalleryAdapter.OnItemClick onItemClick;

    private void initPager() {
        recyclerView = findViewById(R.id.view_pager);

        onItemClick = new GalleryAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view,int position) {
//                Activities choose = FileUtil.activities.get(position);
                Log.i(TAG, "onItemClick: position "+position);
                view.setVisibility(View.VISIBLE);
//                MqttHelper.sendMessage(MqttConstants.PUB_TOPICS_ROBOT, Command.PLAY_VOICE + "_" + choose.getTime() + choose.getName() + choose.getIntro());
            }
        };
        adapter = new GalleryAdapter(ActiveActivity.this, FileUtil.activities, onItemClick);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(adapter);
//        recyclerView.setOnFlingListener(new RecyclerView.OnFlingListener() {
//            @Override
//            public boolean onFling(int velocityX, int velocityY) {
//                Log.i(TAG, "onFling: "+velocityX+" "+velocityY);
//                return false;
//            }
//        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.i(TAG, "onScrolled: "+dx);
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

}

