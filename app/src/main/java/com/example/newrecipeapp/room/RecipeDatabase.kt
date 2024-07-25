package com.example.newrecipeapp.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.newrecipeapp.Converters

@Database(entities = [RecipeEntity::class, UserEntity::class], version = 9)
@TypeConverters(Converters::class)
abstract class RecipeDatabase: RoomDatabase() {

    abstract fun recipeDao():RecipeDao
    abstract fun userDao(): UserDao

    companion object{

        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase{

            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecipeDatabase::class.java,
                        "recipe_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}