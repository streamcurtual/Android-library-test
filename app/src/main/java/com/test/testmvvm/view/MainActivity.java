//package com.test.testmvvm.view;
//
//
//import android.databinding.DataBindingUtil;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//
//import com.test.testmvvm.R;
//import com.test.testmvvm.adapter.NewsAdapter;
//import com.test.testmvvm.databinding.ActivityMainBinding;
//import com.test.testmvvm.Book;
//import com.test.testmvvm.viewmodel.MainViewModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class MainActivity extends AppCompatActivity {
//
//    private static final String TAG = "MainActivity";
//
//    ActivityMainBinding mActivityMainBinding;
//
//    public static NewsAdapter mNewsAdapter;
//    public static List<Book> mNewsList = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // 设置dataBinding、viewModel
//        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//         初始化RecyclerView
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        mActivityMainBinding.recyclerView.setLayoutManager(layoutManager);
//        mNewsAdapter = new NewsAdapter(this, mNewsList);
//        mActivityMainBinding.recyclerView.setAdapter(mNewsAdapter);
//        // 加载数据
//        MainViewModel.getInstance(mActivityMainBinding);
//    }
//}
