package com.chscorp.cosmeticsstore.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "map_table_favorite_status")
data class MapEntity(
    @PrimaryKey
    val keyId: String,
    val value: Boolean
)