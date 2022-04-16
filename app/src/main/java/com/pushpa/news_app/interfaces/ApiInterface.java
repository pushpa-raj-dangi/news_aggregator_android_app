package com.pushpa.news_app.interfaces;

import com.pushpa.news_app.models.MainNews;
import com.pushpa.news_app.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    String BASE_URL = "https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<MainNews> getNews(
            @Query("country") String country,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apiKey
            );
    @GET("top-headlines")
    Call<MainNews> getCategoryNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apiKey
    );
    @GET("everything")
    Call<MainNews> searchNews(
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );

    @GET("https://newsapi.org/v2/everything?q=nepal&from=2022-04-14&sortBy=popularity&apiKey=0677fa6bd45648a8ac8ab8d00e1abfc8")
    Call<MainNews> getNearest();
    @GET("https://newsapi.org/v2/everything?domains=khabarhub.com,thenextweb.com&apiKey=0677fa6bd45648a8ac8ab8d00e1abfc8")
    Call<MainNews> getRecent();
}
