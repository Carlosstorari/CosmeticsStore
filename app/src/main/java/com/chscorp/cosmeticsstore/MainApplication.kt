package com.chscorp.cosmeticsstore

import android.app.Application
import com.chscorp.cosmeticsstore.di.DataModule
import com.chscorp.cosmeticsstore.di.RepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
        }
        DataModule.loadDataModule()
        RepositoryModule.loadRepositoryModules()
    }
}