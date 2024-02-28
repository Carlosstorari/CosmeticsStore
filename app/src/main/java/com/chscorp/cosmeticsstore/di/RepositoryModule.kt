package com.chscorp.cosmeticsstore.di

import com.chscorp.cosmeticsstore.data.repository.DataBaseRepositoryImpl
import com.chscorp.cosmeticsstore.data.repository.ProductsRepositoryImpl
import com.chscorp.cosmeticsstore.domain.repository.DataBaseRepository
import com.chscorp.cosmeticsstore.domain.repository.ProductsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object RepositoryModule {
    fun loadRepositoryModules() {
        loadKoinModules(repositoryModule)
    }

    val repositoryModule = module {
        single<ProductsRepository> { ProductsRepositoryImpl(get()) }
        single<DataBaseRepository> { DataBaseRepositoryImpl(context = androidContext(), get()) }
    }
}
