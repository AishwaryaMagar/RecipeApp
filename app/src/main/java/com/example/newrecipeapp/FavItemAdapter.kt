package com.example.newrecipeapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newrecipeapp.databinding.ItemsRowBinding
import com.example.newrecipeapp.room.RecipeEntity

class FavItemAdapter( val items: List<RecipeEntity>): RecyclerView.Adapter<FavItemAdapter.ViewHolder>() {

    var onItemClick : ((RecipeEntity) -> Unit)? = null
    var onremoveClick : ((RecipeEntity) -> Unit)? = null

    @SuppressLint("ResourceAsColor", "ResourceType")
    class ViewHolder(binding: ItemsRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvTitle = binding.tvTitle
        val image = binding.image
        val cuisine = binding.tvCuisine
        val readRecipe = binding.btnReadRecipe
        val like = binding.btnLike

        init {
            like.setBackgroundResource(R.drawable.ic_save_filled)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavItemAdapter.ViewHolder {
        return FavItemAdapter.ViewHolder(
            ItemsRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FavItemAdapter.ViewHolder, position: Int) {
        val context = holder.itemView.context
        val item = items[position]

       Glide.with(context).load(item.image).into(holder.image)

        holder.tvTitle.text = item.label
        holder.cuisine.text = item.cuisineType
        holder.like.setOnClickListener {
            onremoveClick!!.invoke(item)
        }


        holder.readRecipe.setOnClickListener {
            onItemClick!!.invoke(item)

        }
    }
}