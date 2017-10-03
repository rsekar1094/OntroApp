package com.example.rajrajas.otroapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rajrajas on 10/3/2017.
 */

public class Category_type implements Serializable
{

    @SerializedName("name")
    private String name;

    @SerializedName("post_count")
    private int post_count;

    @SerializedName("meta")
    private meta_item meta;

    public String getName() {
        return name;
    }

    public int getPost_count() {
        return post_count;
    }

    public meta_item getMeta() {
        return meta;
    }


}
