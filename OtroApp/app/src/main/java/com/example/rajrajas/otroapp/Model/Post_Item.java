package com.example.rajrajas.otroapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rajrajas on 10/3/2017.
 */

public class Post_Item
{

    @SerializedName("ID")
    private Integer ID;
    @SerializedName("site_ID")
    private String site_ID;
    @SerializedName("date")
    private String date;
    @SerializedName("modified")
    private String modified;
    @SerializedName("title")
    private String title;

    @SerializedName("URL")
    private String URL;
    @SerializedName("short_URL")
    private String short_URL;
    @SerializedName("content")
    private String content;
    @SerializedName("excerpt")
    private String excerpt;

    @SerializedName("slug")
    private String slug;
    @SerializedName("guid")
    private String guid;
    @SerializedName("status")
    private String status;
    @SerializedName("sticky")
    private String sticky;

    @SerializedName("password")
    private String password;
    @SerializedName("parent")
    private String parent;
    @SerializedName("type")
    private String type;
    @SerializedName("sticky")
    private String sticky;
}
