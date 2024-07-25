package com.example.newrecipeapp.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newrecipeapp.models.Hits


class SharedViewModel : ViewModel() {
    private val _likedItems = MutableLiveData<MutableList<Hits>>(mutableListOf())
    val likedItems: LiveData<MutableList<Hits>> get() = _likedItems

    fun addLikedItem(item: Hits) {
        _likedItems.value?.add(item)
        _likedItems.value = _likedItems.value // trigger LiveData update
    }
}
