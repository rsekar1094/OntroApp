package com.example.rajrajas.otroapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rajrajas on 10/3/2017.
 */

public class TermsItem implements Serializable
{

    @SerializedName("category")
    private Category category;

    @SerializedName("post_tag")
    private post_tag_item post_tag;

    public Category getCategory() {
        return category;
    }

    public post_tag_item getPost_tag() {
        return post_tag;
    }
}
