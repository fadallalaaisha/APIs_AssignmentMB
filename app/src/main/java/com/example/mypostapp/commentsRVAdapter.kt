package com.example.mypostapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class commentsRVAdapter(var commentsList:List<Comments>):RecyclerView.Adapter<commentsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): commentsViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.activity_view_post,parent,false)
        return commentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: commentsViewHolder, position: Int) {
      var currentcomments=commentsList.get(position)
        holder.title.text=currentcomments.postTitle
        holder.body.text=currentcomments.postBody
    }

    override fun getItemCount(): Int {
      return commentsList.size
    }
}
class commentsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
     var title=itemView.findViewById<TextView>(R.id.tvbody)
    var body=itemView.findViewById<TextView>(R.id.tvID)

}
