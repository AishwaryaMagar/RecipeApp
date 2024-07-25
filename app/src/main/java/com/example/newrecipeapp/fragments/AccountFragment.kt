package com.example.newrecipeapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newrecipeapp.DetailedView
import com.example.newrecipeapp.FavDetailedView
import com.example.newrecipeapp.FavItemAdapter
import com.example.newrecipeapp.ItemAdapter
import com.example.newrecipeapp.databinding.FragmentAccountBinding
import com.example.newrecipeapp.models.Hits
import com.example.newrecipeapp.room.RecipeApp
import com.example.newrecipeapp.room.RecipeDao
import com.example.newrecipeapp.room.RecipeEntity
import kotlinx.coroutines.launch


class AccountFragment : Fragment() {

    private lateinit var accountBinding: FragmentAccountBinding

    private var demoList: List<String> = listOf("Hehe", "Haha", "hoho", "Jaja")
    private lateinit var likedList: List<Hits>

    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        accountBinding = FragmentAccountBinding.inflate(layoutInflater, container, false)
        return accountBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipeDao = (context?.applicationContext as RecipeApp).db.recipeDao()
        lifecycleScope.launch {
            recipeDao.fetchallRecipes().collect{
                setUpDataIntoRecyclerView(it)
            }
        }
//        sharedViewModel.likedItems.observe(viewLifecycleOwner, Observer { likedItems ->
//            setUpDataIntoRecyclerView(likedItems)
//        })
    }

    private fun setUpDataIntoRecyclerView(likedList: List<RecipeEntity>) {

        val itemAdapter = FavItemAdapter(likedList)
        accountBinding.accountRv.layoutManager = GridLayoutManager(context, 2)
        accountBinding.accountRv.adapter = itemAdapter

        itemAdapter.onremoveClick = {
            val recipeDao = (context?.applicationContext as RecipeApp).db.recipeDao()
            deleteRecord(recipeDao = recipeDao, it)
        }

        itemAdapter.onItemClick = {
            Log.d("Account Fragment before passing", "RecipeEntity: $it")
            val intent = Intent(context, FavDetailedView::class.java)
            intent.putExtra("RecipeEntity", it)
            startActivity(intent)

        }
    }

    fun deleteRecord(recipeDao: RecipeDao, recipeEntity: RecipeEntity)
    {
        lifecycleScope.launch {
            recipeDao.delete(recipeEntity)
        }
    }




}