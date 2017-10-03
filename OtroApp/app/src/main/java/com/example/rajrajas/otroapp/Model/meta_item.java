package com.example.rajrajas.otroapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rajrajas on 10/3/2017.
 */

public class meta_item implements Serializable
{
    @SerializedName("links")
    private links_type  links;

    public links_type getLinks() {
        return links;
    }
}
