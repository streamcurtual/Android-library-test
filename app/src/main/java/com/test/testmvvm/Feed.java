//package com.test.testmvvm;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import okhttp3.OkHttpClient;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.http.POST;
//
//public class Feed {
//    private String resultcode;
//    private String reason;
//
//    public String getResultcode() {
//        return resultcode;
//    }
//
//    public void setResultcode(String resultcode) {
//        this.resultcode = resultcode;
//    }
//
//    public String getReason() {
//        return reason;
//    }
//
//    public void setReason(String reason) {
//        this.reason = reason;
//    }
//
//    public List<Book> getResult() {
//        return result;
//    }
//
//    public void setResult(List<Book> result) {
//        this.result = result;
//    }
//
//    private List<Book> result;
//
//
//
//    // 通过传进来的url，利用retrofit获取网络数据，回调给viewModel
//    @POST
//    public void loadData(String feedUrl, final LoadListener<Book> loadListener) {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl(feedUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        iNews news = retrofit.create(iNews.class);
//        Call<Feed> feed = news.getFeed();
//        feed.enqueue(new Callback<Feed>() {
//            @Override
//            public void onResponse(Call<Feed> call, Response<Feed> response) {
//                // 获取成功
//                List<Book> newsList = new ArrayList<>();
//                if (response.body() != null) {
//                    newsList.addAll(response.body().getResult());
//                }
//                loadListener.loadSuccess(newsList);
//            }
//
//            @Override
//            public void onFailure(Call<Feed> call, Throwable t) {
//                // 获取失败
//                loadListener.loadFailure(t.getMessage());
//            }
//        });
//    }
//}