package com.chscorp.cosmeticsstore.domain.repository

import android.app.Application
import android.content.Context
import com.chscorp.cosmeticsstore.data.local.MapEntity

interface DataBaseRepository {
    suspend fun update(mapToSave: Map<String, Boolean>?)
    suspend fun getData(): MutableMap<String, Boolean>?
}