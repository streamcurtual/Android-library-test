package com.test.testmvvm.viewmodel;

import android.app.ActivityOptions;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.parobot.pluginlib.plugin.PluginManager;
import com.parobot.pluginlib.plugin.ProxyActivity;
import com.test.testmvvm.R;
import com.test.testmvvm.databinding.ActivityLottieBinding;
import com.test.testmvvm.receiver.Darclass;
import com.test.testmvvm.recycleviewmodel.view.RecycleViewActivity;
import com.test.testmvvm.utils.FileUtil;
import com.test.testmvvm.view.ActiveActivity;
import com.test.testmvvm.view.KtActivity;
import com.test.testmvvm.view.LottieActivity;
import com.test.testmvvm.view.ReflectTestActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LottieViewModel {
    private static final String TAG = LottieViewModel.class.getSimpleName();
    private static LottieViewModel lottieViewModel;
    private Context context;
    public static int theme = 0;


    public static LottieViewModel getInstance(final ActivityLottieBinding activityLottieBinding,Context context){
        if(lottieViewModel == null){
            lottieViewModel = new LottieViewModel();
        }
        activityLottieBinding.setViewmodel(lottieViewModel);

        lottieViewModel.context = context;
        FileUtil.getActivities();

        //        YoYo.with(Techniques.Tada).duration(10000).playOn(activityLottieBinding.iv01);
//        YoYo.with(Techniques.Bounce).duration(10000).playOn(activityLottieBinding.iv02);
//        YoYo.with(Techniques.BounceIn).duration(10000).playOn(activityLottieBinding.iv03);
//        YoYo.with(Techniques.Swing).withListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                animation.start();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        }).duration(10000).playOn(activityLottieBinding.iv04);
//        YoYo.with(Techniques.TakingOff).duration(10000).playOn(activityLottieBinding.iv05);
//        YoYo.with(Techniques.Swing).withListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                YoYo.with(Techniques.Flash).duration(10000).playOn(activityLottieBinding.iv019);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        }).duration(2000).playOn(activityLottieBinding.iv019);
//
//        YoYo.with(Techniques.Wobble).duration(10000).playOn(activityLottieBinding.iv07);
//        YoYo.with(Techniques.ZoomIn).duration(3000).playOn(activityLottieBinding.iv08);
//        YoYo.with(Techniques.Flash).duration(10000).playOn(activityLottieBinding.iv09);
//        YoYo.with(Techniques.FlipInX).duration(10000).playOn(activityLottieBinding.iv010);
//        YoYo.with(Techniques.Hinge).duration(10000).playOn(activityLottieBinding.iv011);
//        YoYo.with(Techniques.Landing).duration(10000).playOn(activityLottieBinding.iv012);
//        YoYo.with(Techniques.RollIn).duration(10000).playOn(activityLottieBinding.iv013);
//        YoYo.with(Techniques.RotateIn).duration(10000).playOn(activityLottieBinding.iv014);
//        YoYo.with(Techniques.RubberBand).duration(10000).playOn(activityLottieBinding.iv015);
//        YoYo.with(Techniques.Shake).duration(10000).playOn(activityLottieBinding.iv016);
//        YoYo.with(Techniques.SlideInDown).duration(10000).playOn(activityLottieBinding.iv017);
//        YoYo.with(Techniques.FlipOutX).duration(10000).playOn(activityLottieBinding.iv019);

        lottieViewModel.testView(activityLottieBinding);
        lottieViewModel.setExitAnima(context);
        lottieViewModel.setEnterAnima();

        return lottieViewModel;
    }

    private void testView(final ActivityLottieBinding activityLottieBinding) {
        activityLottieBinding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityLottieBinding.lav01.setImageAssetsFolder("assets");
                activityLottieBinding.lav01.setAnimation("data.json");
                activityLottieBinding.lav01.setRepeatMode(LottieDrawable.RESTART);
                activityLottieBinding.lav01.playAnimation();
                Log.i(TAG, "start click");
            }
        });
        activityLottieBinding.btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityLottieBinding.lav01.pauseAnimation();
            }
        });
        activityLottieBinding.btnGoKt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, KtActivity.class));
            }
        });
        activityLottieBinding.btnGoRecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, RecycleViewActivity.class));
            }
        });
        activityLottieBinding.btnLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                goToSleep(v.getContext());
