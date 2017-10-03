package com.example.rajrajas.otroapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rajrajas on 10/3/2017.
 */

public class post_tag_sub_item implements Serializable
{

    @SerializedName("name")
    private String  name;

    @SerializedName("meta")
    private links_type meta;

    public String getName() {
        return name;
    }

    public links_type getMeta() {
        return meta;
    }
}
