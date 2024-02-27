package com.chscorp.cosmeticsstore.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.chscorp.cosmeticsstore.presentation.ui.screens.HomeListProductScreenStateful
import com.chscorp.cosmeticsstore.presentation.ui.theme.CosmeticsStoreTheme
import com.chscorp.cosmeticsstore.presentation.ui.viewModel.MainViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CosmeticsStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = koinViewModel<MainViewModel>()
                    HomeListProductScreenStateful(viewModel = viewModel)
                }
            }
        }
    }
}
