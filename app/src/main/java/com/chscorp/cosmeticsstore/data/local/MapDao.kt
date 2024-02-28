package com.chscorp.cosmeticsstore.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MapDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMap(mapEntity: MapEntity)

    @Query("SELECT * FROM map_table_favorite_status")
    suspend fun getAllValues(): List<MapEntity>?
}