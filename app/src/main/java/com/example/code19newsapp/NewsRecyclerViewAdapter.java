package com.example.code19newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsArticleViewHolder> {

    private ArrayList<NewsArticle> newsArticleList;


    public NewsRecyclerViewAdapter(List<NewsArticle> newsArticleList) {
        /**
         * Casting to  Arraylist has removeIf method
         * removeIf method is used to remove the news articles that does not have any description and content
         */
        this.newsArticleList = (ArrayList<NewsArticle>) newsArticleList;
        this.newsArticleList.removeIf(newsArticle -> ((newsArticle.getContent() == null) || (newsArticle.getDescription() == null)));
    }

    @NonNull
    @Override
    public NewsArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_news,parent,false);
        return new NewsArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsArticleViewHolder holder, int position) {
        holder.titleEditView.setText(""+newsArticleList.get(position).getTitle());
        holder.descriptionEditView.setText(""+newsArticleList.get(position).getDescription());
        holder.publishedAt.setText("Published at  - "+newsArticleList.get(position).getPublishedAt());
        Picasso.get().load(newsArticleList.get(position).getImageUrl()).into(holder.newsImageView);

        if(newsArticleList.get(position).getImageUrl() == null || newsArticleList.get(position).getImageUrl() == ""){
            holder.newsImageView.setImageResource(R.drawable.no_image_available_612x612);
        }
        else{
            Picasso.get().load(newsArticleList.get(position).getImageUrl()).into(holder.newsImageView);
        }
    }

    @Override
    public int getItemCount() {
        return newsArticleList.size();
    }

    public class NewsArticleViewHolder extends RecyclerView.ViewHolder {
        private TextView publishedAt;
        private EditText titleEditView,descriptionEditView;
        private ImageView newsImageView;

        public NewsArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            publishedAt = itemView.findViewById(R.id.news_published_at);
            titleEditView = itemView.findViewById(R.id.news_title);
            descriptionEditView = itemView.findViewById(R.id.news_description);
            newsImageView = itemView.findViewById(R.id.news_image_View);

        }
    }
}