//                lock();
                context.startActivity(new Intent(context, ActiveActivity.class));
            }
        });
        activityLottieBinding.smallBlueIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                View sharedView = activityLottieBinding.smallBlueIcon;
                String transitionName = sharedView.getTransitionName();
                ActivityOptions transitionOptions = ActivityOptions.makeSceneTransitionAnimation((LottieActivity) context, Pair.create(sharedView, transitionName));
                Intent intent = new Intent(context, ReflectTestActivity.class);
                context.startActivity(intent, transitionOptions.toBundle());
            }
        });
        activityLottieBinding.btnChangeTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onClick","==");
                if(theme == R.style.AppTheme){
                    theme = R.style.AppTheme1;
                }else{
                    theme = R.style.AppTheme;
                }
                ((LottieActivity) context).recreate();
            }
        });
        Log.d("Build", Build.DISPLAY);

        activityLottieBinding.btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = FileUtil.copyAssetAndWrite(context,"aa.apk");
                Log.i(TAG, "onClick: path = "+path);
                PluginManager pluginManager = PluginManager.getInstance();
                pluginManager.init(context);
                pluginManager.loadApk(path);
            }
        });
        activityLottieBinding.btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, ProxyActivity.class);
                intent.putExtra("className","com.parobot.plugintest.MainActivity");
                context.startActivity(intent);
            }
        });

        hookOnClick(activityLottieBinding.btnChangeTheme);
//            @Override
//            public void onClick(View v) {
//                Log.d("onClick","=====");
//                if(theme == R.style.AppTheme){
//                    theme = R.style.AppTheme1;
//                }else{
//                    theme = R.style.AppTheme;
//                }
//                ((LottieActivity) context).recreate();
//            }
        ;


        Glide.with(context).load(R.mipmap.scroll).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (resource instanceof GifDrawable) {
                    //加载一次
                    ((GifDrawable) resource).setLoopCount(GifDrawable.LOOP_FOREVER);
                }
                return false;
            }
        }).into(activityLottieBinding.ivTips);

    }

    private void setExitAnima(Context context){
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.BOTTOM);
        slide.setMode(Slide.MODE_OUT);
        slide.setDuration(2000);
        ((LottieActivity)context).getWindow().setExitTransition(slide);
    }

    private void setEnterAnima() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        ((LottieActivity)context).getWindow().setEnterTransition(fade);
    }

    private void lock(){
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager)context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName componentName = new ComponentName(context, Darclass.class);
        if (devicePolicyManager != null) {
            boolean isAdminActive = devicePolicyManager.isAdminActive(componentName);
            if(!isAdminActive){//这一句一定要有...
                Intent intent = new Intent();
                //指定动作
                intent.setAction(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                //指定给那个组件授权
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
                context.startActivity(intent);
            }else {
                devicePolicyManager.lockNow();
                Log.i("=====", "======");
            }
        }
    }

    private void hookOnClick(View view){
        try {
            Method getListenerInfo = View.class.getDeclaredMethod("getListenerInfo");
            getListenerInfo.setAccessible(true);
            Object listenerInfo = getListenerInfo.invoke(view);

            Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
            Field mOnClickListener = listenerInfoClz.getDeclaredField("mOnClickListener");
            mOnClickListener.setAccessible(true);
            View.OnClickListener originOnClickListener = (View.OnClickListener)mOnClickListener.get(listenerInfo);

            View.OnClickListener hookedOnClickListener = new HookedOnClickListener(originOnClickListener);
            mOnClickListener.set(listenerInfo,hookedOnClickListener);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    class HookedOnClickListener implements View.OnClickListener{
        private View.OnClickListener origin;

        HookedOnClickListener(View.OnClickListener origin){
            this.origin = origin;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context,"HookedOnClickListener",Toast.LENGTH_SHORT).show();
            Log.d("before click","==");
            if (origin != null){
                origin.onClick(v);
            }
            Log.d("after click","==");

        }
    }
}
