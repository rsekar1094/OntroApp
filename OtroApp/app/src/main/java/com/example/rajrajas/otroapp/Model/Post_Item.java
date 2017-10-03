package com.example.rajrajas.otroapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rajrajas on 10/3/2017.
 */

public class Post_Item implements Serializable
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
    @SerializedName("like_count")
    private int like_count;

    @SerializedName("author")
    private Author_Item author;

    @SerializedName("post_thumbnail")
    private Post_ThumbNail post_thumbnail;


   @SerializedName("terms")
    private TermsItem terms;



    public Integer getID() {
        return ID;
    }

    public String getSite_ID() {
        return site_ID;
    }

    public String getDate() {
        return date;
    }

    public String getModified() {
        return modified;
    }

    public String getTitle() {
        return title;
    }

    public String getURL() {
        return URL;
    }

    public String getShort_URL() {
        return short_URL;
    }

    public String getContent() {
        return content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getSlug() {
        return slug;
    }

    public String getGuid() {
        return guid;
    }

    public String getStatus() {
        return status;
    }

    public String getSticky() {
        return sticky;
    }

    public String getPassword() {
        return password;
    }

    public String getParent() {
        return parent;
    }

    public String getType() {
        return type;
    }

    public int getLike_count() {
        return like_count;
    }

    public Author_Item getAuthor() {
        return author;
    }

    public Post_ThumbNail getPost_thumbnail() {
        return post_thumbnail;
    }

    public TermsItem getTerms() {
        return terms;
    }


}
