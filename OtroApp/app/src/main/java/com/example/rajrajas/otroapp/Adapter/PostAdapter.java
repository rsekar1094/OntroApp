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

import com.example.rajrajas.otroapp.Model.PostLists;
import com.example.rajrajas.otroapp.Model.Post_Item;

import com.example.rajrajas.otroapp.Model.Post_ThumbNail;
import com.example.rajrajas.otroapp.R;
import com.google.gson.Gson;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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

        public MyViewHolder(View view) {
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

        holder.progressBar.setVisibility(View.VISIBLE);
        try {
            Picasso.with(context).load(selectedItem.getPost_thumbnail().getURL())
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .fit()
                    .into(holder.image_view, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                            holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            holder.progressBar.setVisibility(View.GONE);

                        }

                    });
        } catch (Exception e) {
            holder.progressBar.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {

        return post_items.size();
    }

}



