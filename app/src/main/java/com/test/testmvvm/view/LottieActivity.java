package com.test.testmvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.test.testmvvm.R;
import com.test.testmvvm.databinding.ActivityLottieBinding;
import com.test.testmvvm.viewmodel.LottieViewModel;

import static com.test.testmvvm.viewmodel.LottieViewModel.theme;

public class LottieActivity extends AppCompatActivity {
    ActivityLottieBinding activityLottieBinding;
    private static final String TAG = LottieActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!(theme == 0)) {
            setTheme(theme);
        }
        activityLottieBinding = DataBindingUtil.setContentView(this, R.layout.activity_lottie);
        LottieViewModel.getInstance(activityLottieBinding,this);
    }

}
