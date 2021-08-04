package com.example.mypostapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewDebug
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class PostRVAdapter(var PostList:List<Post>, var context:Context):RecyclerView.Adapter<ViewHolderPost>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPost {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.postitem,parent,false)
        return ViewHolderPost(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderPost, position: Int) {
        var currentPost=PostList.get(position)
        holder.tvUserId.text=currentPost.userId.toString()
        holder.tvId.text=currentPost.id.toString()
        holder.tvTitle.text=currentPost.title
        holder.tvBody.text=currentPost.body

        //This will help us to get one id items at a time.
        holder.cvPost.setOnClickListener{
            var intent=Intent(context, ViewHolderPost::class.java)
            intent.putExtra("POST_ID",currentPost.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return PostList.size
    }
}
class ViewHolderPost(itemView:View):RecyclerView.ViewHolder(itemView){
    var tvUserId=itemView.findViewById<TextView>(R.id.tvUserId)
    var tvId=itemView.findViewById<TextView>(R.id.tvId)
    var tvTitle=itemView.findViewById<TextView>(R.id.tvTitle)
    var tvBody=itemView.findViewById<TextView>(R.id.tvBody)

    var cvPost=itemView.findViewById<CardView>(R.id.cvPosts)

}