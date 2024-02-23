package com.chscorp.cosmeticsstore.di

import com.chscorp.cosmeticsstore.data.repository.ProductsRepositoryImpl
import com.chscorp.cosmeticsstore.domain.repository.ProductsRepository
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object RepositoryModule {
    fun loadRepositoryModules() {
        loadKoinModules(repositoryModule)
    }

    val repositoryModule = module {
        single<ProductsRepository> { ProductsRepositoryImpl(get()) }
    }
}
