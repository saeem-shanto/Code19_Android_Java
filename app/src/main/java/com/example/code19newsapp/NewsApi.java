package com.example.code19newsapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsApi {
    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<NewsArticle> articles;

    // Getter methods

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<NewsArticle> getArticles() {
        return articles;
    }
}