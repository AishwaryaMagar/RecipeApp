package com.example.newrecipeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newrecipeapp.databinding.HealthlabelBinding
import com.example.newrecipeapp.databinding.IngredientsBinding
import com.example.newrecipeapp.models.Ingredient

class IngredientsAdapter(private val items: List<Ingredient>): RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {
    class ViewHolder(val binding: IngredientsBinding) : RecyclerView.ViewHolder(binding.root) {

        val ingredientString = binding.tvIngredients
        val measures = binding.measure
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = IngredientsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return IngredientsAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.ingredientString.text = item.text
        holder.measures.text = "Measure: ${item.measure} Weight: ${item.weight.toInt()} Food: ${item.food}"
    }
}