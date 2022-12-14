package ru.naa.theweather.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface CityDao {
    @Query("Select * from City")
    fun getAll(): LiveData<List<CityEntity>>

    @Query("Select * from City where `key` =:key limit 1")
    fun getBykey(key: String): CityEntity

    @Insert(onConflict = REPLACE)
    fun insert(city: CityEntity)

    @Delete
    fun delete(city: CityEntity)
}