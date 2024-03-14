package com.example.code19newsapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView newsRecyclerView;
    NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    SharedPreferences sharedPreferences;
    List<NewsArticle> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        newsRecyclerView = findViewById(R.id.news_recycler_view);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        /**
         * initializing retrofit api
         * to the main domain - https://newsapi.org/
         * GsonConverterFactory is used to convert the json data to java objects
         * for request calls
         */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /**
         * Creating the RetrofitApi class object for  get request
         */

        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<NewsApi> call = retrofitApi.getNewsApi();


        call.enqueue(new Callback<NewsApi>() {

            /**
             * if request is successful, data are received by recyclerVIew
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<NewsApi> call, Response<NewsApi> response) {
                if(response.isSuccessful()){
                    data = response.body().getArticles();
                    newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(data);
                    newsRecyclerView.setAdapter(newsRecyclerViewAdapter);
                }else {
                    Toast.makeText(MainActivity.this,"request was unsuccessful",Toast.LENGTH_SHORT).show();
                }
            }


            /**
             * if request gets failed, tries to get data from the local
             * shows a toast message that there is an error
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<NewsApi> call, Throwable t) {
                try{
                    sharedPreferences = getSharedPreferences("saveNewsArticle",MODE_PRIVATE);
                    data =getList();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                Toast.makeText(MainActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * getList method reads the json String saved in the sharedPreference in keyword "saved_articles"
     * json data are the all news articles
     * @return NewsArticleList
     */

    public List<NewsArticle> getList(){
        List<NewsArticle> arrayItems = null;
        String serializedObject = sharedPreferences.getString("saved_articles", null);
        if (serializedObject != null) {
            Type type = new TypeToken<List<NewsArticle>>(){}.getType();
            arrayItems = new Gson().fromJson(serializedObject, type);
        }
        return arrayItems;
    }

    /**
     * while app is onPause the saveData() method saves request data into memory
     */
    @Override
    protected void onPause() {
        saveData();
        Toast.makeText(this,"data saved",Toast.LENGTH_LONG).show();
        super.onPause();
    }


    /**
     * getSharedPreferences saves the data in "saveNewsArticle" keyword
     * saveData() Method saves the request data in format of a json string in the local memory in xml format
     * Gson is used  to convert Java NewsArticle object into their JSON representation
     */
    public void saveData(){
        try {
            sharedPreferences = getSharedPreferences("saveNewsArticle", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String json = new Gson().toJson(data);
            editor.putString("saved_articles",json);
            editor.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}