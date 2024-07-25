package com.example.newrecipeapp.models


data class RecipeResponse(
    val from: Int,
    val to: Int,
    val count: Int,
    val hits: List<Hits>
)
