package com.example.rajrajas.otroapp.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rajrajas.otroapp.Model.Category_type;
import com.example.rajrajas.otroapp.Model.Post_Item;
import com.example.rajrajas.otroapp.Model.post_tag_sub_item;
import com.example.rajrajas.otroapp.R;
import com.example.rajrajas.otroapp.rest.ApiClient;
import com.example.rajrajas.otroapp.rest.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
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
    private TextView content_value,date_value,author_value,likes_count,url_value,modified_value,status_value,title;
    private LinearLayout likes_lay;
    private GridLayout grid_lay,post_tag_grid_lay;
    private CollapsingToolbarLayout collapsing_toolbar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_activity);

        toolbar=(Toolbar) findViewById(R.id.toolbar);

        Init_lays();


        likes_lay.startAnimation(
                AnimationUtils.loadAnimation(PostActivity.this, R.anim.rotate) );

        Intent i = getIntent();
        post= (Post_Item)i.getSerializableExtra("object");
        Init_values();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transation));
            imageView.setTransitionName("Item_Image");
            collapsing_toolbar.setTransitionName("Item_title");
        }


        rest_call();



    }

    private void Init_lays()
    {
        collapsing_toolbar=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);


        imageView=(ImageView) findViewById(R.id.expandedImage);

        content_value=(TextView) findViewById(R.id.content_value);
        date_value=(TextView) findViewById(R.id.date_value);
        author_value=(TextView) findViewById(R.id.author_value);
        likes_count=(TextView) findViewById(R.id.likes_count);
        //title=(TextView) findViewById(R.id.title);

        url_value=(TextView) findViewById(R.id.url_value);
        modified_value=(TextView) findViewById(R.id.modified_value);
        status_value=(TextView) findViewById(R.id.status_value);

        grid_lay=(GridLayout) findViewById(R.id.grid_lay);
        post_tag_grid_lay=(GridLayout) findViewById(R.id.tag_grid_lay);

        likes_lay=(LinearLayout) findViewById(R.id.likes_lay);

    }

    private void Init_values()
    {

        try {
            Glide.with(getApplicationContext()).load(post.getPost_thumbnail().getURL())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }catch (Exception e)
        {
            Glide.with(getApplicationContext()).load(R.mipmap.ic_launcher)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }

        content_value.setText(Html.fromHtml(post.getContent()));

        date_value.setText(get_date(post.getDate()));
        author_value.setText(Html.fromHtml("<u>"+post.getAuthor().getName()+"</u>"));
        toolbar.setTitle("");
        //title.setText(post.getTitle());
        likes_count.setText(post.getLike_count()+"");

        url_value.setText(Html.fromHtml("<u>"+post.getURL()+"</u>"));
        modified_value.setText(get_date(post.getModified()));
        status_value.setText(post.getStatus());

        collapsing_toolbar.setTitle(post.getTitle());
        collapsing_toolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsing_toolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);


    }

    private void rest_call()
    {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<JsonObject> call = apiService.getPostTitle(post.getID()+"");
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response)
            {
                int statusCode = response.code();
                Gson gson = new Gson();
                try
                {
                    JSONArray Jarray = new JSONArray("["+response.body().toString()+"]");
                    JSONObject json_data = Jarray.getJSONObject(0);
                    String terms_string = json_data.getString("terms");
                    String tags_string = json_data.getString("tags");


                    JSONObject cateogry_data = new JSONObject(terms_string);
                    String category_string = cateogry_data.getString("category");

                    JSONObject post_tag_data = new JSONObject(terms_string);
                    String post_tag_string = post_tag_data.getString("post_tag");


                    JSONObject cateogry_sub_data = new JSONObject(category_string);

                    Iterator<String> keys = cateogry_sub_data.keys();
                    List<Category_type> category_type=new ArrayList<Category_type>();

                    while (keys.hasNext())
                    {
                        String key = keys.next();
                        JSONObject value = cateogry_sub_data.getJSONObject(key);
                        final Category_type c=gson.fromJson(value.toString(), Category_type.class);
                        category_type.add(c);

                        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                        View inflatedLayout= inflater.inflate(R.layout.button_lay, null, false);

                        Button btn=(Button) inflatedLayout.findViewById(R.id.btn);
                        btn.setText(c.getName());
                        btn.setPadding(10,10,10,10);


                        grid_lay.addView(inflatedLayout);
                    }


                    JSONObject post_tag_sub_data = new JSONObject(post_tag_string);

                    Iterator<String> keys1 = post_tag_sub_data.keys();
                    List<post_tag_sub_item> post_tag_sub_items=new ArrayList<post_tag_sub_item>();


                    while (keys1.hasNext())
                    {
                        String key = keys1.next();
                        JSONObject value = post_tag_sub_data.getJSONObject(key);
                        final post_tag_sub_item c=gson.fromJson(value.toString(), post_tag_sub_item.class);
                        post_tag_sub_items.add(c);

                        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                        View inflatedLayout= inflater.inflate(R.layout.button_lay, null, false);

                        Button btn=(Button) inflatedLayout.findViewById(R.id.btn);
                        btn.setText(c.getName());
                        btn.setPadding(10,10,10,10);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            btn.setBackground(getResources().getDrawable(R.drawable.green_btn));
                        }


                        post_tag_grid_lay.addView(inflatedLayout);
                    }



                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }



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

    private String get_date(String date)
    {
        int last_index=date.lastIndexOf("T");
        if(last_index>0)
        {
            return date.substring(0,last_index);
        }
        else
            return date;
    }

}
