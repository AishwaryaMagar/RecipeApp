package com.example.newrecipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.newrecipeapp.databinding.ActivitySignupBinding
import com.example.newrecipeapp.room.RecipeApp
import com.example.newrecipeapp.room.UserEntity
import kotlinx.coroutines.launch

class Signup : AppCompatActivity() {
    lateinit var  binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userDao = (application as RecipeApp).db.userDao()

        var username: String = binding.etUsername.text.toString()
        var password: String = binding.etPassword.text.toString()

        binding.btnSubmit.setOnClickListener {
            lifecycleScope.launch {
                userDao.insert(UserEntity(username, password))
            }
        }
    }
}