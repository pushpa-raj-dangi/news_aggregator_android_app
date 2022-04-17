package com.pushpa.news_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pushpa.news_app.Utils.ApiUtils;
import com.pushpa.news_app.adapters.NewsAdapter;
import com.pushpa.news_app.models.MainNews;
import com.pushpa.news_app.models.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelFragment extends Fragment {
    private RecyclerView recyclerView;
    String API_KEY = "5af4e37fb3c8468bb18fe42149927742";
    ArrayList<News> news;
    String country  = "us";
    NewsAdapter adapter;

    public TravelFragment() {
    }

    public static TravelFragment newInstance() {
        return new TravelFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_travel,container,false);

        recyclerView = view.findViewById(R.id.pRv);


        news = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsAdapter(getContext(),news);
        recyclerView.setAdapter(adapter);

        getNews();
        return view;
    }

    private void getNews() {
        ApiUtils.getApiInterface().getCategoryNews(country,"health",10,API_KEY).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if(response.isSuccessful()){
                    news.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });
    }
}
