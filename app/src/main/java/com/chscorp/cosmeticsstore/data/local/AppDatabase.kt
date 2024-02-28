package com.chscorp.cosmeticsstore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MapEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mapDao(): MapDao
}