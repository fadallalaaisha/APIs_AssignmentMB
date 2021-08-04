package com.example.mypostapp

import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    // Helps us to access diff interface
    @GET("Posts")
    fun getPost():Call<List<Post>>

    //For getting one Id
    @GET("Posts/{Id}")
    fun getPostById(@Path("Id")postId:Int):Call<Post>

    @GET("{Posts/Id}/comments")
    fun getComments(@Path("Id")Id: Int):Call<List<Comments>>
}