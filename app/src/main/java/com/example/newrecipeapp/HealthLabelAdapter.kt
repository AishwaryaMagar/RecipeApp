package com.example.newrecipeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newrecipeapp.databinding.HealthlabelBinding

class HealthLabelAdapter(private val items: List<String>) : RecyclerView.Adapter<HealthLabelAdapter.ViewHolder>() {

    class ViewHolder(val binding: HealthlabelBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvHealthLabel = binding.tvHealthLabel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HealthlabelBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvHealthLabel.text = item
    }
}
