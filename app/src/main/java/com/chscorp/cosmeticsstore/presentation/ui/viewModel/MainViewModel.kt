package com.chscorp.cosmeticsstore.presentation.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chscorp.cosmeticsstore.domain.repository.ProductsRepository
import com.chscorp.cosmeticsstore.domain.util.Resource
import com.chscorp.cosmeticsstore.presentation.states.ProductState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(val repository: ProductsRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<ProductState> = MutableStateFlow(
        ProductState()
    )

    val uiState get() = _uiState.asStateFlow()

    init {
        loadProductInfo()
    }
    private fun loadProductInfo() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            when (val result = repository.getProductData()) {
                is Resource.Success -> {
                    _uiState.value = _uiState.value.copy(
                        productList = result.data,
                        isLoading = false,
                        error = null
                    )
                    Log.i("Lista de produtos", result.data.toString())
                }

                is Resource.Error -> {
                    _uiState.value = _uiState.value.copy(
                        productList = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }

        }
    }
}