package com.example.newrecipeapp.models

import android.os.Parcel
import android.os.Parcelable

data class Recipe(
    val uri: String?,
    val label: String?,
    val image: String?,
    val url: String,
    val cuisineType: List<String>,
    val mealType: List<String>,
    val healthLabels: List<String>,
    val ingredients: List<Ingredient>,
    val calories: Double,
    val totalWeight: Double,
    val totalTime: Double,
    val dishType: List<String>,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: listOf(),
        parcel.createStringArrayList() ?: listOf(),
        parcel.createStringArrayList() ?: listOf(),
        parcel.createTypedArrayList(Ingredient.CREATOR) ?: listOf(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.createStringArrayList() ?: listOf(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uri)
        parcel.writeString(label)
        parcel.writeString(image)
        parcel.writeString(url)
        parcel.writeStringList(cuisineType)
        parcel.writeStringList(mealType)
        parcel.writeStringList(healthLabels)
        parcel.writeTypedList(ingredients)
        parcel.writeDouble(calories)
        parcel.writeDouble(totalWeight)
        parcel.writeDouble(totalTime)
        parcel.writeStringList(dishType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}
