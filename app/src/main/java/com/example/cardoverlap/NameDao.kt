package com.example.cardoverlap

import androidx.room.*

@Dao
interface NameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(name: Name)

    @Update
    suspend fun update(name: Name)

    @Delete
    suspend fun delete(name: Name)

    @Query("SELECT * FROM names")
    suspend fun getAllNames(): List<Name>

    @Query("SELECT * FROM names WHERE id = :id LIMIT 1")
    suspend fun getNameById(id: Int): Name?
}
