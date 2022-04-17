package com.pushpa.news_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pushpa.news_app.Utils.ApiUtils;
import com.pushpa.news_app.adapters.FavoriteAdapter;
import com.pushpa.news_app.adapters.NewsAdapter;
import com.pushpa.news_app.models.MainNews;
import com.pushpa.news_app.models.News;

import java.net.InetAddress;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    ArrayList<News> news;

    SearchView searchView;
    ProgressDialog dialog;
    NewsAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.sRv);
        dialog = new ProgressDialog(SearchActivity.this);

        news = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        adapter = new NewsAdapter(SearchActivity.this,news);

        recyclerView.setAdapter(adapter);

        searchNews("nepal");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Please wait...");
                dialog.show();
                searchNews(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
    private void searchNews(String query){
        ApiUtils.getApiInterface().searchNews(query,"5af4e37fb3c8468bb18fe42149927742").enqueue(new Callback<MainNews>() {

            @Override
            public void onResponse(@NonNull Call<MainNews> call, @NonNull Response<MainNews> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    news.addAll(response.body().getArticles());
                    recyclerView.setAdapter(new NewsAdapter(SearchActivity.this,news));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                    Log.e("TAG", "onResponse: "+response.body().getArticles().get(0).getTitle() );
                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(SearchActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    }





}