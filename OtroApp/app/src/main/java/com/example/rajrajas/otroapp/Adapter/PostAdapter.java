package com.example.rajrajas.otroapp.Adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.rajrajas.otroapp.Model.PostLists;
import com.example.rajrajas.otroapp.Model.Post_Item;

import com.example.rajrajas.otroapp.Model.Post_ThumbNail;
import com.example.rajrajas.otroapp.R;
import com.google.gson.Gson;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.rajrajas.otroapp.R.id.progressBar;


/**
 * Created by rajrajas on 10/3/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    Context context;
    List<Post_Item> post_items;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView title;
       ImageView image_view;
        ProgressBar progressBar;

        private MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            image_view = (ImageView) view.findViewById(R.id.image_view);
            progressBar=(ProgressBar) view.findViewById(R.id.progress_bar);

        }

    }

    public PostAdapter(List<Post_Item> post_items) {
        this.post_items = post_items;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        this.context = parent.getContext();

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.post_list, parent, false);


        return new MyViewHolder(itemView);


    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Post_Item selectedItem = post_items.get(position);
        holder.title.setText(Html.fromHtml(selectedItem.getTitle()));
        try {
            holder.progressBar.setVisibility(View.VISIBLE);
        Glide.with(context).load(selectedItem.getPost_thumbnail().getURL())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;

                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.image_view);
        } catch (Exception e) {
            holder.progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {

        return post_items.size();
    }

}



