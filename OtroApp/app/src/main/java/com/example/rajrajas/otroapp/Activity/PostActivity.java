package com.example.rajrajas.otroapp.Activity;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rajrajas.otroapp.Model.PostLists;
import com.example.rajrajas.otroapp.Model.Post_Item;
import com.example.rajrajas.otroapp.R;
import com.example.rajrajas.otroapp.rest.ApiClient;
import com.example.rajrajas.otroapp.rest.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rajrajas on 10/3/2017.
 */

public class PostActivity extends AppCompatActivity
{

    private ImageView imageView;
    private Post_Item post;
    private TextView content_value,date_value,author_value,likes_count,url_value,modified_value,status_value;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_activity);

        final Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        imageView=(ImageView) findViewById(R.id.expandedImage);

        content_value=(TextView) findViewById(R.id.content_value);
        date_value=(TextView) findViewById(R.id.date_value);
        author_value=(TextView) findViewById(R.id.author_value);
        likes_count=(TextView) findViewById(R.id.likes_count);

        url_value=(TextView) findViewById(R.id.url_value);
        modified_value=(TextView) findViewById(R.id.modified_value);
        status_value=(TextView) findViewById(R.id.status_value);

        likes_count.startAnimation(
                AnimationUtils.loadAnimation(PostActivity.this, R.anim.rotate) );

        Intent i = getIntent();
        post= (Post_Item)i.getSerializableExtra("object");

        Picasso.with(getApplicationContext()).load(post.getPost_thumbnail().getURL())
                .fit()
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(imageView);

        content_value.setText(Html.fromHtml(post.getContent()));
        date_value.setText(Html.fromHtml(post.getDate()));
        author_value.setText(Html.fromHtml("<u>"+post.getAuthor().getName()+"</u>"));
        toolbar.setTitle(post.getTitle());
        likes_count.setText(post.getLike_count()+"");

        url_value.setText(Html.fromHtml("<u>"+post.getURL()+"</u>"));
        modified_value.setText(post.getModified());
        status_value.setText(post.getStatus());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transation));
            imageView.setTransitionName("Item_Image");
        }


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<JsonObject> call = apiService.getPostTitle(post.getID()+"");
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response)
            {
                int statusCode = response.code();

                Gson gson = new Gson();
                Post_Item post_item= gson.fromJson(response.body().toString(), Post_Item.class);


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });



    }

    public void author_call(View view)
    {
        Intent i=new Intent(PostActivity.this,WebViewActivity.class);
        i.putExtra("url",post.getAuthor().getProfile_URL());
        PostActivity.this.startActivity(i);
    }

    public void url_click(View view) {
        Intent i=new Intent(PostActivity.this,WebViewActivity.class);
        i.putExtra("url",post.getURL());
        PostActivity.this.startActivity(i);
    }

}
