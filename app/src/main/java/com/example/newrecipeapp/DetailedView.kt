package com.example.newrecipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.example.newrecipeapp.databinding.ActivityDetailedViewBinding
import com.example.newrecipeapp.models.Hits
import kotlin.math.roundToInt

class DetailedView : AppCompatActivity() {

    private lateinit var binding: ActivityDetailedViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hits = intent.getParcelableExtra<Hits>("Hits")

        hits?.let {
            val title = binding.tvTitle
            val image = binding.image

            title.text = it.recipe.label
            if (it.recipe.cuisineType.isNotEmpty()) {
                binding.tvCuisine.text = it.recipe.cuisineType[0]
            }
            if (it.recipe.dishType.isNotEmpty()) {
                binding.inputDishType.text = it.recipe.dishType[0]
            }

            binding.inputCalories.text = it.recipe.calories.roundToInt().toString()
            binding.inputWeight.text = it.recipe.totalWeight.roundToInt().toString()
            binding.inputTime.text = it.recipe.totalTime.roundToInt().toString()

            // Load image using Glide
            Glide.with(this)
                .load(it.recipe.image)
                .into(image)

            // Setup HealthLabelAdapter with FlexboxLayoutManager
            val healthLabelAdapter = HealthLabelAdapter(it.recipe.healthLabels)
            val flexboxLayoutManager = FlexboxLayoutManager(this)
            flexboxLayoutManager.flexDirection = FlexDirection.ROW
            flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START

            binding.rvHealthLabels.layoutManager = flexboxLayoutManager
            binding.rvHealthLabels.adapter = healthLabelAdapter

            binding.rvIngredients.layoutManager = LinearLayoutManager(this)
            binding.rvIngredients.adapter = IngredientsAdapter(it.recipe.ingredients)
        }
    }
}
