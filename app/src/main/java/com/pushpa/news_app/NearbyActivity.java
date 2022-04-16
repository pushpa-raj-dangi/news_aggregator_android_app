package com.pushpa.news_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pushpa.news_app.Utils.ApiUtils;
import com.pushpa.news_app.adapters.NewsAdapter;
import com.pushpa.news_app.models.MainNews;
import com.pushpa.news_app.models.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearbyActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    String API_KEY = "0677fa6bd45648a8ac8ab8d00e1abfc8";
    ArrayList<News> news;
    String country  = "nepal";
    NewsAdapter adapter;
    private BottomNavigationView btmNav;
    ProgressDialog dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

        recyclerView = findViewById(R.id.recentRv);


        news = new ArrayList<>();

        dialog = new ProgressDialog(NearbyActivity.this);
        dialog.setTitle("Please wait...");
        dialog.show();

        recyclerView.setLayoutManager(new LinearLayoutManager(NearbyActivity.this));
        adapter = new NewsAdapter(NearbyActivity.this,news);
        recyclerView.setAdapter(adapter);
        btmNav = findViewById(R.id.btnNav);


        getNews();
        btmNav.setOnNavigationItemSelectedListener(new


            BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item){


                if (item.getTitle().equals("Recents")) {
                    startActivity(new Intent(NearbyActivity.this, RecentActivity.class));
                    finish();

                }
                if (item.getTitle().equals("Favorites")) {
                    startActivity(new Intent(NearbyActivity.this, FavoriteActivity.class));
                    finish();

                }
                if (item.getTitle().equals("Nearby")) {
                    startActivity(new Intent(NearbyActivity.this, NearbyActivity.class));
                    finish();
                }
                return true;
            }
        });

    }

    private void getNews() {
        TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        String countryName = tm.getNetworkCountryIso();

        ApiUtils.getApiInterface().getNearest().enqueue(new Callback<MainNews>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if(response.isSuccessful()){

                    news.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {
                Log.e("TAG", "onFailure: " );
            }
        });
    }
}