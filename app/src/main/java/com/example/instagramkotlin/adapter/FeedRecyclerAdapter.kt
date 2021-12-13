package com.example.instagramkotlin.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagramkotlin.databinding.RecyclerRowBinding
import com.example.instagramkotlin.model.Post

class FeedRecyclerAdapter(private val postList : ArrayList<Post>) : RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder>() {

    class PostHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.binding.rvEmailText.text = postList.get(position).email
        holder.binding.rvCommmentText.text = postList.get(position).comment
        Glide.with(holder.binding.rvImageView.context).load(postList.get(position).downloadUrl)
            .into(holder.binding.rvImageView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

}