package com.pushpa.news_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pushpa.news_app.Utils.ApiUtils;
import com.pushpa.news_app.Utils.DbHelper;
import com.pushpa.news_app.adapters.FavoriteAdapter;
import com.pushpa.news_app.adapters.NewsAdapter;
import com.pushpa.news_app.models.MainNews;
import com.pushpa.news_app.models.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private  DbHelper dbHelper;
    ArrayList<News> news;
    FavoriteAdapter adapter;
    private BottomNavigationView btmNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerView = findViewById(R.id.favRv);
        dbHelper  = new DbHelper(this);

        news = dbHelper.getFavoriteNews();

        recyclerView.setLayoutManager(new LinearLayoutManager(FavoriteActivity.this));
        adapter = new FavoriteAdapter(FavoriteActivity.this,news);
        recyclerView.setAdapter(adapter);
        adapter.updateRecycler(dbHelper.retrieveData());
        btmNav = findViewById(R.id.btnNav);

        btmNav.setOnNavigationItemSelectedListener(new


            BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item){


                if (item.getTitle().equals("Recents")) {
                    startActivity(new Intent(FavoriteActivity.this, RecentActivity.class));
                    finish();

                }
                if (item.getTitle().equals("Favorites")) {
                    startActivity(new Intent(FavoriteActivity.this, FavoriteActivity.class));
                    finish();

                }
                if (item.getTitle().equals("Nearby")) {
                    startActivity(new Intent(FavoriteActivity.this, NearbyActivity.class));
                    finish();
                }
                return true;
            }
        });

    }


}