package com.chscorp.cosmeticsstore.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.chscorp.cosmeticsstore.presentation.screens.HomeListProductScreenStateful
import com.chscorp.cosmeticsstore.presentation.screens.ProductDetailScreen
import com.chscorp.cosmeticsstore.presentation.theme.CosmeticsStoreTheme
import com.chscorp.cosmeticsstore.presentation.theme.Pink80
import com.chscorp.cosmeticsstore.presentation.viewModel.MainViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            CosmeticsStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Pink80
                ) {
                    val viewModel = koinViewModel<MainViewModel>()
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "HomeListProductScreen"
                    ) {
                        composable(route = "HomeListProductScreen") {
                            HomeListProductScreenStateful(
                                viewModel = viewModel,
                                navController = navController
                            )
                        }
                        composable(route = "ProductDetail/{id}",
                            arguments = listOf(
                                navArgument(name = "id") {type = NavType.StringType}
                            )
                        ) { id ->
                            ProductDetailScreen(
                                viewModel = viewModel,
                                id = id.arguments?.getString("id")
                                )
                        }
                    }
                }
            }
        }
    }
}
