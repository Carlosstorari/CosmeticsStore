package com.chscorp.cosmeticsstore.presentation.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chscorp.cosmeticsstore.domain.repository.ProductsRepository
import com.chscorp.cosmeticsstore.domain.util.Resource
import com.chscorp.cosmeticsstore.presentation.ui.ProductState
import kotlinx.coroutines.launch

class MainViewModel(val repository: ProductsRepository) : ViewModel() {

    var state by mutableStateOf(ProductState())
        private set

    fun loadProductInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )

            when (val result = repository.getProductData()) {
                is Resource.Success -> {
                    state = state.copy(
                        productList = result.data,
                        isLoading = false,
                        error = null
                    )
                    Log.i("Lista de produtos", result.data.toString())
                }

                is Resource.Error -> {
                    state = state.copy(
                        productList = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }

        }
    }
}