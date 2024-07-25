package com.example.newrecipeapp.categories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newrecipeapp.Constants
import com.example.newrecipeapp.DetailedView
import com.example.newrecipeapp.ItemAdapter
import com.example.newrecipeapp.R
import com.example.newrecipeapp.databinding.ActivityCookiesBinding
import com.example.newrecipeapp.databinding.ActivityDessertsBinding
import com.example.newrecipeapp.models.Hits
import com.example.newrecipeapp.models.RecipeResponse
import com.example.newrecipeapp.networks.RecipeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Cookies : AppCompatActivity() {
    private lateinit var binding: ActivityCookiesBinding
    private lateinit var finalList: List<Hits>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCookiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getAllRecipes()
    }

    private fun getAllRecipes() {
        if (Constants.isNetworkAvailable(this)) {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service: RecipeService = retrofit.create(RecipeService::class.java)

            val listCall: Call<RecipeResponse> = service.getRecipe(
                Constants.APP_ID, Constants.APP_KEY, "public", dishType = "Biscuits and cookies"
            )

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
            Toast.makeText(this, "No Internet Connection Available", Toast.LENGTH_LONG).show()
        }

    }

    private fun setUpDataIntoRecyclerView(recipeList: List<Hits>) {

        val itemAdapter = ItemAdapter(finalList)
        binding.cookiesRv.layoutManager = GridLayoutManager(this, 2)
        binding.cookiesRv.adapter = itemAdapter

        itemAdapter.onItemClick = {
            val intent = Intent(this, DetailedView::class.java)
            intent.putExtra("Hits", it)
            startActivity(intent)

        }
    }
}