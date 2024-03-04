package com.chscorp.cosmeticsstore.presentation.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chscorp.cosmeticsstore.domain.product.ProductInfo
import com.chscorp.cosmeticsstore.domain.product.ProductListItem
import com.chscorp.cosmeticsstore.domain.repository.DataBaseRepository
import com.chscorp.cosmeticsstore.domain.repository.ProductsRepository
import com.chscorp.cosmeticsstore.domain.util.Resource
import com.chscorp.cosmeticsstore.presentation.state.FilterBarState
import com.chscorp.cosmeticsstore.presentation.state.ProductState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    val repositoryProduct: ProductsRepository,
    val repositoryDataBase: DataBaseRepository
    ) : ViewModel() {

    private val _uiState: MutableStateFlow<ProductState> = MutableStateFlow(
        ProductState()
    )
    val uiState get() = _uiState.asStateFlow()

    private val _uiFilterBar: MutableStateFlow<FilterBarState> = MutableStateFlow(
        FilterBarState()
    )
    val uiFilterBar get() = _uiFilterBar.asStateFlow()

    lateinit var list: List<ProductListItem>

    init {
        loadProductInfo()
        loadScreenDetail()
    }

    fun listToMap(list: List<ProductListItem>): MutableMap<String, Boolean> {
        val map: MutableMap<String, Boolean> = mutableMapOf()
        list.forEach { product ->
            map[product.id] = false
        }
        return map
    }

    private fun loadProductInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )
            when (val result = repositoryProduct.getProductListItem()) {
                is Resource.Success -> {
                    result.data?.let {
                        list = it
                        val resultDataBase = repositoryDataBase.getData()
                        if (resultDataBase != null && resultDataBase?.size != 0){
                            _uiState.value = _uiState.value.copy(
                                favoriteState = repositoryDataBase.getData() ?: listToMap(list)
                            )
                        } else {
                            _uiState.value = _uiState.value.copy(
                                favoriteState = listToMap(list)
                            )
                        }
                    }
                    _uiState.value = _uiState.value.copy(
                        productList = result.data,
                        isLoading = false,
                        error = null
                    )
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

    private fun loadScreenDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repositoryProduct.getProductInfo()) {
                is Resource.Success -> {
                    _uiState.value = _uiState.value.copy(
                        productInfoList = result.data
                    )
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
        val sortedList = list.sortedByDescending { it.price?.toFloat() }
        _uiState.value = _uiState.value.copy(
            productList = sortedList
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
        _uiFilterBar.value = _uiFilterBar.value.copy(
            selectedOption = option,
        )
    }

    fun getProductInfo(id: String?): ProductInfo? {
        return _uiState.value.productInfoList?.find {
            it.id == id
        }
    }
    fun updateItemToFavoriteById(id: String, newValueFavorite: Boolean) {
        _uiState.value.favoriteState[id]?.let {
            _uiState.value.favoriteState[id] = newValueFavorite
            val favorite = _uiState.value.favoriteState
            _uiState.value.copy(
                favoriteState = favorite
            )
            viewModelScope.launch(Dispatchers.IO) {
                repositoryDataBase.update(_uiState.value.favoriteState)
            }
        }
    }

}