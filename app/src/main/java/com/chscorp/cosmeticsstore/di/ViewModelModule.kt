package com.chscorp.cosmeticsstore.di

import com.chscorp.cosmeticsstore.presentation.ui.viewModel.LoginViewModel
import com.chscorp.cosmeticsstore.presentation.ui.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object ViewModelModule {
    fun loadViewModelModule() {
        loadKoinModules(viewModelModule)
    }

    private val viewModelModule = module {
        viewModel { MainViewModel(get()) }
        viewModel { LoginViewModel() }
    }
}