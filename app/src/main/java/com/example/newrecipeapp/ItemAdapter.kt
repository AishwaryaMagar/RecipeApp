package com.example.newrecipeapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newrecipeapp.databinding.ItemsRowBinding
import com.example.newrecipeapp.fragments.AccountFragment
import com.example.newrecipeapp.models.Hits

class ItemAdapter( val items: List<Hits>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var onItemClick : ((Hits) -> Unit)? = null
    var onLikeClick : ((Hits) -> Unit)? = null

    class ViewHolder(binding: ItemsRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvTitle = binding.tvTitle
        val image = binding.image
        val cuisine = binding.tvCuisine
        val readRecipe = binding.btnReadRecipe
        val like = binding.btnLike


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemsRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size

    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val item = items[position]

        Glide.with(context).load(item.recipe.image).into(holder.image)

        holder.tvTitle.text = item.recipe.label
        holder.cuisine.text = item.recipe.cuisineType[0].toString()
        holder.like.setOnClickListener {
            onLikeClick!!.invoke(item)
            holder.like.setBackgroundResource(R.drawable.ic_save_filled)
        }


        holder.readRecipe.setOnClickListener {
            onItemClick!!.invoke(item)
        }
    }
}