package com.example.newrecipeapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert
    suspend fun insert(recipeEntity: RecipeEntity)

    @Delete
    suspend fun delete(recipeEntity: RecipeEntity)

    @Query("SELECT * FROM `recipe-table`")
    fun fetchallRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT COUNT(*) FROM `recipe-table` WHERE label = :label")
    suspend fun recipeExists(label: String): Int
}