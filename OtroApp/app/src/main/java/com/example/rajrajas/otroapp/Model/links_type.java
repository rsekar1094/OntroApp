package com.example.rajrajas.otroapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rajrajas on 10/3/2017.
 */

public class links_type implements Serializable
{
    @SerializedName("self")
    private String self;
    @SerializedName("help")
    private String help;
    @SerializedName("site")
    private String site;

    public String getSelf() {
        return self;
    }

    public String getHelp() {
        return help;
    }

    public String getSite() {
        return site;
    }

}
