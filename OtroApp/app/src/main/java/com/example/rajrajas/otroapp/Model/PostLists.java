package com.example.rajrajas.otroapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rajrajas on 10/3/2017.
 */

public class PostLists implements Serializable
{
    @SerializedName("posts")
    private List<Post_Item> posts;

    public List<Post_Item> getPosts() {
        return posts;
    }


}
