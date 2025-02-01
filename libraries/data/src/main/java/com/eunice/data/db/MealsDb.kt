package com.eunice.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eunice.data.dao.MealDao
import com.eunice.data.db.converter.IngredientDataConverter
import com.eunice.data.db.entity.MealEntity

/**
 * Created by {EUNICE BAKARE T.} on {6/10/23}
 * Email: {eunice@reach.africa}
 */
//Become the single source of truth for
//Category meals
//Daily meals (on a daily basis)
//Favourite meals
//Only hold the meal object
@Database(
    entities = [MealEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(IngredientDataConverter::class)
abstract class MealsDb: RoomDatabase() {

    abstract fun mealDao(): MealDao

    companion object {
        @Volatile
        private var dbInstance: MealsDb? = null

        fun getDb(context: Context): MealsDb {
            val tempInstance = dbInstance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MealsDb::class.java,
                    "Meals_Db"
                ).fallbackToDestructiveMigration().build()
                dbInstance = instance
                return instance
            }
        }
    }
}