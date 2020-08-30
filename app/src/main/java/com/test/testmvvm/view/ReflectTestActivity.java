package com.test.testmvvm.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.View;

import com.test.testmvvm.Animal;
import com.test.testmvvm.R;
import com.test.testmvvm.databinding.ActivityReflectTestBinding;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectTestActivity extends AppCompatActivity {
    ActivityReflectTestBinding activityReflectTestBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterAnima();
        activityReflectTestBinding = DataBindingUtil.setContentView(this, R.layout.activity_reflect_test);
        activityReflectTestBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReflectTestActivity.this,LottieActivity.class));
                finish();
            }
        });
    }

    Class aClass;

    {
        try {
            aClass = Class.forName("com.test.testmvvm.Animal");
            Constructor constructor = aClass.getConstructor();
            Animal animal = (Animal)constructor.newInstance();
            System.out.println("animal.name = "+animal.name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    private void setEnterAnima() {
        Fade fade = new Fade();
        fade.setDuration(2000);
        getWindow().setEnterTransition(fade);
    }
}
