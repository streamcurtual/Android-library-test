//package com.test.testmvvm.viewmodel;
//
//import android.util.Log;
//
//import com.test.testmvvm.Book;
//import com.test.testmvvm.Feed;
//import com.test.testmvvm.LoadListener;
//import com.test.testmvvm.R;
//import com.test.testmvvm.view.MainActivity;
//import com.test.testmvvm.databinding.ActivityMainBinding;
//
//import java.util.List;
//
//public class MainViewModel {
//
//    private static final String TAG = "MainViewModel";
//    private static MainViewModel mainViewModel = null;
//
//    public static MainViewModel getInstance(ActivityMainBinding activityMainBinding){
//        if(mainViewModel == null){
//            mainViewModel = new MainViewModel();
//        }
//        activityMainBinding.setViewmodel(mainViewModel);
//        mainViewModel.loadNews();
//        return mainViewModel;
//    }
//    private MainViewModel(){
//
//    }
//
//    public void loadNews() {
//        // 获取url
////        feedUrl = getResources().getString(R.string.feed_api_url);
//        // 加载数据
//        Feed feed = new Feed();
//        String feedUrl = "https://www.easy-mock.com/mock/5c3e90f39d8f6075a543c3cd/story/classify/";
//        feed.loadData(feedUrl, new LoadListener<Book>() {
//            @Override
//            public void loadSuccess(List<Book> list) {
//                // 加载数据成功
//                MainActivity.mNewsList.addAll(list);
//                MainActivity.mNewsAdapter.notifyDataSetChanged();
//                Log.i("======","loadsuccess");
//            }
//            @Override
//            public void loadFailure(String message) {
//                Log.i("======","loadFailure "+message);
//                // 加载数据失败
//            }
//        });
//    }
//}