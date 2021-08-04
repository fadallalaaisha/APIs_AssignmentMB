package com.example.mypostapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var rvPost:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
fetchPosts()

    }
    fun fetchPosts(){
        var retrofit=APIClicte.buildAPI(ApiInterface::class.java)
        var request=retrofit.getPost()
        request.enqueue(object : Callback<List<Post>> {

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful){
                    var postList=response.body()!!

                    rvPost=findViewById(R.id.rvPost)
                    var postadapter=PostRVAdapter(postList,baseContext)
                    rvPost.layoutManager=LinearLayoutManager(baseContext)
                    rvPost.adapter=postadapter

//                    Toast.makeText(baseContext,postList.size,Toast.LENGTH_LONG).show()
                    Toast.makeText(baseContext, "${postList!!.size} posts", Toast.
                    LENGTH_LONG).show()

                    var postDate= mutableListOf<Post>()
                    for (x in 1..postList.size){
                        postDate.add(Post(3,85,"Developer","Information not there, Sorry try again later"))
                    }

                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }


}