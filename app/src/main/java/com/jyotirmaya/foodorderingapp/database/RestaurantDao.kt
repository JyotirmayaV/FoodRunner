package com.jyotirmaya.foodorderingapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RestaurantDao {

    @Insert
    fun insertRestaurant(restaurantEntity: RestaurantEntity)

    @Delete
    fun deleteRestaurant(restaurantEntity: RestaurantEntity)

    @Query("SELECT * FROM restaurants")
    fun getAllRestaurants():List<RestaurantEntity>

    @Query("SELECT * FROM restaurants WHERE RestaurantID = :restaurantID")
    fun getRestaurantByID(restaurantID:String) : RestaurantEntity

}