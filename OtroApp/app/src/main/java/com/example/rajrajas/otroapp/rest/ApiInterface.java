package com.example.rajrajas.otroapp.rest;

import com.example.rajrajas.otroapp.Model.PostLists;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by rajrajas on 9/27/2017.
 */

public interface ApiInterface
{
    @Headers("Content-Type: application/json")
    //@POST("rest/v1.1/sites/techcrunch.com/posts")
    @GET
    Call<JsonObject> getPostTitle(@Url String url);


    @Headers("Content-Type: application/json")
    @POST("Customer/Register")
    Call<JsonObject> Register(@Body JsonObject searchstring);

}
