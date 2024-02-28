package com.chscorp.cosmeticsstore.presentation.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chscorp.cosmeticsstore.domain.product.ProductListItem
import com.chscorp.cosmeticsstore.domain.repository.ProductsRepository
import com.chscorp.cosmeticsstore.domain.util.Resource
import com.chscorp.cosmeticsstore.presentation.state.ProductState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(val repository: ProductsRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<ProductState> = MutableStateFlow(
        ProductState()
    )
    val uiState get() = _uiState.asStateFlow()
    lateinit var list: List<ProductListItem>

    init {
        loadProductInfo()
    }

    fun listToMap(list: List<ProductListItem>): MutableMap<String, Boolean> {
        val map: MutableMap<String, Boolean> = mutableMapOf()
        list.forEach { product ->
            map[product.id] = false
        }
        return map
    }

    private fun loadProductInfo() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )
            when (val result = repository.getProductData()) {
                is Resource.Success -> {
                    result.data?.let {
                        list = it
                        _uiState.value = _uiState.value.copy(
                            favoriteState = listToMap(list)
                        )
                    }
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

    fun orderByLowestPrice() {
        _uiState.value = _uiState.value.copy(
            productList = list.sortedBy { it.price?.toFloat() }
        )
    }

    fun orderByBiggestPrice() {
        _uiState.value = _uiState.value.copy(
            productList = list.sortedByDescending { it.price?.toFloat() }
        )
    }

    fun orderByAlphabetica() {
        _uiState.value = _uiState.value.copy(
            productList = list.sortedBy { it.brand }
        )
    }

    fun orderByInvertedAlphabetica() {
        _uiState.value = _uiState.value.copy(
            productList = list.sortedByDescending { it.brand }
        )
    }

    fun orderByFavorites() {
        val idFavorites = _uiState.value.favoriteState
            .filter { (_, value) -> value }
            .keys.toList()
        _uiState.value = _uiState.value.copy(
            productList = list.filter { it.id in idFavorites }
        )
    }


    fun selectedFilterOption(option: String) {
        _uiState.value = _uiState.value.copy(
            selectedOption = option,
        )
    }

    fun updateItemToFavoteById(id: String) {
        _uiState.value.favoriteState[id]?.let {
            _uiState.value.favoriteState[id] = !it
            val favorite = _uiState.value.favoriteState
            _uiState.value.copy(
                favoriteState = favorite
            )
        }
    }

}