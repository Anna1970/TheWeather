package ru.naa.theweather.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CityEntity::class], version = 2)
abstract class LocalDb : RoomDatabase(){
    abstract fun cityDao() : CityDao
}