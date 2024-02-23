package com.chscorp.cosmeticsstore.di

import com.chscorp.cosmeticsstore.data.remote.MakeupApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DataModule{
    fun loadDataModule() {
        loadKoinModules(retrofitModule)
    }
    private const val URL_BASE = "http://makeup-api.herokuapp.com/api/"
    private val retrofitModule = module {
        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(get())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
        single<OkHttpClient> {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        }
        single<MakeupApi> { get<Retrofit>().create(MakeupApi::class.java) }
    }
}
