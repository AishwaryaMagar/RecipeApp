package com.example.newrecipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.newrecipeapp.databinding.ActivityFavDetailedViewBinding
import com.example.newrecipeapp.models.Hits
import com.example.newrecipeapp.models.Recipe
import com.example.newrecipeapp.room.RecipeEntity
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlin.math.roundToInt

class FavDetailedView : AppCompatActivity() {

    private lateinit var binding: ActivityFavDetailedViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavDetailedViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipe = intent.getParcelableExtra<RecipeEntity>("RecipeEntity")
        Log.d("FavDetailedView", "RecipeEntity: $recipe")


        val title = binding.tvTitle
        val image = binding.image

        if (recipe != null) {
            title.text = recipe.label.toString()
            Log.d("FavDetailedView", "Cuisine Type: ${recipe.cuisineType}")
            Log.d("FavDetailedView", "Dish Type: ${recipe.dishType}")


            if (recipe.cuisineType!!.isNotEmpty()) {
                binding.tvCuisine.text = recipe.cuisineType
            }
            if (recipe.dishType!!.isNotEmpty()) {
                binding.inputDishType.text = recipe.dishType
            }

            binding.inputCalories.text = recipe.calories.roundToInt().toString()
            binding.inputWeight.text = recipe.totalWeight.roundToInt().toString()
            binding.inputTime.text = recipe.totalTime.roundToInt().toString()

            // Load image using Glide
            Glide.with(this)
                .load(recipe.image)
                .into(image)

            // Setup HealthLabelAdapter with FlexboxLayoutManager
            val healthLabelAdapter = HealthLabelAdapter(recipe.healthLabels)
            val flexboxLayoutManager = FlexboxLayoutManager(this)
            flexboxLayoutManager.flexDirection = FlexDirection.ROW
            flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START

            binding.rvHealthLabels.layoutManager = flexboxLayoutManager
            binding.rvHealthLabels.adapter = healthLabelAdapter

            binding.rvIngredients.layoutManager = LinearLayoutManager(this)
            binding.rvIngredients.adapter = IngredientsAdapter(recipe.ingredients)
        }


    }
}