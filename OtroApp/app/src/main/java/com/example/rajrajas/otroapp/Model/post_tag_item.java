package com.example.rajrajas.otroapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by rajrajas on 10/3/2017.
 */

public class post_tag_item implements Serializable
{
    @SerializedName("Health")
    private HashMap<String,post_tag_sub_item> Health;

    /*@SerializedName("linguistics")
    private post_tag_sub_item linguistics;

    @SerializedName("Food and Drug Administration")
    private post_tag_sub_item Food_and_Drug_Administration;

    @SerializedName("diabetes")
    private post_tag_sub_item diabetes;*/

    public HashMap<String, post_tag_sub_item> getHealth() {
        return Health;
    }

    /*public post_tag_sub_item getLinguistics() {
        return linguistics;
    }

    public post_tag_sub_item getFood_and_Drug_Administration() {
        return Food_and_Drug_Administration;
    }

    public post_tag_sub_item getDiabetes() {
        return diabetes;
    }*/
}
