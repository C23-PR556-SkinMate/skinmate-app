package com.kdt.skinmate.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kdt.skinmate.data.ScanModel

@Dao
interface ScanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addScan(scan: List<ScanModel>?)

    @Query("SELECT * FROM scanTable")
    fun getAllLocalScan(): List<ScanModel>?

    @Query("DELETE FROM scanTable")
    suspend fun deleteAll()
}