package com.example.newrecipeapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newrecipeapp.Constants
import com.example.newrecipeapp.DetailedView
import com.example.newrecipeapp.ItemAdapter
import com.example.newrecipeapp.databinding.FragmentHomeBinding
import com.example.newrecipeapp.models.Hits
import com.example.newrecipeapp.models.Recipe
import com.example.newrecipeapp.models.RecipeResponse
import com.example.newrecipeapp.networks.RecipeService
import com.example.newrecipeapp.room.RecipeApp
import com.example.newrecipeapp.room.RecipeDao
import com.example.newrecipeapp.room.RecipeEntity
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private var demoList: List<String> = listOf("Hehe", "Haha", "hoho", "Jaja")
    private lateinit var finalList: List<Hits>
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchView = homeBinding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
               getAllRecipes(newText)
                return true
            }

        })
        searchView.setOnCloseListener {
            getAllRecipes("all")
            false  // Return false to allow default behavior of closing SearchView
        }
        getAllRecipes("a")
    }


    private fun getAllRecipes(newText: String) {
        context?.let {
            if (Constants.isNetworkAvailable(it)) {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service: RecipeService = retrofit.create(RecipeService::class.java)
                val listCall: Call<RecipeResponse>

                listCall = service.getRecipe(Constants.APP_ID, Constants.APP_KEY, "public", q = newText)

                listCall.enqueue(object : Callback<RecipeResponse> {
                    override fun onResponse(
                        call: Call<RecipeResponse>,
                        response: Response<RecipeResponse>
                    ) {
                        if (response.isSuccessful) {
                            val recipeList: RecipeResponse? = response.body()
                            finalList = recipeList?.hits ?: emptyList()
                            Log.i("Response Result", "$finalList")
                            setUpDataIntoRecyclerView(finalList)
                        } else {
                            val rc = response.code()
                            when (rc) {
                                400 -> {
                                    Log.e("Error 400", "Bad Request")
                                }
                                404 -> {
                                    Log.e("Error 404", "Not Found")
                                }
                                else -> {
                                    Log.e("Error", "Generic Error")
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                        Log.e("Error", t.message.toString())
                    }
                })
            } else {
                Toast.makeText(it, "No Internet Connection Available", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setUpDataIntoRecyclerView(recipeList: List<Hits>) {

        val itemAdapter = ItemAdapter(finalList)
        homeBinding.homeRv.layoutManager = GridLayoutManager(context, 2)
        homeBinding.homeRv.adapter = itemAdapter

        itemAdapter.onItemClick = {
            val intent = Intent(context, DetailedView::class.java)
            intent.putExtra("Hits", it)
            startActivity(intent)

        }

        itemAdapter.onLikeClick = {
            sharedViewModel.addLikedItem(it)
            val recipeDao = (context?.applicationContext as RecipeApp).db.recipeDao()
            addRecord(recipeDao = recipeDao, it.recipe)
        }
    }

    fun addRecord(recipeDao: RecipeDao, recipe: Recipe)
    {
        lifecycleScope.launch{
            val exists = recipe.label?.let { recipeDao.recipeExists(it) }
            if(exists == 0)
            {
                recipeDao.insert(RecipeEntity(label = recipe.label, image = recipe.image, cuisineType = recipe.cuisineType[0].toString(), mealType = recipe.mealType[0].toString(), healthLabels = recipe.healthLabels, ingredients = recipe.ingredients, calories = recipe.calories, totalWeight = recipe.totalWeight, totalTime = recipe.totalTime, dishType = recipe.dishType[0].toString()))
            }

        }
    }


}