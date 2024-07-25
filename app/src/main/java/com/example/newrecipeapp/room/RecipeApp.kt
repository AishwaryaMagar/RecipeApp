package com.example.newrecipeapp.room

import android.app.Application

class RecipeApp: Application() {

    val db by lazy{
        RecipeDatabase.getInstance(this)
    }
}