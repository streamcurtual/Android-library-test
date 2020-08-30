package com.test.testmvvm;

import android.app.Activity;
import android.content.Intent;

import com.test.testmvvm.view.ReflectTestActivity;

public class ClickHandler {
    private Activity context;

    public ClickHandler(Activity context){
        this.context = context;
    }

    public void onBackClick(){
        context.finish();
    }

    public void startReflectTestActivity(){
        context.startActivity(new Intent(context, ReflectTestActivity.class));
    }
}
