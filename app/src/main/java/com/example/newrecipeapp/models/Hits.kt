package com.example.newrecipeapp.models

import android.os.Parcel
import android.os.Parcelable

data class Hits(
    val recipe: Recipe
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Recipe::class.java.classLoader)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(recipe, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hits> {
        override fun createFromParcel(parcel: Parcel): Hits {
            return Hits(parcel)
        }

        override fun newArray(size: Int): Array<Hits?> {
            return arrayOfNulls(size)
        }
    }
}
