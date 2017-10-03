package com.example.rajrajas.otroapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rajrajas on 10/3/2017.
 */

public class Post_ThumbNail implements Serializable
{
    @SerializedName("URL")
    private String URL;

    public String getURL() {
        return URL;
    }


}
