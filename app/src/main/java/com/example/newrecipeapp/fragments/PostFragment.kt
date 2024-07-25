package com.example.newrecipeapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newrecipeapp.DetailedView
import com.example.newrecipeapp.R
import com.example.newrecipeapp.categories.BreakfastRecipes
import com.example.newrecipeapp.categories.Cookies
import com.example.newrecipeapp.categories.Desserts
import com.example.newrecipeapp.categories.Salads
import com.example.newrecipeapp.categories.VeganRecipes
import com.example.newrecipeapp.databinding.FragmentHomeBinding
import com.example.newrecipeapp.databinding.FragmentPostBinding
import com.example.newrecipeapp.models.Hits

class PostFragment : Fragment() {


    private lateinit var postBinding: FragmentPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        postBinding = FragmentPostBinding.inflate(layoutInflater, container, false)
        return postBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postBinding.dessertImage.setOnClickListener {
            val intent = Intent(context, Desserts::class.java)
            startActivity(intent)
        }
        postBinding.veganImage.setOnClickListener {
            val intent = Intent(context, VeganRecipes::class.java)
            startActivity(intent)
        }
        postBinding.breakfastImage.setOnClickListener {
            val intent = Intent(context, BreakfastRecipes::class.java)
            startActivity(intent)
        }
        postBinding.cookiesImage.setOnClickListener {
            val intent = Intent(context, Cookies::class.java)
            startActivity(intent)
        }
        postBinding.saladImage.setOnClickListener {
            val intent = Intent(context, Salads::class.java)
            startActivity(intent)
        }
    }
}