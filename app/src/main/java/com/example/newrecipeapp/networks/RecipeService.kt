package com.example.newrecipeapp.networks


import com.example.newrecipeapp.models.RecipeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("api/recipes/v2")
    fun getRecipe(
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String,
        @Query("type") type: String,
        @Query("q") q: String? = null,
        @Query("diet") diet: String? =null,
        @Query("mealType") mealType: String? = null,
        @Query("dishType") dishType: String? = null

    ): Call<RecipeResponse>
}