package com.jyotirmaya.foodorderingapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [OrderedItemsEntity::class],version = 1)
abstract class OrderedItemsDatabase : RoomDatabase()
{
    abstract fun OrderedItemsDao() : OrderedItemsDao
}