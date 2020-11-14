package com.jyotirmaya.foodorderingapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ordereditems")
data class OrderHistoryItems (
    @PrimaryKey val ItemID: Int,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "cost") val cost : String
)