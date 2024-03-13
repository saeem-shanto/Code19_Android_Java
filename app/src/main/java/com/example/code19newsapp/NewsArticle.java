package com.example.code19newsapp;

import com.google.gson.annotations.SerializedName;

public class NewsArticle {
    @SerializedName("author")
    private String author;
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("content")
    private String content;
    @SerializedName("url")
    private String urlToNews;
    @SerializedName("urlToImage")
    private String imageUrl;

    public String getAuthor() {
        return author;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getUrlToNews() {
        return urlToNews;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
