package com.example.rajrajas.otroapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rajrajas on 10/3/2017.
 */

public class Category implements Serializable
{

    @SerializedName("Bio")
    private Category_type Bio;


    @SerializedName("Government")
    private Category_type Government;

    @SerializedName("Health")
    private Category_type Health;

    public Category_type getBio() {
        return Bio;
    }

    public Category_type getGovernment() {
        return Government;
    }

    public Category_type getHealth() {
        return Health;
    }
}

