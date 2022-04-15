package com.pushpa.news_app.models;

public class News {
    private String author;
    private String title;

    public News(String author, String title,  String publishedAt, String description, String url, String urlToImage,String favoriteStatus) {
        this.author = author;
        this.title = title;
        this.favoriteStatus = favoriteStatus;
        this.publishedAt = publishedAt;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
    }

    public String getFavoriteStatus() {
        return favoriteStatus;
    }

    public void setFavoriteStatus(String favoriteStatus) {
        this.favoriteStatus = favoriteStatus;
    }

    private String favoriteStatus;



    private  String publishedAt;

    public News() {

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getLink() {
        return url;
    }

    public void setLink(String url) {
        this.url = url;
    }

    public String getImage() {
        return urlToImage;
    }

    public void setImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    private String description;
    private String url;
    private String urlToImage;


    public String getTime() {
        return publishedAt;
    }

    public void setTime(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
