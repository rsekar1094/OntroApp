package com.example.rajrajas.otroapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rajrajas on 10/3/2017.
 */

public class Author_Item implements Serializable
{
    @SerializedName("name")
    private String name;
    @SerializedName("profile_URL")
    private String profile_URL;


    public String getName() {
        return name;
    }

    public String getProfile_URL() {
        return profile_URL;
    }

}
