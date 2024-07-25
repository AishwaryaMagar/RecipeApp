package com.example.newrecipeapp.models

import android.os.Parcel
import android.os.Parcelable

data class Ingredient(
    val food: String?,
    val foodCategory: String?,
    val foodId: String?,
    val image: String?,
    val measure: String?,
    val quantity: Double,
    val text: String?,
    val weight: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(food)
        parcel.writeString(foodCategory)
        parcel.writeString(foodId)
        parcel.writeString(image)
        parcel.writeString(measure)
        parcel.writeDouble(quantity)
        parcel.writeString(text)
        parcel.writeDouble(weight)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ingredient> {
        override fun createFromParcel(parcel: Parcel): Ingredient {
            return Ingredient(parcel)
        }

        override fun newArray(size: Int): Array<Ingredient?> {
            return arrayOfNulls(size)
        }
    }
}
