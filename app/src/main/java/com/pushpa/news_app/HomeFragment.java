package com.pushpa.news_app;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import java.net.InetAddress;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.pushpa.news_app.Utils.ApiUtils;
import com.pushpa.news_app.Utils.DbHelper;
import com.pushpa.news_app.adapters.NewsAdapter;
import com.pushpa.news_app.models.MainNews;
import com.pushpa.news_app.models.News;

import java.net.InetAddress;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    DbHelper dbHelper;
    private RecyclerView recyclerView;
    //    String API_KEY = "cfe309f934664d619b81486921cb83eb";
    String API_KEY = "0677fa6bd45648a8ac8ab8d00e1abfc8";

    ArrayList<News> news;
    String country  = "us";
    NewsAdapter adapter;
    ProgressDialog dialog;
    SwipeRefreshLayout swipeRefreshLayout;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView = view.findViewById(R.id.pRv);
        swipeRefreshLayout = view.findViewById(R.id.refreshLayout);
    dbHelper = new DbHelper(getActivity());
        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Please wait...");
        dialog.show();
        news = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsAdapter(getContext(),news);

        recyclerView.setAdapter(adapter);
        getNews();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNews();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getNews() {
        if(isNetworkConnected()) {
            ApiUtils.getApiInterface().getNews(country, 10, API_KEY).enqueue(new Callback<MainNews>() {
                @Override
                public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                    if (response.isSuccessful()) {
                        news.addAll(response.body().getArticles());
                        adapter.notifyDataSetChanged();
                        for (News news: response.body().getArticles()) {
                            dbHelper.insertDataToDb(news);

                        }
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<MainNews> call, Throwable t) {
                }
            });
        }else{
            news.addAll(dbHelper.retrieveData());
            adapter.notifyDataSetChanged();

            dialog.dismiss();


        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();

    }

    public boolean isInternetAvailable() {

        try {
            InetAddress ipAddr = InetAddress.getByName("https://api.androidhive.info/");
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
}
