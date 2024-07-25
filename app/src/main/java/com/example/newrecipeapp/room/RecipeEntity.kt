package com.example.newrecipeapp.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newrecipeapp.models.Ingredient

@Entity(tableName = "recipe-table")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val label: String?,
    val image: String?,
    val cuisineType: String?,
    val mealType: String?,
    val healthLabels: List<String>,
    val ingredients: List<Ingredient>,
    val calories: Double,
    val totalWeight: Double,
    val totalTime: Double,
    val dishType: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList() ?: listOf(),
        parcel.createTypedArrayList(Ingredient.CREATOR) ?: listOf(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(label)
        parcel.writeString(image)
        parcel.writeString(cuisineType)
        parcel.writeString(mealType)
        parcel.writeStringList(healthLabels)
        parcel.writeTypedList(ingredients)
        parcel.writeDouble(calories)
        parcel.writeDouble(totalWeight)
        parcel.writeDouble(totalTime)
        parcel.writeString(dishType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeEntity> {
        override fun createFromParcel(parcel: Parcel): RecipeEntity {
            return RecipeEntity(parcel)
        }

        override fun newArray(size: Int): Array<RecipeEntity?> {
            return arrayOfNulls(size)
        }
    }


}

//// Extension function to read a List<String> from a Parcel
//private fun Parcel.createStringList(): List<String> {
//    val list = ArrayList<String>()
//    this.readStringList(list)
//    return list
//}
//
//// Extension function to read a List<Ingredient> from a Parcel
//private fun Parcel.createIngredientList(): List<Ingredient> {
//    val size = readInt()
//    val list = ArrayList<Ingredient>(size)
//    for (i in 0 until size) {
//        list.add(Ingredient.CREATOR.createFromParcel(this))
//    }
//    return list
//}
