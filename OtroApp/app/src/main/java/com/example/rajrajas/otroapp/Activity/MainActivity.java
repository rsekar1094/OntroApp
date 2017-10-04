package com.example.rajrajas.otroapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rajrajas.otroapp.Adapter.PostAdapter;
import com.example.rajrajas.otroapp.Model.PostLists;
import com.example.rajrajas.otroapp.Model.Post_Item;
import com.example.rajrajas.otroapp.R;
import com.example.rajrajas.otroapp.RecyclerTouchListener;
import com.example.rajrajas.otroapp.rest.ApiClient;
import com.example.rajrajas.otroapp.rest.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    List<Post_Item> post_items;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init()
    {
        recyclerView=(RecyclerView) findViewById(R.id.item_list);
        progressBar=(ProgressBar)  findViewById(R.id.progress_bar);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        post_items =new ArrayList<>();

        Call<JsonObject> call = apiService.getPostTitle("");
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response)
            {
                int statusCode = response.code();
                progressBar.setVisibility(View.GONE);
                if(statusCode== 200)
                {
                    Gson gson = new Gson();
                    PostLists postLists = gson.fromJson(response.body().toString(), PostLists.class);
                    List<Post_Item> post_items = postLists.getPosts();
                    initRecycler(post_items);
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t)
            {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });




    }
    private void initRecycler(final List<Post_Item> post_items) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        recyclerView.setAdapter(new PostAdapter(post_items));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(final View view, final int position) {
                final Post_Item f = post_items.get(position);

                view.findViewById(R.id.my_savings_lay).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(MainActivity.this, PostActivity.class);

                        intent.putExtra("object",f);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                        {
                            Pair<View, String> p1 = Pair.create(view.findViewById(R.id.image_view), "Item_Image");
                            Pair<View, String> p2 = Pair.create(view.findViewById(R.id.title), "Item_title");

                            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) MainActivity.this, p1, p2);
                            MainActivity.this.startActivity(intent, optionsCompat.toBundle());
                        }
                        else
                        {
                            MainActivity.this.startActivity(intent);
                        }

                    }
                });
            }

            @Override
            public void onLongClick(View view, int position) {


            }
        }));
    }
}
