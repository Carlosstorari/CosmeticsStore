package com.chscorp.cosmeticsstore.data.repository

import android.content.Context
import com.chscorp.cosmeticsstore.data.local.MapDao
import com.chscorp.cosmeticsstore.data.local.MapEntity
import com.chscorp.cosmeticsstore.domain.repository.DataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataBaseRepositoryImpl(private val context: Context, val mapDao: MapDao):DataBaseRepository {
    override suspend fun update(mapToSave: Map<String, Boolean>?) {
        mapToSave?.forEach { (key, value) ->
            val mapEntity = MapEntity(keyId = key, value = value)
            mapDao.insertMap(mapEntity)
        }
    }

    override suspend fun getData(): MutableMap<String, Boolean>? {
        return withContext(Dispatchers.IO) {
            var allValues = mutableMapOf<String, Boolean>()

            mapDao.getAllValues()?.forEach { keyValueEntity ->
                allValues[keyValueEntity.keyId] = keyValueEntity.value
            }

            allValues
        }
    }
}