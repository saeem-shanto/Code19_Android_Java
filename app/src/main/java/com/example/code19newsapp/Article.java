package com.example.code19newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Article extends AppCompatActivity {
    Button homeButton;
    EditText title,content;
    TextView publishedAt;
    ImageView articleImage;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_article);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        homeButton = findViewById(R.id.article_home_button);
        title = findViewById(R.id.article_title);
        content = findViewById(R.id.article_content);
        publishedAt = findViewById(R.id.article_published_at);
        articleImage = findViewById(R.id.article_image);


        /**
         * Geting the intent data from the previous Activity
         * Setting the data to the current article activity view elements
         */


        title.setText(getIntent().getStringExtra("title"));
        content.setText(getIntent().getStringExtra("content"));
        publishedAt.setText(getIntent().getStringExtra("publishedAt"));
        Picasso.get().load(getIntent().getStringExtra("imageUrl")).into(articleImage);
        Toast.makeText(Article.this,getIntent().getStringExtra("title"),Toast.LENGTH_LONG).show();


        /**
         * Switching to Main Activity Layout
         */
        homeButton.setOnClickListener(v -> {
            intent = new Intent(Article.this,MainActivity.class);
            startActivity(intent);
        });
    }
}