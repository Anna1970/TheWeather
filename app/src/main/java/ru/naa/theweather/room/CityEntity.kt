package ru.naa.theweather.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "City")
data class CityEntity(
    @PrimaryKey(autoGenerate = false)
    var key: String,
    var name: String,
    var type: String
)

