package com.example.mypostapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewPostActivity : AppCompatActivity() {
    lateinit var tvID:TextView
    lateinit var tvbody:TextView
    lateinit var rvPosts:RecyclerView

    var postId=0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_post)
        postId=intent.getIntExtra("POST_ID",0)
        tvID=findViewById(R.id.tvID)
        tvbody=findViewById(R.id.tvbody)
        rvPosts=findViewById(R.id.rvPosts)
        fetchPostById()
        getComments()
    }
    fun fetchPostById(){
        var apiClient=APIClicte.buildAPI(ApiInterface::class.java)
        var request=apiClient.getPostById(postId)
        request.enqueue(object : Callback<Post> {

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    var post = response.body()
                    tvID.text = post?.title
                    tvbody.text = post?.body
                }
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
    fun getComments(){
        var apiClient=APIClicte.buildAPI(ApiInterface::class.java)
        var request=apiClient.getComments(postId)
        request.enqueue(object :Callback<List<Comments>>{
            override fun onResponse(
                call: Call<List<Comments>>,
                response: Response<List<Comments>>
            ) {
               if(response.isSuccessful){
                   var commentResponse=response.body()!!
//                   var commentsAdapter=commentsRVAdapter(commentResponse,)
                   rvPosts=findViewById(R.id.rvPosts)
                   var postAdapters=commentsRVAdapter(commentResponse)
                   rvPosts.layoutManager=LinearLayoutManager(baseContext)
                   rvPosts.adapter=postAdapters

                   Toast.makeText(baseContext,"${commentResponse!!.size} comments", Toast.LENGTH_LONG).show()

               }
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
}