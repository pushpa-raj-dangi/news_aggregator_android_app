package com.pushpa.news_app.models;

import java.util.ArrayList;

public class MainNews {
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<News> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<News> articles) {
        this.articles = articles;
    }

    private String status;

    public MainNews(String status, String totalResults, ArrayList<News> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    private String totalResults;
    private ArrayList<News>  articles;

}
